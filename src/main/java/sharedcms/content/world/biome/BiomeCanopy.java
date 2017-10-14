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
import sharedcms.content.world.buffer.surface.SplitSurfaceBuffer;
import sharedcms.content.world.meta.objects.MetaWorld;
import sharedcms.util.GEN;
import sharedcms.util.SimplexProperties;
import sharedcns.api.biome.BiomeHumidity;
import sharedcns.api.biome.BiomeTemperature;
import sharedcns.api.biome.DecorationPass;

public class BiomeCanopy extends LBiomeBase
{
	public BiomeCanopy(int level)
	{
		super(level);
		variation += 0.16;
		setBiomeHumidity(BiomeHumidity.DAMP);
		setBiomeTemperature(BiomeTemperature.CHILLY);

		SplitSurfaceBuffer buffer = new SplitSurfaceBuffer(Content.Block.PODZOL_MOSSY, Content.Block.COLD_DIRT, Content.Block.COLD_STONE);
		buffer.putTop(Content.Block.PODZOL, BiomeHumidity.ARID);
		buffer.putTop(Content.Block.PODZOL, BiomeHumidity.DRY);
		buffer.putTop(Content.Block.PODZOL, BiomeTemperature.SCORCHED);
		buffer.putTop(Content.Block.PODZOL, BiomeTemperature.HOT);
		buffer.putTop(Content.Block.PODZOL, BiomeTemperature.WARM);
		
		ShrubBuffer mainShrub = new ShrubBuffer(40);
		mainShrub.setDecorationPass(DecorationPass.PASS_12);
		mainShrub.addSoil(Content.Block.PODZOL_MOSSY);
		mainShrub.addSoil(Content.Block.PODZOL);
		mainShrub.addBlock(Content.Block.THIN_GRASS, 20);
		mainShrub.addBlock(Content.Block.TALL_GRASS, 20);
		mainShrub.addBlock(Content.Block.STUBBED_GRASS, 20);
		mainShrub.addBlock(Content.Block.SPOKED_GRASS, 20);
		mainShrub.bindAllHumidities();
		mainShrub.bindAllTemperatures();

		ShrubBuffer normalShrub = new ShrubBuffer(30);
		normalShrub.setDecorationPass(DecorationPass.PASS_12);
		normalShrub.addSoil(Content.Block.PODZOL_MOSSY);
		normalShrub.addSoil(Content.Block.PODZOL);

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
		hotShrub.addSoil(Content.Block.PODZOL_MOSSY);
		hotShrub.addSoil(Content.Block.PODZOL);
		hotShrub.addBlock(Content.Block.DEAD_BUSH, 20);
		hotShrub.addBlock(Content.Block.DEAD_TWIG, 20);
		hotShrub.bindHumidity(BiomeHumidity.ARID);
		hotShrub.bindHumidity(BiomeHumidity.DRY);
		hotShrub.bindTemperature(BiomeTemperature.HOT);
		hotShrub.bindTemperature(BiomeTemperature.SCORCHED);

		ShrubBuffer dryShrub = new ShrubBuffer(20);
		dryShrub.setDecorationPass(DecorationPass.PASS_12);
		dryShrub.addSoil(Content.Block.PODZOL_MOSSY);
		dryShrub.addSoil(Content.Block.PODZOL);
		dryShrub.addBlock(Content.Block.DEAD_BUSH, 20);
		dryShrub.addBlock(Content.Block.DEAD_TWIG, 20);
		dryShrub.bindAllHumidities();
		dryShrub.unbindHumidity(BiomeHumidity.DAMP);
		dryShrub.unbindHumidity(BiomeHumidity.MOIST);
		dryShrub.bindTemperature(BiomeTemperature.HOT);
		dryShrub.bindTemperature(BiomeTemperature.WARM);

		TreeBuffer normalTree = new TreeBuffer(TreeModel.CANOPY, 26, Content.Block.LOG_MOSSY, Content.Block.LEAVES_OAK)
		{
			@Override
			public void updateForGen(World w, int x, int z)
			{
				super.updateForGen(w, x, z);
				
				int dist = distanceToBorder(w, x, z, 67);
				setTreeHeight((int) (10 + GEN.getNoise("canopy-height", x, z, 6, 0) + (dist / 3)));
			}
		};
		normalTree.setTreeHeight(10);
		normalTree.setDecorationPass(DecorationPass.PASS_10);
		normalTree.addSoil(Content.Block.PODZOL_MOSSY);
		normalTree.addSoil(Content.Block.PODZOL);
		normalTree.bindAllHumidities();
		normalTree.bindAllTemperatures();
		normalTree.unbindHumidity(BiomeHumidity.ARID);
		normalTree.unbindHumidity(BiomeHumidity.DRY);
		normalTree.unbindTemperature(BiomeTemperature.SCORCHED);
		normalTree.unbindTemperature(BiomeTemperature.HOT);

		TreeBuffer superTree = new TreeBuffer(TreeModel.CANOPY, 9, Content.Block.LOG_MOSSY, Content.Block.LEAVES_OAK)
		{
			@Override
			public void updateForGen(World w, int x, int z)
			{
				super.updateForGen(w, x, z);
				
				int dist = distanceToBorder(w, x, z, 67);
				setTreeHeight((int) (10 + GEN.getNoise("canopy-height", x, z, 6, 0) + (dist / 3)));
			}
		};
		superTree.setTreeHeight(23);
		superTree.setDecorationPass(DecorationPass.PASS_11);
		superTree.addSoil(Content.Block.PODZOL_MOSSY);
		superTree.addSoil(Content.Block.PODZOL);
		superTree.bindAllHumidities();
		superTree.bindAllTemperatures();
		superTree.unbindHumidity(BiomeHumidity.ARID);
		superTree.unbindHumidity(BiomeHumidity.DRY);
		superTree.unbindTemperature(BiomeTemperature.SCORCHED);
		superTree.unbindTemperature(BiomeTemperature.HOT);
		superTree.setWbonus(4);
		superTree.setWrbonus(1);

		TreeBuffer choppedTree = new TreeBuffer(TreeModel.CANOPY, 4, Content.Block.LOG_MOSSY, Blocks.air);
		choppedTree.setTreeHeight(5);
		choppedTree.setDecorationPass(DecorationPass.PASS_10);
		choppedTree.addSoil(Content.Block.PODZOL_MOSSY);
		choppedTree.addSoil(Content.Block.PODZOL);
		choppedTree.bindAllHumidities();
		choppedTree.bindAllTemperatures();

		TreeBuffer burnedTree = new TreeBuffer(TreeModel.CANOPY, 13, Content.Block.LOG_MOSSY, Blocks.air);
		burnedTree.setTreeHeight(12);
		burnedTree.setDecorationPass(DecorationPass.PASS_10);
		burnedTree.addSoil(Content.Block.PODZOL_MOSSY);
		burnedTree.addSoil(Content.Block.PODZOL);
		burnedTree.bindHumidity(BiomeHumidity.ARID);
		burnedTree.bindHumidity(BiomeHumidity.DRY);
		burnedTree.bindTemperature(BiomeTemperature.SCORCHED);
		burnedTree.bindTemperature(BiomeTemperature.HOT);

		TreeBuffer cookedTree = new TreeBuffer(TreeModel.CANOPY, 9, Content.Block.LOG_DARK, Blocks.air);
		cookedTree.setTreeHeight(7);
		cookedTree.setDecorationPass(DecorationPass.PASS_10);
		cookedTree.addSoil(Content.Block.PODZOL_MOSSY);
		cookedTree.addSoil(Content.Block.PODZOL);
		cookedTree.bindAllHumidities();
		cookedTree.bindTemperature(BiomeTemperature.SCORCHED);
		cookedTree.bindTemperature(BiomeTemperature.HOT);
		
		setSurfaceBuffer(buffer);
		addScatterBuffer(superTree);
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
