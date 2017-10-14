package sharedcns.api.biome;

public class LeveledBiome
{
	private IBiome biome;
	private int level;

	public LeveledBiome(IBiome biome, int level)
	{
		this.biome = biome;
		this.level = level;
	}

	public IBiome getBiome()
	{
		return biome;
	}

	public int getLevel()
	{
		return level;
	}
}
