package sharedcns.api.biome;

import java.util.Random;

import net.minecraft.world.World;
import sharedcms.content.world.generator.SimplexNoiseGenerator;
import sharedcms.util.GMap;

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

	private double getNoise(Class<? extends Enum> e, Enum<?> f, double x, double z)
	{
		return generators.get(e).getNoise(x / scales.get(e), 1337, z / scales.get(e));
	}

	public double getNormal(Class<? extends Enum> e, Enum<?> f, double x, double z)
	{
		return ((getNoise(e, f, x, z) / 2.0) + 0.5 + getNormalFor(e, f)) / 2.0;
	}

	public double getNormalFor(Class<? extends Enum> e, Enum<?> f)
	{
		try
		{
			double total = ((Integer) e.getMethod("length").invoke(null)).doubleValue();
			double of = ((Integer) f.getClass().getMethod("ordinal").invoke(f)).doubleValue();

			return of / total;
		}

		catch(Exception xe)
		{
			xe.printStackTrace();
		}

		return 0.5;
	}

	public Enum<?> get(Class<? extends Enum> e, Enum<?> f, double x, double z)
	{
		try
		{
			double total = ((Integer) e.getMethod("length").invoke(null)).doubleValue();
			double normal = getNormal(e, f, x, z);
			int ordinal = (int) (total * normal);

			return (Enum<?>) e.getMethod("of", int.class).invoke(null, ordinal);
		}

		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		return null;
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
