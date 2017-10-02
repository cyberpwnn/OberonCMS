package sharedcms.shuriken.api.health;

import java.util.List;

import sharedcms.shuriken.api.damage.IDamage;
import sharedcms.shuriken.api.damage.IDamageLayer;
import sharedcms.shuriken.api.damage.IDamageModifier;

public interface IHealthLayer
{
	public double getMaximum();
	
	public void setMaximum(double max);
	
	public double getCurrent();
	
	public void set(double value);
	
	public void heal(double health);
	
	public IDamage damage(IDamage damage);
	
	public IDamageLayer damage(IDamageLayer damage);
	
	public HealthType getType();
	
	public List<IDamageModifier> getModifiers();
	
	public void addModifier(IDamageModifier mod);
}
