package sharedcms.util;

import java.util.Map;

import sharedcms.content.world.generator.SimplexNoiseGenerator;
import sharedcns.api.biome.BiomeHumidity;
import sharedcns.api.biome.BiomeTemperature;

public class GEN
{
	private static Map<SimplexProperties, SimplexNoiseGenerator> generators = null;

	public static void addGenerator(SimplexProperties prop)
	{
		if(generators == null)
		{
			System.out.println("Creating Streambuffer matrix");
			generators = new GMap<SimplexProperties, SimplexNoiseGenerator>();
		}
		
		if(get(prop.getId()) != null)
		{
			System.out.println("Swap Stream Buffers: " + prop.getId());
			generators.remove(getProp(prop.getId()));
			System.out.println("Removed Strem Buffer: " + prop.toString());
		}

		System.out.println("Added Stream Buffer: " + prop.toString());
		SimplexNoiseGenerator gen = new SimplexNoiseGenerator(prop.getSeed());
		generators.put(prop, gen);
	}

	public static SimplexNoiseGenerator get(String id)
	{
		for(SimplexProperties i : generators.keySet())
		{
			if(i.getId().equals(id))
			{
				return generators.get(i);
			}
		}

		return null;
	}

	public static SimplexProperties getProp(String id)
	{
		for(SimplexProperties i : generators.keySet())
		{
			if(i.getId().equals(id))
			{
				return i;
			}
		}

		return null;
	}

	public BiomeTemperature getTemperature(String id, double x, double z)
	{
		return (BiomeTemperature) BiomeTemperature.of((int) (getNoise(id, x, z, 1, 1) / BiomeTemperature.length()));
	}

	public BiomeHumidity getHumidity(String id, double x, double z)
	{
		return (BiomeHumidity) BiomeHumidity.of((int) (getNoise(id, x, z, 0.5, 1) / BiomeHumidity.length()));
	}

	public static double getNoise(String id, double x, double z, double amp, double min)
	{
		return getNoise(id, x, 1337, z, amp, min);
	}

	public static double getNoise(String id, double x, double y, double z, double amp, double min)
	{
		SimplexProperties prop = getProp(id);
		double noise = get(id).getNoise(x / prop.getScale(), y / prop.getScale(), z / prop.getScale()) + min;

		return noise * amp;
	}

	public static double getNoise(String id, double x, double y, double z)
	{
		return getNoise(id, x, y, z, 0.5, 1);
	}

	public static double getNoise(String id, double x, double z)
	{
		return getNoise(id, x, z, 0.5, 1);
	}
}
