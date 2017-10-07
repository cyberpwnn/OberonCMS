package sharedcms.content.structure.model;

import sharedcms.content.structure.brush.IBrush;

public class ModelMaterial implements IModelMaterial
{
	private String id;
	private IBrush brush;

	public ModelMaterial(String id, IBrush brush)
	{
		this.id = id;
		this.brush = brush;
	}

	@Override
	public String getId()
	{
		return id;
	}

	@Override
	public IBrush getBrush()
	{
		return brush;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brush == null) ? 0 : brush.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		ModelMaterial other = (ModelMaterial) obj;
		if(brush == null)
		{
			if(other.brush != null)
				return false;
		}
		else if(!brush.equals(other.brush))
			return false;
		if(id == null)
		{
			if(other.id != null)
				return false;
		}
		else if(!id.equals(other.id))
			return false;
		return true;
	}
}
