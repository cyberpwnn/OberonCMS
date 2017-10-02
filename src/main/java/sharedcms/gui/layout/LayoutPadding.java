package sharedcms.gui.layout;

public class LayoutPadding
{
	private int up;
	private int down;
	private int left;
	private int right;

	public LayoutPadding(int up, int down, int left, int right)
	{
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}

	public LayoutPadding(int up, int down)
	{
		this(up, down, 0, 0);
	}

	public LayoutPadding()
	{
		this(0, 0);
	}

	public int getPaddingUp()
	{
		return up;
	}

	public int getPaddingDown()
	{
		return down;
	}

	public int getPaddingLeft()
	{
		return left;
	}

	public int getPaddingRight()
	{
		return right;
	}

	public void setPaddingUp(int up)
	{
		this.up = up;
	}

	public void setPaddingDown(int down)
	{
		this.down = down;
	}

	public void setPaddingLeft(int left)
	{
		this.left = left;
	}

	public void setPaddingRight(int right)
	{
		this.right = right;
	}
}
