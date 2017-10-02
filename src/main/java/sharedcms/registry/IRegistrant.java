package sharedcms.registry;

import cpw.mods.fml.relauncher.Side;
import sharedcms.proxy.ProxyCMS;

public interface IRegistrant
{
	public void onPreRegister(ProxyCMS cms, Side side);
}
