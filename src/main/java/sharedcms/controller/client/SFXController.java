package sharedcms.controller.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.sound.PlaySoundSourceEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
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

	public void replaceBlockSounds()
	{
		for(Object i : GameData.getBlockRegistry())
		{
			Block b = (Block) i;
			
			if(b.stepSound.getBreakSound().equals("dig.stone"))
			{
				System.out.println(" ===> Tweaking " + b.getUnlocalizedName() + " step sound.");
				b.stepSound = Content.BlockSoundType.STONE;
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
			play(Content.SoundMaterial.AMBIENT_JUMP, 0.2f, rf(0.1f, 1f));
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

			BlockSound.justLanded = true;
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

		String k = "sharedcms:" + s;

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
