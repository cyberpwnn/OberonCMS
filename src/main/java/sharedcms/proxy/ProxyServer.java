package sharedcms.proxy;

import java.util.UUID;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import sharedcms.mutex.server.ServerHostProxy;
import sharedcms.network.PacketDispatcher;
import sharedcms.network.PlayClientUpdateHealth;
import sharedcms.util.GMap;

public class ProxyServer extends ProxyCommon
{
	private ServerHostProxy host;

	@Override
	public void onPreInit(FMLPreInitializationEvent e)
	{
		super.onPreInit(e);
		host = new ServerHostProxy();
		MinecraftForge.EVENT_BUS.register(host);
		FMLCommonHandler.instance().bus().register(host);
		host.onPreInit(e);
	}

	@Override
	public void onInit(FMLInitializationEvent e)
	{
		super.onInit(e);
		host.onInit(e);
	}

	@Override
	public void onPostInit(FMLPostInitializationEvent e)
	{
		super.onPostInit(e);
		host.onPostInit(e);
	}
}
