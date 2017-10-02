package sharedcms.content.world.genlayer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenMesa;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class CauldronShore extends Cauldron
{
	private static final String __OBFID = "CL_00000568";

	public CauldronShore(long seed, GenLayer parent)
	{
		super(seed);
		this.parent = parent;
	}

	public int[] getInts(int par1, int par2, int par3, int par4)
	{
		int[] aint = this.parent.getInts(par1 - 1, par2 - 1, par3 + 2, par4 + 2);
		int[] aint1 = IntCache.getIntCache(par3 * par4);

		for(int i1 = 0; i1 < par4; ++i1)
		{
			for(int j1 = 0; j1 < par3; ++j1)
			{
				this.initChunkSeed((long) (j1 + par1), (long) (i1 + par2));
				int k1 = aint[j1 + 1 + (i1 + 1) * (par3 + 2)];
				BiomeGenBase biomegenbase = BiomeGenBase.getBiome(k1);
				int l1;
				int i2;
				int j2;
				int k2;

				if(k1 == BiomeGenBase.mushroomIsland.biomeID)
				{
					l1 = aint[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
					i2 = aint[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
					j2 = aint[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
					k2 = aint[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];

					if(l1 != BiomeGenBase.ocean.biomeID && i2 != BiomeGenBase.ocean.biomeID && j2 != BiomeGenBase.ocean.biomeID && k2 != BiomeGenBase.ocean.biomeID)
					{
						aint1[j1 + i1 * par3] = k1;
					}

					else
					{
						aint1[j1 + i1 * par3] = BiomeGenBase.mushroomIslandShore.biomeID;
					}
				}

				else if(biomegenbase != null && biomegenbase.getBiomeClass() == BiomeGenJungle.class)
				{
					l1 = aint[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
					i2 = aint[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
					j2 = aint[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
					k2 = aint[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];

					if(this.compareSets(l1) && this.compareSets(i2) && this.compareSets(j2) && this.compareSets(k2))
					{
						if(!isBiomeOceanic(l1) && !isBiomeOceanic(i2) && !isBiomeOceanic(j2) && !isBiomeOceanic(k2))
						{
							aint1[j1 + i1 * par3] = k1;
						}

						else
						{
							aint1[j1 + i1 * par3] = BiomeGenBase.beach.biomeID;
						}
					}

					else
					{
						aint1[j1 + i1 * par3] = BiomeGenBase.jungleEdge.biomeID;
					}
				}

				else if(k1 != BiomeGenBase.extremeHills.biomeID && k1 != BiomeGenBase.extremeHillsPlus.biomeID && k1 != BiomeGenBase.extremeHillsEdge.biomeID)
				{
					if(biomegenbase != null && biomegenbase.func_150559_j())
					{
						this.makeBeach(aint, aint1, j1, i1, par3, k1, BiomeGenBase.coldBeach.biomeID);
					}

					else if(k1 != BiomeGenBase.mesa.biomeID && k1 != BiomeGenBase.mesaPlateau_F.biomeID)
					{
						if(k1 != BiomeGenBase.ocean.biomeID && k1 != BiomeGenBase.deepOcean.biomeID && k1 != BiomeGenBase.river.biomeID && k1 != BiomeGenBase.swampland.biomeID)
						{
							l1 = aint[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
							i2 = aint[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
							j2 = aint[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
							k2 = aint[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];

							if(!isBiomeOceanic(l1) && !isBiomeOceanic(i2) && !isBiomeOceanic(j2) && !isBiomeOceanic(k2))
							{
								aint1[j1 + i1 * par3] = k1;
							}

							else
							{
								aint1[j1 + i1 * par3] = BiomeGenBase.beach.biomeID;
							}
						}

						else
						{
							aint1[j1 + i1 * par3] = k1;
						}
					}

					else
					{
						l1 = aint[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
						i2 = aint[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
						j2 = aint[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
						k2 = aint[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];

						if(!isBiomeOceanic(l1) && !isBiomeOceanic(i2) && !isBiomeOceanic(j2) && !isBiomeOceanic(k2))
						{
							if(this.compareBases(l1) && this.compareBases(i2) && this.compareBases(j2) && this.compareBases(k2))
							{
								aint1[j1 + i1 * par3] = k1;
							}

							else
							{
								aint1[j1 + i1 * par3] = BiomeGenBase.desert.biomeID;
							}
						}

						else
						{
							aint1[j1 + i1 * par3] = k1;
						}
					}
				}

				else
				{
					this.makeBeach(aint, aint1, j1, i1, par3, k1, BiomeGenBase.stoneBeach.biomeID);
				}
			}
		}

		return aint1;
	}

	private void makeBeach(int[] par1, int[] par2, int par3, int par4, int par5, int par6, int par7)
	{
		if(isBiomeOceanic(par6))
		{
			par2[par3 + par4 * par5] = par6;
		}
		
		else
		{
			int j1 = par1[par3 + 1 + (par4 + 1 - 1) * (par5 + 2)];
			int k1 = par1[par3 + 1 + 1 + (par4 + 1) * (par5 + 2)];
			int l1 = par1[par3 + 1 - 1 + (par4 + 1) * (par5 + 2)];
			int i2 = par1[par3 + 1 + (par4 + 1 + 1) * (par5 + 2)];

			if(!isBiomeOceanic(j1) && !isBiomeOceanic(k1) && !isBiomeOceanic(l1) && !isBiomeOceanic(i2))
			{
				par2[par3 + par4 * par5] = par6;
			}
			
			else
			{
				par2[par3 + par4 * par5] = par7;
			}
		}
	}

	private boolean compareSets(int par1)
	{
		return BiomeGenBase.getBiome(par1) != null && BiomeGenBase.getBiome(par1).getBiomeClass() == BiomeGenJungle.class ? true : par1 == BiomeGenBase.jungleEdge.biomeID || par1 == BiomeGenBase.jungle.biomeID || par1 == BiomeGenBase.jungleHills.biomeID || par1 == BiomeGenBase.forest.biomeID || par1 == BiomeGenBase.taiga.biomeID || isBiomeOceanic(par1);
	}

	private boolean compareBases(int par1)
	{
		return BiomeGenBase.getBiome(par1) != null && BiomeGenBase.getBiome(par1) instanceof BiomeGenMesa;
	}
}