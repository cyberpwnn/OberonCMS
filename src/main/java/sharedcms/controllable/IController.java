package sharedcms.controllable;

import cpw.mods.fml.relauncher.Side;
import sharedcms.util.GList;

public interface IController
{
	public String getControllerName();
	
	public Side getControllerSide();
	
	public void onPreInitialization();
	
	public void onInitialization();
	
	public void onPostInitialization();
}
