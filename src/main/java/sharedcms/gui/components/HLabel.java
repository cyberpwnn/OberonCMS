package sharedcms.gui.components;

import java.awt.Color;

import scala.sys.process.processInternal;
import sharedcms.gui.component.BaseComponent;
import sharedcms.gui.component.ITextual;
import sharedcms.gui.util.Point;
import sharedcms.gui.util.R;
import sharedcms.gui.util.Text;
import sharedcms.gui.util.TextAlignment;

public class HLabel extends BaseComponent implements ITextual
{
	private Text text;

	public HLabel(Text text)
	{
		super(0, 0);

		this.text = text;

		setFontSize(14);
		int height = text.getProperties().getWrapper().shouldWrapText() ? (R.FONTRENDERER().splitStringWidth(text.get(), text.getProperties().getWrapper().getWrapLength()) / 9) * text.getProperties().getFontSize() : text.getProperties().getFontSize();
		int width = text.getProperties().getWrapper().shouldWrapText() ? R.FONTRENDERER().getStringWidth((String) R.FONTRENDERER().listFormattedStringToWidth(text.get(), text.getProperties().getWrapper().getWrapLength()).get(0)) : R.FONTRENDERER().getStringWidth(text.get());
		getSize().setX(R.GLSCALEFONTMUL(width, getFontSize()));
		getSize().setY(height);
		setShadowVisible(true);
	}

	public HLabel(String text)
	{
		this(new Text(text));
	}

	@Override
	public void onDraw(Point offset)
	{
		R.TEXT(offset, text.get(), text.getProperties());
	}

	@Override
	public String getText()
	{
		return getTextObject().get();
	}

	@Override
	public void setText(String text)
	{
		getTextObject().set(text);
	}

	@Override
	public boolean shouldWordWrap()
	{
		return getTextObject().getProperties().getWrapper().shouldWrapText();
	}

	@Override
	public void setWordWrap(boolean wrap)
	{
		getTextObject().getProperties().getWrapper().wrapText(wrap);
	}

	@Override
	public int getWordWrapLength()
	{
		return getTextObject().getProperties().getWrapper().getWrapLength();
	}

	@Override
	public void setWordWrapLength(int length)
	{
		getTextObject().getProperties().getWrapper().setWrapLength(length);
	}

	@Override
	public void setTextColor(Color color)
	{
		getTextObject().getProperties().setColor(color);
	}

	@Override
	public void setTextOpacity(double opacity)
	{
		setTextColor(R.OPACIFY(getTextColor(), opacity));
	}

	@Override
	public Color getTextColor()
	{
		return getTextObject().getProperties().getColor();
	}

	@Override
	public void setShadowColor(Color color)
	{
		getTextObject().getProperties().setShadowColor(color);
	}

	@Override
	public void setShadowOpacity(double opacity)
	{
		setShadowColor(R.OPACIFY(getShadowColor(), opacity));
	}

	@Override
	public Color getShadowColor()
	{
		return getTextObject().getProperties().getShadowColor();
	}

	@Override
	public void setFontSize(int size)
	{
		getTextObject().getProperties().setFontSize(size);
	}

	@Override
	public int getFontSize()
	{
		return getTextObject().getProperties().getFontSize();
	}

	@Override
	public void setShadowVisible(boolean shadow)
	{
		getTextObject().getProperties().setShadow(shadow);
	}

	@Override
	public boolean isShadowVisible()
	{
		return getTextObject().getProperties().hasShadow();
	}

	@Override
	public void setTextAlignment(TextAlignment alignment)
	{
		getTextObject().getProperties().setAlignment(alignment);
	}

	@Override
	public TextAlignment getTextAlignment()
	{
		return getTextObject().getProperties().getAlignment();
	}

	@Override
	public void setShadowDistance(int distance)
	{
		getTextObject().getProperties().setShadowOffset(distance);
	}

	@Override
	public int getShadowDistance()
	{
		return getTextObject().getProperties().getShadowOffset();
	}

	@Override
	public Text getTextObject()
	{
		return text;
	}
}
