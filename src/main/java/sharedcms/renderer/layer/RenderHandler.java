package sharedcms.renderer.layer;

import java.awt.Color;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import sharedcms.Colors;
import sharedcms.mutex.client.ClientHostProxy;
import sharedcms.shuriken.api.health.HealthType;
import sharedcms.shuriken.api.health.IHealthLayer;
import sharedcms.util.F;
import sharedcms.util.PlayerUtils;

public class RenderHandler
{
	private Minecraft mc;

	public RenderHandler()
	{
		mc = Minecraft.getMinecraft();
	}

	@SubscribeEvent
	public void on(RenderGameOverlayEvent.Pre e)
	{
		if(PlayerUtils.getGameMode().equals(GameType.SURVIVAL))
		{
			if(e.type.equals(ElementType.HEALTHMOUNT))
			{
				e.setCanceled(true);
			}

			if(e.type.equals(ElementType.JUMPBAR))
			{
				e.setCanceled(true);
			}

			if(e.type.equals(ElementType.HEALTH))
			{
				e.setCanceled(true);

				new RenderLayerVitality(ClientHostProxy.health);
			}

			if(e.type.equals(ElementType.ARMOR))
			{
				e.setCanceled(true);
				// Render better armor bar
			}

			if(e.type.equals(ElementType.AIR))
			{
				e.setCanceled(true);
				// No one ever wanted this anyways
			}

			if(e.type.equals(ElementType.FOOD))
			{
				e.setCanceled(true);
				// I'm not hungry anymore
			}

			if(e.type.equals(ElementType.EXPERIENCE))
			{
				e.setCanceled(true);
				// I'm not experienced at this either
			}

			if(e.type.equals(ElementType.HOTBAR))
			{
				e.setCanceled(true);
				// No one even wanted this either
			}
		}

		if(e.type.equals(ElementType.DEBUG))
		{
			new RenderLayerDebug();
		}
	}

	@SubscribeEvent
	public void onRenderGui(RenderGameOverlayEvent.Post e)
	{

	}

	@SubscribeEvent
	public void onRenderGui(RenderGameOverlayEvent.Text e)
	{

	}
}
