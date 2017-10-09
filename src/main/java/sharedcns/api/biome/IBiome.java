package sharedcns.api.biome;

import net.minecraft.block.Block;

public interface IBiome
{
	public int getId();

	public String getName();

	public BiomeTemperature getBiomeTemperature();

	public BiomeTemperature getBiomeHumidity();
	
	public Block getTopBlock(int x, int y, int z);
	
	public Block getFillerBlock(int x, int y, int z);
	
	public void decorate(int x, int y, int z, DecorationPass pass, Block surface, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity);
}
