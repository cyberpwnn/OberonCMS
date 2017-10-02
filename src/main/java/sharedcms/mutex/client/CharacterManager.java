package sharedcms.mutex.client;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.sound.PlaySoundSourceEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.world.BlockEvent;
import sharedcms.audio.DSound;
import sharedcms.audio.SFX;
import sharedcms.base.BSM;
import sharedcms.content.Content;
import sharedcms.proxy.IProxy;
import sharedcms.util.C;
import sharedcms.util.M;
import sharedcms.util.PlayerUtils;
import sharedcms.util.SuperLocation;

public class CharacterManager implements IProxy
{
	private boolean moving = false;
	private boolean sprinting = false;
	private boolean windy = false;
	private long lastCall = M.ms();

	@Override
	public void onPreInit(FMLPreInitializationEvent e)
	{
		for(Object i : GameData.getBlockRegistry())
		{
			Block b = (Block) i;
			b.setBlockUnbreakable();
			System.out.println(" ===>>> Setting " + b.getLocalizedName() + " unbreakable.");
		}
	}

	@Override
	public void onInit(FMLInitializationEvent e)
	{

	}

	@Override
	public void onPostInit(FMLPostInitializationEvent e)
	{

	}

	@SubscribeEvent
	public void on(DrawBlockHighlightEvent e)
	{
		if(Minecraft.getMinecraft().thePlayer == null)
		{
			return;
		}

		if(PlayerUtils.getGameMode().equals(GameType.SURVIVAL))
		{
			e.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void on(BlockEvent.PlaceEvent e)
	{
		if(Minecraft.getMinecraft().thePlayer == null)
		{
			return;
		}

		if(PlayerUtils.getGameMode().equals(GameType.SURVIVAL))
		{
			e.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void on(BlockEvent.BreakEvent e)
	{
		if(Minecraft.getMinecraft().thePlayer == null)
		{
			return;
		}

		if(PlayerUtils.getGameMode().equals(GameType.SURVIVAL))
		{
			e.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void on(ClientTickEvent e)
	{
		C.tick++;

		if(Minecraft.getMinecraft().thePlayer == null)
		{
			return;
		}

		if(PlayerUtils.getGameMode().equals(GameType.SURVIVAL))
		{
			PlayerUtils.denyBreaking();
		}
	}

	@SubscribeEvent
	public void on(LivingJumpEvent e)
	{
		if(Minecraft.getMinecraft().thePlayer != null && e.entityLiving.equals(Minecraft.getMinecraft().thePlayer))
		{
			if(M.ms() - lastCall < 100)
			{
				return;
			}

			lastCall = M.ms();

			BSM b = getSoundSet(getSoundType((EntityPlayer) e.entityLiving));

			if(b != null)
			{
				play(b.getJump(), 0.2f, rf(0.1f, 1f));
				play(b.getWalk(), 0.2f, rf(0.1f, 1f));
			}
		}
	}

	@SubscribeEvent
	public void on(LivingFallEvent e)
	{
		if(Minecraft.getMinecraft().thePlayer != null && e.entityLiving.equals(Minecraft.getMinecraft().thePlayer))
		{
			if(M.ms() - lastCall < 100)
			{
				return;
			}

			lastCall = M.ms();

			BSM b = getSoundSet(getSoundType((EntityPlayer) e.entityLiving));

			if(b != null)
			{
				play(b.getLand(), 0.2f, rf(0.1f, 1f));
			}
		}
	}

	@SubscribeEvent
	public void on(PlaySoundSourceEvent e)
	{
		EntityPlayer el = Minecraft.getMinecraft().thePlayer;

		if(el == null)
		{
			return;
		}

		BSM b = getSoundSet(el.getEntityWorld().getBlock((int) e.x, (int) e.y - 2, (int) e.z).stepSound);

		if(b == null)
		{
			return;
		}

		if(e.sound.getPositionedSoundLocation().getResourcePath().startsWith("step."))
		{
			play(sprinting ? b.getRun() : b.getWander(), 0.3f, rf(0.1f, 1f));
		}
	}

	@SubscribeEvent
	public void on(WorldTickEvent e)
	{
		EntityPlayer el = Minecraft.getMinecraft().thePlayer;

		if(el == null)
		{
			return;
		}

		if(moving && el.isSprinting())
		{
			sprinting = true;
		}

		else
		{
			sprinting = false;
		}

		if(el.distanceWalkedModified - el.prevDistanceWalkedModified != 0 && !moving)
		{
			moving = true;

			BSM b = getSoundSet(getSoundType(el));

			if(b != null)
			{
				play(b.getWander(), 0.2f, rf(0.1f, 1f));
			}
		}

		if(el.distanceWalkedModified - el.prevDistanceWalkedModified == 0 && moving)
		{
			moving = false;

			BSM b = getSoundSet(getSoundType(el));

			if(b != null)
			{
				play(b.getWander(), 0.2f, rf(0.1f, 1f));
			}
		}

		if(el.posY > 120)
		{
			windy = true;

			if(Math.random() * 500 < 1)
			{
				play(Content.SoundMaterial.AMBIENT_WIND, rr(12), rr(12) + 40, rr(12), 10f, rf(0.1f, 1f));
			}
		}

		else
		{
			windy = false;
		}
	}

	public float rf(float maxDiv, float origin)
	{
		return (float) (origin + rr(maxDiv));
	}

	public double rr(double maxSide)
	{
		return ((Math.random() - 0.5) * 2) * maxSide;
	}

	public void play(String s, double x, double y, double z, float v, float p)
	{
		EntityPlayer el = Minecraft.getMinecraft().thePlayer;

		if(el == null)
		{
			return;
		}

		String k = "sharedcms:" + s;

		SFX.play(new DSound(k, v, p, 0.1f), new SuperLocation(el.posX + x, el.posY + y, el.posZ + z));
	}

	public void play(String s, float v, float p)
	{
		play(s, 0, 0, 0, v, p);
	}

	public Block getBlockUnder(EntityPlayer p)
	{
		return p.worldObj.getBlock((int) p.posX, (int) p.posY - 2, (int) p.posZ);
	}

	public SoundType getSoundType(EntityPlayer p)
	{
		Block b = getBlockUnder(p);

		return b.stepSound;
	}

	public BSM getSoundSet(SoundType s)
	{
		if(s != null)
		{
			if(s.equals(Block.soundTypeAnvil))
			{
				return Content.BlockSound.METALBAR;
			}

			else if(s.equals(Block.soundTypeCloth))
			{
				return Content.BlockSound.WOOL;
			}

			else if(s.equals(Block.soundTypeGlass))
			{
				return Content.BlockSound.GLASS;
			}

			else if(s.equals(Block.soundTypeGrass))
			{
				return Content.BlockSound.GRASS;
			}

			else if(s.equals(Block.soundTypeGravel))
			{
				return Content.BlockSound.GRAVEL;
			}

			else if(s.equals(Block.soundTypeLadder))
			{
				return Content.BlockSound.PLANKS;
			}

			else if(s.equals(Block.soundTypeMetal))
			{
				return Content.BlockSound.METALBLOCK;
			}

			else if(s.equals(Block.soundTypePiston))
			{
				return Content.BlockSound.METALBLOCK;
			}

			else if(s.equals(Block.soundTypeSand))
			{
				return Content.BlockSound.SAND;
			}

			else if(s.equals(Block.soundTypeSnow))
			{
				return Content.BlockSound.SNOW;
			}

			else if(s.equals(Block.soundTypeStone))
			{
				return Content.BlockSound.ROCK;
			}

			else if(s.equals(Block.soundTypeWood))
			{
				return Content.BlockSound.WOOD;
			}

			else
			{

			}
		}

		else
		{

		}

		return null;
	}
}
