package sharedcms.util;

public class SimplexProperties
{
	private double scale;
	private long seed;
	private String id;

	public SimplexProperties(String id, long seed, double scale)
	{
		this.id = id;
		this.seed = seed;
		this.scale = scale;
	}

	public long getSeed()
	{
		return seed;
	}

	public double getScale()
	{
		return scale;
	}

	public String getId()
	{
		return id;
	}
	
	public String toString()
	{
		return F.f(scale, 0) + "x scale #" + id + " (" + seed + ")";
	}
}
