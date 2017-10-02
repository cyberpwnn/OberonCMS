package sharedcms.util;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import sharedcms.renderer.layer.SuperPosition;

public class SuperLocation
{
	public double x;
	public double y;
	public double z;

	public SuperLocation(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public SuperLocation(Entity e)
	{
		this.x = e.posX;
		this.y = e.posY;
		this.z = e.posZ;
	}

	public SuperLocation()
	{
		this(0, 0, 0);
	}

	public SuperLocation copy()
	{
		return new SuperLocation(x, y, z);
	}

	public double distanceSquared(SuperLocation other)
	{
		return Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2) + Math.pow(z - other.z, 2);
	}

	public double distance(SuperLocation other)
	{
		return Math.sqrt(distanceSquared(other));
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		SuperLocation other = (SuperLocation) obj;
		if(Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if(Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if(Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}
}
