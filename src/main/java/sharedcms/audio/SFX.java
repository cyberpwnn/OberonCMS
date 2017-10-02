package sharedcms.audio;

import sharedcms.util.SuperLocation;

public class SFX
{
	public static void play(DSound sound)
	{
		AudioManager.playSound(sound);
	}
	
	public static void play(DSound sound, SuperLocation s)
	{
		AudioManager.playSound(sound, s);
	}
}
