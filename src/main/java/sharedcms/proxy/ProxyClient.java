package sharedcms.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class ProxyClient extends ProxyCommon
{
	@Override
	public void onPreInit(FMLPreInitializationEvent e)
	{
		super.onPreInit(e);
	}

	@Override
	public void onInit(FMLInitializationEvent e)
	{
		super.onInit(e);
	}

	@Override
	public void onPostInit(FMLPostInitializationEvent e)
	{
		super.onPostInit(e);
	}

	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx)
	{
		return(ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
	}
}
