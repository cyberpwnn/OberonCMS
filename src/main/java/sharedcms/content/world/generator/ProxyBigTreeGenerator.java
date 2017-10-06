package sharedcms.content.world.generator;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class ProxyBigTreeGenerator extends WorldGenTrees
{
	private WorldGenBigTree bigTreeGen;

	public ProxyBigTreeGenerator(WorldGenBigTree bigTreeGen)
	{
		super(false);
		this.bigTreeGen = bigTreeGen;
	}

	public boolean generate(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_)
	{
		return bigTreeGen.generate(p_76484_1_, p_76484_2_, p_76484_3_, p_76484_4_, p_76484_5_);
	}
}
