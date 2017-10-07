package sharedcms.content.world.decorator;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import sharedcms.base.AresDecorator;
import sharedcms.util.M;

public abstract class SpecializedDecorator extends AresDecorator
{
	public SpecializedDecorator(int itr)
	{
		super(itr);
	}
	
	public int height(World w, int x, int z)
	{
		for(int i = 255; i > 0; i--)
		{
			if(w.getBlock(x, i, z).equals(Blocks.water) || w.getBlock(x, i, z).equals(Blocks.flowing_water))
			{
				return i;
			}

			if(w.getBlockLightOpacity(x, i, z) == 255)
			{
				return i;
			}
		}

		return 0;
	}
	
	public int heightOF(World w, int x, int z, Block b)
	{
		for(int i = 255; i > 0; i--)
		{
			if(w.getBlock(x, i, z).equals(b))
			{
				return i;
			}
		}

		return -1;
	}
	
	public int heightOF(World w, int x, int z, Block... b)
	{
		for(int i = 255; i > 0; i--)
		{
			for(Block j : b)
			{
				if(w.getBlock(x, i, z).equals(j))
				{
					return i;
				}
			}
		}

		return -1;
	}
	
	public boolean isAtop(World w, int x, int y, int z, Block... b)
	{
		return is(w, x, y - 1, z, b);
	}
	
	public boolean is(World w, int x, int y, int z, Block... b)
	{
		for(Block i : b)
		{
			if(w.getBlock(x, y, z).equals(i))
			{
				return true;
			}
		}
		
		return false;
	}
	
	
	public void drawHeightCircle(World w, Random r, int xx, int yy, int rad, int height, int h, double opacity, Block surf, Block... blocks)
	{
		for(int y = -rad; y <= rad; y++)
		{
			for(int x = -rad; x <= rad; x++)
			{
				if(x * x + y * y <= rad * rad)
				{
					if(h < 0)
					{
						h = height(w, xx + x, yy + y);
					}
					
					if(h < 0)
					{
						continue;
					}
					
					for(int i = 0; i < height; i++)
					{
						if(M.r(opacity))
						{
							w.setBlock(xx + x, h + i, yy + y, rand(r, blocks));
							
							continue;
						}
						
						break;
					}
				}
			}
		}
	}
	
	public Block rand(Random r, Block... b)
	{
		return b[r.nextInt(b.length)];
	}
}
