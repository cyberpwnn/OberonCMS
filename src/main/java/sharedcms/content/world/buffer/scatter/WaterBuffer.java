package sharedcms.content.world.buffer.scatter;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import sharedcns.api.biome.BiomeHumidity;
import sharedcns.api.biome.BiomeHumidityModifier;
import sharedcns.api.biome.BiomeTemperature;
import sharedcns.api.biome.BiomeTemperatureModifier;
import sharedcns.api.biome.DecorationPass;
import sharedcns.api.biome.LudicrousScatterBuffer;

public class WaterBuffer extends LudicrousScatterBuffer
{
	private int waterHeight;
	private int waterDepth;
	
	public WaterBuffer(int strength, int waterHeight, int waterDepth)
	{
		super(strength, 1);
		
		this.waterHeight = waterHeight;
		this.waterDepth = waterDepth;
	}
	
	@Override
	public boolean canDecorate(World w, Random r, DecorationPass pass, Block surface, int x, int y, int z, BiomeTemperatureModifier temperatureModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity hum)
	{
		if(y >= waterHeight)
		{
			return false;
		}
			
		return true;
	}

	@Override
	public void decorate(World w, Random r, DecorationPass pass, Block surface, int x, int y, int z, BiomeTemperatureModifier temperatureModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity hum)
	{
		for(int i = waterHeight; i < y; i--)
		{
			w.setBlock(x, i, z, Blocks.water);
		}
	}
}
