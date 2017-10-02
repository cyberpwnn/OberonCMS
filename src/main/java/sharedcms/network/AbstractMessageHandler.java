package sharedcms.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import sharedcms.Ares;

public abstract class AbstractMessageHandler<T extends IMessage> implements IMessageHandler<T, IMessage>
{
	/**
	 * Handle a message received on the client side
	 * 
	 * @return a message to send back to the Server, or null if no reply is
	 *         necessary
	 */
	@SideOnly(Side.CLIENT)
	public abstract IMessage handleClientMessage(EntityPlayer player, T message, MessageContext ctx);

	/**
	 * Handle a message received on the server side
	 * 
	 * @return a message to send back to the Client, or null if no reply is
	 *         necessary
	 */
	public abstract IMessage handleServerMessage(EntityPlayer player, T message, MessageContext ctx);

	/*
	 * Here is where I parse the side and get the player to pass on to the abstract
	 * methods. This way it is immediately clear which side received the packet
	 * without having to remember or check on which side it was registered and the
	 * player is immediately available without a lengthy syntax.
	 */
	@Override
	public IMessage onMessage(T message, MessageContext ctx)
	{
		if(ctx.side.isClient())
		{
			return handleClientMessage(Ares.instance.proxy.getPlayerEntity(ctx), message, ctx);
		}
		
		else
		{
			return handleServerMessage(Ares.instance.proxy.getPlayerEntity(ctx), message, ctx);
		}
	}
}