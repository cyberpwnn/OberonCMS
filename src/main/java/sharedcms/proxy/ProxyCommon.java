package sharedcms.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import sharedcms.Ares;
import sharedcms.Info;
import sharedcms.asm.util.Clicker;
import sharedcms.mutex.shared.SharedHostProxy;
import sharedcms.network.PacketDispatcher;
import sharedcms.registry.IRegistrant;

public class ProxyCommon implements IProxy
{
	private ProxyCMS cms;
	private ProxyVoxel voxel;
	private SharedHostProxy host;

	@Override
	public void onPreInit(FMLPreInitializationEvent e)
	{
		Info.load();
		host = new SharedHostProxy();
		voxel = new ProxyVoxel();
		cms = new ProxyCMS();
		PacketDispatcher.registerPackets();

		for(IRegistrant i : ProxyCMS.getRegistrants())
		{
			i.onPreRegister(cms, e.getSide());
		}

		voxel.onPreInit(e);
		cms.onPreInit(e);
		host.onPreInit(e);
		MinecraftForge.EVENT_BUS.register(Ares.proxy);
		MinecraftForge.EVENT_BUS.register(host);
		FMLCommonHandler.instance().bus().register(host);
		FMLCommonHandler.instance().bus().register(Ares.proxy);
		System.out.println("Register proxy events " + Ares.proxy.getClass().toString());
		Clicker.clip();
	}

	@Override
	public void onInit(FMLInitializationEvent e)
	{
		cms.onInit(e);
		host.onInit(e);
	}

	@Override
	public void onPostInit(FMLPostInitializationEvent e)
	{
		cms.onPostInit(e);
		host.onPostInit(e);
		Info.save();
	}

	public EntityPlayer getPlayerEntity(MessageContext ctx)
	{
		return ctx.getServerHandler().playerEntity;
	}
}
