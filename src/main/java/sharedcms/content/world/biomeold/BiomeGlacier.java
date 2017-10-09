package sharedcms.content.world.biomeold;

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

public class BiomeGlacier extends AresBiome
{
	public BiomeGlacier(int id)
	{
		super(id);
	}
	
	@Override
	public List<Type> getBiomeTypes(List<Type> list)
	{
		list.add(Type.COLD);
		list.add(Type.MOUNTAIN);
		return list;
	}

	@Override
	public PrecipitationType getPrecipitationType()
	{
		return PrecipitationType.SNOW;
	}

	@Override
	public Block getTopBlock()
	{
		return Content.Block.GLACIAL_GRASS;
	}

	@Override
	public Block getFillerBlock()
	{
		return Content.Block.GLACIAL_DIRT;
	}

	@Override
	public float getRainfall()
	{
		return 1f;
	}

	@Override
	public float getTemperature()
	{
		return 0f;
	}

	@Override
	public float getHeightVariation()
	{
		return super.getHeightVariation() + 0.07f;
	}

	@Override
	public float getHeight()
	{
		return super.getHeight() + 0.7f;
	}

	@Override
	public int getColor()
	{
		return Color.red.getRGB();
	}

	@Override
	public BiomeDecorator getDecorator()
	{
		return Content.BiomeDecorator.GLACIER;
	}

	@Override
	public WorldGenTrees getTreeGenerator()
	{
		return new WorldTessellatedTreeGen(Content.Block.LOG_GLACIAL, Content.Block.LEAVES_GLACIAL, false, 5, 0, 0, false);
	}

	@Override
	public WorldGenBigTree getBigTreeGenerator()
	{
		return new WorldGenBigTessellatedTreeGen(Content.Block.LOG_GLACIAL, Content.Block.LEAVES_GLACIAL, false);
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
		List<FlowerEntry> f = new ArrayList<FlowerEntry>();
		f.clear();

		for(AresBlockShrub i : Content.flowers())
		{
			if(i.isGlacial())
			{
				f.add(new FlowerEntry(i, 0, i.getWeight()));
			}
		}

		return f;
	}

	@Override
	public int getFlowersPerChunk()
	{
		return 3;
	}
}
