package sharedcms.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;

public interface IProxy
{
	public void onPreInit(FMLPreInitializationEvent e);
	
	public void onInit(FMLInitializationEvent e);
	
	public void onPostInit(FMLPostInitializationEvent e);
}
