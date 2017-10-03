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
	public void load();
	
	public void save();
	
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

	public NBTTagCompound getNBT();

	public boolean hasNBT(String tag);
	
	public void setNBT(NBTTagCompound t);

	public void writeNBT(String key, double d);

	public void writeNBT(String key, int d);

	public void writeNBT(String key, boolean d);

	public void writeNBT(String key, String d);

	public void writeNBT(String key, byte[] d);

	public byte[] readNBTByteArray(String key);
	
	public double readNBTDouble(String key);

	public int readNBTInt(String key);

	public boolean readNBTBoolean(String key);

	public String readNBTString(String key);
	
	public int getLevel();
	
	public void setLevel(int level);
}
