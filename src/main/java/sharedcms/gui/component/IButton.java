package sharedcms.gui.component;

import java.awt.Color;

public interface IButton
{
	public int getButtonPadding();
	
	public void setButtonPadding(int padding);
	
	public int getButtonShadowDistance();
	
	public void setButtonShadowDistance(int distance);
	
	public boolean isButtonShadowVisible();
	
	public void setButtonShadowVisible(boolean visible);
	
	public void setButtonShadowColor(Color color);
	
	public Color getButtonShadowColor();
	
	public void setButtonShadowOpacity(double opacity);

	public void setClicked(boolean b);

	public void setHovered(boolean b);
	
	public boolean isClicked();
	
	public boolean isHovered();
	
	public void clicked();
}
