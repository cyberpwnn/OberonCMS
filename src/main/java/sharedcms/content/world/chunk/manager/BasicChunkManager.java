package sharedcms.content.world.chunk.manager;

import net.minecraft.world.World;
import sharedcms.base.AresGenLayerZoom;
import sharedcms.base.AresWorldChunkManager;
import sharedcms.content.world.genlayer.BasicGenLayerZoom;

public class BasicChunkManager extends AresWorldChunkManager
{
	public BasicChunkManager(World world)
	{
		super(world);
	}

	@Override
	public AresGenLayerZoom getGenLayer(long seed)
	{
		return new BasicGenLayerZoom(seed);
	}
}
