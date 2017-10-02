package sharedcms.content.world.genlayer;

import net.minecraft.world.gen.layer.GenLayer;
import sharedcms.base.AresBiome;
import sharedcms.base.AresGenLayerBiome;
import sharedcms.content.Content;

public class BasicGenLayerBiome extends AresGenLayerBiome
{
	public BasicGenLayerBiome(long seed, GenLayer genlayer2)
	{
		super(seed);
		
		parent = genlayer2;
	}

	@Override
	public AresBiome[] getAllowedBiomes()
	{
		return Content.biomes().toArray(new AresBiome[Content.biomes().size()]);
	}

}
