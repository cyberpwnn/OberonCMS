package sharedcms.gui.component;

import java.awt.Color;

import sharedcms.gui.util.Point;

public interface IColorable
{
	public Color getColor();
	
	public void setColor(Color color);
	
	public void setOpacity(double opacity);
}
