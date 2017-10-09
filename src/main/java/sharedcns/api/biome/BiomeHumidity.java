package sharedcns.api.biome;

public enum BiomeHumidity
{
	ARID,
	DRY,
	DAMP,
	MOIST,
	WET,
	DRENCHED;

	public static int length()
	{
		return values().length - 1;
	}
	
	public static Enum<?> of(int ordinal)
	{
		return values()[ordinal];
	}
	
	public float getHumidity()
	{
		return ordinal() / 6f;
	}

	public boolean hasModification(BiomeHumidity to)
	{
		return getModification(to) != null;
	}

	public float getModificationFactor(BiomeHumidity to)
	{
		if(!hasModification(to))
		{
			return 0f;
		}

		float f = getHumidity() + 1000f;
		float t = to.getHumidity() + 1000f;

		return t - f;
	}

	public BiomeHumidityModifier getModification(BiomeHumidity to)
	{
		int f = ordinal() + 10000;
		int t = to.ordinal() + 10000;

		if(f > t)
		{
			if(f - t > 2)
			{
				return BiomeHumidityModifier.EVAPORATE;
			}

			else
			{
				return BiomeHumidityModifier.DRY;
			}
		}

		else if(f < t)
		{
			if(t - f > 2)
			{
				return BiomeHumidityModifier.FLOOD;
			}

			else
			{
				return BiomeHumidityModifier.MOISTEN;
			}
		}

		return null;
	}
}
