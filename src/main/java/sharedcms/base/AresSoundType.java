package sharedcms.base;

import net.minecraft.block.Block;
import sharedcms.Info;

public class AresSoundType extends Block.SoundType
{
	public final String soundNameStep;
	public final String soundNameBreak;
	public final String soundNamePlace;

	public AresSoundType(String soundNameBreak, String soundNameStep, String soundNamePlace, float volume, float frequency)
	{
		super(soundNameStep, volume, frequency);
		this.soundNameStep = soundNameStep;
		this.soundNameBreak = soundNameBreak;
		this.soundNamePlace = soundNamePlace;
	}

	@Override
	public String getBreakSound()
	{
		return this.soundNameBreak;
	}

	@Override
	public String getStepResourcePath()
	{
		return this.soundNameStep;
	}

	@Override
	public String func_150496_b()
	{
		return this.soundNamePlace;
	}
}
