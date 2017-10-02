package sharedcms.base;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class AresWorldGenerator extends WorldGenerator
{
	@Override
	public abstract boolean generate(World world, Random random, int x, int y, int z);
}
