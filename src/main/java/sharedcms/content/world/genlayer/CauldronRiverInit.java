package sharedcms.content.world.genlayer;

import net.minecraft.world.gen.layer.IntCache;

public class CauldronRiverInit extends Cauldron
{
	public CauldronRiverInit(long seed, Cauldron parent)
	{
		super(seed);
		this.parent = parent;
	}

	public int[] getInts(int par1, int par2, int par3, int par4)
	{
		int[] aint = this.parent.getInts(par1, par2, par3, par4);
		int[] aint1 = IntCache.getIntCache(par3 * par4);

		for(int i1 = 0; i1 < par4; ++i1)
		{
			for(int j1 = 0; j1 < par3; ++j1)
			{
				this.initChunkSeed((long) (j1 + par1), (long) (i1 + par2));
				aint1[j1 + i1 * par3] = aint[j1 + i1 * par3] > 0 ? this.nextInt(299999) + 2 : 0;
			}
		}

		return aint1;
	}
}