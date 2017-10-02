package sharedcms.shuriken.api.damage;

import java.util.List;

public interface IDamage
{
	public List<IDamageModifier> getModifiers();
	
	public List<IDamageLayer> getDamage();
	
	public void addDamage(IDamageLayer layer);
	
	public void addModifier(IDamageModifier modifier);
	
	public double getTotalDamage();
	
	public void compile();
}
