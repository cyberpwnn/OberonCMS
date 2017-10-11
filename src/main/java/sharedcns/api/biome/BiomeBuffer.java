package sharedcns.api.biome;

import java.util.List;

import sharedcms.util.GEN;
import sharedcms.util.GList;
import sharedcms.util.GSet;

public class BiomeBuffer
{
	private GSet<IBiome> biomes;
	
	public BiomeBuffer()
	{
		biomes = new GSet<IBiome>();
	}
	
	public BiomeBuffer(List<LudicrousBiome> list)
	{
		this();
		
		biomes.addAll(list);
	}
	
	public BiomeBuffer(GList<IBiome> list)
	{
		this();
		
		biomes.addAll(list);
	}
	
	public void put(IBiome biome)
	{
		biomes.add(biome);
	}
	
	public void remove(IBiome b)
	{
		biomes.remove(b);
	}
	
	public GSet<IBiome> getBiomes()
	{
		return biomes;
	}

	public int size()
	{
		return biomes.size();
	}
}
