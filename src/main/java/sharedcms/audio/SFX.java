package sharedcms.audio;

import sharedcms.util.Location;

public class SFX
{
	public static void play(DSound sound)
	{
		AudioManager.playSound(sound);
	}
	
	public static void play(DSound sound, Location s)
	{
		AudioManager.playSound(sound, s);
	}
}
