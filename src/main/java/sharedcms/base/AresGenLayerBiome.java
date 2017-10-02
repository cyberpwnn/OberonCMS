package sharedcms.base;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public abstract class AresGenLayerBiome extends GenLayer
{
	public AresGenLayerBiome(long seed)
	{
		super(seed);
	}

	private AresGenLayerBiome(long seed, GenLayer genlayer)
	{
		super(seed);
		this.parent = genlayer;
	}

	public abstract AresBiome[] getAllowedBiomes();

	@Override
	public int[] getInts(int x, int z, int width, int depth)
	{
		int[] dest = IntCache.getIntCache(width * depth);

		for(int dz = 0; dz < depth; dz++)
		{
			for(int dx = 0; dx < width; dx++)
			{
				this.initChunkSeed(dx + x, dz + z);
				dest[(dx + dz * width)] = getAllowedBiomes()[nextInt(getAllowedBiomes().length)].biomeID;
			}
		}

		return dest;
	}
}
