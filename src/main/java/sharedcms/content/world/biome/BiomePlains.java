package sharedcms.content.world.biome;

import sharedcms.base.AresBlockShrub;
import sharedcms.content.Content;
import sharedcms.content.Content.Block;
import sharedcms.content.world.buffer.scatter.ShrubBuffer;
import sharedcms.content.world.buffer.surface.SolidSurfaceBuffer;
import sharedcns.api.biome.BiomeHumidity;
import sharedcns.api.biome.BiomeTemperature;

public class BiomePlains extends LBiomeBase
{
	public BiomePlains()
	{
		setBiomeHumidity(BiomeHumidity.DAMP);
		setBiomeTemperature(BiomeTemperature.WARM);
		setSurfaceBuffer(new SolidSurfaceBuffer(Content.Block.COLD_GRASS, Content.Block.COLD_DIRT, Content.Block.COLD_STONE));
		ShrubBuffer normal = new ShrubBuffer(12, 0.8, true);
		normal.addSoil(Content.Block.COLD_GRASS);
		
		for(AresBlockShrub i : Content.flowers())
		{
			normal.addBlock(i, i.getWeight());
		}
		
		
		addScatterBuffer(normal);
		
	}
}
