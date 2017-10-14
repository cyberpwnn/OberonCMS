package sharedcms.content.world.genlayer;

import java.util.List;

import net.minecraft.world.gen.layer.GenLayer;
import sharedcms.base.AresBiome;
import sharedcms.base.AresGenLayerBiome;
import sharedcms.content.Content;
import sharedcms.util.GList;
import sharedcns.api.biome.BiomeBuffer;
import sharedcns.api.biome.IBiome;
import sharedcns.api.biome.LudicrousBiome;

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
	public LudicrousBiome[] getAllowedBiomes(int x, int z)
	{
		BiomeBuffer buffer = Content.WorldType.ARES.getBiomeOperator().getBiomes(x * 4, z * 4);
		List<LudicrousBiome> biomes = new GList<LudicrousBiome>();
		
		for(IBiome i : buffer.getBiomes())
		{
			biomes.add((LudicrousBiome) i);
		}
		
		return biomes.toArray(new LudicrousBiome[biomes.size()]);
	}
}
