package sharedcms.registry;

import cpw.mods.fml.relauncher.Side;
import sharedcms.controller.shared.ContentController;

public interface IRegistrant
{
	public void onPreRegister(ContentController cms, Side side);
}
