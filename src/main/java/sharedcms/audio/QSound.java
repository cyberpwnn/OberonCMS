package sharedcms.audio;

import net.minecraft.client.Minecraft;
import sharedcms.util.SuperLocation;

public class QSound
{
	public DSound sound;
	public SuperLocation location;

	public QSound(DSound sound, SuperLocation location)
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
		SuperLocation s = new SuperLocation();

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
