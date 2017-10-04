package sharedcms.audio;

import sharedcms.controller.client.AudioController;
import sharedcms.util.Location;

public class SFX
{
	public static void play(DSound sound)
	{
		AudioController.playSound(sound);
	}
	
	public static void play(DSound sound, Location s)
	{
		AudioController.playSound(sound, s);
	}
}
