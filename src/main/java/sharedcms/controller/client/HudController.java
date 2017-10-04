package sharedcms.controller.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import sharedcms.controllable.Controller;
import sharedcms.renderer.layer.RenderLayerDebug;
import sharedcms.util.PlayerUtils;

public class HudController extends Controller
{
	private Minecraft mc;

	public HudController()
	{
		mc = Minecraft.getMinecraft();
	}

	@Override
	public void onPreInitialization()
	{

	}

	@Override
	public void onInitialization()
	{

	}

	@Override
	public void onPostInitialization()
	{

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
