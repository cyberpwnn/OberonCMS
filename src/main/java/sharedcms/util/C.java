package sharedcms.util;

import java.awt.Color;

import sharedcms.gui.util.R;

public class C
{
	public static long tick = 0;

	public static Color getRGB(double hue)
	{
		return Color.getHSBColor(R.CLIP((float) hue, 0f, 1f), 1f, 1f);
	}

	public static Color getRGBShift(double speed)
	{
		return getRGB((Math.sin((double) tick / (1.0 / speed)) + 1) / 2);
	}
	
	public static Color getRGBShift(double speed, int tickOffset)
	{
		return getRGB((Math.sin((double) (tick + tickOffset) / (1.0 / speed)) + 1) / 2);
	}
}
