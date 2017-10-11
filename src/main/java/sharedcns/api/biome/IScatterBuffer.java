package sharedcns.api.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public interface IScatterBuffer
{
	public int getStrength();
	
	public DecorationPass getDecorationPass();
	
	public void setDecorationPass(DecorationPass pass);
	
	public double getChance();
	
	public boolean canDecorate(World w, Random r, DecorationPass pass, Block surface, int x, int y, int z, BiomeTemperatureModifier temperatureModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity hum);
	
	public void decorate(World w, Random r, DecorationPass pass, Block surface, int x, int y, int z, BiomeTemperatureModifier temperatureModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity hum);
}
