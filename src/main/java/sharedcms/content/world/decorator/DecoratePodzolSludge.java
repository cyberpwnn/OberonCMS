package sharedcms.content.world.decorator;

import java.util.Random;

import net.minecraft.world.World;
import sharedcms.content.Content;

public class DecoratePodzolSludge extends SpecializedDecorator
{
	public DecoratePodzolSludge(int itr)
	{
		super(itr);
	}

	@Override
	public boolean generate(World w, Random r, int x, int y, int z)
	{
		int h = heightOF(w, x, z, Content.Block.PODZOL_MOSSY);

		if(h < 0)
		{
			return true;
		}

		w.setBlock(x, h, z, Content.Block.PODZOL);

		int h2 = heightOF(w, x, z, Content.Block.PODZOL);

		if(h2 < 0)
		{
			return true;
		}

		w.setBlock(x, h2, z, Content.Block.PODZOL_MOSSY);

		return true;
	}
}
