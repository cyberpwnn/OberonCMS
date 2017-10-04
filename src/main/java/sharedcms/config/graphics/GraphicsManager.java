package sharedcms.config.graphics;

import sharedcms.Info;
import sharedcms.config.GConfig;
import sharedcms.config.GG;
import sharedcms.controller.client.GuiController;
import sharedcms.gui.util.R;

public class GraphicsManager
{
	public static void set(int level)
	{
		set(GraphicsLevel.values()[R.CLIP(level, 0, 4)]);
	}

	public static void set(GraphicsLevel level)
	{
		System.out.println("G: " + level.toString());
		Info.GRAPHICS_LEVEL = level.ordinal();
	}
	
	public static void apply()
	{
		GuiController.doWork("Please Wait", "Updating Graphics Settings", new Runnable()
		{
			@Override
			public void run()
			{
				GG.refreshChunks();
				GraphicsLevel level = getLevel();
				GConfig.setShaderPack("shaders-" + level.name().toLowerCase());
				GG.refreshChunks();
				
				switch(level)
				{
					case BARE:
						GG.RENDER_DISTANCE_CHUNKS.set(2);
						Info.SHADER_BLUR_RADIUS = 2;
						Info.TESSELLATION_SIMPLEX = false;
						Info.TESSELLATION_VERTEX = false;
						break;
					case LOW:
						GG.RENDER_DISTANCE_CHUNKS.set(4);
						Info.SHADER_BLUR_RADIUS = 12;
						Info.TESSELLATION_SIMPLEX = false;
						Info.TESSELLATION_VERTEX = true;
						break;
					case MEDIUM:
						GG.RENDER_DISTANCE_CHUNKS.set(6);
						Info.SHADER_BLUR_RADIUS = 18;
						Info.TESSELLATION_SIMPLEX = true;
						Info.TESSELLATION_VERTEX = true;
						break;
					case HIGH:
						GG.RENDER_DISTANCE_CHUNKS.set(8);
						Info.SHADER_BLUR_RADIUS = 22;
						Info.TESSELLATION_SIMPLEX = true;
						Info.TESSELLATION_VERTEX = true;
						break;
					case ULTRA:
						GG.RENDER_DISTANCE_CHUNKS.set(10);
						Info.SHADER_BLUR_RADIUS = 28;
						Info.TESSELLATION_SIMPLEX = true;
						Info.TESSELLATION_VERTEX = true;
						break;
				}
				
				GG.refreshChunks();
				GConfig.forceSaveALL();
			}
		});
	}

	public static GraphicsLevel getLevel()
	{
		return GraphicsLevel.values()[R.CLIP(Info.GRAPHICS_LEVEL, 0, 4)];
	}
}
