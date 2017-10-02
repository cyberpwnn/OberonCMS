/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiIngame
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.RenderGameOverlayEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Post
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Pre
 *  net.minecraftforge.client.event.RenderPlayerEvent
 *  net.minecraftforge.client.event.RenderPlayerEvent$Pre
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GL14
 *  org.lwjgl.util.vector.Vector2f
 */
package sharedcms.renderer.camera;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import sharedcms.renderer.camera.ShoulderLoader;
import sharedcms.renderer.camera.ShoulderSettings;
import sharedcms.renderer.camera.renderer.ShoulderRenderBin;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.util.vector.Vector2f;

public class ShoulderEvents
{
	private static float lastX = 0.0f;
	private static float lastY = 0.0f;

	@SubscribeEvent
	public void postRenderCrosshairs(RenderGameOverlayEvent.Post event)
	{
		if(event.type == RenderGameOverlayEvent.ElementType.CROSSHAIRS)
		{
			// empty if block
		}
	}

	@SubscribeEvent
	public void preRenderPlayer(RenderPlayerEvent.Pre event)
	{
		if(ShoulderRenderBin.skipPlayerRender && event.isCancelable())
		{
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void preRenderCrosshairs(RenderGameOverlayEvent.Pre event)
	{
		if(event.type == RenderGameOverlayEvent.ElementType.CROSSHAIRS)
		{
			if(!ShoulderSettings.IS_DYNAMIC_CROSSHAIR_ENABLED)
			{
				return;
			}
			float tick = event.partialTicks;
			GuiIngame g = ShoulderLoader.mc.ingameGUI;
			ScaledResolution sr = new ScaledResolution(ShoulderLoader.mc, ShoulderLoader.mc.displayWidth, ShoulderLoader.mc.displayHeight);
			if(ShoulderLoader.mc.gameSettings.thirdPersonView == 0)
			{
				lastX = sr.getScaledWidth() * sr.getScaleFactor() / 2;
				lastY = sr.getScaledHeight() * sr.getScaleFactor() / 2;
				this.bind(Gui.icons);
				g.drawTexturedModalRect(sr.getScaledWidth() / 2 - 7, sr.getScaledHeight() / 2 - 7, 0, 0, 16, 16);
			}
			else if(ShoulderLoader.mc.gameSettings.thirdPersonView == 1)
			{
				if(ShoulderRenderBin.projectedVector != null)
				{
					GL11.glEnable((int) 3042);
					this.bind(Gui.icons);
					if(ShoulderRenderBin.rayTraceInReach)
					{
						GL14.glBlendColor((float) 0.2f, (float) 0.2f, (float) 1.0f, (float) 1.0f);
						GL11.glBlendFunc((int) 775, (int) 32769);
					}
					else
					{
						GL14.glBlendColor((float) 1.0f, (float) 0.2f, (float) 0.2f, (float) 1.0f);
						GL11.glBlendFunc((int) 775, (int) 32769);
					}
					float diffX = (ShoulderRenderBin.projectedVector.x - lastX) * tick;
					float diffY = (ShoulderRenderBin.projectedVector.y - lastY) * tick;
					g.drawTexturedModalRect((int) ((lastX + diffX / 10) / (float) sr.getScaleFactor() - 7.0f), (int) ((lastY + diffY / 10) / (float) sr.getScaleFactor() - 7.0f), 0, 0, 16, 16);
					lastX += diffX / 10;
					lastY += diffY / 10;
					GL11.glDisable((int) 3042);
				}
				else if(ShoulderSettings.TRACE_TO_HORIZON_LAST_RESORT)
				{
					this.bind(Gui.icons);
					GL11.glEnable((int) 3042);
					GL14.glBlendColor((float) 1.0f, (float) 0.2f, (float) 0.2f, (float) 1.0f);
					GL11.glBlendFunc((int) 775, (int) 32769);
					float diffX = ((float) (sr.getScaledWidth() * sr.getScaleFactor() / 2) - lastX) * tick;
					float diffY = ((float) (sr.getScaledHeight() * sr.getScaleFactor() / 2) - lastY) * tick;
					g.drawTexturedModalRect((int) ((lastX + diffX) / (float) sr.getScaleFactor() - 7.0f), (int) ((lastY + diffY) / (float) sr.getScaleFactor() - 7.0f), 0, 0, 16, 16);
					lastX += diffX;
					lastY += diffY;
					GL11.glDisable((int) 3042);
				}
			}
			if(event.isCancelable())
			{
				event.setCanceled(true);
			}
		}
	}

	private void bind(ResourceLocation res)
	{
		ShoulderLoader.mc.getTextureManager().bindTexture(res);
	}
}
