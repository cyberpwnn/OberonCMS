package sharedcms.content.world.generator;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenFlowers;

public class AresWorldGenFlowers extends WorldGenFlowers
{
	private List<Block> flowers;
	private int fpc;

	public AresWorldGenFlowers(List<Block> flowers, int i)
	{
		super(null);
		
		fpc = i;
		this.flowers = flowers;
	}

	public boolean generate(World world, Random random, int x, int y, int z)
	{
		if(flowers.isEmpty())
		{
			return true;
		}
		
		int km = 0;
		
		if(fpc < 1)
		{
			return true;
		}
		
		for(int l = 0; l < 128; ++l)
		{
			int i1 = x + random.nextInt(8) - random.nextInt(8);
			int j1 = y + random.nextInt(4) - random.nextInt(4);
			int k1 = z + random.nextInt(8) - random.nextInt(8);

			Block b = flowers.get(random.nextInt(flowers.size()));
			if(world.isAirBlock(i1, j1, k1) && (!world.provider.hasNoSky || j1 < 255) && b.canBlockStay(world, i1, j1, k1))
			{
				world.setBlock(i1, j1, k1, b, 0, 2);
				km++;
			}
			
			if(km >= fpc)
			{
				return true;
			}
		}

		return true;
	}
}