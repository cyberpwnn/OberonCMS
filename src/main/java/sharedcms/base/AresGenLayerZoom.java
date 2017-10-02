package sharedcms.base;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class AresGenLayerZoom extends GenLayer
{
	protected long seed;
	
	public AresGenLayerZoom(long seed)
	{
		super(seed);
		this.seed = seed;
	}

	public abstract AresGenLayerBiome getGenLayerBiome();
	
	public GenLayer[] construct(long seed, WorldType type)
	{
		GenLayer biomes = getGenLayerBiome();
		
		biomes = new GenLayerFuzzyZoom(100L, biomes);
		biomes = new GenLayerFuzzyZoom(101L, biomes);
		biomes = new GenLayerFuzzyZoom(102L, biomes);
		biomes = new GenLayerFuzzyZoom(103L, biomes);
		biomes = new GenLayerFuzzyZoom(104L, biomes);
		biomes = new GenLayerFuzzyZoom(105L, biomes);
		biomes = new GenLayerSmooth(106l, biomes);
		GenLayer genlayervoronoizoom = new GenLayerVoronoiZoom(10L, biomes);
		biomes.initWorldGenSeed(seed);
		genlayervoronoizoom.initWorldGenSeed(seed);
		return new GenLayer[] { biomes, genlayervoronoizoom };
	}

	@Override
	public int[] getInts(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_)
	{
		return null;
	}
}
