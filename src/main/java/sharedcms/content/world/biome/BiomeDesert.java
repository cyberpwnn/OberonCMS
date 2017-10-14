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

public class BiomeDesert extends LBiomeBase
{
	public BiomeDesert(int level)
	{
		super(level);
		variation += 0.16;
		setBiomeHumidity(BiomeHumidity.DRY);
		setBiomeTemperature(BiomeTemperature.HOT);

		SplitSurfaceBuffer buffer = new SplitSurfaceBuffer(Blocks.sand, Content.Block.PATH_SAND, Content.Block.PATH_SAND);
		buffer.putTop(Content.Block.ARID_SAND, BiomeHumidity.ARID);
		buffer.putTop(Content.Block.ARID_SAND, BiomeHumidity.DRY);
		buffer.putTop(Content.Block.ARID_SAND, BiomeTemperature.SCORCHED);
		buffer.putTop(Content.Block.ARID_SAND, BiomeTemperature.HOT);

		ShrubBuffer mainShrub = new ShrubBuffer(40);
		mainShrub.setDecorationPass(DecorationPass.PASS_12);
		mainShrub.addSoil(Blocks.sand);
		mainShrub.addSoil(Content.Block.ARID_SAND);
		mainShrub.addBlock(Content.Block.DEAD_BUSH, 20);
		mainShrub.addBlock(Content.Block.DEAD_TWIG, 20);
		mainShrub.bindAllHumidities();
		mainShrub.bindAllTemperatures();

		ShrubBuffer normalShrub = new ShrubBuffer(30);
		normalShrub.setDecorationPass(DecorationPass.PASS_12);
		normalShrub.addSoil(Blocks.sand);
		normalShrub.addSoil(Content.Block.ARID_SAND);

		for(AresBlockShrub i : Content.flowers())
		{
			normalShrub.addBlock(i, i.getWeight());
		}

		normalShrub.bindAllHumidities();
		normalShrub.unbindHumidity(BiomeHumidity.ARID);
		normalShrub.unbindHumidity(BiomeHumidity.DRY);
		normalShrub.unbindHumidity(BiomeHumidity.DAMP);
		normalShrub.bindTemperature(BiomeTemperature.COLD);
		normalShrub.bindTemperature(BiomeTemperature.CHILLY);
		normalShrub.bindTemperature(BiomeTemperature.WARM);

		TreeBuffer burnedTree = new TreeBuffer(TreeModel.BASIC, 3, Content.Block.LOG_ARID, Blocks.air)
		{
			@Override
			public void updateForGen(World w, int x, int z)
			{
				super.updateForGen(w, x, z);

				int dist = distanceToBorder(w, x, z, 67);
				setTreeHeight((int) (7 + GEN.getNoise("canopy-height", x, z, 6, 0) + (dist / 5)));
			}
		};

		burnedTree.setTreeHeight(2);
		burnedTree.setDecorationPass(DecorationPass.PASS_10);
		burnedTree.addSoil(Blocks.sand);
		burnedTree.addSoil(Content.Block.ARID_SAND);
		burnedTree.bindAllHumidities();
		burnedTree.bindAllTemperatures();
		burnedTree.unbindHumidity(BiomeHumidity.ARID);
		burnedTree.unbindHumidity(BiomeHumidity.DRY);
		burnedTree.unbindTemperature(BiomeTemperature.SCORCHED);
		burnedTree.unbindTemperature(BiomeTemperature.HOT);

		setSurfaceBuffer(buffer);
		addScatterBuffer(mainShrub);
		addScatterBuffer(normalShrub);
		addScatterBuffer(burnedTree);
	}
}
