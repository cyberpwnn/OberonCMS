package sharedcms.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import sharedcms.mutex.client.ClientHostProxy;

public class ProxyClient extends ProxyCommon
{
	private ClientHostProxy host;

	@Override
	public void onPreInit(FMLPreInitializationEvent e)
	{
		super.onPreInit(e);
		host = new ClientHostProxy();
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

	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx)
	{
		return(ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
	}
}
