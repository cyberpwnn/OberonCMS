package sharedcms.mutex.shared.object;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import sharedcms.shuriken.api.damage.IDamage;
import sharedcms.shuriken.api.health.IHealthPool;
import sharedcms.util.SuperLocation;

public interface IPlayer
{
	public UUID getUUID();

	public EntityPlayer getEntity();

	public IHealthPool getHealthPool();

	public void onRawDamageTaken(DamageSource source, float damage);

	public void tick(int tick);

	public void updateHealth();

	public void takeDamage(IDamage damage, SuperLocation l);
	
	public SuperLocation getLocation();
	
	public void takeDamage(IDamage damage);

	public void regenHealth();
		
	public void die();
}
