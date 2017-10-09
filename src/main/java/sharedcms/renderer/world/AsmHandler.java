package sharedcms.renderer.world;

import java.nio.IntBuffer;

import net.minecraft.client.renderer.WorldRenderer;
import sharedcms.controller.client.ChunkAnimationController;
import sharedcms.renderer.world.AnimationHandler;

public class AsmHandler
{
	public static void callLists(IntBuffer glLists)
	{
		ChunkAnimationController.INSTANCE.animationHandler.callLists(glLists);
	}

	public static void setPosition(WorldRenderer worldRenderer)
	{
		ChunkAnimationController.INSTANCE.animationHandler.setPosition(worldRenderer);
	}
}
