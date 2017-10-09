package sharedcms.content.world.biomeold;

import java.awt.Color;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.common.BiomeDictionary.Type;
import sharedcms.base.AresBiome;
import sharedcms.content.Content;
import sharedcms.content.world.generator.ProxyBigTreeGenerator;
import sharedcms.content.world.generator.WorldGenBigTessellatedTreeGen;
import sharedcms.content.world.generator.WorldGenCustomTree;
import sharedcms.content.world.generator.WorldTessellatedTreeGen;
import sharedcms.content.world.generator.WorldTreeGenTops;

public class BiomeRoofedForest extends AresBiome
{
	public BiomeRoofedForest(int id)
	{
		super(id);
	}
	
	@Override
	public List<Type> getBiomeTypes(List<Type> list)
	{
		list.add(Type.DENSE);
		list.add(Type.FOREST);
		list.add(Type.CONIFEROUS);
		return list;
	}

	@Override
	public PrecipitationType getPrecipitationType()
	{
		return super.getPrecipitationType();
	}

	@Override
	public Block getTopBlock()
	{
		return Content.Block.PODZOL_MOSSY;
	}

	@Override
	public Block getFillerBlock()
	{
		return super.getFillerBlock();
	}

	@Override
	public float getRainfall()
	{
		return 1f;
	}

	@Override
	public float getTemperature()
	{
		return super.getTemperature();
	}

	@Override
	public float getHeightVariation()
	{
		return super.getHeightVariation() + 0.1f;
	}

	@Override
	public float getHeight()
	{
		return super.getHeight();
	}

	@Override
	public int getColor()
	{
		return Color.red.getRGB();
	}

	@Override
	public BiomeDecorator getDecorator()
	{
		return Content.BiomeDecorator.ROOFED_FOREST;
	}

	@Override
	public WorldGenTrees getTreeGenerator()
	{
		return new WorldTreeGenTops(Content.Block.LOG_MOSSY, Content.Block.LEAVES_DARK);
	}

	@Override
	public WorldGenBigTree getBigTreeGenerator()
	{
		return new WorldGenCustomTree(Content.Block.LOG_MOSSY, Content.Block.LEAVES_OAK, false);
	}

	@Override
	public List<SpawnListEntry> getCreatureSpawnList()
	{
		return super.getCreatureSpawnList();
	}

	@Override
	public List<SpawnListEntry> getMonsterSpawnList()
	{
		return super.getMonsterSpawnList();
	}

	@Override
	public List<SpawnListEntry> getWaterSpawnList()
	{
		return super.getWaterSpawnList();
	}

	@Override
	public List<SpawnListEntry> getCaveSpawnList()
	{
		return super.getCaveSpawnList();
	}

	@Override
	public List<FlowerEntry> getFlowers()
	{
		return super.getFlowers();
	}
	
	@Override
	public int getFlowersPerChunk()
	{
		return 9;
	}
}
