package sharedcms.content.world.biome;

import java.awt.Color;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.common.BiomeDictionary.Type;
import sharedcms.base.AresBiome;
import sharedcms.content.Content;
import sharedcms.content.world.generator.WorldGenBigTessellatedTreeGen;
import sharedcms.content.world.generator.WorldTreeGenerator;

public class BiomeDesert extends AresBiome
{
	public BiomeDesert(int id)
	{
		super(id);
	}

	@Override
	public PrecipitationType getPrecipitationType()
	{
		return super.getPrecipitationType();
	}

	@Override
	public Block getTopBlock()
	{
		return Blocks.sand;
	}

	@Override
	public List<Type> getBiomeTypes(List<Type> list)
	{
		list.add(Type.DRY);
		list.add(Type.HOT);
		return list;
	}

	@Override
	public Block getFillerBlock()
	{
		return Blocks.sandstone;
	}

	@Override
	public float getRainfall()
	{
		return 0f;
	}

	@Override
	public float getTemperature()
	{
		return 2f;
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
		return Content.BiomeDecorator.DESERT;
	}

	@Override
	public WorldGenTrees getTreeGenerator()
	{
		return new WorldTreeGenerator(false, 5, 0, 0, false);
	}

	@Override
	public WorldGenBigTree getBigTreeGenerator()
	{
		return new WorldGenBigTessellatedTreeGen(Content.Block.LOG_OAK, Content.Block.LEAVES_OAK, false);
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
		return 0;
	}
}
