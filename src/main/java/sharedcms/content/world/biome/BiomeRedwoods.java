package sharedcms.content.world.biome;

import java.awt.Color;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.common.BiomeDictionary.Type;
import sharedcms.base.AresBiome;
import sharedcms.content.Content;
import sharedcms.content.world.generator.WorldGenBigTessellatedTreeGen;
import sharedcms.content.world.generator.WorldTessellatedTreeGen;

public class BiomeRedwoods extends AresBiome
{
	public BiomeRedwoods(int id)
	{
		super(id);
	}

	@Override
	public PrecipitationType getPrecipitationType()
	{
		return PrecipitationType.NONE;
	}

	@Override
	public List<Type> getBiomeTypes(List<Type> list)
	{
		list.add(Type.DRY);
		list.add(Type.COLD);
		list.add(Type.FOREST);
		list.add(Type.MOUNTAIN);
		return list;
	}

	@Override
	public Block getTopBlock()
	{
		return Content.Block.PODZOL;
	}

	@Override
	public Block getFillerBlock()
	{
		return Content.Block.ROUGH_DIRT;
	}

	@Override
	public float getRainfall()
	{
		return 1f;
	}

	@Override
	public float getTemperature()
	{
		return 1.25f;
	}

	@Override
	public float getHeightVariation()
	{
		return super.getHeightVariation() + 0.1f;
	}

	@Override
	public float getHeight()
	{
		return super.getHeight() + 0.3f;
	}

	@Override
	public int getColor()
	{
		return Color.red.getRGB();
	}

	@Override
	public BiomeDecorator getDecorator()
	{
		return Content.BiomeDecorator.REDWOODS;
	}

	@Override
	public WorldGenTrees getTreeGenerator()
	{
		return new WorldTessellatedTreeGen(Content.Block.LOG_REDWOOD, Content.Block.LEAVES_REDWOOD, false, 18, 0, 0, false);
	}

	@Override
	public WorldGenBigTree getBigTreeGenerator()
	{
		return new WorldGenBigTessellatedTreeGen(Content.Block.LOG_REDWOOD, Content.Block.LEAVES_REDWOOD, false);
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
		return 12;
	}
}
