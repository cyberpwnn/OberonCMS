package sharedcms.content.world.genlayer;

import net.minecraft.world.gen.layer.IntCache;

public class CauldronEdge extends Cauldron
{
	private final CauldronEdge.Mode mode;

	public CauldronEdge(long seed, Cauldron parent, CauldronEdge.Mode mode)
	{
		super(seed);
		this.parent = parent;
		this.mode = mode;
	}

	public int[] getInts(int par1, int par2, int par3, int par4)
	{
		switch(CauldronEdge.SwitchMode.modeSet[this.mode.ordinal()])
		{
			case 1:
			default:
				return this.getIntsCoolWarm(par1, par2, par3, par4);
			case 2:
				return this.getIntsHeatIce(par1, par2, par3, par4);
			case 3:
				return this.getIntsSpecial(par1, par2, par3, par4);
		}
	}

	private int[] getIntsCoolWarm(int par1, int par2, int par3, int par4)
	{
		int i1 = par1 - 1;
		int j1 = par2 - 1;
		int k1 = 1 + par3 + 1;
		int l1 = 1 + par4 + 1;
		int[] aint = this.parent.getInts(i1, j1, k1, l1);
		int[] aint1 = IntCache.getIntCache(par3 * par4);

		for(int i2 = 0; i2 < par4; ++i2)
		{
			for(int j2 = 0; j2 < par3; ++j2)
			{
				this.initChunkSeed((long) (j2 + par1), (long) (i2 + par2));
				int k2 = aint[j2 + 1 + (i2 + 1) * k1];

				if(k2 == 1)
				{
					int l2 = aint[j2 + 1 + (i2 + 1 - 1) * k1];
					int i3 = aint[j2 + 1 + 1 + (i2 + 1) * k1];
					int j3 = aint[j2 + 1 - 1 + (i2 + 1) * k1];
					int k3 = aint[j2 + 1 + (i2 + 1 + 1) * k1];
					boolean flag = l2 == 3 || i3 == 3 || j3 == 3 || k3 == 3;
					boolean flag1 = l2 == 4 || i3 == 4 || j3 == 4 || k3 == 4;

					if(flag || flag1)
					{
						k2 = 2;
					}
				}

				aint1[j2 + i2 * par3] = k2;
			}
		}

		return aint1;
	}

	private int[] getIntsHeatIce(int par1, int par2, int par3, int par4)
	{
		int i1 = par1 - 1;
		int j1 = par2 - 1;
		int k1 = 1 + par3 + 1;
		int l1 = 1 + par4 + 1;
		int[] aint = this.parent.getInts(i1, j1, k1, l1);
		int[] aint1 = IntCache.getIntCache(par3 * par4);

		for(int i2 = 0; i2 < par4; ++i2)
		{
			for(int j2 = 0; j2 < par3; ++j2)
			{
				int k2 = aint[j2 + 1 + (i2 + 1) * k1];

				if(k2 == 4)
				{
					int l2 = aint[j2 + 1 + (i2 + 1 - 1) * k1];
					int i3 = aint[j2 + 1 + 1 + (i2 + 1) * k1];
					int j3 = aint[j2 + 1 - 1 + (i2 + 1) * k1];
					int k3 = aint[j2 + 1 + (i2 + 1 + 1) * k1];
					boolean flag = l2 == 2 || i3 == 2 || j3 == 2 || k3 == 2;
					boolean flag1 = l2 == 1 || i3 == 1 || j3 == 1 || k3 == 1;

					if(flag1 || flag)
					{
						k2 = 3;
					}
				}

				aint1[j2 + i2 * par3] = k2;
			}
		}

		return aint1;
	}

	private int[] getIntsSpecial(int par1, int par2, int par3, int par4)
	{
		int[] aint = this.parent.getInts(par1, par2, par3, par4);
		int[] aint1 = IntCache.getIntCache(par3 * par4);

		for(int i1 = 0; i1 < par4; ++i1)
		{
			for(int j1 = 0; j1 < par3; ++j1)
			{
				this.initChunkSeed((long) (j1 + par1), (long) (i1 + par2));
				int k1 = aint[j1 + i1 * par3];

				if(k1 != 0 && this.nextInt(13) == 0)
				{
					k1 |= 1 + this.nextInt(15) << 8 & 3840;
				}

				aint1[j1 + i1 * par3] = k1;
			}
		}

		return aint1;
	}

	public static enum Mode
	{
		COOL_WARM,
		HEAT_ICE,
		SPECIAL;
	}

	static final class SwitchMode
	{
		static final int[] modeSet = new int[CauldronEdge.Mode.values().length];

		static
		{
			try
			{
				modeSet[CauldronEdge.Mode.COOL_WARM.ordinal()] = 1;
			}
			catch(NoSuchFieldError e)
			{

			}

			try
			{
				modeSet[CauldronEdge.Mode.HEAT_ICE.ordinal()] = 2;
			}

			catch(NoSuchFieldError e)
			{

			}

			try
			{
				modeSet[CauldronEdge.Mode.SPECIAL.ordinal()] = 3;
			}

			catch(NoSuchFieldError e)
			{

			}
		}
	}
}