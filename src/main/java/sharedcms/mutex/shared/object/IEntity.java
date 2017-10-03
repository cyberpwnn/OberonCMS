package sharedcms.mutex.shared.object;

import java.util.UUID;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import sharedcms.shuriken.api.damage.IDamage;
import sharedcms.shuriken.api.health.IHealthPool;
import sharedcms.util.Location;

public interface IEntity
{
	public IHealthPool getHealthPool();

	public void onRawDamageTaken(DamageSource source, float damage);

	public void tick(int tick);

	public void takeDamage(IDamage damage, Location l);

	public void takeDamage(IDamage damage);

	public void regenHealth();

	public void die();

	public UUID getUUID();

	public EntityLivingBase getEntity();

	public Location getLocation();
}
