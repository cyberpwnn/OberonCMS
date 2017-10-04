package sharedcms.audio;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block.SoundType;

@SideOnly(Side.CLIENT)
public class BlockSound extends SoundType
{
	public static boolean walking = true;
	public static boolean justLanded = false;
	private String walkSound;
	private String runSound;
	private String landSound;

	public BlockSound(String walkSound, String runSound, String landSound, float volume, float pitch)
	{
		super(walkSound, volume, pitch);
		
		this.walkSound = walkSound;
		this.runSound = runSound;
		this.landSound = landSound;
	}

	public String getBreakSound()
	{
		return walkSound;
	}

	public String getStepResourcePath()
	{
		if(justLanded)
		{
			justLanded = false;
			return landSound;
		}
		
		return walking ? walkSound : runSound;
	}

	public String func_150496_b()
	{
		return this.getBreakSound();
	}

	public static boolean isWalking()
	{
		return walking;
	}

	public static boolean isJustLanded()
	{
		return justLanded;
	}

	public String getWalkSound()
	{
		return walkSound;
	}

	public String getRunSound()
	{
		return runSound;
	}

	public String getLandSound()
	{
		return landSound;
	}
}
