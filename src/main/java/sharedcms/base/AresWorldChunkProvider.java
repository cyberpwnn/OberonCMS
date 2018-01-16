package sharedcms.base;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.MINESHAFT;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.RAVINE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.SCATTERED_FEATURE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.STRONGHOLD;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.VILLAGE;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import sharedcms.Status;
import sharedcms.content.Content;
import sharedcms.content.world.generator.SelectorGenerator;
import sharedcms.content.world.meta.objects.MetaWorld;
import sharedcms.controller.shared.WorldHostController;
import sharedcms.gui.util.R;
import sharedcms.util.GEN;
import sharedcms.util.GList;
import sharedcms.util.M;
import sharedcns.api.biome.BiomeHumidity;
import sharedcns.api.biome.BiomeHumidityModifier;
import sharedcns.api.biome.BiomeTemperature;
import sharedcns.api.biome.BiomeTemperatureModifier;
import sharedcns.api.biome.DecorationPass;
import sharedcns.api.biome.IBiome;
import sharedcns.api.biome.IScatterBuffer;
import sharedcns.api.biome.LudicrousScatterBuffer;

public class AresWorldChunkProvider implements IChunkProvider
{
	private Random rand;
	private NoiseGeneratorOctaves noiseOct1;
	private NoiseGeneratorOctaves noiseOct2;
	private NoiseGeneratorOctaves noiseOct3;
	public NoiseGeneratorOctaves noiseOct4;
	public NoiseGeneratorOctaves noiseGen5;
	public NoiseGeneratorOctaves noiseGen6;
	private NoiseGeneratorPerlin noisePerlin1;
	private World worldObj;
	private final boolean mapFeaturesEnabled;
	private WorldType type;
	public boolean featureLakeLava = true;
	public boolean featureLakeWater = true;
	public boolean featureDungeon = true;
	public boolean featureSpawns = true;
	public boolean featureFrosty = true;
	public boolean featureCaves = true;
	public boolean featureRavines = true;
	private final double[] doubleSet;
	private final float[] parabolicField;
	private double[] stoneNoise = new double[256];
	private MapGenBase caveGenerator = new MapGenCaves();
	private MapGenStronghold strongholdGenerator = new MapGenStronghold();
	private MapGenVillage villageGenerator = new MapGenVillage();
	private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
	private MapGenScatteredFeature scatteredFeatureGenerator = new MapGenScatteredFeature();
	private MapGenBase ravineGenerator = new MapGenRavine();
	private BiomeGenBase[] biomesForGeneration;
	double[] noise1;
	double[] noise2;
	double[] noise3;
	double[] noise4;
	int[][] field_73219_j = new int[32][32];
	private SelectorGenerator sel;

	private static final String __OBFID = "CL_00000396";
	{
		caveGenerator = TerrainGen.getModdedMapGen(caveGenerator, CAVE);
		strongholdGenerator = (MapGenStronghold) TerrainGen.getModdedMapGen(strongholdGenerator, STRONGHOLD);
		villageGenerator = (MapGenVillage) TerrainGen.getModdedMapGen(villageGenerator, VILLAGE);
		mineshaftGenerator = (MapGenMineshaft) TerrainGen.getModdedMapGen(mineshaftGenerator, MINESHAFT);
		scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(scatteredFeatureGenerator, SCATTERED_FEATURE);
		ravineGenerator = TerrainGen.getModdedMapGen(ravineGenerator, RAVINE);
	}

	public AresWorldChunkProvider(World p_i2006_1_, long p_i2006_2_, boolean p_i2006_4_)
	{
		this.worldObj = p_i2006_1_;
		this.mapFeaturesEnabled = p_i2006_4_;
		this.type = p_i2006_1_.getWorldInfo().getTerrainType();
		this.rand = new Random(p_i2006_2_);
		this.noiseOct1 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseOct2 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseOct3 = new NoiseGeneratorOctaves(this.rand, 8);
		this.noisePerlin1 = new NoiseGeneratorPerlin(this.rand, 4);
		this.noiseOct4 = new NoiseGeneratorOctaves(this.rand, 10);
		this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 8);
		this.doubleSet = new double[825];
		this.parabolicField = new float[25];
		sel = new SelectorGenerator(0, p_i2006_2_);

		for(int j = -2; j <= 2; ++j)
		{
			for(int k = -2; k <= 2; ++k)
			{
				float f = 10.0F / MathHelper.sqrt_float((float) (j * j + k * k) + 0.2F);
				this.parabolicField[j + 2 + (k + 2) * 5] = f;
			}
		}

