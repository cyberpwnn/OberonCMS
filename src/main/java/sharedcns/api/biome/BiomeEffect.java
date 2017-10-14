package sharedcns.api.biome;

public class BiomeEffect
{
	private IBiome biome;
	private int levelMin;
	private int levelMax;

	public BiomeEffect(IBiome biome, int levelMin, int levelMax)
	{
		this.biome = biome;
		this.levelMin = levelMin;
		this.levelMax = levelMax;
	}

	public IBiome getBiome()
	{
		return biome;
	}

	public int getLevelMin()
	{
		return levelMin;
	}

	public int getLevelMax()
	{
		return levelMax;
	}
}
