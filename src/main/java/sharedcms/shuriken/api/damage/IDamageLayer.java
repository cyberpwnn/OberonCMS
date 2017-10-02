package sharedcms.shuriken.api.damage;

public interface IDamageLayer
{
	public DamageElement getType();
	
	public double getDamage();
	
	public void add(double damage);
	
	public void set(double i);
}
