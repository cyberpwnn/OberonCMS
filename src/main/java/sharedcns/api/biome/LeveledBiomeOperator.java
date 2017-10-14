package sharedcns.api.biome;

import java.util.Set;

import sharedcms.util.DimensionalLevel;
import sharedcms.util.GEN;
import sharedcms.util.GList;

public class LeveledBiomeOperator extends BiomeOperator implements IBiomeOperator
{
	private LevelSpliterator levelSplit;
	private GList<LeveledBiome> biomeLevels;
	private GList<BiomeSimplexEffect> biomeEffects;

	public LeveledBiomeOperator(BiomeBuffer b, GList<LeveledBiome> biomeLevels, GList<BiomeSimplexEffect> biomeEffects)
	{
		super(b);

		this.biomeLevels = biomeLevels;
		this.biomeEffects = biomeEffects;
		levelSplit = new LevelSpliterator(biomeLevels.size());

		for(LeveledBiome i : biomeLevels)
		{
			levelSplit.add(i.getLevel());
		}
	}

	@Override
	public BiomeBuffer getBiomes(int x, int z)
	{
		IBiome[] b = biomes.getBiomes().toArray(new IBiome[biomes.size()]);
		BiomeBuffer bb = new BiomeBuffer();
		int level = DimensionalLevel.getLevel(x, z);
		int biomeLevel = levelSplit.getSplitLevel(level);
		IBiome sel = null;

		for(LeveledBiome i : biomeLevels)
		{
			if(i.getLevel() == biomeLevel)
			{
				bb.put(i.getBiome());
			}
		}

		bb = applyEffects(bb, x, z, biomeLevel, level);

		return bb;
	}

	public int distanceToAnyBiome(BiomeBuffer buffer, int level)
	{
		Set<IBiome> b = buffer.getBiomes();

		int dist = Integer.MAX_VALUE;
		for(IBiome i : b)
		{
			if(Math.abs((level + 10000) - (i.getLevel() + 10000)) < dist)
			{
				dist = Math.abs((level + 10000) - (i.getLevel() + 10000));
			}
		}

		return dist;
	}

	public void clip(BiomeBuffer buffer, int level)
	{
		Set<IBiome> b = buffer.getBiomes();

		int dist = 0;
		IBiome target = null;

		for(IBiome i : b)
		{
			if(Math.abs((level + 10000) - (i.getLevel() + 10000)) > dist)
			{
				dist = Math.abs((level + 10000) - (i.getLevel() + 10000));
				target = i;
			}
		}

		if(target != null)
		{
			buffer.remove(target);
		}
	}
}
