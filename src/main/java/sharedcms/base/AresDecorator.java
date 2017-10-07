package sharedcms.base;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class AresDecorator extends WorldGenerator
{
	private int itr;
	
	public AresDecorator(int itr)
	{
		this.itr = itr;
	}
	
	@Override
	public abstract boolean generate(World w, Random r, int x, int y, int z);
	
	public int getTries()
	{
		return itr;
	}
}
