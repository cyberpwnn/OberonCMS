package sharedcms.content.structure.artifact;

import java.util.List;

import net.minecraft.world.World;
import sharedcms.content.structure.model.IModel;
import sharedcms.util.Location;

public interface IArtifact
{
	public void build(World w, Location origin);
	
	public List<IArtifact> getChildren();
	
	public void addChild(IArtifact s);
	
	public void setOffset(Location location);
	
	public Location getOffset();
	
	public IModel getModel();
	
	public void setModel(IModel model);
}
