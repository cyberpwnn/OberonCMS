package sharedcms.base;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.terraingen.DeferredBiomeDecorator;
import sharedcms.content.Content;
import sharedcms.util.GList;

public abstract class AresBiome extends BiomeGenBase
{
	public enum PrecipitationType
	{
		RAIN,
		SNOW,
		BOTH,
		NONE;
	}

	public AresBiome(int id)
	{
		super(id);
	}

	public void postInit()
	{
		topBlock = getTopBlock();
		fillerBlock = getFillerBlock();
		rootHeight = getHeight();
		setColor(getColor());
		heightVariation = getHeightVariation();
		temperature = getTemperature();
		rainfall = getPrecipitationType().equals(PrecipitationType.NONE) ? 0f : getRainfall();
		waterColorMultiplier = getWaterColor();
		enableRain = getPrecipitationType().equals(PrecipitationType.NONE) ? false : (getPrecipitationType().equals(PrecipitationType.RAIN) || getPrecipitationType().equals(PrecipitationType.BOTH));
		enableSnow = getPrecipitationType().equals(PrecipitationType.NONE) ? false : (getPrecipitationType().equals(PrecipitationType.SNOW) || getPrecipitationType().equals(PrecipitationType.BOTH));
		theBiomeDecorator = getDecorator();
		worldGeneratorTrees = getTreeGenerator();
		worldGeneratorBigTree = getBigTreeGenerator();
		spawnableCreatureList = getCreatureSpawnList();
		spawnableMonsterList = getMonsterSpawnList();
		spawnableCaveCreatureList = getCaveSpawnList();
		spawnableWaterCreatureList = getWaterSpawnList();
		flowers = getFlowers();
		List<Type> t = getBiomeTypes(new GList<Type>());
		Type[] k = t.toArray(new Type[t.size()]);
		BiomeDictionary.registerBiomeType(this, k);
	}

	public List<Type> getBiomeTypes(List<Type> list)
	{
		return list;
	}

	public PrecipitationType getPrecipitationType()
	{
		return PrecipitationType.BOTH;
	}

	public Block getTopBlock()
	{
		return Content.Block.COLD_GRASS;
	}

	public Block getFillerBlock()
	{
		return Content.Block.COLD_DIRT;
	}

	public int getWaterColor()
	{
		return 16777215;
	}

	public float getRainfall()
	{
		return 0.5f;
	}

	public float getTemperature()
	{
		return 0.5f;
	}

	public float getHeightVariation()
	{
		return 0.2f;
	}

	public float getHeight()
	{
		return 0.1f;
	}

	public int getColor()
	{
		return 9286496;
	}

	public BiomeDecorator getDecorator()
	{
		return new DeferredBiomeDecorator(new BiomeDecorator());
	}

	public WorldGenTrees getTreeGenerator()
	{
		return new WorldGenTrees(false);
	}

	public WorldGenBigTree getBigTreeGenerator()
	{
		return new WorldGenBigTree(false);
	}

	public List<SpawnListEntry> getCreatureSpawnList()
	{
		List<SpawnListEntry> l = new ArrayList<SpawnListEntry>();
		l.add(new BiomeGenBase.SpawnListEntry(EntitySheep.class, 12, 4, 4));
		l.add(new BiomeGenBase.SpawnListEntry(EntityPig.class, 10, 4, 4));
		l.add(new BiomeGenBase.SpawnListEntry(EntityChicken.class, 10, 4, 4));
		l.add(new BiomeGenBase.SpawnListEntry(EntityCow.class, 8, 4, 4));

		return l;
	}

	public List<SpawnListEntry> getMonsterSpawnList()
	{
		List<SpawnListEntry> l = new ArrayList<SpawnListEntry>();
		l.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 100, 4, 4));
		l.add(new BiomeGenBase.SpawnListEntry(EntityZombie.class, 100, 4, 4));
		l.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
		l.add(new BiomeGenBase.SpawnListEntry(EntityCreeper.class, 100, 4, 4));
		l.add(new BiomeGenBase.SpawnListEntry(EntitySlime.class, 100, 4, 4));
		l.add(new BiomeGenBase.SpawnListEntry(EntityEnderman.class, 10, 1, 4));
		l.add(new BiomeGenBase.SpawnListEntry(EntityWitch.class, 5, 1, 1));

		return l;
	}

	public List<SpawnListEntry> getWaterSpawnList()
	{
		List<SpawnListEntry> l = new ArrayList<SpawnListEntry>();
		l.add(new BiomeGenBase.SpawnListEntry(EntitySquid.class, 10, 4, 4));

		return l;
	}

	public List<SpawnListEntry> getCaveSpawnList()
	{
		List<SpawnListEntry> l = new ArrayList<SpawnListEntry>();
		l.add(new BiomeGenBase.SpawnListEntry(EntityBat.class, 10, 8, 8));

		return l;
	}

	public List<FlowerEntry> getFlowers()
	{
		List<FlowerEntry> f = new ArrayList<FlowerEntry>();
		f.clear();

		for(AresBlockShrub i : Content.flowers())
		{
			f.add(new FlowerEntry(i, 0, i.getWeight()));
		}

		return f;
	}

	public List<Block> getProcessedFlowers()
	{
		List<Block> f = new ArrayList<Block>();

		for(FlowerEntry i : getFlowers())
		{
			for(int j = 0; j < i.itemWeight; j++)
			{
				f.add(i.block);
			}
		}

		return f;
	}

	public int getFlowersPerChunk()
	{
		return 0;
	}
}
