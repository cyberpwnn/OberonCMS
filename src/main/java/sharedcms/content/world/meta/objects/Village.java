package sharedcms.content.world.meta.objects;

import java.io.File;
import java.util.List;

import sharedcms.renderer.layer.SuperPosition;

public class Village extends MetaObject implements IVillage
{
	private ICity city;

	public Village(SuperPosition position, ICity city)
	{
		super(position);

		this.city = city;
	}

	@Override
	public ICity getCity()
	{
		return city;
	}
}
