package sharedcms.mutex.shared.object;

import net.minecraft.entity.player.EntityPlayer;

public class ACharacter extends AEntity implements ICharacter
{
	public ACharacter(EntityPlayer instance)
	{
		super(instance);
	}

	@Override
	public EntityPlayer getPlayer()
	{
		return (EntityPlayer) getEntity();
	}
}
