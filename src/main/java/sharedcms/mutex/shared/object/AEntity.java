package sharedcms.mutex.shared.object;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import sharedcms.Info;
import sharedcms.shuriken.api.damage.DamageElement;
import sharedcms.shuriken.api.damage.IDamage;
import sharedcms.shuriken.api.damage.IDamageModifier;
import sharedcms.shuriken.api.health.HealthType;
import sharedcms.shuriken.api.health.IHealthLayer;
import sharedcms.shuriken.api.health.IHealthPool;
import sharedcms.shuriken.damage.Damage;
import sharedcms.shuriken.damage.DamageLayer;
import sharedcms.shuriken.damage.DamageModifier;
import sharedcms.shuriken.health.HealthLayer;
import sharedcms.shuriken.health.HealthPool;
import sharedcms.util.Location;

public class AEntity implements IEntity
{
	private IHealthPool health;
	private EntityLivingBase instance;
	private UUID uuid;
	private double regenRateBase;
	private double regenRateMultiplier;
	private double regenDelay;
	private double regenTicks;
	private double damageTicks;
	private boolean healthModified;
	private int damageTickSet;
	private int level;

	public AEntity(EntityLivingBase instance)
	{
		health = new HealthPool();
		health.addLayer(new HealthLayer(HealthType.HEALTH, 100));
		this.instance = instance;
		uuid = instance.getUniqueID();
		regenRateBase = 0.005;
		regenRateMultiplier = 0.001;
		regenDelay = 10;
		damageTicks = 0;
		regenTicks = 0;
		healthModified = true;
		damageTickSet = 0;
		level = 0;
		load();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
		{
			return true;
		}

		if(obj == null)
		{
			return false;
		}

		if(getClass() != obj.getClass())
		{
			return false;
		}

		AEntity other = (AEntity) obj;

		if(uuid == null)
		{
			if(other.uuid != null)
			{
				return false;
			}
		}

		else if(!uuid.equals(other.uuid))
		{
			return false;
		}

		return true;
	}

	@Override
	public UUID getUUID()
	{
		return uuid;
	}

	@Override
	public EntityLivingBase getEntity()
	{
		return instance;
	}

	@Override
	public IHealthPool getHealthPool()
	{
		return health;
	}

