package sharedcms.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import sharedcms.Ares;
import sharedcms.Info;
import sharedcms.network.PacketDispatcher;

public class ProxyCommon implements IProxy
{
	@Override
	public void onPreInit(FMLPreInitializationEvent e)
	{
		Info.load();
	}

	@Override
	public void onInit(FMLInitializationEvent e)
	{
		
	}

	@Override
	public void onPostInit(FMLPostInitializationEvent e)
	{
		Info.save();
	}

	public EntityPlayer getPlayerEntity(MessageContext ctx)
	{
		return ctx.getServerHandler().playerEntity;
	}
}
