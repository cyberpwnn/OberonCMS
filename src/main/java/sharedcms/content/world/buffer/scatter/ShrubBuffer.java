package sharedcms.content.world.buffer.scatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import sharedcns.api.biome.BiomeHumidity;
import sharedcns.api.biome.BiomeHumidityModifier;
import sharedcns.api.biome.BiomeTemperature;
import sharedcns.api.biome.BiomeTemperatureModifier;
import sharedcns.api.biome.DecorationPass;
import sharedcns.api.biome.LudicrousScatterBuffer;

public class ShrubBuffer extends LudicrousScatterBuffer
{
	private List<Block> soil;
	private Map<Block, Integer> blocks;
	
	public ShrubBuffer(int strength)
	{
		super(strength, 1);
		
		soil = new ArrayList<Block>();
		blocks = new HashMap<Block, Integer>();
	}
	
	public void addSoil(Block b)
	{
		soil.add(b);
	}
	
	public void addBlock(Block b, int weight)
	{
		blocks.put(b, weight);
	}
	
	public Block pick(Random r)
	{
		int total = 0;
		
		for(Block i : blocks.keySet())
		{
			total += blocks.get(i);
		}
		
		for(Block i : blocks.keySet())
		{
			if(r.nextInt(total) < blocks.get(i))
			{
				return i;
			}
		}
		
		return blocks.keySet().iterator().next();
	}
	
	@Override
	public boolean canDecorate(World w, Random r, DecorationPass pass, Block surface, int x, int y, int z, BiomeTemperatureModifier temperatureModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity hum)
	{
		if(!soil.contains(surface))
		{
			return false;
		}
		
		return true;
	}

	@Override
	public void decorate(World w, Random r, DecorationPass pass, Block surface, int x, int y, int z, BiomeTemperatureModifier temperatureModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity hum)
	{
		w.setBlock(x, y + 1, z, pick(r));
	}
}
