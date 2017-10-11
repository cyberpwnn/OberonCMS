package sharedcms.content.world.biome;

import net.minecraft.init.Blocks;
import sharedcms.base.AresBlockShrub;
import sharedcms.content.Content;
import sharedcms.content.Content.Block;
import sharedcms.content.world.buffer.scatter.ShrubBuffer;
import sharedcms.content.world.buffer.surface.SolidSurfaceBuffer;
import sharedcns.api.biome.BiomeHumidity;
import sharedcns.api.biome.BiomeTemperature;

public class BiomePlains extends LBiomeBase
{
	public BiomePlains(int level)
	{
		super(level);
		
		setBiomeHumidity(BiomeHumidity.DAMP);
		setBiomeTemperature(BiomeTemperature.WARM);
		setSurfaceBuffer(new SolidSurfaceBuffer(Content.Block.COLD_GRASS, Content.Block.COLD_DIRT, Content.Block.COLD_STONE));
		
		ShrubBuffer main = new ShrubBuffer(80);
		main.addSoil(Content.Block.COLD_GRASS);
		main.addBlock(Content.Block.THIN_GRASS, 20);
		main.addBlock(Content.Block.TALL_GRASS, 20);
		main.addBlock(Content.Block.STUBBED_GRASS, 20);
		main.addBlock(Content.Block.SPOKED_GRASS, 20);
		main.bindAllHumidities();
		main.bindAllTemperatures();
		
		ShrubBuffer normal = new ShrubBuffer(50);
		normal.addSoil(Content.Block.COLD_GRASS);
		
		for(AresBlockShrub i : Content.flowers())
		{
			normal.addBlock(i, i.getWeight());
		}
		
		normal.bindAllHumidities();
		normal.unbindHumidity(BiomeHumidity.ARID);
		normal.unbindHumidity(BiomeHumidity.DRY);
		normal.bindTemperature(BiomeTemperature.CHILLY);
		normal.bindTemperature(BiomeTemperature.WARM);
		normal.bindTemperature(BiomeTemperature.HOT);
	
		ShrubBuffer hot = new ShrubBuffer(56);
		hot.addSoil(Content.Block.COLD_GRASS);
		hot.addBlock(Content.Block.DEAD_BUSH, 20);
		hot.addBlock(Content.Block.DEAD_TWIG, 20);
		hot.bindHumidity(BiomeHumidity.ARID);
		hot.bindHumidity(BiomeHumidity.DRY);
		hot.bindTemperature(BiomeTemperature.HOT);
		hot.bindTemperature(BiomeTemperature.SCORCHED);
		
		ShrubBuffer dry = new ShrubBuffer(56);
		dry.addSoil(Content.Block.COLD_GRASS);
		dry.addBlock(Content.Block.DEAD_BUSH, 20);
		dry.addBlock(Content.Block.DEAD_TWIG, 20);
		dry.bindAllHumidities();
		dry.unbindHumidity(BiomeHumidity.DAMP);
		dry.unbindHumidity(BiomeHumidity.MOIST);
		dry.bindTemperature(BiomeTemperature.HOT);
		dry.bindTemperature(BiomeTemperature.WARM);
	
		addScatterBuffer(main);
		addScatterBuffer(normal);
		addScatterBuffer(dry);
		addScatterBuffer(hot);
	}
}
