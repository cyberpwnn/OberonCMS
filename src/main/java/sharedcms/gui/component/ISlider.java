package sharedcms.gui.component;

import java.awt.Color;

import sharedcms.gui.util.MouseEvent;
import sharedcms.gui.util.Point;

public interface ISlider
{
	public int getMaxValue();

	public int getMinValue();

	public int getSliderWidth();

	public void setMaxValue(int v);

	public void setMinValue(int v);

	public void setSliderWidth(int v);

	public void setValue(int v);

	public boolean isSliding();
	
	public int getValue();

	public void slid(Point p);

	public boolean contains(Point p);

	public void setNotchColor(Color color);

	public void setNotchOpacity(double opacity);

	public Color getNotchColor();

	public void setButtonShadowOpacity(double opacity);

	public void setButtonShadowColor(Color color);

	public Color getButtonShadowColor();

	public void uslid();
	
	public void setAsPercent(double pc);
}
