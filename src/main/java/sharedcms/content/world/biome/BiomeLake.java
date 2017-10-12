package sharedcms.content.world.biome;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
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
import sharedcns.api.biome.BiomeTemperature;
import sharedcns.api.biome.DecorationPass;

public class BiomeLake extends LBiomeBase
{
	public BiomeLake(int level)
	{
		super(level);
		height -= 0.8;
		variation += 0.07;
		setBiomeHumidity(BiomeHumidity.DRY);
		setBiomeTemperature(BiomeTemperature.HOT);

		SplitSurfaceBuffer buffer = new SplitSurfaceBuffer(Blocks.sand, Content.Block.PATH_SAND, Content.Block.PATH_SAND);
		buffer.putTop(Content.Block.ARID_SAND, BiomeHumidity.ARID);
		buffer.putTop(Content.Block.ARID_SAND, BiomeHumidity.DRY);
		buffer.putTop(Content.Block.ARID_SAND, BiomeTemperature.SCORCHED);
		buffer.putTop(Content.Block.ARID_SAND, BiomeTemperature.HOT);
		
		WaterBuffer water = new WaterBuffer(1024, 67, 4);
		water.bindAllHumidities();
		water.bindAllTemperatures();
		
		addScatterBuffer(water);
		setSurfaceBuffer(buffer);
	}
}
