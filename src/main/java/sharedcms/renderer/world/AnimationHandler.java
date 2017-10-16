package sharedcms.renderer.world;

import java.lang.reflect.Field;
import java.nio.IntBuffer;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WorldRenderer;
import sharedcms.asm.ASMHandler;
import sharedcms.controller.client.ChunkAnimationController;
import sharedcms.gui.util.R;
import sharedcms.util.Location;

public class AnimationHandler
{
	static Field glRenderList;
	HashMap<Integer, RendererInfo> timeStamps = new HashMap();

	public void callLists(IntBuffer glLists)
	{
		if(glLists == null)
		{
			System.out.println("I'M NULL AS FUCK BOI");
		}

		while(glLists.hasRemaining())
		{
			int renderListID = glLists.get();
			double modY = 0.0;
			double p = 1;
			double chunkY = 0;

			if(this.timeStamps.containsKey(renderListID))
			{
				long timeDif;
				int animationDuration;
				RendererInfo renderInfo = this.timeStamps.get(renderListID);
				long time = renderInfo.timeStamp;

				if(time == -1)
				{
					time = renderInfo.timeStamp = System.currentTimeMillis();
				}

				if((timeDif = System.currentTimeMillis() - time) < (long) (animationDuration = ChunkAnimationController.INSTANCE.config.getAnimationDuration()))
				{
					chunkY = renderInfo.posY;
					int mode = ChunkAnimationController.INSTANCE.config.getMode();

					if(mode == 2)
					{
						mode = chunkY < Minecraft.getMinecraft().theWorld.provider.getHorizon() ? 0 : 1;
					}

					switch(mode)
					{
						case 0:
						{
							p = (double) timeDif / (double) animationDuration;
							modY = -chunkY + chunkY / (double) animationDuration * (double) timeDif;
							break;
						}

						case 1:
						{
							p = (double) timeDif / (double) animationDuration;
							modY = 256.0 - chunkY - (256.0 - chunkY) / (double) animationDuration * (double) timeDif;
						}
					}
				}

				else
				{
					this.timeStamps.remove(renderListID);
				}
			}

			double pv = Math.pow(p, 0.1);
			double my = modY * (1.0 - pv);

			if(modY != 0.0)
			{
				GL11.glTranslated((double) 0.0, (double) my, (double) 0.0);
			}

			GL11.glCallList((int) renderListID);

			if(modY == 0.0)
			{
				continue;
			}

			GL11.glTranslated((double) 0.0, -my, (double) 0.0);
		}
	}

	public void setPosition(WorldRenderer worldRenderer)
	{
		int renderID = -1;

		try
		{
			renderID = glRenderList.getInt((Object) worldRenderer);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		this.timeStamps.put(renderID, new RendererInfo(worldRenderer.posX, worldRenderer.posY, worldRenderer.posZ, -1));
		this.timeStamps.put(renderID + 1, new RendererInfo(worldRenderer.posX, worldRenderer.posY, worldRenderer.posZ, -1));
	}

	static
	{
		try
		{
			Field ff = null;

			try
			{
				ff = WorldRenderer.class.getDeclaredField("glRenderList");
			}

			catch(NoSuchFieldException e)
			{
				System.out.println("117 Field is not rf..? Maybe its an SRG");

				try
				{
					ff = WorldRenderer.class.getDeclaredField("field_78942_y");
				}

				catch(NoSuchFieldException ex)
				{
					System.out.println("886 CANNOT FIND FIELD");
				}
			}

			if(ff == null)
			{
				System.out.println("888 CANNOT FIND FIELD");
			}

			glRenderList = ff;
			glRenderList.setAccessible(true);
		}

		catch(Exception e)
		{

		}
	}

	private class RendererInfo
	{
		public int posX;
		public int posY;
		public int posZ;
		public long timeStamp;

		public RendererInfo(int posX, int posY, int posZ, long timeStamp)
		{
			this.posX = posX;
			this.posZ = posZ;
			this.posY = posY;
			this.timeStamp = timeStamp;
		}
	}

}
