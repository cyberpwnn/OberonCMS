package sharedcms.content.world.buffer.surface;

import net.minecraft.block.Block;
import sharedcns.api.biome.BiomeHumidity;
import sharedcns.api.biome.BiomeHumidityModifier;
import sharedcns.api.biome.BiomeTemperature;
import sharedcns.api.biome.BiomeTemperatureModifier;
import sharedcns.api.biome.LudicrousSurfaceBuffer;

public class SolidSurfaceBuffer extends LudicrousSurfaceBuffer
{
	private Block top;
	private Block filler;
	private Block rock;
	
	public SolidSurfaceBuffer(Block top, Block filler, Block rock)
	{
		this.top = top;
		this.filler = filler;
		this.rock = rock;
	}
	
	@Override
	public Block getTopBlock(int x, int y, int z, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity)
	{
		return top;
	}

	@Override
	public Block getFillerBlock(int x, int y, int z, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity)
	{
		return filler;
	}

	@Override
	public Block getRockBlock(int x, int y, int z, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity)
	{
		return rock;
	}
}
