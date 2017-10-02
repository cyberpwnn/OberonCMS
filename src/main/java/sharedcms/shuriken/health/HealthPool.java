package sharedcms.shuriken.health;

import java.util.ArrayList;
import java.util.List;

import sharedcms.shuriken.api.damage.IDamage;
import sharedcms.shuriken.api.damage.IDamageModifier;
import sharedcms.shuriken.api.health.HealthType;
import sharedcms.shuriken.api.health.IHealthLayer;
import sharedcms.shuriken.api.health.IHealthPool;
import sharedcms.util.GList;

public class HealthPool implements IHealthPool
{
	private List<IHealthLayer> layers;
	
	public HealthPool()
	{
		layers = new ArrayList<IHealthLayer>();
	}
	
	public List<IHealthLayer> getLayers()
	{
		return layers;
	}
	
	public double getTotalHealth()
	{
		double h = 0;
		
		for(IHealthLayer i : getLayers())
		{
			h += i.getCurrent();
		}
		
		return h;
	}
	
	public double getTotalMaximumHealth()
	{
		double h = 0;
		
		for(IHealthLayer i : getLayers())
		{
			h += i.getMaximum();
		}
		
		return h;
	}
	
	public List<IDamageModifier> getDamageModifiers()
	{
		List<IDamageModifier> modifiers = new ArrayList<IDamageModifier>();
		
		for(IHealthLayer i : getLayers())
		{
			for(IDamageModifier j : i.getModifiers())
			{
				boolean d = true;
				
				for(IDamageModifier k : modifiers)
				{
					if(k.getTargetType().equals(j.getTargetType()))
					{
						k.add(j.getModifier());
						d = false;
					}
				}
				
				if(d)
				{
					modifiers.add(j);
				}
			}
		}
		
		return modifiers;
	}
	
	public IDamage damage(IDamage damage)
	{
		for(IHealthLayer i : getLayers())
		{
			i.damage(damage);
			
			if(damage.getTotalDamage() <= 0)
			{
				return damage;
			}
		}
		
		return damage;
	}
	
	@Override
	public String toString()
	{
		String m = (int) getTotalHealth() + "/" + (int) getTotalMaximumHealth() + " ";
		
		for(IHealthLayer i : getLayers())
		{
			m += (int) i.getCurrent() + "/" + (int) i.getMaximum() + " " + i.getType().toString() + " <| ";
		}
		
		return m;
	}
	
	public void addLayer(IHealthLayer layer)
	{
		layers.add(layer);
	}

	@Override
	public List<IHealthLayer> getLayers(HealthType type)
	{
		List<IHealthLayer> l = new GList<IHealthLayer>();
		
		for(IHealthLayer i : getLayers())
		{
			if(i.getType().equals(type))
			{
				l.add(i);
			}
		}
		
		return l;
	}
}
