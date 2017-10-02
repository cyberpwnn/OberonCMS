package sharedcms.gui.util;

public class Viewport
{
	private int width;
	private int height;
	private int offsetX;
	private int offsetY;

	public Viewport(int offsetX, int offsetY, int width, int height)
	{
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.width = width;
		this.height = height;
	}

	public Viewport(int width, int height)
	{
		this(0, 0, width, height);
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public int getOffsetX()
	{
		return offsetX;
	}

	public void setOffsetX(int offsetX)
	{
		this.offsetX = offsetX;
	}

	public int getOffsetY()
	{
		return offsetY;
	}

	public void setOffsetY(int offsetY)
	{
		this.offsetY = offsetY;
	}

	public Point getSize()
	{
		return new Point(getWidth(), getHeight());
	}

	public Point getOffset()
	{
		return new Point(getOffsetX(), getOffsetY());
	}
}
