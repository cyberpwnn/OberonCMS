package sharedcms.content.world.buffer.surface;

import java.util.Map;

import net.minecraft.block.Block;
import sharedcms.util.GMap;
import sharedcns.api.biome.BiomeHumidity;
import sharedcns.api.biome.BiomeHumidityModifier;
import sharedcns.api.biome.BiomeTemperature;
import sharedcns.api.biome.BiomeTemperatureModifier;

public class SplitSurfaceBuffer extends SolidSurfaceBuffer
{
	private Map<BiomeTemperature, Block> topTemp;
	private Map<BiomeHumidity, Block> topHumidity;
	private Map<BiomeTemperature, Block> fillerTemp;
	private Map<BiomeHumidity, Block> fillerHumidity;
	private Map<BiomeTemperature, Block> rockTemp;
	private Map<BiomeHumidity, Block> rockHumidity;

	public SplitSurfaceBuffer(Block top, Block filler, Block rock)
	{
		super(top, filler, rock);

		topTemp = new GMap<BiomeTemperature, Block>();
		topHumidity = new GMap<BiomeHumidity, Block>();
		fillerTemp = new GMap<BiomeTemperature, Block>();
		fillerHumidity = new GMap<BiomeHumidity, Block>();
		rockTemp = new GMap<BiomeTemperature, Block>();
		rockHumidity = new GMap<BiomeHumidity, Block>();
	}

	public void putTop(Block b, BiomeTemperature temp)
	{
		topTemp.put(temp, b);
	}
	
	public void putTop(Block b, BiomeHumidity h)
	{
		topHumidity.put(h, b);
	}
	
	public void putTop(Block b, BiomeTemperature t, BiomeHumidity h)
	{
		putTop(b, t);
		putTop(b, h);
	}
	
	
	
	public void putFiller(Block b, BiomeTemperature temp)
	{
		fillerTemp.put(temp, b);
	}
	
	public void putFiller(Block b, BiomeHumidity h)
	{
		fillerHumidity.put(h, b);
	}
	
	public void putFiller(Block b, BiomeTemperature t, BiomeHumidity h)
	{
		putFiller(b, t);
		putFiller(b, h);
	}
	
	public void putRock(Block b, BiomeTemperature temp)
	{
		rockTemp.put(temp, b);
	}
	
	public void putRock(Block b, BiomeHumidity h)
	{
		rockHumidity.put(h, b);
	}
	
	public void putRock(Block b, BiomeTemperature t, BiomeHumidity h)
	{
		putRock(b, t);
		putRock(b, h);
	}
	
	@Override
	public Block getTopBlock(int x, int y, int z, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity)
	{
		if(topTemp.containsKey(temp) && topHumidity.containsKey(humidity))
		{
			if(topTemp.get(temp).equals(topHumidity.get(humidity)))
			{
				return topTemp.get(temp);
			}

			return super.getTopBlock(x, y, z, tempModifier, humidityModifier, temp, humidity);
		}
		
		return super.getTopBlock(x, y, z, tempModifier, humidityModifier, temp, humidity);
	}

	@Override
	public Block getFillerBlock(int x, int y, int z, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity)
	{
		if(fillerTemp.containsKey(temp) && fillerHumidity.containsKey(humidity))
		{
			if(fillerTemp.get(temp).equals(fillerHumidity.get(humidity)))
			{
				return fillerTemp.get(temp);
			}

			return super.getFillerBlock(x, y, z, tempModifier, humidityModifier, temp, humidity);
		}

		return super.getFillerBlock(x, y, z, tempModifier, humidityModifier, temp, humidity);
	}

	@Override
	public Block getRockBlock(int x, int y, int z, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity)
	{
		if(rockTemp.containsKey(temp) && rockHumidity.containsKey(humidity))
		{
			if(rockTemp.get(temp).equals(rockHumidity.get(humidity)))
			{
				return rockTemp.get(temp);
			}

			return super.getRockBlock(x, y, z, tempModifier, humidityModifier, temp, humidity);
		}

		return super.getRockBlock(x, y, z, tempModifier, humidityModifier, temp, humidity);
	}

}
