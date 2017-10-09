package sharedcms.content.world.genlayer;

import java.util.List;

import net.minecraft.world.gen.layer.GenLayer;
import sharedcms.base.AresBiome;
import sharedcms.base.AresGenLayerBiome;
import sharedcms.content.Content;
import sharedcms.content.world.meta.objects.MetaWorld;
import sharedcms.controller.shared.WorldHostController;
import sharedcms.util.DimensionalLevel;

public class BasicGenLayerBiome extends AresGenLayerBiome
{
	private long seed;
	
	public BasicGenLayerBiome(long seed, GenLayer genlayer2)
	{
		super(seed);

		this.seed = seed;
		parent = genlayer2;
	}

	@Override
	public AresBiome[] getAllowedBiomes(int x, int z)
	{
		int level = DimensionalLevel.getChunkLevel(x, z);
		List<AresBiome> biomes = Content.biomes(level);
		
		return biomes.toArray(new AresBiome[biomes.size()]);
	}
}
