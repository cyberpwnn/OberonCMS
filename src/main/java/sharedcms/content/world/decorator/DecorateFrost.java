package sharedcms.content.world.decorator;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import sharedcms.content.Content;
import sharedcms.content.world.generator.SimplexNoiseGenerator;
import sharedcms.util.GList;

public class DecorateFrost extends SpecializedDecorator
{
	private Block[] ice;
	private int rad;
	
	public DecorateFrost(int itr, Block... ice)
	{
		super(itr);
		
		this.ice = ice;
	}
	
	public DecorateFrost(int itr)
	{
		this(itr, Content.Block.ICE_CRUSHED_FRAME, Content.Block.ICE_FRAME, Content.Block.ICE_SCRATCHED_FRAME, Content.Block.ICE_SMOOTH_FRAME, Content.Block.IICE_SMOOTH);
	}

	@Override
	public boolean generate(World w, Random r, int x, int y, int z)
	{
		int h = heightOF(w, x, z, Blocks.water, Blocks.flowing_water);
		
		if(h > 0)
		{
			if(w.getBlock(x, h, z).equals(Blocks.water) || w.getBlock(x, h, z).equals(Blocks.flowing_water))
			{
				w.setBlock(x, h + 1, z, rand(r, ice));
			}
		}
		
		return true;
	}
}
