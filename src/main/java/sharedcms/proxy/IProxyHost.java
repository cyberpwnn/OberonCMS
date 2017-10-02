package sharedcms.proxy;

import java.util.List;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public interface IProxyHost
{
	public void addSubproxy(IProxy proxy);
	
	public List<IProxy> getSubproxies();
	
	public void callPreInit(FMLPreInitializationEvent e);
	
	public void callInit(FMLInitializationEvent e);
	
	public void callPostInit(FMLPostInitializationEvent e);
}
