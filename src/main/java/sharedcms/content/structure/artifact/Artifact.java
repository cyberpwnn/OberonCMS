package sharedcms.content.structure.artifact;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.world.World;
import sharedcms.content.structure.model.IModel;
import sharedcms.util.Location;

public class Artifact implements IArtifact
{
	private Location offset;
	private IModel model;
	private List<IArtifact> children;

	public Artifact(IModel model, Location offset)
	{
		this.model = model;
		this.offset = offset;
		children = new ArrayList<IArtifact>();
	}

	public Artifact(IModel model)
	{
		this(model, new Location());
	}

	@Override
	public void build(World w, Location origin)
	{
		for(IArtifact i : getChildren())
		{
			i.getModel().draw(w, origin.clone().add(i.getOffset()));
		}

		getModel().draw(w, origin.clone().add(getOffset()));
	}

	@Override
	public List<IArtifact> getChildren()
	{
		return children;
	}

	@Override
	public void addChild(IArtifact s)
	{
		getChildren().add(s);
	}

	@Override
	public void setOffset(Location location)
	{
		this.offset = location;
	}

	@Override
	public Location getOffset()
	{
		return offset;
	}

	@Override
	public IModel getModel()
	{
		return model;
	}

	@Override
	public void setModel(IModel model)
	{
		this.model = model;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((offset == null) ? 0 : offset.hashCode());
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
		Artifact other = (Artifact) obj;
		if(children == null)
		{
			if(other.children != null)
				return false;
		}
		else if(!children.equals(other.children))
			return false;
		if(model == null)
		{
			if(other.model != null)
				return false;
		}
		else if(!model.equals(other.model))
			return false;
		if(offset == null)
		{
			if(other.offset != null)
				return false;
		}
		else if(!offset.equals(other.offset))
			return false;
		return true;
	}
}
