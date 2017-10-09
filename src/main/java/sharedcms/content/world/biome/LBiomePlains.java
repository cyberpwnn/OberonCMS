package sharedcms.content.world.biome;

import sharedcms.content.Content;
import sharedcms.content.world.buffer.surface.SolidSurfaceBuffer;
import sharedcns.api.biome.BiomeHumidity;
import sharedcns.api.biome.BiomeTemperature;

public class LBiomePlains extends LBiomeBase
{
	public LBiomePlains()
	{
		setBiomeHumidity(BiomeHumidity.DAMP);
		setBiomeTemperature(BiomeTemperature.WARM);
		setSurfaceBuffer(new SolidSurfaceBuffer(Content.Block.COLD_GRASS, Content.Block.COLD_DIRT, Content.Block.COLD_STONE));
	}
}
