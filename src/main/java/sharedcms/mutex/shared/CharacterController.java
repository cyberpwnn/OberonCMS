package sharedcms.mutex.shared;

import java.util.List;
import java.util.UUID;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import sharedcms.mutex.shared.object.ACharacter;
import sharedcms.mutex.shared.object.AEntity;
import sharedcms.mutex.shared.object.ICharacter;
import sharedcms.mutex.shared.object.IEntity;
import sharedcms.util.GList;

public class CharacterController
{
	private int tick;
	private GList<ICharacter> players;
	private GList<IEntity> entities;
	private CombatEnvironment combat;

	public CharacterController()
	{
		tick = 0;
		combat = new CombatEnvironment();
		FMLCommonHandler.instance().bus().register(combat);
		MinecraftForge.EVENT_BUS.register(combat);
		players = new GList<ICharacter>();
		entities = new GList<IEntity>();
	}

	@SubscribeEvent
	public void on(ServerTickEvent e)
	{
		tick++;

		for(ICharacter i : getPlayers())
		{
			i.tick(tick);
		}
	}
	
	@SubscribeEvent
	public void on(LivingSpawnEvent e)
	{
		if(!(e.entityLiving instanceof EntityPlayer))
		{
			for(IEntity i : entities)
			{
				if(i.getUUID().equals(e.entityLiving.getUniqueID()))
				{
					return;
				}
			}
			
			AEntity a = new AEntity(e.entityLiving);
			a.setLevel(SharedHostProxy.getLevel(a.getLocation()));
			entities.add(a);
		}
	}
	
	@SubscribeEvent
	public void on(LivingDeathEvent e)
	{
		for(IEntity i : entities.copy())
		{
			if(i.getUUID().equals(e.entityLiving.getUniqueID()))
			{
				return;
			}
			
			entities.remove(i);
		}
	}

	@SubscribeEvent
	public void on(PlayerLoggedInEvent e)
	{
		players.add(new ACharacter(e.player));
		System.out.println(e.player.getDisplayName() + " JOINED");
	}

	@SubscribeEvent
	public void on(PlayerLoggedOutEvent e)
	{
		players.remove(getPlayer(e.player));
		System.out.println(e.player.getDisplayName() + " LEFT");
	}

	public List<ICharacter> getPlayers()
	{
		return players.copy();
	}
	
	public List<IEntity> getEntities()
	{
		return entities.copy();
	}
	
	public IEntity getEntity(UUID uuid)
	{
		for(IEntity i : getEntities())
		{
			if(i.getUUID().equals(uuid))
			{
				return i;
			}
		}
		
		return null;
	}

	public ICharacter getPlayer(EntityPlayer p)
	{
		for(ICharacter i : getPlayers())
		{
			if(i.getUUID().equals(p.getUniqueID()))
			{
				return i;
			}
		}

		return null;
	}

	public ICharacter getPlayer(UUID uuid)
	{
		for(ICharacter i : getPlayers())
		{
			if(i.getUUID().equals(uuid))
			{
				return i;
			}
		}

		return null;
	}
}
