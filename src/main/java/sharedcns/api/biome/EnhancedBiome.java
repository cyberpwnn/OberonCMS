package sharedcns.api.biome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import sharedcms.base.AresBiome;

public class EnhancedBiome extends AresBiome implements IBiome
{
	private ISurfaceBuffer paver;
	private List<IScatterBuffer> decorators;
	private int level;

	public EnhancedBiome(int id)
	{
		super(id);
		level = 0;
		paver = new DefaultBiomePaver();
		decorators = new ArrayList<IScatterBuffer>();
	}

	@Override
	public int getId()
	{
		return biomeID;
	}

	@Override
	public List<IScatterBuffer> getScatterBuffers()
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
		return getSurfaceBuffer().getTopBlock(x, y, z, tempModifier, humidityModifier, temp, humidity);
	}

	@Override
	public Block getFillerBlock(int x, int y, int z, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity)
	{
		return getSurfaceBuffer().getFillerBlock(x, y, z, tempModifier, humidityModifier, temp, humidity);
	}

	@Override
	public Block getRockBlock(int x, int y, int z, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity)
	{
		return getSurfaceBuffer().getRockBlock(x, y, z, tempModifier, humidityModifier, temp, humidity);
	}

	@Override
	public void decorate(World w, Random r, int x, int y, int z, DecorationPass pass, Block surface, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity)
	{
		for(IScatterBuffer i : getScatterBuffers())
		{
			if(i.canDecorate(w, r, pass, surface, x, y, z, tempModifier, humidityModifier, temp, humidity))
			{
				i.decorate(w, r, pass, surface, x, y, z, tempModifier, humidityModifier, temp, humidity);
			}
		}
	}

	@Override
	public void addScatterBuffer(IScatterBuffer decorator)
	{
		getScatterBuffers().add(decorator);
	}

	@Override
	public ISurfaceBuffer getSurfaceBuffer()
	{
		return paver;
	}

	@Override
	public void setSurfaceBuffer(ISurfaceBuffer paver)
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

	@Override
	public int getLevel()
	{
		return level;
	}

	@Override
	public void setLevel(int level)
	{
		this.level = level;
	}
}
