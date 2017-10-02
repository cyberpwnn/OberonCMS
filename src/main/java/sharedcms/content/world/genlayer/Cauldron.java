package sharedcms.content.world.genlayer;

import java.util.concurrent.Callable;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerShore;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;

public abstract class Cauldron extends GenLayer
{
	private long worldGenSeed;
	private long chunkSeed;

	public Cauldron(long seed)
	{
		super(seed);
	}

	public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType worldType, byte biomeSize)
	{
		boolean flag = false;
		CauldronIsland genlayerisland = new CauldronIsland(1L);
		CauldronFuzzyZoom genlayerfuzzyzoom = new CauldronFuzzyZoom(2000L, genlayerisland);
		CauldronAddIsland genlayeraddisland = new CauldronAddIsland(1L, genlayerfuzzyzoom);
		CauldronZoom genlayerzoom = new CauldronZoom(2001L, genlayeraddisland);
		genlayeraddisland = new CauldronAddIsland(2L, genlayerzoom);
		genlayeraddisland = new CauldronAddIsland(50L, genlayeraddisland);
		genlayeraddisland = new CauldronAddIsland(70L, genlayeraddisland);
		CauldronClipOcean genlayerremovetoomuchocean = new CauldronClipOcean(2L, genlayeraddisland);
		CauldronAddSnow genlayeraddsnow = new CauldronAddSnow(2L, genlayerremovetoomuchocean);
		genlayeraddisland = new CauldronAddIsland(3L, genlayeraddsnow);
		CauldronEdge genlayeredge = new CauldronEdge(2L, genlayeraddisland, CauldronEdge.Mode.COOL_WARM);
		genlayeredge = new CauldronEdge(2L, genlayeredge, CauldronEdge.Mode.HEAT_ICE);
		genlayeredge = new CauldronEdge(3L, genlayeredge, CauldronEdge.Mode.SPECIAL);
		genlayerzoom = new CauldronZoom(2002L, genlayeredge);
		genlayerzoom = new CauldronZoom(2003L, genlayerzoom);
		genlayeraddisland = new CauldronAddIsland(4L, genlayerzoom);
		CauldronDeepOecan genlayerdeepocean = new CauldronDeepOecan(4L, genlayeraddisland);
		Cauldron genlayer2 = CauldronZoom.magnify(1000L, genlayerdeepocean, 0);
		Cauldron genlayer = CauldronZoom.magnify(1000L, genlayer2, 0);
		CauldronRiverInit genlayerriverinit = new CauldronRiverInit(100L, genlayer);
		Object object = worldType.getBiomeLayer(seed, genlayer2);
		Cauldron genlayer1 = CauldronZoom.magnify(1000L, genlayerriverinit, 2);
		CauldronHills genlayerhills = new CauldronHills(1000L, (GenLayer) object, genlayer1);
		genlayer = CauldronZoom.magnify(1000L, genlayerriverinit, 2);
		genlayer = CauldronZoom.magnify(1000L, genlayer, biomeSize);
		CauldronRiver genlayerriver = new CauldronRiver(1L, genlayer);
		CauldronSmooth genlayersmooth = new CauldronSmooth(1000L, genlayerriver);
		object = new CauldronRare(1001L, genlayerhills);

		for(int j = 0; j < biomeSize; ++j)
		{
			object = new CauldronZoom((long) (1000 + j), (GenLayer) object);

			if(j == 0)
			{
				object = new CauldronAddIsland(3L, (GenLayer) object);
			}

			if(j == 1)
			{
				object = new CauldronShore(1000L, (GenLayer) object);
			}
		}

		CauldronSmooth genlayersmooth1 = new CauldronSmooth(1000L, (GenLayer) object);
		CauldronRiverMix genlayerrivermix = new CauldronRiverMix(100L, genlayersmooth1, genlayersmooth);
		CauldronVoronoiZoom genlayervoronoizoom = new CauldronVoronoiZoom(10L, genlayerrivermix);
		genlayerrivermix.initWorldGenSeed(seed);
		genlayervoronoizoom.initWorldGenSeed(seed);
		return new GenLayer[] {genlayerrivermix, genlayervoronoizoom, genlayerrivermix};
	}

	public void initWorldGenSeed(long seed)
	{
		this.worldGenSeed = seed;

		if(this.parent != null)
		{
			this.parent.initWorldGenSeed(seed);
		}

		this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
		this.worldGenSeed += this.baseSeed;
		this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
		this.worldGenSeed += this.baseSeed;
		this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
		this.worldGenSeed += this.baseSeed;
	}

	public void initChunkSeed(long seed, long mod)
	{
		this.chunkSeed = this.worldGenSeed;
		this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
		this.chunkSeed += seed;
		this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
		this.chunkSeed += mod;
		this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
		this.chunkSeed += seed;
		this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
		this.chunkSeed += mod;
	}

	protected int nextInt(int n)
	{
		int j = (int) ((this.chunkSeed >> 24) % (long) n);

		if(j < 0)
		{
			j += n;
		}

		this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
		this.chunkSeed += this.worldGenSeed;
		return j;
	}

	public abstract int[] getInts(int par1, int par2, int par3, int par4);

	protected static boolean compareBiomesById(final int par1, final int par2)
	{
		if(par1 == par2)
		{
			return true;
		}

		else if(par1 != BiomeGenBase.mesaPlateau_F.biomeID && par1 != BiomeGenBase.mesaPlateau.biomeID)
		{
			try
			{
				return BiomeGenBase.getBiome(par1) != null && BiomeGenBase.getBiome(par2) != null ? BiomeGenBase.getBiome(par1).isEqualTo(BiomeGenBase.getBiome(par2)) : false;
			}

			catch(Throwable throwable)
			{
				CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Comparing biomes");
				CrashReportCategory crashreportcategory = crashreport.makeCategory("Biomes being compared");
				crashreportcategory.addCrashSection("Biome A ID", Integer.valueOf(par1));
				crashreportcategory.addCrashSection("Biome B ID", Integer.valueOf(par2));
				crashreportcategory.addCrashSectionCallable("Biome A", new Callable()
				{
					public String call()
					{
						return String.valueOf(BiomeGenBase.getBiome(par1));
					}
				});

				crashreportcategory.addCrashSectionCallable("Biome B", new Callable()
				{
					public String call()
					{
						return String.valueOf(BiomeGenBase.getBiome(par2));
					}
				});

				throw new ReportedException(crashreport);
			}
		}

		else
		{
			return par2 == BiomeGenBase.mesaPlateau_F.biomeID || par2 == BiomeGenBase.mesaPlateau.biomeID;
		}
	}

	protected static boolean isBiomeOceanic(int id)
	{
		return BiomeManager.oceanBiomes.contains(BiomeGenBase.getBiome(id));
	}

	protected int selectRandom(int... ints)
	{
		return ints[this.nextInt(ints.length)];
	}

	protected int selectModeOrRandom(int par1, int par2, int par3, int par4)
	{
		return par2 == par3 && par3 == par4 ? par2 : (par1 == par2 && par1 == par3 ? par1 : (par1 == par2 && par1 == par4 ? par1 : (par1 == par3 && par1 == par4 ? par1 : (par1 == par2 && par3 != par4 ? par1 : (par1 == par3 && par2 != par4 ? par1 : (par1 == par4 && par2 != par3 ? par1 : (par2 == par3 && par1 != par4 ? par2 : (par2 == par4 && par1 != par3 ? par2 : (par3 == par4 && par1 != par2 ? par3 : this.selectRandom(new int[] {par1, par2, par3, par4}))))))))));
	}

	protected long nextLong(long par1)
	{
		long j = (this.chunkSeed >> 24) % par1;

		if(j < 0)
		{
			j += par1;
		}

		this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
		this.chunkSeed += this.worldGenSeed;
		return j;
	}

	public static byte getModdedBiomeSize(WorldType worldType, byte original)
	{
		WorldTypeEvent.BiomeSize event = new WorldTypeEvent.BiomeSize(worldType, original);
		MinecraftForge.TERRAIN_GEN_BUS.post(event);
		return event.newSize;
	}
}