	@Override
	public void onRawDamageTaken(DamageSource source, float damage)
	{
		IDamage d = new Damage();
		damage *= 17;
		Location sourceLocation = getLocation();

		if(source.getDamageType().equals(DamageSource.anvil.damageType))
		{
			d.addDamage(new DamageLayer(DamageElement.BLUNT, damage));
		}

		else if(source.getDamageType().equals(DamageSource.cactus.damageType))
		{
			d.addDamage(new DamageLayer(DamageElement.PUNCTURE, damage / 2));
			d.addDamage(new DamageLayer(DamageElement.SLASH, damage / 2));
		}

		else if(source.getDamageType().equals(DamageSource.drown.damageType))
		{
			d.addDamage(new DamageLayer(DamageElement.FLOW, damage / 2));
			d.addDamage(new DamageLayer(DamageElement.VAMPIRIC, damage / 2));
		}

		else if(source.getDamageType().equals(DamageSource.fall.damageType))
		{
			d.addDamage(new DamageLayer(DamageElement.BLUNT, damage));
		}

		else if(source.getDamageType().equals(DamageSource.fallingBlock.damageType))
		{
			d.addDamage(new DamageLayer(DamageElement.BLUNT, damage));
		}

		else if(source.getDamageType().equals(DamageSource.generic.damageType))
		{
			d.addDamage(new DamageLayer(DamageElement.BLUNT, damage / 3));
			d.addDamage(new DamageLayer(DamageElement.PUNCTURE, damage / 3));
			d.addDamage(new DamageLayer(DamageElement.SLASH, damage / 3));
		}

		else if(source.getDamageType().equals(DamageSource.inFire.damageType))
		{
			d.addDamage(new DamageLayer(DamageElement.FIRE, damage));
		}

		else if(source.getDamageType().equals(DamageSource.inWall.damageType))
		{
			d.addDamage(new DamageLayer(DamageElement.FLOW, damage / 2));
			d.addDamage(new DamageLayer(DamageElement.VAMPIRIC, damage / 2));
		}

		else if(source.getDamageType().equals(DamageSource.lava.damageType))
		{
			d.addDamage(new DamageLayer(DamageElement.LAVA, damage));
		}

		else if(source.getDamageType().equals(DamageSource.magic.damageType))
		{
			d.addDamage(new DamageLayer(DamageElement.ENERGY, damage));
		}

		else if(source.getDamageType().equals(DamageSource.onFire.damageType))
		{
			d.addDamage(new DamageLayer(DamageElement.HEAT, damage));
		}

		else if(source.getDamageType().equals(DamageSource.outOfWorld.damageType))
		{
			d.addDamage(new DamageLayer(DamageElement.CHAOS, damage));
		}

		else if(source.getDamageType().equals(DamageSource.starve.damageType))
		{
			d.addDamage(new DamageLayer(DamageElement.VAMPIRIC, damage));
		}

		else if(source.getDamageType().equals(DamageSource.wither.damageType))
		{
			d.addDamage(new DamageLayer(DamageElement.DEMONIC, damage));
		}

		else if(source.isExplosion())
		{
			d.addDamage(new DamageLayer(DamageElement.EXPLOSIVE, damage));
		}

		else if(source.isProjectile())
		{
			d.addDamage(new DamageLayer(DamageElement.PUNCTURE, damage));
		}

		else if(source instanceof EntityDamageSourceIndirect)
		{
			EntityDamageSourceIndirect sx = (EntityDamageSourceIndirect) source;

			if(sx.getEntity() instanceof EntityLiving)
			{
				EntityLiving e = (EntityLiving) sx.getEntity();
				d.addDamage(new DamageLayer(DamageElement.BLUNT, damage));
				sourceLocation = new Location(e);
			}
		}

		else
		{
			d.addDamage(new DamageLayer(DamageElement.BLUNT, damage / 3));
			d.addDamage(new DamageLayer(DamageElement.PUNCTURE, damage / 3));
			d.addDamage(new DamageLayer(DamageElement.SLASH, damage / 3));
		}

		d.compile();
		takeDamage(d, sourceLocation);
	}

	@Override
	public void tick(int tick)
	{
		regenHealth();

		if(damageTickSet > 0)
		{
			damageTickSet--;
		}

		if(tick % 20 == 0)
		{
			save();
		}
	}

	public void regenHealth()
	{
		if(damageTicks > 0)
		{
			damageTicks--;
			return;
		}

		for(IHealthLayer i : health.getLayers())
		{
			if(i.getCurrent() < i.getMaximum() && i.getType().equals(HealthType.ENERGY))
			{
				regenTicks++;
				double regen = regenRateBase + (regenTicks * regenRateMultiplier);
				i.heal(regen);
				healthModified = true;
			}
		}
	}

	@Override
	public void takeDamage(IDamage damage, Location l)
	{
		if(damageTickSet > 0)
		{
			return;
		}

		getEntity().worldObj.playSoundEffect(getEntity().posX, getEntity().posY, getEntity().posZ, "game.neutral.hurt", 0.6f, (float) (1f + ((Math.random() - 1.5) * 2) * 0.3));
		damageTickSet = 10;
		damageTicks = regenDelay;
		regenTicks = 0;
		healthModified = true;
		health.damage(damage);

		if(health.getTotalHealth() == 0)
		{
			die();
		}
	}

	@Override
	public void die()
	{
		getEntity().setDead();
	}

	@Override
	public Location getLocation()
	{
		return new Location(getEntity().posX, getEntity().posY, getEntity().posZ);
	}

	@Override
	public void takeDamage(IDamage damage)
	{
		takeDamage(damage, getLocation());
	}

