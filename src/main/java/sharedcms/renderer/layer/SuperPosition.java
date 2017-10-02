package sharedcms.renderer.layer;

import sharedcms.util.SuperLocation;

public class SuperPosition
{
	public int x;
	public int y;

	public SuperPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public SuperPosition add(int x, int y)
	{
		this.x += x;
		this.y += y;

		return this;
	}

	public SuperPosition copy()
	{
		return new SuperPosition(x, y);
	}

	public double distanceSquared(SuperPosition other)
	{
		return Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2);
	}

	public double distance(SuperPosition other)
	{
		return Math.sqrt(distanceSquared(other));
	}

	public SuperPosition()
	{
		this(0, 0);
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}
}
