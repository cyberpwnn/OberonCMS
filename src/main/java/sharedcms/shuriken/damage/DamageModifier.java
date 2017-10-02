package sharedcms.shuriken.damage;

import sharedcms.shuriken.api.damage.DamageElement;
import sharedcms.shuriken.api.damage.IDamageModifier;

public class DamageModifier implements IDamageModifier
{
	private DamageElement target;
	private double modifier;
	
	public DamageModifier(DamageElement target, double modifier)
	{
		this.target = target;
		this.modifier = modifier;
	}
	
	public DamageElement getTargetType()
	{
		return target;
	}
	
	public double getModifier()
	{
		return modifier;
	}
	
	public void add(double mod)
	{
		modifier += mod;
	}
}
