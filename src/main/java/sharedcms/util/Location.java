package sharedcms.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class Location implements Cloneable
{
	public double x;
	public double y;
	public double z;

	public Location(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Location()
	{
		this(0, 0, 0);
	}
	
	public Location(Location clone)
	{
		this(clone.x, clone.y, clone.z);
	}
	
	public Location(Entity e)
	{
		this(e.posX, e.posY, e.posZ);
	}
	
	public Location clone()
	{
		return new Location(this);
	}
	
	public Location invert()
	{
		Location c = clone();
		c.x = -c.x;
		c.y = -c.y;
		c.z = -c.z;
		
		return c;
	}
	
	public Location add(Location l)
	{
		return add(l.x, l.y, l.z);
	}
	
	public Location add(double x, double y, double z)
	{
		Location c = clone();
		c.x += x;
		c.y += y;
		c.z += z;
		
		return c;
	}
	
	public Location subtract(double x, double y, double z)
	{
		Location c = clone();
		c.x -= x;
		c.y -= y;
		c.z -= z;
		
		return c;
	}
	
	public Location subtract(Location l)
	{
		return add(-l.x, -l.y, -l.z);
	}
	
	public Location center()
	{
		return clone().add(0.5, 0.5, 0.5);
	}
	
	public double distance(Location to)
	{
		return Math.sqrt(distanceSquared(to));
	}
	
	public double distanceSquared(Location to)
	{
		return Math.pow(to.x - x, 2) + Math.pow(to.y - y, 2) + Math.pow(to.z - z, 2);
	}
}
