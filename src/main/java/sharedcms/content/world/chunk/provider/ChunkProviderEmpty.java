package sharedcms.content.world.chunk.provider;

import net.minecraft.world.World;
import sharedcms.base.AresWorldChunkProvider;

public class ChunkProviderEmpty extends AresWorldChunkProvider
{
	public ChunkProviderEmpty(World world, long seed, boolean decorate)
	{
		super(world, seed, false);
		featureDungeon = false;
		featureLakeLava = false;
		featureLakeWater = false;
		featureFrosty = false;
		featureSpawns = false;
		featureRavines = false;
		featureCaves = false;
	}
}
