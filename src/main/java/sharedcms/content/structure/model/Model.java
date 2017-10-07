package sharedcms.content.structure.model;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.World;
import scala.Int;
import sharedcms.util.Location;

public class Model implements IModel
{
	private Map<Location, IModelMaterial> rawModel;
	private Location shift;

	public Model()
	{
		shift = new Location(0, 0, 0);
		rawModel = new HashMap<Location, IModelMaterial>();
	}

	@Override
	public Map<Location, IModelMaterial> getRawModel()
	{
		return rawModel;
	}

	@Override
	public void draw(World world, Location offset)
	{
		for(Location i : getRawModel().keySet())
		{
			Location p = offset.clone().add(i).add(getShift());
			world.setBlock((int) p.x, (int) p.y, (int) p.z, getRawModel().get(i).getBrush().pick().getBlock());
		}
	}

	@Override
	public void put(int x, int y, int z, IModelMaterial mat)
	{
		getRawModel().put(new Location(x, y, z), mat);
	}

	@Override
	public void scale(double x, double y, double z)
	{
		Map<Location, IModelMaterial> scaledModel = new HashMap<Location, IModelMaterial>();
		int oW = getWidth();
		int oH = getHeight();
		int oD = getDepth();
		int nW = (int) ((double) oW * x);
		int nH = (int) ((double) oH * y);
		int nD = (int) ((double) oD * z);
		int rW = (int) ((double) (oW) / (double) nW);
		int rH = (int) ((double) (oH) / (double) nH);
		int rD = (int) ((double) (oD) / (double) nD);

		for(int i = 0; i < nW; i++)
		{
			for(int j = 0; j < nH; j++)
			{
				for(int k = 0; k < nD; k++)
				{
					int pX = ((i * rW));
					int pY = ((j * rH));
					int pZ = ((k * rD));
					Location m = new Location(pX, pY, pZ);
					Location n = new Location(i, j, k);

					if(has(m))
					{
						scaledModel.put(n, getRawModel().get(m));
					}
				}
			}
		}

		setShift(getShift().clone().multiply(rW, rH, rD));
		rawModel = scaledModel;
	}

	@Override
	public Location getSize()
	{
		return new Location(getWidth(), getHeight(), getDepth());
	}

	@Override
	public int getWidth()
	{
		int min = Int.MaxValue();
		int max = Int.MinValue();

		if(getRawModel().keySet().isEmpty())
		{
			return 0;
		}

		for(Location i : getRawModel().keySet())
		{
			if(i.x > max)
			{
				max = (int) i.x + 1;
			}

			if(i.x < min)
			{
				min = (int) i.x;
			}
		}

		return max - min;
	}

	@Override
	public int getHeight()
	{
		int min = Int.MaxValue();
		int max = Int.MinValue();

		if(getRawModel().keySet().isEmpty())
		{
			return 0;
		}

		for(Location i : getRawModel().keySet())
		{
			if(i.y > max)
			{
				max = (int) i.y + 1;
			}

			if(i.y < min)
			{
				min = (int) i.y;
			}
		}

		return max - min;
	}

	@Override
	public int getDepth()
	{
		int min = Int.MaxValue();
		int max = Int.MinValue();

		if(getRawModel().keySet().isEmpty())
		{
			return 0;
		}

		for(Location i : getRawModel().keySet())
		{
			if(i.z > max)
			{
				max = (int) i.z + 1;
			}

			if(i.z < min)
			{
				min = (int) i.z;
			}
		}

		return max - min;
	}

	@Override
	public void add(ModelMashMode mode, IModel... models)
	{
		for(IModel i : models)
		{
			add(mode, i);
		}
	}

	private void add(final ModelMashMode mode, final IModel model)
	{
		int w = Math.max(model.getWidth(), getWidth());
		int h = Math.max(model.getHeight(), getHeight());
		int d = Math.max(model.getDepth(), getDepth());

		beginDrawing(new IModelWriter()
		{
			@Override
			public IModelMaterial write(int x, int y, int z, IModelMaterial material)
			{
				if(has(x, y, z) && model.has(x, y, z))
				{
					if(mode.equals(ModelMashMode.HARD))
					{
						return model.get(x, y, z);
					}
				}

				else if(model.has(x, y, z))
				{
					return model.get(x, y, z);
				}

				return material;
			}
		}, w, h, d);
	}

