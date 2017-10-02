package sharedcms.base;

import java.awt.Color;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import sharedcms.audio.DSound;
import sharedcms.audio.SFX;
import sharedcms.content.Content;
import sharedcms.util.M;
import sharedcms.util.SuperLocation;

public abstract class AresBlockShrub extends AresBlock
{
	private int weight;
	private boolean oriental;
	private boolean glacial;
	private long ls = System.currentTimeMillis();

	public AresBlockShrub(String unlocalizedName, Material material, SoundType type, int weight)
	{
		super(unlocalizedName, material, Content.Tab.SHRUBS, type, false);
		float f = 0.2F;
		oriental = false;
		glacial = false;
		this.weight = weight;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
	}

	public boolean isOriental()
	{
		return oriental;
	}

	public AresBlockShrub setOriental(boolean oriental)
	{
		this.oriental = oriental;

		return this;
	}

	public AresBlockShrub setGlacial(boolean g)
	{
		this.oriental = g;

		return this;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World w, int x, int y, int z, Random r)
	{
		Content.Effect.FIREFLY.play(w, x, y, z, getRadiantColor());
		
		if(M.r(0.01))
		{
			if(w.getWorldTime() > 12966 && w.getWorldTime() < 22916)
			{
				if(M.r(0.4))
				{
					SFX.play(new DSound("sharedcms:" + Content.SoundMaterial.AMBIENT_CRICKETS, 2f, 1f, 0.2f), new SuperLocation(x, y, z));
				}
			}
			
			else
			{
				SFX.play(new DSound("sharedcms:" + Content.SoundMaterial.AMBIENT_PLAINS, 2f, 1f, 0.2f), new SuperLocation(x, y, z));
			}
		}
	}

	public abstract Color getRadiantColor();

	public boolean canPlaceBlockAt(World w, int x, int y, int z)
	{
		return canBlockStay(w, x, y, z);
	}

	public boolean canBlockStay(World w, int x, int y, int z)
	{
		return dch(w, x + 1, y, z - 1) && dch(w, x + 1, y, z + 1) && dch(w, x - 1, y, z - 1) && dch(w, x - 1, y, z + 1) && dch(w, x + 1, y, z) && dch(w, x - 1, y, z) && dch(w, x, y, z + 1) && dch(w, x, y, z - 1);
	}

	public boolean dch(World w, int x, int y, int z)
	{
		if(w.getBlock(x, y - 1, z) instanceof AresNaturalLeavesBlock)
		{
			return false;
		}

		if(w.getBlock(x, y - 1, z) instanceof BlockLiquid)
		{
			return false;
		}

		if(w.getBlock(x, y - 1, z) instanceof AresBlockShrub)
		{
			return false;
		}

		if(w.getBlock(x, y - 1, z) == Blocks.air)
		{
			return false;
		}

		if(!w.getBlock(x, y - 1, z).isCollidable())
		{
			return false;
		}

		return true;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	{
		return null;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public int getRenderType()
	{
		return 1;
	}

	public int getWeight()
	{
		return weight;
	}

	public boolean isGlacial()
	{
		return glacial;
	}
}
