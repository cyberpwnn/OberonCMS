package sharedcms.content.world.generator;

import java.util.Random;

import net.minecraft.world.World;
import sharedcms.base.AresWorldGenerator;

public class WorldGeneratorEmpty extends AresWorldGenerator
{
	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		return false;
	}
}
