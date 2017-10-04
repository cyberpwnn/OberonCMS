package sharedcms.controllable;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;
import sharedcms.Ares;

public abstract class Controller implements IController
{
	private Side side;

	public Controller()
	{
		side = Ares.side;
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
	}

	@Override
	public String getControllerName()
	{
		return getClass().getSimpleName();
	}

	@Override
	public Side getControllerSide()
	{
		return side;
	}
}
