package sharedcms.mutex.shared.object;

import java.util.UUID;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.IChatComponent;
import sharedcms.network.NET;
import sharedcms.shuriken.api.damage.DamageElement;
import sharedcms.shuriken.api.damage.IDamage;
import sharedcms.shuriken.api.health.HealthType;
import sharedcms.shuriken.api.health.IHealthLayer;
import sharedcms.shuriken.api.health.IHealthPool;
import sharedcms.shuriken.damage.Damage;
import sharedcms.shuriken.damage.DamageLayer;
import sharedcms.shuriken.health.HealthLayer;
import sharedcms.shuriken.health.HealthPool;
import sharedcms.util.SuperLocation;

public class Player implements IPlayer
{
	private IHealthPool health;
	private EntityPlayer instance;
	private UUID uuid;
	private double regenRateBase;
	private double regenRateMultiplier;
	private double regenDelay;
	private double regenTicks;
	private double damageTicks;
	private boolean healthModified;
	private int damageTickSet;

	public Player(EntityPlayer instance)
	{
		health = new HealthPool();
		IHealthLayer defaultShields = new HealthLayer(HealthType.ENERGY, 1000);
		IHealthLayer defaultArmor = new HealthLayer(HealthType.ARMOR, 200);
		IHealthLayer defaultVitality = new HealthLayer(HealthType.HEALTH, 1800);
		health.addLayer(defaultShields);
		health.addLayer(defaultArmor);
		health.addLayer(defaultVitality);
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

		Player other = (Player) obj;

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
	public EntityPlayer getEntity()
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
		SuperLocation sourceLocation = getLocation();
		
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
				sourceLocation = new SuperLocation(e);
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

		if(tick % 4 == 0 && healthModified)
		{
			updateHealth();
		}

		if(tick % 4 == 0 && getEntity().getFoodStats().needFood())
		{
			getEntity().getFoodStats().addStats(20, 2f);
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
	public void updateHealth()
	{
		NET.updateHealth(getEntity(), getHealthPool());
		healthModified = false;
	}

	@Override
	public void takeDamage(IDamage damage, SuperLocation l)
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

	public void sendMessage(String text)
	{
		sendMessage(new ChatComponentText(text));
	}

	public void sendMessage(IChatComponent chat)
	{
		getEntity().addChatMessage(chat);
	}

	@Override
	public void die()
	{
		getEntity().setDead();
	}

	@Override
	public SuperLocation getLocation()
	{
		return new SuperLocation(getEntity().posX, getEntity().posY, getEntity().posZ);
	}

	@Override
	public void takeDamage(IDamage damage)
	{
		takeDamage(damage, getLocation());
	}
}
