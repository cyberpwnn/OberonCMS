package sharedcms.gui.util;

public class ScreenUtil
{
	public Point getCenter(Point offset, Point size)
	{
		return getCenter(size).add(offset);
	}

	public Point getCenter(Point size)
	{
		return size.clone().multiply(0.5);
	}

	public Point getCenter(Viewport view)
	{
		return getCenter(view.getOffset(), view.getSize());
	}

	public Point getCenter(Screen s)
	{
		return getCenter(s.getSize());
	}
}
