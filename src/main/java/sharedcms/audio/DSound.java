package sharedcms.audio;

import sharedcms.util.SuperLocation;

public class DSound
{
	private String name;
	private float volume;
	private float pitch;
	private float pitchShift;

	public DSound(String name, float volume, float pitch, float pitchShift)
	{
		this.name = name;
		this.volume = volume;
		this.pitch = pitch;
		this.pitchShift = pitchShift;
	}

	public DSound(String name, float volume, float pitch)
	{
		this(name, volume, pitch, 0.2f);
	}

	public DSound(String name, float volume)
	{
		this(name, volume, 1f);
	}

	public DSound(String name)
	{
		this(name, 1f);
	}

	public String getName()
	{
		return name;
	}

	public float getVolume()
	{
		return volume;
	}

	public float getPitch()
	{
		return pitch;
	}

	public float getPitchShift()
	{
		return pitchShift;
	}
}
