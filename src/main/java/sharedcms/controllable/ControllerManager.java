package sharedcms.controllable;

import java.lang.reflect.InvocationTargetException;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import sharedcms.proxy.IProxy;
import sharedcms.util.GList;

public abstract class ControllerManager implements IProxy
{
	private GList<IController> controllers;
	private Side side;

	public ControllerManager(Side side)
	{
		this.side = side;
		controllers = new GList<IController>();

		buildControlledLink();

		if(side.equals(Side.CLIENT))
		{
			buildControlledClient();
		}

		if(side.equals(Side.SERVER))
		{
			buildControlledServer();
		}
	}

	public void register(IController controller)
	{
		controllers.add(controller);
	}

	public abstract void buildControlledLink();

	public abstract void buildControlledClient();

	public abstract void buildControlledServer();

	@Override
	public void onPreInit(FMLPreInitializationEvent e)
	{
		for(IController i : controllers)
		{
			i.onPreInitialization();
		}
	}

	@Override
	public void onInit(FMLInitializationEvent e)
	{
		for(IController i : controllers)
		{
			i.onInitialization();
		}
	}

	@Override
	public void onPostInit(FMLPostInitializationEvent e)
	{
		for(IController i : controllers)
		{
			i.onPostInitialization();
		}
	}

}
