package sharedcms.content.world.buffer.scatter;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import sharedcns.api.biome.BiomeHumidity;
import sharedcns.api.biome.BiomeHumidityModifier;
import sharedcns.api.biome.BiomeTemperature;
import sharedcns.api.biome.BiomeTemperatureModifier;
import sharedcns.api.biome.DecorationPass;
import sharedcns.api.biome.LudicrousBiome;
import sharedcns.api.biome.LudicrousScatterBuffer;

public class ModelBuffer extends LudicrousScatterBuffer
{
	
	
	public ModelBuffer(int strength)
	{
		super(strength, 1);
	}

	@Override
	public boolean canDecorate(World w, Random r, DecorationPass pass, Block surface, int x, int y, int z, BiomeTemperatureModifier temperatureModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity hum)
	{
		return false;
	}

	@Override
	public void decorate(World w, Random r, DecorationPass pass, Block surface, int x, int y, int z, BiomeTemperatureModifier temperatureModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity hum)
	{
		
	}
}
