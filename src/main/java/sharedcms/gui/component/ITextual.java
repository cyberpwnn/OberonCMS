package sharedcms.gui.component;

import java.awt.Color;

import sharedcms.gui.util.Text;
import sharedcms.gui.util.TextAlignment;

public interface ITextual
{
	public String getText();

	public void setText(String text);

	public boolean shouldWordWrap();

	public void setWordWrap(boolean wrap);

	public int getWordWrapLength();

	public void setWordWrapLength(int length);

	public void setTextColor(Color color);

	public void setTextOpacity(double opacity);

	public Color getTextColor();

	public void setShadowColor(Color color);

	public void setShadowOpacity(double opacity);

	public Color getShadowColor();
	
	public void setFontSize(int size);
	
	public int getFontSize();
	
	public void setShadowVisible(boolean shadow);
	
	public boolean isShadowVisible();
	
	public void setTextAlignment(TextAlignment alignment);
	
	public TextAlignment getTextAlignment();
	
	public void setShadowDistance(int distance);
	
	public int getShadowDistance();
	
	public Text getTextObject();
}
