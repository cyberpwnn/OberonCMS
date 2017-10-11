package sharedcns.api.biome;

import java.util.Set;

import sharedcms.util.DimensionalLevel;
import sharedcms.util.GList;

public class LeveledBiomeOperator extends BiomeOperator implements IBiomeOperator
{
	public LeveledBiomeOperator(BiomeBuffer b)
	{
		super(b);
	}

	@Override
	public BiomeBuffer getBiomes(int x, int z)
	{
		BiomeBuffer list = new BiomeBuffer(new GList<IBiome>(biomes.getBiomes()));
		int l = DimensionalLevel.getLevel(x, z);
		int d = distanceToAnyBiome(list, l);
		int k = 1;
		k += d > 3 && d < 10 ? 0 : 1;
		
		while(list.size() > k)
		{
			clip(list, DimensionalLevel.getLevel(x, z));
		}
		
		return list;
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
