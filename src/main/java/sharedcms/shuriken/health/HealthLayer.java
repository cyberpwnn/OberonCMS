package sharedcms.shuriken.health;

import java.util.ArrayList;
import java.util.List;

import sharedcms.shuriken.api.damage.IDamage;
import sharedcms.shuriken.api.damage.IDamageLayer;
import sharedcms.shuriken.api.damage.IDamageModifier;
import sharedcms.shuriken.api.health.HealthType;
import sharedcms.shuriken.api.health.IHealthLayer;
import sharedcms.shuriken.damage.Damage;

public class HealthLayer implements IHealthLayer
{
	private double maximum;
	private double current;
	private HealthType type;
	private List<IDamageModifier> modifiers;
	
	public HealthLayer(HealthType type, double maximum)
	{
		this.maximum = maximum;
		this.type = type;
		set(maximum);
		modifiers = new ArrayList<IDamageModifier>();
	}
	
	public double getMaximum()
	{
		return maximum;
	}
	
	public double getCurrent()
	{
		return current;
	}
	
	public void set(double value)
	{
		current = value;
	}
	
	public void heal(double value)
	{
		if(getCurrent() + value < 0)
		{
			set(0);
		}
		
		else if(getCurrent() + value > getMaximum())
		{
			set(getMaximum());
		}
		
		else
		{
			set(getCurrent() + value);
		}
	}
	
	public IDamage damage(IDamage damage)
	{
		IDamage d = new Damage();
		
		for(IDamageLayer i : damage.getDamage())
		{
			damage(i);
			
			if(i.getDamage() > 0)
			{
				d.addDamage(i);
			}
		}
		
		return d;
	}
	
	public IDamageLayer damage(IDamageLayer damage)
	{
		if(getCurrent() > 0)
		{
			double ed = damage.getDamage();
			
			for(IDamageModifier i : getModifiers())
			{
				if(i.getTargetType().equals(damage.getType()))
				{
					ed += (damage.getDamage() * -i.getModifier());
				}
			}
			
			if(getCurrent() - ed <= 0)
			{
				set(0);
				ed = ed - getCurrent();
			}
			
			else
			{
				set(getCurrent() - ed);
				ed = 0;
			}
			
			damage.set(ed);
			
			return damage;
		}
		
		return damage;
	}
	
	public HealthType getType()
	{
		return type;
	}
	
	public void setMaximum(double max)
	{
		maximum = max;
	}
	
	public List<IDamageModifier> getModifiers()
	{
		return modifiers;
	}
	
	public void addModifier(IDamageModifier mod)
	{
		for(IDamageModifier i : modifiers)
		{
			if(i.getTargetType().equals(mod.getTargetType()))
			{
				i.add(mod.getModifier());
				return;
			}
		}
		
		modifiers.add(mod);
	}
}
