package sharedcms.controller.shared;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;
import sharedcms.controllable.Controller;
import sharedcms.network.PacketDispatcher;

public class NetworkController extends Controller
{
	public NetworkController()
	{

	}

	@Override
	public void onPreInitialization()
	{
		PacketDispatcher.registerPackets();
	}

	@Override
	public void onInitialization()
	{

	}

	@Override
	public void onPostInitialization()
	{

	}
}
