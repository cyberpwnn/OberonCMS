package sharedcms.mutex.shared.object;

import java.util.UUID;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;

public interface ICharacter extends IEntity
{
	public EntityPlayer getPlayer();
}
