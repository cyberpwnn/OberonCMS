package sharedcns.api.biome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import sharedcms.base.AresBiome;
import sharedcms.content.world.meta.objects.MetaWorld;
import sharedcms.controller.shared.WorldHostController;

public class EnhancedBiome extends AresBiome implements IBiome
{
	private ISurfaceBuffer paver;
	private List<IScatterBuffer> decorators;
	private int level;

	public EnhancedBiome(int id)
	{
		super(id);
		level = 0;
		paver = new DefaultBiomePaver();
		decorators = new ArrayList<IScatterBuffer>();
	}

	@Override
	public int getId()
	{
		return biomeID;
	}

	@Override
	public List<IScatterBuffer> getScatterBuffers()
	{
		return decorators;
	}

	@Override
	public String getName()
	{
		return biomeName;
	}

	@Override
	public BiomeTemperature getBiomeTemperature()
	{
		return (BiomeTemperature) BiomeTemperature.of((int) ((getTemperature() / 2f) / BiomeTemperature.length()));
	}

	@Override
	public BiomeHumidity getBiomeHumidity()
	{
		return (BiomeHumidity) BiomeHumidity.of((int) (getRainfall() / BiomeHumidity.length()));
	}

	@Override
	public Block getTopBlock(int x, int y, int z, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity)
	{
		return getSurfaceBuffer().getTopBlock(x, y, z, tempModifier, humidityModifier, temp, humidity);
	}

	@Override
	public Block getFillerBlock(int x, int y, int z, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity)
	{
		return getSurfaceBuffer().getFillerBlock(x, y, z, tempModifier, humidityModifier, temp, humidity);
	}

	@Override
	public Block getRockBlock(int x, int y, int z, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity)
	{
		return getSurfaceBuffer().getRockBlock(x, y, z, tempModifier, humidityModifier, temp, humidity);
	}

	@Override
	public void decorate(World w, Random r, int x, int y, int z, DecorationPass pass, Block surface, BiomeTemperatureModifier tempModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity humidity)
	{
		for(IScatterBuffer i : getScatterBuffers())
		{
			if(i.canDecorate(w, r, pass, surface, x, y, z, tempModifier, humidityModifier, temp, humidity))
			{
				i.decorate(w, r, pass, surface, x, y, z, tempModifier, humidityModifier, temp, humidity);
			}
		}
	}

	@Override
	public void addScatterBuffer(IScatterBuffer decorator)
	{
		getScatterBuffers().add(decorator);
	}

	@Override
	public ISurfaceBuffer getSurfaceBuffer()
	{
		return paver;
	}

	@Override
	public void setSurfaceBuffer(ISurfaceBuffer paver)
	{
		this.paver = paver;
	}

	@Override
	public void setBiomeHumidity(BiomeHumidity h)
	{
		rainfall = h.getHumidity();
	}

	@Override
	public void setBiomeTemperature(BiomeTemperature t)
	{
		temperature = t.getTemperature();
	}

	@Override
	public int getLevel()
	{
		return level;
	}

	@Override
	public void setLevel(int level)
	{
		this.level = level;
	}

	public void genTerrainBlocks(World w, Random r, Block[] blocks, byte[] bytes, int x, int z, double nid)
	{
		gbt(w, r, blocks, bytes, x, z, nid);
	}

	public void gbt(World world, Random random, Block[] blocks, byte[] bytes, int chunkX, int chunkZ, double nid)
	{
		boolean flag = true;
		Block blockTop = this.topBlock;
		byte b0 = (byte) (this.field_150604_aj & 255);
		Block blockFiller = this.fillerBlock;
		int vx = chunkX;
		int vy = 100;
		int vz = chunkZ;
		World w = world;
		MetaWorld mw = WorldHostController.getWorldMeta(w);
		BiomeTemperature temp =  mw.getCsx().get(getBiomeTemperature(), vx, vz);
		BiomeTemperatureModifier temperatureModifier = getBiomeTemperature().getModification(temp);
		BiomeHumidity hum =  mw.getCsx().get(getBiomeHumidity(), vx, vz);
		BiomeHumidityModifier humidityModifier = getBiomeHumidity().getModification(hum);
		blockTop = getSurfaceBuffer().getTopBlock(vx, vy, vz, temperatureModifier, humidityModifier, temp, hum);
		blockFiller = getSurfaceBuffer().getFillerBlock(vx, vy, vz, temperatureModifier, humidityModifier, temp, hum);
		int k = -1;
		int l = (int) (nid / 3.0D + 3.0D + random.nextDouble() * 0.25D);
		int i1 = chunkX & 15;
		int j1 = chunkZ & 15;
		int k1 = blocks.length / 256;

		for(int l1 = 255; l1 >= 0; --l1)
		{
			int i2 = (j1 * 16 + i1) * k1 + l1;
			
			if(l1 <= 0 + random.nextInt(5))
			{
				blocks[i2] = Blocks.bedrock;
			}

			else
			{
				Block block2 = blocks[i2];

				if(block2 != null && block2.getMaterial() != Material.air)
				{
					if(block2 != null)
					{
						if(k == -1)
						{
							if(l <= 0)
							{
								blockTop = null;
								b0 = 0;
								blockFiller = getSurfaceBuffer().getRockBlock(vx, vy, vz, temperatureModifier, humidityModifier, temp, hum);
							}

							else if(l1 >= 59 && l1 <= 64)
							{
								blockTop = getSurfaceBuffer().getTopBlock(vx, vy, vz, temperatureModifier, humidityModifier, temp, hum);
								b0 = (byte) (this.field_150604_aj & 255);
								blockFiller = getSurfaceBuffer().getFillerBlock(vx, vy, vz, temperatureModifier, humidityModifier, temp, hum);
							}

							if(l1 < 63 && (blockTop == null || blockTop.getMaterial() == Material.air))
							{
								if(this.getFloatTemperature(chunkX, l1, chunkZ) < 0.15F)
								{
									blockTop = Blocks.ice;
									b0 = 0;
								}

								else
								{
									blockTop = Blocks.water;
									b0 = 0;
								}
							}

							k = l;

							if(l1 >= 62)
							{
								blocks[i2] = blockTop;
								bytes[i2] = b0;
							}

							else if(l1 < 56 - l)
							{
								blockTop = null;
								blockFiller = getSurfaceBuffer().getRockBlock(vx, vy, vz, temperatureModifier, humidityModifier, temp, hum);
								blocks[i2] = Blocks.gravel;
							}

							else
							{
								blocks[i2] = getSurfaceBuffer().getRockBlock(vx, vy, vz, temperatureModifier, humidityModifier, temp, hum);
							}
						}

						else if(k > 0)
						{
							--k;
							blocks[i2] = blockFiller;

							if(k == 0 && blockFiller == Blocks.sand)
							{
								k = random.nextInt(4) + Math.max(0, l1 - 63);
								blockFiller = Blocks.sandstone;
							}
						}
					}
				}

				else
				{
					k = -1;
				}
				
				block2 = blocks[i2];
			
				if(block2 == Blocks.stone)
				{
					blocks[i2] = getSurfaceBuffer().getRockBlock(vx, vy, vz, temperatureModifier, humidityModifier, temp, hum);
				}
			}
		}
	}
}
