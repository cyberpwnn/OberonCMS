package sharedcms.gui.util;

public class TextWrapper
{
	private boolean wrapText;
	private int wrapLength;
	
	public TextWrapper()
	{
		this.wrapLength = 12;
		this.wrapText = false;
	}

	public boolean shouldWrapText()
	{
		return wrapText;
	}

	public void wrapText(boolean wrapText)
	{
		this.wrapText = wrapText;
	}

	public int getWrapLength()
	{
		return wrapLength;
	}

	public void setWrapLength(int wrapLength)
	{
		this.wrapLength = wrapLength;
	}
}
