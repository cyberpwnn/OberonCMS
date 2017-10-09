package sharedcns.api.biome;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import sharedcms.content.Content;

public class DefaultBiomePaver implements IBiomePaver
{
	@Override
	public Block getTopBlock(int x, int y, int z, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity)
	{
		return Content.Block.COLD_GRASS;
	}

	@Override
	public Block getFillerBlock(int x, int y, int z, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity)
	{
		return Content.Block.COLD_DIRT;
	}

	@Override
	public Block getRockBlock(int x, int y, int z, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity)
	{
		return Content.Block.COLD_STONE;
	}
}
