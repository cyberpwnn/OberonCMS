package sharedcms.shuriken.api.damage;

public interface IDamageModifier
{
	public DamageElement getTargetType();
	
	public double getModifier();
	
	public void add(double mod);
}
