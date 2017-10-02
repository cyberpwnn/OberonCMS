package sharedcms.gui.util;

public class Screen
{
	private int width;
	private int height;

	public Screen(int width, int height)
	{
		this.width = width;
		this.height = height;
	}

	public Point getSize()
	{
		return new Point(getWidth(), getHeight());
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
}