		NoiseGenerator[] noiseGens = {noiseOct1, noiseOct2, noiseOct3, noisePerlin1, noiseOct4, noiseGen6, noiseGen5};
		noiseGens = TerrainGen.getModdedNoiseGenerators(p_i2006_1_, this.rand, noiseGens);
		this.noiseOct1 = (NoiseGeneratorOctaves) noiseGens[0];
		this.noiseOct2 = (NoiseGeneratorOctaves) noiseGens[1];
		this.noiseOct3 = (NoiseGeneratorOctaves) noiseGens[2];
		this.noisePerlin1 = (NoiseGeneratorPerlin) noiseGens[3];
		this.noiseOct4 = (NoiseGeneratorOctaves) noiseGens[4];
		this.noiseGen6 = (NoiseGeneratorOctaves) noiseGens[5];
		this.noiseGen5 = (NoiseGeneratorOctaves) noiseGens[6];
		List<BiomeGenBase> biomes = new GList<BiomeGenBase>();

		for(AresBiome i : Content.biomes())
		{
			biomes.add(i);
		}

		biomesForGeneration = biomes.toArray(new BiomeGenBase[biomes.size()]);
	}

	public void generateChunk(int x, int z, Block[] blocks)
	{
		byte waterHeight = 63;
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
		this.func_147423_a(x * 4, 0, z * 4);
		for(int k = 0; k < 4; ++k)
		{
			int l = k * 5;
			int i1 = (k + 1) * 5;

			for(int j1 = 0; j1 < 4; ++j1)
			{
				int k1 = (l + j1) * 33;
				int l1 = (l + j1 + 1) * 33;
				int i2 = (i1 + j1) * 33;
				int j2 = (i1 + j1 + 1) * 33;

				for(int k2 = 0; k2 < 32; ++k2)
				{
					double d0 = 0.125D;
					double d1 = this.doubleSet[k1 + k2];
					double d2 = this.doubleSet[l1 + k2];
					double d3 = this.doubleSet[i2 + k2];
					double d4 = this.doubleSet[j2 + k2];
					double d5 = (this.doubleSet[k1 + k2 + 1] - d1) * d0;
					double d6 = (this.doubleSet[l1 + k2 + 1] - d2) * d0;
					double d7 = (this.doubleSet[i2 + k2 + 1] - d3) * d0;
					double d8 = (this.doubleSet[j2 + k2 + 1] - d4) * d0;

					for(int l2 = 0; l2 < 8; ++l2)
					{
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;

						for(int i3 = 0; i3 < 4; ++i3)
						{
							int j3 = i3 + k * 4 << 12 | 0 + j1 * 4 << 8 | k2 * 8 + l2;
							short short1 = 256;
							j3 -= short1;
							double d14 = 0.25D;
							double d16 = (d11 - d10) * d14;
							double d15 = d10 - d16;

							for(int k3 = 0; k3 < 4; ++k3)
							{
								if((d15 += d16) > 0.0D)
								{
									Block b = Blocks.stone;

									blocks[j3 += short1] = b;
								}
								else if(k2 * 8 + l2 < waterHeight)
								{
									blocks[j3 += short1] = Blocks.water;
								}

								else
								{
									blocks[j3 += short1] = null;
								}
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}
	}

	public void replaceBlocksForBiome(int x, int z, Block[] blocks, byte[] bytes, BiomeGenBase[] biomes)
	{
		double d0 = 0.03125D;
		this.stoneNoise = this.noisePerlin1.func_151599_a(this.stoneNoise, (double) (x * 16), (double) (z * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

		for(int k = 0; k < 16; ++k)
		{
			for(int l = 0; l < 16; ++l)
			{
				BiomeGenBase biomegenbase = biomes[l + k * 16];
				biomegenbase.genTerrainBlocks(this.worldObj, this.rand, blocks, bytes, x * 16 + k, z * 16 + l, this.stoneNoise[l + k * 16]);
			}
		}
	}

	public Chunk loadChunk(int x, int z)
	{
		return this.provideChunk(x, z);
	}

	public Chunk provideChunk(int x, int z)
	{
		long ns = M.ms();
		this.rand.setSeed((long) x * 341873128712L + (long) z * 132897987541L);
		Block[] ablock = new Block[65536];
		byte[] abyte = new byte[65536];
		this.generateChunk(x, z, ablock);
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, x * 16, z * 16, 16, 16);
		this.replaceBlocksForBiome(x, z, ablock, abyte, this.biomesForGeneration);
		Chunk chunk = new Chunk(this.worldObj, ablock, abyte, x, z);
		byte[] abyte1 = chunk.getBiomeArray();

		for(int k = 0; k < abyte1.length; ++k)
		{
			abyte1[k] = (byte) this.biomesForGeneration[k].biomeID;
		}

		chunk.generateSkylightMap();
		long time = M.ms() - ns;
		Status.CHUNK_GEN_TIME = time;

		return chunk;
	}

	private void func_147423_a(int p_147423_1_, int p_147423_2_, int p_147423_3_)
	{
		double d0 = 684.412D;
		double d1 = 684.412D;
		double d2 = 512.0D;
		double d3 = 512.0D;
		this.noise4 = this.noiseGen6.generateNoiseOctaves(this.noise4, p_147423_1_, p_147423_3_, 5, 5, 200.0D, 200.0D, 0.5D);
		this.noise1 = this.noiseOct3.generateNoiseOctaves(this.noise1, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, 8.555150000000001D, 4.277575000000001D, 8.555150000000001D);
		this.noise2 = this.noiseOct1.generateNoiseOctaves(this.noise2, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, 684.412D, 684.412D, 684.412D);
		this.noise3 = this.noiseOct2.generateNoiseOctaves(this.noise3, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, 684.412D, 684.412D, 684.412D);
		boolean flag1 = false;
		boolean flag = false;
		int l = 0;
		int i1 = 0;
		double d4 = 8.5D;

		for(int j1 = 0; j1 < 5; ++j1)
		{
			for(int k1 = 0; k1 < 5; ++k1)
			{
				float f = 0.0F;
				float f1 = 0.0F;
				float f2 = 0.0F;
				byte b0 = 2;
				BiomeGenBase biomegenbase = this.biomesForGeneration[j1 + 2 + (k1 + 2) * 10];

				for(int l1 = -b0; l1 <= b0; ++l1)
				{
					for(int i2 = -b0; i2 <= b0; ++i2)
					{
						BiomeGenBase biomegenbase1 = this.biomesForGeneration[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
						float f3 = biomegenbase1.rootHeight;
						float f4 = biomegenbase1.heightVariation;

						if(this.type == WorldType.AMPLIFIED && f3 > 0.0F)
						{
							f3 = 1.0F + f3 * 2.0F;
							f4 = 1.0F + f4 * 4.0F;
						}

						float f5 = this.parabolicField[l1 + 2 + (i2 + 2) * 5] / (f3 + 2.0F);

						if(biomegenbase1.rootHeight > biomegenbase.rootHeight)
						{
							f5 /= 2.0F;
						}

						f += f4 * f5;
						f1 += f3 * f5;
						f2 += f5;
					}
				}

				f /= f2;
				f1 /= f2;
				f = f * 0.9F + 0.1F;
				f1 = (f1 * 4.0F - 1.0F) / 8.0F;

				double d12 = (this.noise4[i1]) / 8000.0D;

				if(d12 < 0.0D)
				{
					d12 = -d12 * 0.3D;
				}

				d12 = d12 * 3.0D - 2.0D;

				if(d12 < 0.0D)
				{
					d12 /= 2.0D;

					if(d12 < -1.0D)
					{
						d12 = -1.0D;
					}

					d12 /= 1.4D;
					d12 /= 2.0D;
				}
				else
				{
					if(d12 > 1.0D)
					{
						d12 = 1.0D;
					}

					d12 /= 8.0D;
				}

				++i1;
				double d13 = (double) f1;
				double d14 = (double) f;
				d13 += d12 * 0.2D;
				d13 = d13 * 8.5D / 8.0D;
				double d5 = 8.5D + d13 * 4.0D;

				for(int j2 = 0; j2 < 33; ++j2)
				{
					double d6 = ((double) j2 - d5) * 12.0D * 128.0D / 256.0D / d14;

					if(d6 < 0.0D)
					{
						d6 *= 4.0D;
					}

					double d7 = this.noise2[l] / 512.0D;
					double d8 = this.noise3[l] / 512.0D;
					double d9 = (this.noise1[l] / 10D + 1.0D) / 2.0D;
					double d10 = MathHelper.denormalizeClamp(d7, d8, d9) - d6;

					if(j2 > 29)
					{
						double d11 = (double) ((float) (j2 - 29) / 3.0F);
						d10 = d10 * (1.0D - d11) + -10.0D * d11;
					}

					this.doubleSet[l] = d10;
					++l;
				}
			}
		}
	}

	public boolean chunkExists(int p_73149_1_, int p_73149_2_)
	{
		return true;
	}

	public void populate(IChunkProvider provider, int x, int z)
	{
		BlockFalling.fallInstantly = true;
		int k = x * 16;
		int l = z * 16;
		int k1;
		int l1;
		int i2;
		BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(k + 16, l + 16);
		this.rand.setSeed(this.worldObj.getSeed());
		long i1 = this.rand.nextLong() / 2L * 2L + 1L;
		long j1 = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed((long) x * i1 + (long) z * j1 ^ this.worldObj.getSeed());
		boolean flag = false;

		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(provider, worldObj, rand, x, z, flag));

		for(DecorationPass pass : DecorationPass.values())
		{
			if(biomegenbase instanceof IBiome)
			{
				IBiome b = (IBiome) biomegenbase;

				for(IScatterBuffer i : b.getScatterBuffers())
				{
					if(!i.getDecorationPass().equals(pass))
					{
						continue;
					}

					double rx = rand.nextDouble() * i.getChance();

					for(k1 = 0; k1 < (double) i.getStrength() * rx; k1++)
					{
						World w = worldObj;
						Random r = rand;
						Integer vx = k + r.nextInt(16) + 8;
						Integer vz = l + r.nextInt(16) + 8;
						Integer vy = w.getHeightValue(vx, vz) - 1;
						Block surface = w.getBlock(vx, vy, vz);
						MetaWorld mw = WorldHostController.getWorldMeta(w);
						BiomeTemperature temp = mw.getTemperatureEnum(b.getBiomeTemperature(), vx, vz);
						BiomeTemperatureModifier temperatureModifier = b.getBiomeTemperature().getModification(temp);
						BiomeHumidity hum = mw.getHumidityEnum(b.getBiomeHumidity(), vx, vz);
						BiomeHumidityModifier humidityModifier = b.getBiomeHumidity().getModification(hum);

						if(i instanceof LudicrousScatterBuffer)
						{
							LudicrousScatterBuffer lu = (LudicrousScatterBuffer) i;

							if(!lu.isHumidityBound(hum) || !lu.isTemperatureBound(temp))
							{
								continue;
							}
						}
						
						if(i.canDecorate(w, r, pass, surface, vx, vy, vz, temperatureModifier, humidityModifier, temp, hum))
						{
							i.decorate(w, r, pass, surface, vx, vy, vz, temperatureModifier, humidityModifier, temp, hum);
						}
					}
				}
			}
		}

		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(provider, worldObj, rand, x, z, flag));

		BlockFalling.fallInstantly = false;
	}

	/**
	 * Two modes of operation: if passed true, save all Chunks in one go. If passed
	 * false, save up to two chunks. Return true if all chunks have been saved.
	 */
	public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_)
	{
		return true;
	}

	/**
	 * Save extra data not associated with any Chunk. Not saved during autosave,
	 * only during world unload. Currently unimplemented.
	 */
	public void saveExtraData()
	{
	}

	/**
	 * Unloads chunks that are marked to be unloaded. This is not guaranteed to
	 * unload every such chunk.
	 */
	public boolean unloadQueuedChunks()
	{
		return false;
	}

	/**
	 * Returns if the IChunkProvider supports saving.
	 */
	public boolean canSave()
	{
		return true;
	}

	/**
	 * Converts the instance data to a readable string.
	 */
	public String makeString()
	{
		return "RandomLevelSource";
	}

	/**
	 * Returns a list of creatures of the specified type that can spawn at the given
	 * location.
	 */
	public List getPossibleCreatures(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_)
	{
		BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(p_73155_2_, p_73155_4_);
		return p_73155_1_ == EnumCreatureType.monster && this.scatteredFeatureGenerator.func_143030_a(p_73155_2_, p_73155_3_, p_73155_4_) ? this.scatteredFeatureGenerator.getScatteredFeatureSpawnList() : biomegenbase.getSpawnableList(p_73155_1_);
	}

	public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
	{
		return "Stronghold".equals(p_147416_2_) && this.strongholdGenerator != null ? this.strongholdGenerator.func_151545_a(p_147416_1_, p_147416_3_, p_147416_4_, p_147416_5_) : null;
	}

	public int getLoadedChunkCount()
	{
		return 0;
	}

	public void recreateStructures(int p_82695_1_, int p_82695_2_)
	{
		if(this.mapFeaturesEnabled)
		{
			this.mineshaftGenerator.func_151539_a(this, this.worldObj, p_82695_1_, p_82695_2_, (Block[]) null);
			this.villageGenerator.func_151539_a(this, this.worldObj, p_82695_1_, p_82695_2_, (Block[]) null);
			this.strongholdGenerator.func_151539_a(this, this.worldObj, p_82695_1_, p_82695_2_, (Block[]) null);
			this.scatteredFeatureGenerator.func_151539_a(this, this.worldObj, p_82695_1_, p_82695_2_, (Block[]) null);
		}
	}
}