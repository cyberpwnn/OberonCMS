package sharedcns.api.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public interface IBiomeDecorator
{
	public boolean canDecorate(World w, Random r, DecorationPass pass, Block surface, int x, int y, int z, BiomeTemperatureModifier temperatureModifier, BiomeHumidityModifier humidityModifier);
	
	public void decorate(World w, Random r, DecorationPass pass, Block surface, int x, int y, int z, BiomeTemperatureModifier temperatureModifier, BiomeHumidityModifier humidityModifier);
}
