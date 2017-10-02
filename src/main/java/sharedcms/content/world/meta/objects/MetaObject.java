package sharedcms.content.world.meta.objects;

import java.io.File;

import sharedcms.renderer.layer.SuperPosition;

public class MetaObject implements IMetaObject
{
	private SuperPosition position;
	
	public MetaObject(SuperPosition position)
	{
		this.position = position;
	}

	@Override
	public SuperPosition getPosition()
	{
		return position;
	}
}
