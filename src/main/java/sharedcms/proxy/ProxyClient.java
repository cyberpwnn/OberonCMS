package sharedcms.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import sharedcms.asm.util.Clicker;
import sharedcms.mutex.client.ClientHostProxy;
import sharedcms.renderer.animation.MoBends;
import sharedcms.renderer.camera.ShoulderSurfing;
import sharedcms.renderer.layer.RenderHandler;

public class ProxyClient extends ProxyCommon
{
	private MoBends bends;
	private ShoulderSurfing ss;
	private RenderHandler renderHandler;
	private ClientHostProxy host;

	@Override
	public void onPreInit(FMLPreInitializationEvent e)
	{
		super.onPreInit(e);
		ss = new ShoulderSurfing();
		bends = new MoBends();
		host = new ClientHostProxy();
		renderHandler = new RenderHandler();
		MinecraftForge.EVENT_BUS.register(host);
		FMLCommonHandler.instance().bus().register(host);
		MinecraftForge.EVENT_BUS.register(renderHandler);
		ss.onPreInit(e);
		bends.onPreInit(e);
		host.onPreInit(e);
		Clicker.clip();
	}

	@Override
	public void onInit(FMLInitializationEvent e)
	{
		super.onInit(e);
		ss.onInit(e);
		host.onInit(e);
		Clicker.clip();
	}

	@Override
	public void onPostInit(FMLPostInitializationEvent e)
	{
		super.onPostInit(e);
		ss.onPostInit(e);
		host.onPostInit(e);
		Clicker.clip();
	}

	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx)
	{
		return(ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
	}
}
