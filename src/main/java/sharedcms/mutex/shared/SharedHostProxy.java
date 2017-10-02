package sharedcms.mutex.shared;

import java.util.List;
import java.util.UUID;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.WorldEvent;
import sharedcms.command.CommandCMS;
import sharedcms.content.world.meta.objects.MetaWorld;
import sharedcms.mutex.shared.object.IPlayer;
import sharedcms.mutex.shared.object.Player;
import sharedcms.proxy.IProxy;
import sharedcms.util.GList;
import sharedcms.util.GMap;

public class SharedHostProxy implements IProxy
{
	public static int tick = 0;
	public static GList<IPlayer> players;
	public static GMap<World, MetaWorld> meta;

	@Override
	public void onPreInit(FMLPreInitializationEvent e)
	{
		players = new GList<IPlayer>();
		meta = new GMap<World, MetaWorld>();
	}

	@Override
	public void onInit(FMLInitializationEvent e)
	{

	}

	@Override
	public void onPostInit(FMLPostInitializationEvent e)
	{
		System.out.println("SHARED HOST ONLINE");
	}

	@SubscribeEvent
	public void on(WorldEvent.Load e)
	{
		meta.put(e.world, new MetaWorld(e.world));
	}

	@SubscribeEvent
	public void on(WorldEvent.Save e)
	{
		meta.get(e.world).save();
	}

	@SubscribeEvent
	public void on(PlayerLoggedInEvent e)
	{
		players.add(new Player(e.player));
		System.out.println(e.player.getDisplayName() + " JOINED");
	}

	@SubscribeEvent
	public void on(PlayerLoggedOutEvent e)
	{
		players.remove(getPlayer(e.player));
		System.out.println(e.player.getDisplayName() + " LEFT");
	}

	@SubscribeEvent
	public void on(LivingHurtEvent e)
	{
		if(e.entityLiving instanceof EntityPlayer)
		{
			IPlayer p = getPlayer(e.entityLiving.getUniqueID());
			p.onRawDamageTaken(e.source, e.ammount);
			e.setCanceled(true);
			e.ammount = 0;
		}
	}

	@SubscribeEvent
	public void on(LivingAttackEvent e)
	{
		if(e.entityLiving instanceof EntityPlayer)
		{
			on(new LivingHurtEvent(e.entityLiving, e.source, e.ammount));
			e.setCanceled(true);
			return;
		}

		e.entityLiving.setHealth(e.entityLiving.getHealth() - e.ammount);
		e.entityLiving.worldObj.playSoundEffect(e.entityLiving.posX, e.entityLiving.posY, e.entityLiving.posZ, "game.neutral.hurt", 0.6f, (float) (1f + ((Math.random() - 1.5) * 2) * 0.3));
		e.setCanceled(true);
	}

	@SubscribeEvent
	public void on(ServerTickEvent e)
	{
		tick++;

		for(IPlayer i : getPlayers())
		{
			i.tick(tick);
		}
	}

	public MetaWorld getWorldMeta(World world)
	{
		return meta.get(world);
	}

	public List<IPlayer> getPlayers()
	{
		return players.copy();
	}

	public IPlayer getPlayer(EntityPlayer p)
	{
		for(IPlayer i : getPlayers())
		{
			if(i.getUUID().equals(p.getUniqueID()))
			{
				return i;
			}
		}

		return null;
	}

	public IPlayer getPlayer(UUID uuid)
	{
		for(IPlayer i : getPlayers())
		{
			if(i.getUUID().equals(uuid))
			{
				return i;
			}
		}

		return null;
	}
}
