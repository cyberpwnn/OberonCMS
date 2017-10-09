package sharedcms.content.world.genlayer;

import net.minecraft.world.gen.layer.GenLayer;
import sharedcms.base.AresBiome;
import sharedcms.base.AresGenLayerBiome;
import sharedcms.content.Content;
import sharedcms.util.DimensionalLevel;
import sharedcns.api.biome.ConditionalSimplexNoiseGenerator;

public class BasicGenLayerBiome extends AresGenLayerBiome
{
	public BasicGenLayerBiome(long seed, GenLayer genlayer2)
	{
		super(seed);

		parent = genlayer2;
	}

	@Override
	public AresBiome[] getAllowedBiomes(int x, int z)
	{
		int level = DimensionalLevel.getChunkLevel(x, z);
		
		return Content.biomes((int) level).toArray(new AresBiome[Content.biomes((int) level).size()]);
	}
}
