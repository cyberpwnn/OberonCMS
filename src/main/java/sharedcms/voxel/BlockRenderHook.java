package sharedcms.voxel;

import net.minecraft.block.Block;
import sharedcms.controller.client.BoxelController;

public class BlockRenderHook
{
	public static boolean shouldHookRenderer(Block block)
	{
		return BoxelController.isBlockSoft(block) || BoxelController.isBlockLiquid(block);
	}
}
