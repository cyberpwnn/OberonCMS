package sharedcms.renderer.layer;

import java.awt.Color;

public class TextElement
{
	private String text;
	private Color color;

	public TextElement(String text, Color color)
	{
		super();
		this.text = text;
		this.color = color;
	}

	public TextElement(String text)
	{
		this(text, Color.WHITE);
	}

	public String getText()
	{
		return text;
	}

	public Color getColor()
	{
		return color;
	}
}
