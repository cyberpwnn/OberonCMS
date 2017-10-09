package sharedcns.api.biome;

import java.util.Random;

import net.minecraft.world.World;
import sharedcms.content.world.generator.SimplexNoiseGenerator;
import sharedcms.util.GMap;
import sharedcms.util.M;

public class ConditionalSimplexNoiseGenerator
{
	private GMap<Class<? extends Enum>, SimplexNoiseGenerator> generators;
	private GMap<Class<? extends Enum>, Double> scales;
	private World world;
	private long seed;

	public ConditionalSimplexNoiseGenerator(World world)
	{
		this.world = world;
		seed = world.getSeed();
		generators = new GMap<Class<? extends Enum>, SimplexNoiseGenerator>();
		scales = new GMap<Class<? extends Enum>, Double>();
	}

	public void addGenerator(Class<? extends Enum> e, double scale)
	{
		System.out.println("Constructing CSX " + e.getSimpleName() + " at " + (int) scale + "x magnification.");
		generators.put(e, new SimplexNoiseGenerator(getNextSeed()));
		scales.put(e, scale);
	}

	private double getNoise(Class<? extends Enum> e, double x, double z)
	{
		double noise = generators.get(e).getNoise(x / scales.get(e), 1337, z / scales.get(e));
		
		return noise;
	}

	public double getNormal(BiomeTemperature f, double x, double z)
	{
		return ((getNoise(f.getDeclaringClass(), x, z) / 2.0) + 0.5 + getNormalFor(f)) / 2.0;
	}
	
	public double getNormal(BiomeHumidity f, double x, double z)
	{
		return ((getNoise(f.getDeclaringClass(), x, z) / 2.0) + 0.5 + getNormalFor(f)) / 2.0;
	}

	public double getNormalFor(BiomeTemperature f)
	{
		double total = f.length();
		double of = f.ordinal();

		return of / total;
	}

	public double getNormalFor(BiomeHumidity f)
	{
		double total = f.length();
		double of = f.ordinal();

		return of / total;
	}
	
	public BiomeTemperature get(BiomeTemperature f, double x, double z)
	{
		double total = f.length();
		double normal = getNormal(f, x, z);
		int ordinal = (int) (total * normal);

		return (BiomeTemperature) f.of(ordinal);
	}
	
	public BiomeHumidity get(BiomeHumidity f, double x, double z)
	{
		double total = f.length();
		double normal = getNormal(f, x, z);
		int ordinal = (int) (total * normal);

		return (BiomeHumidity) f.of(ordinal);
	}

	public long getNextSeed()
	{
		Random r = new Random(seed);
		long seed = -this.seed;

		for(int i = 0; i < generators.size(); i++)
		{
			seed += r.nextLong();
		}

		return seed;
	}
}
