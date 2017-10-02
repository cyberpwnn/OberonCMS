package sharedcms.network;

import static cpw.mods.fml.common.network.ByteBufUtils.readVarInt;
import static cpw.mods.fml.common.network.ByteBufUtils.writeVarInt;

import java.awt.Color;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import sharedcms.mutex.client.ClientHostProxy;
import sharedcms.shuriken.api.damage.DamageElement;
import sharedcms.shuriken.api.damage.IDamageModifier;
import sharedcms.shuriken.api.health.HealthType;
import sharedcms.shuriken.api.health.IHealthLayer;
import sharedcms.shuriken.api.health.IHealthPool;
import sharedcms.shuriken.damage.DamageModifier;
import sharedcms.shuriken.health.HealthLayer;
import sharedcms.shuriken.health.HealthPool;

public class PlayClientUpdateHealth implements IMessage
{
	private IHealthPool pool;

	public PlayClientUpdateHealth()
	{

	}

	public PlayClientUpdateHealth(EntityPlayer player, IHealthPool pool)
	{
		this.pool = pool;
	}

	@Override
	public void fromBytes(ByteBuf b)
	{
		IHealthPool mPool = new HealthPool();
		int layerCount = readVarInt(b, 1);

		for(int i = 0; i < layerCount; i++)
		{
			HealthType lType = HealthType.values()[readVarInt(b, 1)];
			double lCurrent = readVarInt(b, 4);
			double lMax = readVarInt(b, 4);
			int modifierCount = readVarInt(b, 1);
			IHealthLayer mLayer = new HealthLayer(lType, lMax);
			mLayer.set(lCurrent);

			for(int j = 0; j < modifierCount; j++)
			{
				DamageElement mDamageElement = DamageElement.values()[readVarInt(b, 1)];
				double mModifier = (double) readVarInt(b, 4) / 1000.0;
				IDamageModifier mDamageModifier = new DamageModifier(mDamageElement, mModifier);
				mLayer.addModifier(mDamageModifier);
			}

			mPool.addLayer(mLayer);
		}

		pool = mPool;
	}

	@Override
	public void toBytes(ByteBuf b)
	{
		writeVarInt(b, pool.getLayers().size(), 1);

		for(IHealthLayer i : pool.getLayers())
		{
			writeVarInt(b, i.getType().ordinal(), 1);
			writeVarInt(b, (int) i.getCurrent(), 4);
			writeVarInt(b, (int) i.getMaximum(), 4);
			writeVarInt(b, i.getModifiers().size(), 1);

			for(IDamageModifier j : i.getModifiers())
			{
				writeVarInt(b, j.getTargetType().ordinal(), 1);
				writeVarInt(b, (int) (j.getModifier() * 1000), 4);
			}
		}
	}

	public static class Handler extends AbstractClientMessageHandler<PlayClientUpdateHealth>
	{
		@Override
		public IMessage handleClientMessage(EntityPlayer player, PlayClientUpdateHealth message, MessageContext ctx)
		{
			ClientHostProxy.health = message.pool;

			return null;
		}
	}
}