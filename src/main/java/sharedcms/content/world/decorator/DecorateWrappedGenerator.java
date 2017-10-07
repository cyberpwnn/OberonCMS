package sharedcms.content.world.decorator;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class DecorateWrappedGenerator extends SpecializedDecorator
{
	private WorldGenerator gen;
	
	public DecorateWrappedGenerator(int itr, WorldGenerator gen)
	{
		super(itr);
		
		this.gen = gen;
	}

	@Override
	public boolean generate(World w, Random r, int x, int y, int z)
	{
		return gen.generate(w, r, x, y, z);
	}
}
