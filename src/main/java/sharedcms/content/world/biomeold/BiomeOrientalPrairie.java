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

public class BiomeOrientalPrairie extends AresBiome
{
	public BiomeOrientalPrairie(int id)
	{
		super(id);
	}

	@Override
	public PrecipitationType getPrecipitationType()
	{
		return super.getPrecipitationType();
	}
	
	@Override
	public List<Type> getBiomeTypes(List<Type> list)
	{
		list.add(Type.LUSH);
		list.add(Type.PLAINS);
		list.add(Type.WET);
		list.add(Type.MAGICAL);
		return list;
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
		return 1f;
	}

	@Override
	public float getHeightVariation()
	{
		return super.getHeightVariation() + 0.02f;
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
		return Content.BiomeDecorator.ORIENTAL;
	}

	@Override
	public WorldGenTrees getTreeGenerator()
	{
		return new WorldTessellatedTreeGen(Content.Block.LOG_BIRCH, Content.Block.LEAVES_CHERRY_BLOSSOM, false, 1, 0, 0, false, true);
	}

	@Override
	public WorldGenBigTree getBigTreeGenerator()
	{
		WorldGenBigTessellatedTreeGen w = new WorldGenBigTessellatedTreeGen(Content.Block.LOG_BIRCH, Content.Block.LEAVES_CHERRY_BLOSSOM, false, true, 120);
		w.branchDensity = 0.5;
		w.branchSlope = 0.1f;
		w.scaleWidth = 8f;
		w.leafDistanceLimit = 7;
		
		return w;
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
			if(i.isOriental())
			{
				f.add(new FlowerEntry(i, 0, i.getWeight()));
			}
		}

		return f;
	}
	
	@Override
	public int getFlowersPerChunk()
	{
		return 9;
	}
}
