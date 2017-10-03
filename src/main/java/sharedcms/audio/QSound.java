package sharedcms.audio;

import net.minecraft.client.Minecraft;
import sharedcms.util.Location;

public class QSound
{
	public DSound sound;
	public Location location;

	public QSound(DSound sound, Location location)
	{
		super();
		this.sound = sound;
		this.location = location;
	}

	public QSound(DSound sound)
	{
		this(sound, null);
	}

	public void play()
	{
		Location s = new Location();

		if(location != null)
		{
			s = location;
		}

		else
		{
			s.x = Minecraft.getMinecraft().thePlayer.posX;
			s.y = Minecraft.getMinecraft().thePlayer.posY;
			s.z = Minecraft.getMinecraft().thePlayer.posZ;
		}

		Minecraft.getMinecraft().thePlayer.worldObj.playSound(s.x, s.y, s.z, sound.getName(), sound.getVolume(), sound.getPitch() + (float) (((Math.random() - 0.5) * 2) * sound.getPitchShift()), false);
	}
}
