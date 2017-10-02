package sharedcms.gui.util;

public class Point implements Cloneable
{
	private int x;
	private int y;

	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public Point(Point clone)
	{
		this.x = clone.x;
		this.y = clone.y;
	}

	public boolean isWithin(Point from, Point to)
	{
		return isWithin(x, from.x, to.x) && isWithin(y, from.y, to.y);
	}

	public boolean isWithin(int v, int from, int to)
	{
		return v >= from && v <= to;
	}

	public Point cadd(Point p)
	{
		return clone().add(p);
	}

	public Point invert()
	{
		x = -x;
		y = -y;

		return this;
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

	public Point add(Point value)
	{
		x += value.x;
		y += value.y;

		return this;
	}

	public Point multiply(double value)
	{
		x = (int) (x * value);
		y = (int) (y * value);

		return this;
	}

	public String toString()
	{
		return x + "," + y;
	}

	public Point clone()
	{
		return new Point(this);
	}
}
