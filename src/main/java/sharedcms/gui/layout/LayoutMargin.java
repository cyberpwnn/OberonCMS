package sharedcms.gui.layout;

public class LayoutMargin
{
	private int up;
	private int down;
	private int left;
	private int right;

	public LayoutMargin(int up, int down, int left, int right)
	{
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}

	public LayoutMargin(int up, int down)
	{
		this(up, down, 0, 0);
	}

	public LayoutMargin()
	{
		this(0, 0);
	}

	public int getMarginUp()
	{
		return up;
	}

	public int getMarginDown()
	{
		return down;
	}

	public int getMarginLeft()
	{
		return left;
	}

	public int getMarginRight()
	{
		return right;
	}

	public void setMarginUp(int up)
	{
		this.up = up;
	}

	public void setMarginDown(int down)
	{
		this.down = down;
	}

	public void setMarginLeft(int left)
	{
		this.left = left;
	}

	public void setMarginRight(int right)
	{
		this.right = right;
	}
}
