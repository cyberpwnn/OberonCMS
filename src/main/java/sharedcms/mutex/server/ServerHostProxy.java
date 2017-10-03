package sharedcms.mutex.server;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import sharedcms.Info;
import sharedcms.proxy.IProxy;

public class ServerHostProxy implements IProxy
{
	@Override
	public void onPreInit(FMLPreInitializationEvent e)
	{
		
	}

	@Override
	public void onInit(FMLInitializationEvent e)
	{

	}

	@Override
	public void onPostInit(FMLPostInitializationEvent e)
	{
		System.out.println("SERVER HOST ONLINE");
	}

	@SubscribeEvent
	public void on(LivingUpdateEvent e)
	{
		if(e.entityLiving instanceof EntityPlayerMP)
		{
			if(Info.TESSELLATION_STEP)
			{
				e.entityLiving.noClip = true;
			}
		}
	}
}
