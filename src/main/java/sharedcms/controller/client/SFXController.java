package sharedcms.controller.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import sharedcms.audio.BlockSound;
import sharedcms.audio.DSound;
import sharedcms.audio.SFX;
import sharedcms.content.Content;
import sharedcms.controllable.Controller;
import sharedcms.util.Location;
import sharedcms.util.M;

public class SFXController extends Controller
{
	private boolean moving = false;
	private boolean sprinting = false;
	private boolean windy = false;
	private long lastCall = M.ms();

	public SFXController()
	{

	}

	@Override
	public void onPreInitialization()
	{

	}

	@Override
	public void onInitialization()
	{

	}

	@Override
	public void onPostInitialization()
	{
		replaceBlockSounds();
	}
	
	@SubscribeEvent
	public void on(SoundEvent.SoundSourceEvent e)
	{
		if(e.name.contains("fs."))
		{
			SFX.playArmorRustleSounds(Minecraft.getMinecraft().thePlayer);
		}
	}

	public void replaceBlockSounds()
	{
		for(Object i : GameData.getBlockRegistry())
		{
			Block b = (Block) i;
			boolean t = false;

			if(b.stepSound.getBreakSound().equals("dig.stone"))
			{
				b.stepSound = Content.BlockSoundType.STONE;
				t = true;
			}

			if(b.stepSound.getBreakSound().equals("dig.wood"))
			{
				b.stepSound = Content.BlockSoundType.WOOD;
				t = true;
			}

			if(b.stepSound.getBreakSound().equals("dig.grass"))
			{
				b.stepSound = Content.BlockSoundType.GRASS;
				t = true;
			}

			if(b.stepSound.getBreakSound().equals("dig.gravel"))
			{
				b.stepSound = Content.BlockSoundType.GRAVEL;
				t = true;
			}

			if(b.stepSound.getBreakSound().equals("dig.metal"))
			{
				b.stepSound = Content.BlockSoundType.METAL;
				t = true;
			}

			if(b.stepSound.getBreakSound().equals("dig.cloth"))
			{
				b.stepSound = Content.BlockSoundType.CLOTH;
				t = true;
			}

			if(b.stepSound.getBreakSound().equals("dig.sand"))
			{
				b.stepSound = Content.BlockSoundType.SAND;
				t = true;
			}

			if(b.stepSound.getBreakSound().equals("dig.snow"))
			{
				b.stepSound = Content.BlockSoundType.SNOW;
				t = true;
			}

			if(b.stepSound.getBreakSound().equals("dig.ladder"))
			{
				b.stepSound = Content.BlockSoundType.WOOD;
				t = true;
			}

			if(b.stepSound.getBreakSound().equals("dig.anvil"))
			{
				b.stepSound = Content.BlockSoundType.METAL;
				t = true;
			}

			if(b.getMaterial().equals(Material.cactus))
			{
				b.stepSound = Content.BlockSoundType.CLOTH;
			}

			if(b.getMaterial().equals(Material.cake))
			{
				b.stepSound = Content.BlockSoundType.CLOTH;
			}

			if(b.getMaterial().equals(Material.carpet))
			{
				b.stepSound = Content.BlockSoundType.CLOTH;
			}

			if(b.getMaterial().equals(Material.circuits))
			{
				b.stepSound = Content.BlockSoundType.SAND;
			}

			if(b.getMaterial().equals(Material.clay))
			{
				b.stepSound = Content.BlockSoundType.MUD;
			}

			if(b.getMaterial().equals(Material.cloth))
			{
				b.stepSound = Content.BlockSoundType.CLOTH;
			}

			if(b.getMaterial().equals(Material.coral))
			{
				b.stepSound = Content.BlockSoundType.STONE;
			}

			if(b.getMaterial().equals(Material.craftedSnow))
			{
				b.stepSound = Content.BlockSoundType.SNOW;
			}

			if(b.getMaterial().equals(Material.dragonEgg))
			{
				b.stepSound = Content.BlockSoundType.STONE;
			}

			if(b.getMaterial().equals(Material.fire))
			{
				b.stepSound = Content.BlockSoundType.STONE;
			}

			if(b.getMaterial().equals(Material.glass))
			{
				b.stepSound = Content.BlockSoundType.GLASS;
			}

			if(b.getMaterial().equals(Material.gourd))
			{
				b.stepSound = Content.BlockSoundType.GRASS;
			}

			if(b.getMaterial().equals(Material.grass))
			{
				b.stepSound = Content.BlockSoundType.GRASS;
			}

			if(b.getMaterial().equals(Material.ground))
			{
				b.stepSound = Content.BlockSoundType.DIRT;
			}

			if(b.getMaterial().equals(Material.ice))
			{
				b.stepSound = Content.BlockSoundType.ICE;
			}

			if(b.getMaterial().equals(Material.iron))
			{
				b.stepSound = Content.BlockSoundType.METAL;
			}

			if(b.getMaterial().equals(Material.lava))
			{
				b.stepSound = Content.BlockSoundType.STONE;
			}

			if(b.getMaterial().equals(Material.leaves))
			{
				b.stepSound = Content.BlockSoundType.LEAVES;
			}

			if(b.getMaterial().equals(Material.packedIce))
			{
				b.stepSound = Content.BlockSoundType.ICE;
			}

			if(b.getMaterial().equals(Material.piston))
			{
				b.stepSound = Content.BlockSoundType.STONE;
			}

			if(b.getMaterial().equals(Material.plants))
			{
				b.stepSound = Content.BlockSoundType.LEAVES;
			}

			if(b.getMaterial().equals(Material.portal))
			{
				b.stepSound = Content.BlockSoundType.STONE;
			}

			if(b.getMaterial().equals(Material.redstoneLight))
			{
				b.stepSound = Content.BlockSoundType.GLASS;
			}

			if(b.getMaterial().equals(Material.rock))
			{
				b.stepSound = Content.BlockSoundType.STONE;
			}

			if(b.getMaterial().equals(Material.sand))
			{
				b.stepSound = Content.BlockSoundType.SAND;
			}

			if(b.getMaterial().equals(Material.snow))
			{
				b.stepSound = Content.BlockSoundType.SNOW;
			}

			if(b.getMaterial().equals(Material.sponge))
			{
				b.stepSound = Content.BlockSoundType.GRASS;
			}

			if(b.getMaterial().equals(Material.tnt))
			{
				b.stepSound = Content.BlockSoundType.SAND;
			}

			if(b.getMaterial().equals(Material.vine))
			{
				b.stepSound = Content.BlockSoundType.LEAVES;
			}

			if(b.getMaterial().equals(Material.water))
			{
				b.stepSound = Content.BlockSoundType.STONE;
			}

			if(b.getMaterial().equals(Material.web))
			{
				b.stepSound = Content.BlockSoundType.CLOTH;
			}

			if(b.getMaterial().equals(Material.wood))
			{
				b.stepSound = Content.BlockSoundType.WOOD;
			}

			if(t)
			{
				System.out.println(" ===> Tweaked " + b.getUnlocalizedName() + " sound to " + b.stepSound.getBreakSound().toString());
			}
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
			play("sharedcms:" + Content.SoundMaterial.AMBIENT_JUMP, 0.2f, rf(0.1f, 1f));
			landedSound(Minecraft.getMinecraft().thePlayer);
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

			landedSound(Minecraft.getMinecraft().thePlayer);
		}
	}

	private void landedSound(EntityPlayer p)
	{
		Block b = p.worldObj.getBlock((int) p.posX, (int) (p.posY - 2), (int) p.posZ);

		if(b.stepSound instanceof BlockSound)
		{
			BlockSound s = (BlockSound) b.stepSound;
			play(s.getLandSound(), 0.2f, (float) (1f + (Math.random() * 0.25)));
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

		BlockSound.walking = !el.isSprinting();

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
		}

		if(el.distanceWalkedModified - el.prevDistanceWalkedModified == 0 && moving)
		{
			moving = false;
		}

		if(el.posY > 120)
		{
			windy = true;
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

		String k = s;

		SFX.play(new DSound(k, v, p, 0.1f), new Location(el.posX + x, el.posY + y, el.posZ + z));
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
}
