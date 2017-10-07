package sharedcms.content.structure.model;

import java.util.Map;

import net.minecraft.world.World;
import sharedcms.util.Location;

public interface IModel
{
	public Map<Location, IModelMaterial> getRawModel();

	public void draw(World world, Location offset);

	public void put(int x, int y, int z, IModelMaterial mat);

	public void scale(double x, double y, double z);

	public Location getSize();

	public Location getShift();
		
	public void setShift(Location l);
	
	public int getWidth();

	public int getHeight();

	public int getDepth();
	
	public void shift(Location l);
	
	public int getExposition(int x, int y, int z);
	
	public void smooth(int strength, SmoothingMode... modes);
	
	public void clear();
	
	public void center();
	
	public void cuboid(Location a, Location b, IModelMaterial material);
	
	public void cuboid(int xa, int ya, int za, int xb, int yb, int zb, IModelMaterial material);
	
	public void fill(Location point, Location size, IModelMaterial material);
	
	public void fill(int x, int y, int z, int w, int h, int d, IModelMaterial material);
	
	public void shift(int x, int y, int z);
	
	public IModelMaterial get(Location l);
	
	public IModelMaterial get(int x, int y, int z);

	public boolean has(Location l);

	public boolean has(int x, int y, int z);

	public void add(ModelMashMode mode, IModel... model);

	public void beginDrawing(IModelWriter writer);

	public void beginDrawing(IModelWriter writer, int w, int h, int d);
}
