package sharedcms.base;

import java.awt.Color;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import sharedcms.audio.DSound;
import sharedcms.audio.SFX;
import sharedcms.content.Content;
import sharedcms.util.Location;
import sharedcms.util.M;

public abstract class AresNaturalLeavesBlock extends AresNaturalBlock
{
	int[] field_150128_a;
	@SideOnly(Side.CLIENT)
	protected int field_150127_b;
	protected IIcon[][] field_150129_M = new IIcon[2][];
	private static final String __OBFID = "CL_00000263";

	public AresNaturalLeavesBlock(String unlocalizedName, Material material, SoundType type)
	{
		super(unlocalizedName, material, type);
		this.setTickRandomly(false);
		this.setHardness(0.2F);
		this.setLightOpacity(1);
	}

	@SideOnly(Side.CLIENT)
	public int getBlockColor()
	{
		double d0 = 0.5D;
		double d1 = 1.0D;
		return ColorizerFoliage.getFoliageColor(d0, d1);
	}

	/**
	 * Returns the color this block should be rendered. Used by leaves.
	 */
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int p_149741_1_)
	{
		return ColorizerFoliage.getFoliageColorBasic();
	}

	/**
	 * Returns a integer with hex for 0xrrggbb with this color multiplied against
	 * the blocks color. Note only called when first determining what to render.
	 */
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
	{
		int l = 0;
		int i1 = 0;
		int j1 = 0;

		for(int k1 = -1; k1 <= 1; ++k1)
		{
			for(int l1 = -1; l1 <= 1; ++l1)
			{
				int i2 = p_149720_1_.getBiomeGenForCoords(p_149720_2_ + l1, p_149720_4_ + k1).getBiomeFoliageColor(p_149720_2_ + l1, p_149720_3_, p_149720_4_ + k1);
				l += (i2 & 16711680) >> 16;
				i1 += (i2 & 65280) >> 8;
				j1 += i2 & 255;
			}
		}

		return (l / 9 & 255) << 16 | (i1 / 9 & 255) << 8 | j1 / 9 & 255;
	}

	/**
	 * A randomly called display update to be able to add particles or other items
	 * for display
	 */
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World w, int x, int y, int z, Random r)
	{
		Content.Effect.FALLING_LEAF.play(w, x, y, z, new Color(getBlockColor()));
		
		if(w.canLightningStrikeAt(x, y + 1, z) && !World.doesBlockHaveSolidTopSurface(w, x, y - 1, z) && r.nextInt(15) == 1)
		{
			double d0 = (double) ((float) x + r.nextFloat());
			double d1 = (double) y - 0.05D;
			double d2 = (double) ((float) z + r.nextFloat());
			w.spawnParticle("dripWater", d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
		
		if(M.r(0.004))
		{
			if(w.getWorldTime() > 12966 && w.getWorldTime() < 22916)
			{
				if(M.r(0.01))
				{
					SFX.play(new DSound("sharedcms:" + Content.SoundMaterial.AMBIENT_OWL, 2f, 1f, 0.2f), new Location(x, y, z));
				}
			}
			
			else
			{
				if(M.r(0.4))
				{
					SFX.play(new DSound("sharedcms:" + Content.SoundMaterial.AMBIENT_FOREST, 2f, 1f, 0.2f), new Location(x, y, z));
				}
			}
		}
	}
}
