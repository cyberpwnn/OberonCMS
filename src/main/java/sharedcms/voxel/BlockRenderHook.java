package sharedcms.voxel;

import net.minecraft.block.Block;
import sharedcms.proxy.ProxyVoxel;

public class BlockRenderHook
{
	public static boolean shouldHookRenderer(Block block)
	{
		return ProxyVoxel.isBlockSoft(block) || ProxyVoxel.isBlockLiquid(block);
	}
}
