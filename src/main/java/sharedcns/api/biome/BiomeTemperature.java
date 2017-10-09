package sharedcns.api.biome;

public enum BiomeTemperature
{
	FREEZING,
	COLD,
	CHILLY,
	WARM,
	HOT,
	SCORCHED;

	public static int length()
	{
		return values().length - 1;
	}
	
	public static Enum<?> of(int ordinal)
	{
		return values()[ordinal];
	}
	
	public float getTemperature()
	{
		return ordinal() / 3f;
	}
	
	public boolean hasModification(BiomeTemperature to)
	{
		return getModification(to) != null;
	}

	public float getModificationFactor(BiomeTemperature to)
	{
		if(!hasModification(to))
		{
			return 0f;
		}

		float f = getTemperature() + 1000f;
		float t = to.getTemperature() + 1000f;

		return t - f;
	}
	
	public BiomeTemperatureModifier getModification(BiomeTemperature to)
	{
		int f = ordinal() + 10000;
		int t = to.ordinal() + 10000;
		
		if(f > t)
		{
			if(f - t > 2)
			{
				return BiomeTemperatureModifier.FREEZE;
			}
			
			else
			{
				return BiomeTemperatureModifier.COOL;
			}
		}
		
		else if(f < t)
		{
			if(t - f > 2)
			{
				return BiomeTemperatureModifier.BURN;
			}
			
			else
			{
				return BiomeTemperatureModifier.HEAT;
			}
		}
		
		return null;
	}
}
