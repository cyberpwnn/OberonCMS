package sharedcns.api.biome;

import sharedcms.content.Content;
import sharedcms.util.GEN;

public class BiomeOperator implements IBiomeOperator
{
	protected BiomeBuffer biomes;

	public BiomeOperator(BiomeBuffer biomes)
	{
		this.biomes = biomes;
	}

	@Override
	public BiomeBuffer getBiomes(int x, int z)
	{
		IBiome[] b = biomes.getBiomes().toArray(new IBiome[biomes.size()]);
		double n = GEN.getNoise("biomeset", x, z, 0.5, 1);
		
		IBiome bi = b[(int) (n * b.length)];
		BiomeBuffer bb = new BiomeBuffer();
		bb.put(bi);
		
		return bb;
	}

	@Override
	public BiomeBuffer getBiomes()
	{
		return biomes;
	}
}
