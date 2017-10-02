package sharedcms.content.world.biome;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase.FlowerEntry;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.common.BiomeDictionary.Type;
import sharedcms.base.AresBiome;
import sharedcms.base.AresBlockShrub;
import sharedcms.content.Content;
import sharedcms.content.world.generator.WorldGenBigTessellatedTreeGen;
import sharedcms.content.world.generator.WorldTessellatedTreeGen;
import sharedcms.content.world.generator.WorldTreeGenerator;

public class BiomePlainsTrees extends AresBiome
{
	public BiomePlainsTrees(int id)
	{
		super(id);

		setColor(Color.red.getRGB());
	}
	
	@Override
	public List<Type> getBiomeTypes(List<Type> list)
	{
		list.add(Type.LUSH);
		list.add(Type.PLAINS);
		list.add(Type.FOREST);
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
		return super.getTopBlock();
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
		return 0.65f;
	}

	@Override
	public float getHeightVariation()
	{
		return super.getHeightVariation();
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
		return Content.BiomeDecorator.PLAINS_TREES;
	}

	@Override
	public WorldGenTrees getTreeGenerator()
	{
		return new WorldTessellatedTreeGen(Content.Block.LOG_BIRCH, Content.Block.LEAVES_OAK, false, 4, 0, 0, false);
	}

	@Override
	public WorldGenBigTree getBigTreeGenerator()
	{
		return new WorldGenBigTessellatedTreeGen(Content.Block.LOG_BIRCH, Content.Block.LEAVES_OAK, false, false, 1000);
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
	public int getFlowersPerChunk()
	{
		return 16;
	}
}