package sharedcms.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import sharedcms.mutex.server.ServerHostProxy;

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
