package sharedcms.content.world.biome;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import sharedcms.base.AresBlockShrub;
import sharedcms.content.Content;
import sharedcms.content.Content.Block;
import sharedcms.content.world.buffer.scatter.ShrubBuffer;
import sharedcms.content.world.buffer.scatter.TreeBuffer;
import sharedcms.content.world.buffer.scatter.TreeModel;
import sharedcms.content.world.buffer.surface.SolidSurfaceBuffer;
import sharedcms.util.GEN;
import sharedcns.api.biome.BiomeHumidity;
import sharedcns.api.biome.BiomeTemperature;
import sharedcns.api.biome.DecorationPass;

public class BiomeMountains extends LBiomeBase
{
	public BiomeMountains(int level)
	{
		super(level);
		height += 1.8;
		variation += 0.45;
		setBiomeHumidity(BiomeHumidity.DAMP);
		setBiomeTemperature(BiomeTemperature.CHILLY);
		setSurfaceBuffer(new SolidSurfaceBuffer(Content.Block.COLD_GRASS, Content.Block.COLD_DIRT, Content.Block.COLD_STONE));

		ShrubBuffer mainShrub = new ShrubBuffer(40);
		mainShrub.setDecorationPass(DecorationPass.PASS_12);
		mainShrub.addSoil(Content.Block.COLD_GRASS);
		mainShrub.addBlock(Content.Block.THIN_GRASS, 20);
		mainShrub.addBlock(Content.Block.TALL_GRASS, 20);
		mainShrub.addBlock(Content.Block.STUBBED_GRASS, 20);
		mainShrub.addBlock(Content.Block.SPOKED_GRASS, 20);
		mainShrub.bindAllHumidities();
		mainShrub.bindAllTemperatures();

		ShrubBuffer normalShrub = new ShrubBuffer(30);
		normalShrub.setDecorationPass(DecorationPass.PASS_12);
		normalShrub.addSoil(Content.Block.COLD_GRASS);

		for(AresBlockShrub i : Content.flowers())
		{
			normalShrub.addBlock(i, i.getWeight());
		}

		normalShrub.bindAllHumidities();
		normalShrub.unbindHumidity(BiomeHumidity.ARID);
		normalShrub.unbindHumidity(BiomeHumidity.DRY);
		normalShrub.bindTemperature(BiomeTemperature.CHILLY);
		normalShrub.bindTemperature(BiomeTemperature.WARM);
		normalShrub.bindTemperature(BiomeTemperature.HOT);

		ShrubBuffer hotShrub = new ShrubBuffer(25);
		hotShrub.setDecorationPass(DecorationPass.PASS_12);
		hotShrub.addSoil(Content.Block.COLD_GRASS);
		hotShrub.addBlock(Content.Block.DEAD_BUSH, 20);
		hotShrub.addBlock(Content.Block.DEAD_TWIG, 20);
		hotShrub.bindHumidity(BiomeHumidity.ARID);
		hotShrub.bindHumidity(BiomeHumidity.DRY);
		hotShrub.bindTemperature(BiomeTemperature.HOT);
		hotShrub.bindTemperature(BiomeTemperature.SCORCHED);

		ShrubBuffer dryShrub = new ShrubBuffer(20);
		dryShrub.setDecorationPass(DecorationPass.PASS_12);
		dryShrub.addSoil(Content.Block.COLD_GRASS);
		dryShrub.addBlock(Content.Block.DEAD_BUSH, 20);
		dryShrub.addBlock(Content.Block.DEAD_TWIG, 20);
		dryShrub.bindAllHumidities();
		dryShrub.unbindHumidity(BiomeHumidity.DAMP);
		dryShrub.unbindHumidity(BiomeHumidity.MOIST);
		dryShrub.bindTemperature(BiomeTemperature.HOT);
		dryShrub.bindTemperature(BiomeTemperature.WARM);

		TreeBuffer normalTree = new TreeBuffer(TreeModel.CONIFEROUS, 20, Content.Block.LOG_OAK, Content.Block.LEAVES_OAK)
		{
			@Override
			public void updateForGen(World w, int x, int z)
			{
				super.updateForGen(w, x, z);
				
				int dist = distanceToBorder(w, x, z, 67);
				setTreeHeight((int) ((14 + GEN.getNoise("canopy-height", x, z, 0.5, 1) * (dist / 5))));
			}
		};
		
		normalTree.setTreeHeight(12);
		normalTree.setDecorationPass(DecorationPass.PASS_10);
		normalTree.addSoil(Content.Block.COLD_GRASS);
		normalTree.bindAllHumidities();
		normalTree.bindAllTemperatures();
		normalTree.unbindHumidity(BiomeHumidity.ARID);
		normalTree.unbindHumidity(BiomeHumidity.DRY);
		normalTree.unbindTemperature(BiomeTemperature.SCORCHED);
		normalTree.unbindTemperature(BiomeTemperature.HOT);
		
		TreeBuffer choppedTree = new TreeBuffer(TreeModel.BASIC, 4, Content.Block.LOG_SPRUCE, Blocks.air);
		choppedTree.setTreeHeight(2);
		choppedTree.setDecorationPass(DecorationPass.PASS_10);
		choppedTree.addSoil(Content.Block.COLD_GRASS);
		choppedTree.bindAllHumidities();
		choppedTree.bindAllTemperatures();

		TreeBuffer burnedTree = new TreeBuffer(TreeModel.BASIC, 13, Content.Block.LOG_DARK, Blocks.air);
		burnedTree.setTreeHeight(4);
		burnedTree.setDecorationPass(DecorationPass.PASS_10);
		burnedTree.addSoil(Content.Block.COLD_GRASS);
		burnedTree.bindAllHumidities();
		burnedTree.unbindHumidity(BiomeHumidity.ARID);
		burnedTree.unbindHumidity(BiomeHumidity.DRY);
		burnedTree.unbindTemperature(BiomeTemperature.SCORCHED);
		burnedTree.unbindTemperature(BiomeTemperature.HOT);
		
		TreeBuffer cookedTree = new TreeBuffer(TreeModel.BASIC, 9, Content.Block.LOG_ARID, Blocks.air);
		cookedTree.setTreeHeight(5);
		cookedTree.setDecorationPass(DecorationPass.PASS_10);
		cookedTree.addSoil(Content.Block.COLD_GRASS);
		cookedTree.bindAllHumidities();
		cookedTree.bindHumidity(BiomeHumidity.ARID);
		cookedTree.bindHumidity(BiomeHumidity.DRY);
		cookedTree.bindTemperature(BiomeTemperature.SCORCHED);
		cookedTree.bindTemperature(BiomeTemperature.HOT);

		addScatterBuffer(mainShrub);
		addScatterBuffer(normalShrub);
		addScatterBuffer(dryShrub);
		addScatterBuffer(hotShrub);
		addScatterBuffer(burnedTree);
		addScatterBuffer(normalTree);
		addScatterBuffer(cookedTree);
		addScatterBuffer(choppedTree);
	}
}
