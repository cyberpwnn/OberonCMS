package sharedcms.content.world.genlayer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.IntCache;

public class CauldronRiverMix extends Cauldron
{
	private Cauldron biomePatternGeneratorChain;
	private Cauldron riverPatternGeneratorChain;

	public CauldronRiverMix(long seed, Cauldron biomePattern, Cauldron riverPattern)
	{
		super(seed);
		this.biomePatternGeneratorChain = biomePattern;
		this.riverPatternGeneratorChain = riverPattern;
	}

	public void initWorldGenSeed(long seed)
	{
		this.biomePatternGeneratorChain.initWorldGenSeed(seed);
		this.riverPatternGeneratorChain.initWorldGenSeed(seed);
		super.initWorldGenSeed(seed);
	}

	public int[] getInts(int par1, int par2, int par3, int par4)
	{
		int[] aint = this.biomePatternGeneratorChain.getInts(par1, par2, par3, par4);
		int[] aint1 = this.riverPatternGeneratorChain.getInts(par1, par2, par3, par4);
		int[] aint2 = IntCache.getIntCache(par3 * par4);

		for(int i1 = 0; i1 < par3 * par4; ++i1)
		{
			if(aint[i1] != BiomeGenBase.ocean.biomeID && aint[i1] != BiomeGenBase.deepOcean.biomeID)
			{
				if(aint1[i1] == BiomeGenBase.river.biomeID)
				{
					if(aint[i1] == BiomeGenBase.icePlains.biomeID)
					{
						aint2[i1] = BiomeGenBase.frozenRiver.biomeID;
					}

					else if(aint[i1] != BiomeGenBase.mushroomIsland.biomeID && aint[i1] != BiomeGenBase.mushroomIslandShore.biomeID)
					{
						aint2[i1] = aint1[i1] & 255;
					}

					else
					{
						aint2[i1] = BiomeGenBase.mushroomIslandShore.biomeID;
					}
				}

				else
				{
					aint2[i1] = aint[i1];
				}
			}

			else
			{
				aint2[i1] = aint[i1];
			}
		}

		return aint2;
	}
}