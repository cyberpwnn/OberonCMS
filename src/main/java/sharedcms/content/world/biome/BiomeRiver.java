package sharedcms.content.world.biome;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenOcean;
import sharedcms.base.AresBlockShrub;
import sharedcms.content.Content;
import sharedcms.content.Content.Block;
import sharedcms.content.world.buffer.scatter.ShrubBuffer;
import sharedcms.content.world.buffer.scatter.TreeBuffer;
import sharedcms.content.world.buffer.scatter.TreeModel;
import sharedcms.content.world.buffer.scatter.WaterBuffer;
import sharedcms.content.world.buffer.surface.SolidSurfaceBuffer;
import sharedcms.content.world.buffer.surface.SplitSurfaceBuffer;
import sharedcms.content.world.meta.objects.MetaWorld;
import sharedcms.util.GEN;
import sharedcms.util.SimplexProperties;
import sharedcns.api.biome.BiomeHumidity;
import sharedcns.api.biome.BiomeHumidityModifier;
import sharedcns.api.biome.BiomeTemperature;
import sharedcns.api.biome.BiomeTemperatureModifier;
import sharedcns.api.biome.DecorationPass;

public class BiomeRiver extends LBiomeBase
{
	public BiomeRiver(int level)
	{
		super(level);

		height = -0.66f;
		variation = 0.02f;
		allowWater = true;
		setBiomeHumidity(BiomeHumidity.DRENCHED);
		setBiomeTemperature(BiomeTemperature.CHILLY);

		SplitSurfaceBuffer buffer = new SplitSurfaceBuffer(Content.Block.PATH_SAND, Content.Block.SMOOTH_DIRT, Content.Block.COLD_STONE);

		setSurfaceBuffer(buffer);
	}
}
