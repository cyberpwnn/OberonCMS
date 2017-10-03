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
import sharedcms.network.NET;
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
}
