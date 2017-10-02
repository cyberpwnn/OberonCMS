package sharedcms.shuriken.damage;

import sharedcms.shuriken.api.damage.DamageElement;
import sharedcms.shuriken.api.damage.IDamageLayer;

public class DamageLayer implements IDamageLayer
{
	private DamageElement element;
	private double damage;
	
	public DamageLayer(DamageElement element, double damage)
	{
		this.element = element;
		this.damage = damage;
	}
	
	public DamageElement getType()
	{
		return element;
	}
	
	public double getDamage()
	{
		return damage;
	}
	
	public void add(double damage)
	{
		this.damage += damage;
	}
	
	public void set(double i)
	{
		damage = i;
	}
}
