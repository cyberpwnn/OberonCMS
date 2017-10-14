package sharedcns.api.biome;

import java.util.Set;

import sharedcms.util.DimensionalLevel;
import sharedcms.util.GList;

public class LeveledBiomeOperator extends BiomeOperator implements IBiomeOperator
{
	private LevelSpliterator levelSplit;
	
	public LeveledBiomeOperator(BiomeBuffer b)
	{
		super(b);
		
		levelSplit = new LevelSpliterator(b.size());
		
		for(IBiome i : b.getBiomes())
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
		
		for(IBiome i : b)
		{
			if(i.getLevel() == biomeLevel)
			{
				bb.put(i);
			}
		}
		
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
