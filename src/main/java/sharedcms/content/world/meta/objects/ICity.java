package sharedcms.content.world.meta.objects;

import java.util.List;

public interface ICity extends IMetaObject
{
	public List<IVillage> getVillages();
	
	public void addVillage(IVillage village);
}
