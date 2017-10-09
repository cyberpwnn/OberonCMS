package sharedcns.api.biome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import sharedcms.base.AresBiome;

public class EnhancedBiome extends AresBiome implements IBiome
{
	private IBiomePaver paver;
	private List<IBiomeDecorator> decorators;

	public EnhancedBiome(int id)
	{
		super(id);
		paver = new DefaultBiomePaver();
		decorators = new ArrayList<IBiomeDecorator>();
	}

	@Override
	public int getId()
	{
		return biomeID;
	}

	@Override
	public List<IBiomeDecorator> getDecorators()
	{
		return decorators;
	}

	@Override
	public String getName()
	{
		return biomeName;
	}

	@Override
	public BiomeTemperature getBiomeTemperature()
	{
		return (BiomeTemperature) BiomeTemperature.of((int) ((getTemperature() / 2f) / BiomeTemperature.length()));
	}

	@Override
	public BiomeHumidity getBiomeHumidity()
	{
		return (BiomeHumidity) BiomeHumidity.of((int) (getRainfall() / BiomeHumidity.length()));
	}

	@Override
	public Block getTopBlock(int x, int y, int z, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity)
	{
		return getPaver().getTopBlock(x, y, z, tempModifier, humidityModifier, temp, humidity);
	}

	@Override
	public Block getFillerBlock(int x, int y, int z, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity)
	{
		return getPaver().getFillerBlock(x, y, z, tempModifier, humidityModifier, temp, humidity);
	}

	@Override
	public Block getRockBlock(int x, int y, int z, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity)
	{
		return getPaver().getRockBlock(x, y, z, tempModifier, humidityModifier, temp, humidity);
	}

	@Override
	public void decorate(World w, Random r, int x, int y, int z, DecorationPass pass, Block surface, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity)
	{
		for(IBiomeDecorator i : getDecorators())
		{
			if(i.canDecorate(w, r, pass, surface, x, y, z, tempModifier, humidityModifier))
			{
				i.decorate(w, r, pass, surface, x, y, z, tempModifier, humidityModifier);
			}
		}
	}

	@Override
	public void addDecorator(IBiomeDecorator decorator)
	{
		getDecorators().add(decorator);
	}

	@Override
	public IBiomePaver getPaver()
	{
		return paver;
	}

	@Override
	public void setPaver(IBiomePaver paver)
	{
		this.paver = paver;
	}

	@Override
	public void setBiomeHumidity(BiomeHumidity h)
	{
		rainfall = h.getHumidity();
	}

	@Override
	public void setBiomeTemperature(BiomeTemperature t)
	{
		temperature = t.getTemperature();
	}
}
