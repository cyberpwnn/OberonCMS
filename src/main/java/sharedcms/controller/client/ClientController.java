package sharedcms.controller.client;

import org.lwjgl.opengl.Display;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.event.world.BlockEvent;
import sharedcms.controllable.Controller;
import sharedcms.util.C;
import sharedcms.util.PlayerUtils;

public class ClientController extends Controller
{
	@Override
	public void onPreInitialization()
	{
		for(Object i : GameData.getBlockRegistry())
		{
			Block b = (Block) i;
			b.setBlockUnbreakable();
		}
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
	public void on(DrawBlockHighlightEvent e)
	{
		if(Minecraft.getMinecraft().thePlayer == null)
		{
			return;
		}

		if(PlayerUtils.getGameMode().equals(GameType.SURVIVAL))
		{
			e.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void on(BlockEvent.PlaceEvent e)
	{
		if(Minecraft.getMinecraft().thePlayer == null)
		{
			return;
		}

		if(PlayerUtils.getGameMode().equals(GameType.SURVIVAL))
		{
			e.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void on(BlockEvent.BreakEvent e)
	{
		if(Minecraft.getMinecraft().thePlayer == null)
		{
			return;
		}

		if(PlayerUtils.getGameMode().equals(GameType.SURVIVAL))
		{
			e.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void on(ClientTickEvent e)
	{
		C.tick++;

		if(Minecraft.getMinecraft().thePlayer == null)
		{
			return;
		}

		if(PlayerUtils.getGameMode().equals(GameType.SURVIVAL))
		{
			PlayerUtils.denyBreaking();
		}
	}
}
