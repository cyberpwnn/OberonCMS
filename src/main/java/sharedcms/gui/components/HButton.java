package sharedcms.gui.components;

import java.awt.Color;

import sharedcms.gui.component.IButton;
import sharedcms.gui.component.ITextual;
import sharedcms.gui.util.Point;
import sharedcms.gui.util.R;
import sharedcms.gui.util.Text;
import sharedcms.gui.util.TextAlignment;

public class HButton extends HPanel implements ITextual, IButton
{
	protected Text text;
	protected int padding;
	protected int buttonShadowDistance;
	protected Color shadowColor;
	protected boolean buttonShadow;
	protected boolean hovered;
	protected boolean clicked;
	protected Point sa;
	protected Point sb;

	public HButton(Text text)
	{
		super(0, 0);

		this.text = text;
		int height = text.getProperties().getWrapper().shouldWrapText() ? (R.FONTRENDERER().splitStringWidth(text.get(), text.getProperties().getWrapper().getWrapLength()) / 9) * text.getProperties().getFontSize() : text.getProperties().getFontSize();
		int width = text.getProperties().getWrapper().shouldWrapText() ? R.FONTRENDERER().getStringWidth((String) R.FONTRENDERER().listFormattedStringToWidth(text.get(), text.getProperties().getWrapper().getWrapLength()).get(0)) : R.FONTRENDERER().getStringWidth(text.get());
		getSize().setX(width);
		getSize().setY(height);
		padding = 2;
		buttonShadow = true;
		buttonShadowDistance = 1;
		clicked = false;
		hovered = false;
		setColor(R.OPACIFY(Color.WHITE, 0.0));
		shadowColor = R.OPACIFY(Color.black, 0.0);
		sa = new Point(0, 0);
		sb = new Point(0, 0);
	}

	public HButton(String text)
	{
		this(new Text(text));
	}

	@Override
	public boolean contains(Point p)
	{
		boolean c = p.isWithin(R.GLSCALEFONTMUL(sa, getFontSize()), R.GLSCALEFONTMUL(sb, getFontSize()));

		if(!c)
		{
			setHovered(false);
			setClicked(false);
		}

		return c;
	}

	@Override
	public void onDraw(Point offset)
	{
		Point rectOffset = offset.clone();

		if(getTextAlignment().equals(TextAlignment.CENTER))
		{
			rectOffset.setX(offset.getX() - (getSize().getX() / 2));
			rectOffset.setY(offset.getY() - (getSize().getY() / 2));
		}

		Color bg = getButtonShadowColor();
		Color fg = getColor();

		if(isClicked())
		{
			Color s = bg;

			bg = fg;
			fg = s;
			fg = R.OPACIFY(fg, R.CLIP((float) (R.OPACITY(fg) + 0.17), 0f, 1f));
			bg = R.OPACIFY(bg, R.CLIP((float) (R.OPACITY(bg) + 0.17), 0f, 1f));

		}

		else if(isHovered())
		{
			fg = R.OPACIFY(fg, R.CLIP((float) (R.OPACITY(fg) + 0.17), 0f, 1f));
		}

		Point a = new Point(0, 0);
		Point b = new Point(0, 0);
		R.TEXT(offset, text.get(), text.getProperties(), fg, getButtonPadding(), bg, getButtonShadowDistance(), a, b);
		sa = a;
		sb = b;
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

	@Override
	public int getButtonPadding()
	{
		return padding;
	}

	@Override
	public void setButtonPadding(int padding)
	{
		this.padding = padding;
	}

	@Override
	public int getButtonShadowDistance()
	{
		return buttonShadowDistance;
	}

	@Override
	public void setButtonShadowDistance(int distance)
	{
		this.buttonShadowDistance = distance;
	}

	@Override
	public boolean isButtonShadowVisible()
	{
		return buttonShadow;
	}

	@Override
	public void setButtonShadowVisible(boolean visible)
	{
		this.buttonShadow = visible;
	}

	@Override
	public void setButtonShadowColor(Color color)
	{
		this.shadowColor = color;
	}

	@Override
	public Color getButtonShadowColor()
	{
		return shadowColor;
	}

	@Override
	public void setButtonShadowOpacity(double opacity)
	{
		setButtonShadowColor(R.OPACIFY(getButtonShadowColor(), opacity));
	}

	@Override
	public void setClicked(boolean b)
	{
		clicked = b;
	}

	@Override
	public void setHovered(boolean b)
	{
		hovered = b;
	}

	@Override
	public boolean isClicked()
	{
		return clicked;
	}

	@Override
	public boolean isHovered()
	{
		return hovered;
	}

	@Override
	public void clicked()
	{

	}
}