	@Override
	public boolean has(Location l)
	{
		return getRawModel().containsKey(l);
	}

	@Override
	public boolean has(int x, int y, int z)
	{
		return has(new Location(x, y, z));
	}

	@Override
	public void beginDrawing(IModelWriter writer)
	{
		beginDrawing(writer, getWidth(), getHeight(), getDepth());
	}

	@Override
	public void beginDrawing(IModelWriter writer, int w, int h, int d)
	{
		Map<Location, IModelMaterial> m = new HashMap<Location, IModelMaterial>();
		
		for(int i = 0; i < w; i++)
		{
			for(int j = 0; j < h; j++)
			{
				for(int k = 0; k < d; k++)
				{
					Location index = new Location(i, j, k);
					IModelMaterial material = writer.write(i, j, k, getRawModel().get(index));

					if(material == null)
					{
						m.remove(index);
					}

					else
					{
						m.put(index, material);
					}
				}
			}
		}
		
		rawModel = m;
	}

	@Override
	public IModelMaterial get(Location l)
	{
		return getRawModel().get(l);
	}

	@Override
	public IModelMaterial get(int x, int y, int z)
	{
		return get(new Location(x, y, z));
	}

	@Override
	public Location getShift()
	{
		return shift;
	}

	@Override
	public void setShift(Location shift)
	{
		this.shift = shift;
	}

	@Override
	public void shift(int x, int y, int z)
	{
		shift(new Location(x, y, z));
	}

	@Override
	public void shift(Location l)
	{
		setShift(getShift().clone().add(l.clone().invert()));
	}

	@Override
	public int getExposition(int x, int y, int z)
	{
		int exposed = 0;

		exposed += has(x + 1, y, z) ? 0 : 1;
		exposed += has(x - 1, y, z) ? 0 : 1;
		exposed += has(x, y + 1, z) ? 0 : 1;
		exposed += has(x, y - 1, z) ? 0 : 1;
		exposed += has(x, y, z + 1) ? 0 : 1;
		exposed += has(x, y, z - 1) ? 0 : 1;

		return exposed;
	}

	private void smooth(final int over)
	{
		beginDrawing(new IModelWriter()
		{
			@Override
			public IModelMaterial write(int x, int y, int z, IModelMaterial material)
			{
				if(has(x, y, z) && getExposition(x, y, z) >= over)
				{
					return null;
				}

				return material;
			}
		});
	}

	@Override
	public void smooth(int strength, SmoothingMode... modes)
	{
		for(int i = 0; i < strength; i++)
		{
			for(SmoothingMode j : modes)
			{
				smooth(j.ordinal());
			}
		}
	}

	@Override
	public void clear()
	{
		getRawModel().clear();
	}

	@Override
	public void cuboid(Location a, Location b, IModelMaterial material)
	{
		cuboid((int) a.x, (int) a.y, (int) a.z, (int) b.x, (int) b.y, (int) b.z, material);
	}

	@Override
	public void cuboid(int xa, int ya, int za, int xb, int yb, int zb, IModelMaterial material)
	{
		int x = Math.min(xa, xb);
		int y = Math.min(ya, yb);
		int z = Math.min(za, zb);
		int w = Math.max(xa, xb) - x;
		int h = Math.max(ya, yb) - y;
		int d = Math.max(za, zb) - z;

		fill(x, y, z, w, h, d, material);
	}

	@Override
	public void fill(Location point, Location size, IModelMaterial material)
	{
		fill((int) point.x, (int) point.y, (int) point.z, (int) size.x, (int) size.y, (int) size.z, material);
	}

	@Override
	public void fill(int x, int y, int z, int w, int h, int d, IModelMaterial material)
	{
		for(int i = x; i < x + w; i++)
		{
			for(int j = y; j < y + h; j++)
			{
				for(int k = z; k < z + d; k++)
				{
					put(i, j, k, material);
				}
			}
		}
	}

	@Override
	public void center()
	{
		setShift(new Location(getWidth() / 2.0, getHeight() / 2.0, getDepth() / 2.0).invert());
	}
}
