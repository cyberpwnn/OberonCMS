package sharedcms.controller.client;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.sound.PlaySoundSourceEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.world.BlockEvent;
import sharedcms.audio.DSound;
import sharedcms.audio.SFX;
import sharedcms.base.BSM;
import sharedcms.content.Content;
import sharedcms.controllable.Controller;
import sharedcms.util.C;
import sharedcms.util.Location;
import sharedcms.util.M;
import sharedcms.util.PlayerUtils;

public class ClientController extends Controller
{
	public ClientController()
	{

	}

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
