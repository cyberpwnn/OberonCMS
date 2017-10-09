package sharedcms.util;

import sharedcms.Info;
import sharedcms.content.world.generator.SimplexNoiseGenerator;

public class DimensionalLevel
{
	public static SimplexNoiseGenerator simplex = new SimplexNoiseGenerator(69133769);

	public static int getLevel(Location l)
	{
		return getLevel((int) l.x, (int) l.z);
	}

	public static int getLevel(int x, int z)
	{
		double distance = new Location().distance(new Location(x, 0, z));
		double noise = 0;
		noise += simplex.noise((double) x / 10000, 1337, (double) z / 10000) * 36;
		noise += simplex.noise((double) z / 1000, 1337, (double) x / 1000) * 14;
		noise += distance * Info.LEVEL_DISTANCE_MULTIPLIER;

		return (int) Math.abs(noise);
	}

	public static int getChunkLevel(int x, int z)
	{
		return getLevel(x << 4, z << 4);
	}
}