	@Override
	public NBTTagCompound getNBT()
	{
		NBTTagCompound t = new NBTTagCompound();
		getEntity().writeToNBT(t);

		return t;
	}

	@Override
	public void setNBT(NBTTagCompound t)
	{
		getEntity().readFromNBT(t);
	}

	@Override
	public void writeNBT(String key, double d)
	{
		NBTTagCompound t = getNBT();
		t.setDouble(key, d);
		setNBT(t);
	}

	@Override
	public void writeNBT(String key, int d)
	{
		NBTTagCompound t = getNBT();
		t.setInteger(key, d);
		setNBT(t);
	}

	@Override
	public void writeNBT(String key, boolean d)
	{
		NBTTagCompound t = getNBT();
		t.setBoolean(key, d);
		setNBT(t);
	}

	@Override
	public void writeNBT(String key, String d)
	{
		NBTTagCompound t = getNBT();
		t.setString(key, d);
		setNBT(t);
	}

	@Override
	public void writeNBT(String key, byte[] d)
	{
		NBTTagCompound t = getNBT();
		t.setByteArray(key, d);
		setNBT(t);
	}

	@Override
	public double readNBTDouble(String key)
	{
		return getNBT().getDouble(key);
	}

	@Override
	public int readNBTInt(String key)
	{
		return getNBT().getInteger(key);
	}

	@Override
	public boolean readNBTBoolean(String key)
	{
		return getNBT().getBoolean(key);
	}

	@Override
	public String readNBTString(String key)
	{
		return getNBT().getString(key);
	}

	@Override
	public byte[] readNBTByteArray(String key)
	{
		return getNBT().getByteArray(key);
	}

	@Override
	public boolean hasNBT(String tag)
	{
		return getNBT().hasKey(tag);
	}

	public static int readVarInt(InputStream buf, int maxSize)
	{
		try
		{
			int i = 0;
			int j = 0;
			byte b0;

			do
			{
				b0 = (byte) buf.read();
				i |= (b0 & 127) << j++ * 7;

				if(j > maxSize)
				{
					throw new RuntimeException("VarInt too big");
				}
			}

			while((b0 & 128) == 128);

			return i;
		}

		catch(Exception e)
		{
			return -1;
		}
	}

	public static void writeVarInt(OutputStream to, int toWrite, int maxSize)
	{
		try
		{
			while((toWrite & -128) != 0)
			{
				to.write(toWrite & 127 | 128);
				toWrite >>>= 7;
			}

			to.write(toWrite);
		}

		catch(Exception e)
		{

		}
	}

	@Override
	public void load()
	{
		if(hasNBT("cms-health"))
		{
			ByteArrayInputStream b = new ByteArrayInputStream(readNBTByteArray("cms-health"));
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

			health = mPool;
		}

		if(hasNBT("cms-level"))
		{
			level = readNBTInt("cms-level");
		}

		if(hasNBT("cms-regenbase"))
		{
			regenRateBase = readNBTDouble("cms-regenbase");
		}

		if(hasNBT("cms-regenmult"))
		{
			regenRateMultiplier = readNBTDouble("cms-regenmult");
		}

		if(hasNBT("cms-regendelay"))
		{
			regenDelay = readNBTDouble("cms-regendelay");
		}
	}

	@Override
	public void save()
	{
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		IHealthPool pool = getHealthPool();

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

		writeNBT("cms-health", b.toByteArray());
		writeNBT("cms-level", (int) level);
		writeNBT("cms-regenbase", (double) regenRateBase);
		writeNBT("cms-regenmult", (double) regenRateMultiplier);
		writeNBT("cms-regendelay", (double) regenDelay);
	}

	@Override
	public int getLevel()
	{
		return level;
	}

	@Override
	public void setLevel(int level)
	{
		this.level = level;
		health.getLayers(HealthType.HEALTH).get(0).setMaximum(100 + ((double) level * Info.LEVEL_HP_MULTIPLIER));
		save();
	}
}
