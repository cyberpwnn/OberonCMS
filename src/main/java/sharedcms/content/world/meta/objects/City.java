package sharedcms.content.world.meta.objects;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import sharedcms.renderer.layer.SuperPosition;

public class City extends MetaObject implements ICity
{
	private List<IVillage> villages;
	
	public City(SuperPosition position)
	{
		super(position);
		
		villages = new ArrayList<IVillage>();
	}

	@Override
	public List<IVillage> getVillages()
	{
		return villages;
	}

	@Override
	public void addVillage(IVillage village)
	{
		getVillages().add(village);
	}
}
