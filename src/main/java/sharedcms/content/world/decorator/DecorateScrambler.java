package sharedcms.content.world.decorator;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import sharedcms.content.world.generator.SimplexNoiseGenerator;
import sharedcms.util.GList;

public class DecorateScrambler extends SpecializedDecorator
{
	private SimplexNoiseGenerator snd;
	private GList<Block> scrambled;
	private Block[] scrambleda;
	private int rad;
	
	public DecorateScrambler(int itr, int rad, Block... scrambled)
	{
		super(itr);
		
		this.rad = rad;
		snd = new SimplexNoiseGenerator(676767696969l);
		this.scrambled = new GList<Block>(scrambled);
		this.scrambleda = scrambled;
	}

	@Override
	public boolean generate(World w, Random r, int x, int y, int z)
	{
		int h = heightOF(w, x, z, scrambleda);
		
		if(h > 0)
		{
			int xx = (int) (x + (snd.getNoise(x / 50.0, 100, z / 50.0) * rad));
			int zz = (int) (z + (snd.getNoise(x / 50.0, 90, z / 50.0) * rad));
			int kk = heightOF(w, x, z, scrambleda);
			
			if(kk > 0)
			{
				Block a = w.getBlock(x, h, z);
				Block b = w.getBlock(xx, kk, zz);
				
				if(scrambled.contains(a) && scrambled.contains(b))
				{
					if(!a.equals(b))
					{
						w.setBlock(x, h, z, b);
						w.setBlock(xx, kk, zz, a);
					}
				}
			}
		}
		
		return true;
	}
}
