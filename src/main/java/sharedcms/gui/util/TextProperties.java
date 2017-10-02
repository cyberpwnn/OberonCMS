package sharedcms.gui.util;

import java.awt.Color;

public class TextProperties
{
	private TextWrapper wrapper;
	private TextAlignment alignment;
	private boolean shadow;
	private int fontSize;
	private Color shadowColor;
	private int shadowOffset;
	private Color color;

	public TextProperties()
	{
		wrapper = new TextWrapper();
		alignment = TextAlignment.LEFT;
		shadow = false;
		fontSize = 9;
		color = Color.WHITE;
		shadowColor = Color.black;
		shadowOffset = 1;
	}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

	public TextAlignment getAlignment()
	{
		return alignment;
	}

	public void setAlignment(TextAlignment alignment)
	{
		this.alignment = alignment;
	}

	public TextWrapper getWrapper()
	{
		return wrapper;
	}

	public boolean hasShadow()
	{
		return shadow;
	}

	public void setShadow(boolean shadow)
	{
		this.shadow = shadow;
	}

	public int getFontSize()
	{
		return fontSize;
	}

	public void setFontSize(int fontSize)
	{
		this.fontSize = fontSize;
	}

	public Color getShadowColor()
	{
		return shadowColor;
	}

	public void setShadowColor(Color shadowColor)
	{
		this.shadowColor = shadowColor;
	}

	public int getShadowOffset()
	{
		return shadowOffset;
	}

	public void setShadowOffset(int shadowOffset)
	{
		this.shadowOffset = shadowOffset;
	}
}
