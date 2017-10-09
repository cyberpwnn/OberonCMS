package sharedcms.content.world.genlayer;

import net.minecraft.world.gen.layer.GenLayer;
import sharedcms.base.AresBiome;
import sharedcms.base.AresGenLayerBiome;
import sharedcms.content.Content;
import sharedcms.content.world.generator.SimplexNoiseGenerator;
import sharedcms.content.world.generator.SimplexOctaveGenerator;
import sharedcms.util.Location;
import sharedcms.util.DimensionalLevel;

public class BasicGenLayerBiome extends AresGenLayerBiome
{
	private SimplexNoiseGenerator simplex;

	public BasicGenLayerBiome(long seed, GenLayer genlayer2)
	{
		super(seed);

		simplex = new SimplexNoiseGenerator(seed);
		parent = genlayer2;
	}

	@Override
	public AresBiome[] getAllowedBiomes(int x, int z)
	{
		int level = DimensionalLevel.getChunkLevel(x, z);
		
		return Content.biomes((int) level).toArray(new AresBiome[Content.biomes((int) level).size()]);
	}
}
