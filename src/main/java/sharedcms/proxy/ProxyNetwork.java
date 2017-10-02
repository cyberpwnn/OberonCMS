package sharedcms.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import sharedcms.network.PacketDispatcher;

public class ProxyNetwork implements IProxy
{
	@Override
	public void onPreInit(FMLPreInitializationEvent e)
	{
		PacketDispatcher.registerPackets();
	}

	@Override
	public void onInit(FMLInitializationEvent e)
	{
		
	}

	@Override
	public void onPostInit(FMLPostInitializationEvent e)
	{
		
	}
}
