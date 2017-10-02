package sharedcms.content.world.chunk.provider;

import net.minecraft.world.World;
import sharedcms.base.AresWorldChunkProvider;

public class ChunkProviderSimple extends AresWorldChunkProvider
{
	public ChunkProviderSimple(World world, long seed, boolean decorate)
	{
		super(world, seed, true);
		featureDungeon = false;
		featureLakeLava = false;
		featureLakeWater = true;
		featureFrosty = false;
		featureSpawns = true;
		featureRavines = false;
		featureCaves = true;
	}
}
