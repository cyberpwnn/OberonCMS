package sharedcms.shuriken.api.health;

import java.util.List;

import sharedcms.shuriken.api.damage.IDamage;
import sharedcms.shuriken.api.damage.IDamageModifier;

public interface IHealthPool
{
	public List<IHealthLayer> getLayers();
	
	public List<IHealthLayer> getLayers(HealthType type);
	
	public double getTotalHealth();
	
	public double getTotalMaximumHealth();
	
	public List<IDamageModifier> getDamageModifiers();
	
	public IDamage damage(IDamage damage);
	
	public void addLayer(IHealthLayer layer);
}
