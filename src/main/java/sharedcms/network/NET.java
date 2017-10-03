package sharedcms.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import sharedcms.mutex.shared.object.IEntity;
import sharedcms.shuriken.api.health.IHealthPool;
import sharedcms.util.Location;

public class NET
{
	public static void updateHealth(EntityPlayer p, IHealthPool h)
	{
		s(new PlayClientUpdateHealth(p, h), p);
	}

	private static void s(IMessage m, EntityPlayer p)
	{
		PacketDispatcher.sendTo(m, (EntityPlayerMP) p);
	}

	private static void s(IMessage m, EntityPlayer p, double range)
	{
		PacketDispatcher.sendToAllAround(m, p, range);
	}

	private static void s(IMessage m, int dim, double x, double y, double z, double range)
	{
		PacketDispatcher.sendToAllAround(m, dim, x, y, z, range);
	}

	private static void s(IMessage m, int dim, Location l, double range)
	{
		PacketDispatcher.sendToAllAround(m, dim, l.x, l.y, l.z, range);
	}
}
