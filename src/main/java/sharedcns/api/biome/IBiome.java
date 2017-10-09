package sharedcns.api.biome;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public interface IBiome
{
	public int getId();

	public void addDecorator(IBiomeDecorator decorator);
	
	public List<IBiomeDecorator> getDecorators();

	public IBiomePaver getPaver();
	
	public void setPaver(IBiomePaver paver);

	public String getName();

	public BiomeTemperature getBiomeTemperature();

	public BiomeHumidity getBiomeHumidity();
	
	public void setBiomeHumidity(BiomeHumidity h);
	
	public void setBiomeTemperature(BiomeTemperature t);

	public Block getTopBlock(int x, int y, int z, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity);

	public Block getFillerBlock(int x, int y, int z, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity);

	public Block getRockBlock(int x, int y, int z, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity);

	public void decorate(World w, Random r, int x, int y, int z, DecorationPass pass, Block surface, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity);
}
