package sharedcms.gui.components;

import java.awt.Color;

import sharedcms.gui.component.ISlider;
import sharedcms.gui.component.ITextual;
import sharedcms.gui.util.Point;
import sharedcms.gui.util.R;
import sharedcms.gui.util.Text;
import sharedcms.gui.util.TextAlignment;

public class HSlider extends HPanel implements ISlider, ITextual
{
	protected Text text;
	protected int padding;
	protected int buttonShadowDistance;
	protected boolean buttonShadow;
	protected boolean hovered;
	protected boolean clicked;
	protected Point sa;
	protected Point sb;
	protected int max;
	protected int min;
	protected int ww;
	protected int value;
	protected Color notchColor;
	protected Color buttonShadowColor;
	protected boolean sliding;
	protected int swing;
	public boolean manualText = false;

	public HSlider(int sliderWidth, int min, int max)
	{
		super(0, 0);

		swing = 0;
		this.min = min;
		this.max = max;
		setSliderWidth(sliderWidth);
		text = new Text(min + "");
		int height = text.getProperties().getWrapper().shouldWrapText() ? (R.FONTRENDERER().splitStringWidth(text.get(), text.getProperties().getWrapper().getWrapLength()) / 9) * text.getProperties().getFontSize() : text.getProperties().getFontSize();
		int width = text.getProperties().getWrapper().shouldWrapText() ? R.FONTRENDERER().getStringWidth((String) R.FONTRENDERER().listFormattedStringToWidth(text.get(), text.getProperties().getWrapper().getWrapLength()).get(0)) : ((R.FONTRENDERER().getStringWidth(text.get()) / 9) * getFontSize());
		getSize().setX(width);
		getSize().setY(height);
		padding = 2;
		buttonShadow = true;
		buttonShadowDistance = 1;
		clicked = false;
		hovered = false;
		buttonShadowColor = R.OPACIFY(Color.black, 0.25);
		notchColor = R.OPACIFY(Color.black, 0.15);
		setShadowVisible(true);
		sliding = false;
		sa = new Point(0, 0);
		sb = new Point(0, 0);
	}

	@Override
	public void onDraw(Point offset)
	{
		Color c = getShadowColor();
		Color s = getColor();

		if(sliding)
		{
			Color f = c;
			c = s;
			s = f;
		}

		int height = text.getProperties().getWrapper().shouldWrapText() ? (R.FONTRENDERER().splitStringWidth(text.get(), text.getProperties().getWrapper().getWrapLength()) / 9) * text.getProperties().getFontSize() : text.getProperties().getFontSize();
		int width = text.getProperties().getWrapper().shouldWrapText() ? R.FONTRENDERER().getStringWidth((String) R.FONTRENDERER().listFormattedStringToWidth(text.get(), text.getProperties().getWrapper().getWrapLength()).get(0)) : ((R.FONTRENDERER().getStringWidth(text.get())) * (int) (getFontSize() / 9.0));
		getSize().setX(width);
		getSize().setY(height);
		swing = width / 3;
		if(!manualText)
		{
			setText(getValue() + "");
		}
		int lThick = R.CLIP(getFontSize() / 8, 2, 1000);
		int lWidth = getSliderWidth();
		int position = getValue();
		double percent = R.FROMFOR(position, getMinValue(), getMaxValue());
		int xMin = offset.getX();
		int xMax = xMin + lWidth;
		double xRange = max - min;
		double xScale = xRange / lWidth;
		int xStart = (int) ((1.0 / xScale) * position);
		int xPos = xStart + xMin - (width / 2) - min;
		int xPosb = xPos + width;
		int yPola = (getSize().getY() / 2) + offset.getY() + lThick;
		int yPolb = (getSize().getY() / 2) + offset.getY() - lThick;
		int yPolc = offset.getY();
		int yPold = offset.getY() + height;
		Point lStart = new Point(xMin, yPola);
		Point lEnd = new Point(xMax, yPolb);
		Point bStart = new Point(xPos, yPolc).add(new Point(width / 4, height / 4).invert());
		Point bEnd = new Point(xPosb, yPold).add(new Point(1 + (width / 4), (height / 4) - 3));
		Point bSet = bEnd.clone().add(bStart.clone().invert());
		Point sOff = new Point(1, 1);
		Point tStart = bStart.clone().add(new Point((width / 4) - 1, height / 4));
		lStart = R.GLSCALEFONTOF(lStart, getFontSize());
		lEnd = R.GLSCALEFONTOF(lEnd, getFontSize());
		bStart = R.GLSCALEFONTOF(bStart, getFontSize());
		bEnd = R.GLSCALEFONTOF(bEnd, getFontSize());
		sa = new Point(lStart.getX(), bStart.getY());
		sb = new Point(lEnd.getX(), bEnd.getY());
		R.GLSCALE(R.FONTSIZEOF(getFontSize()));
		R.RECT(lStart, lEnd, getNotchColor());
		R.RECT(bStart, bEnd, getColor());
		R.RECT(bStart.clone().add(sOff), bEnd.clone().add(sOff), getButtonShadowColor());
		R.GLUNSCALE(R.FONTSIZEOF(getFontSize()));
		R.TEXT(tStart, text.get(), text.getProperties());
	}

	private int swing()
	{
		return swing;
	}

	@Override
	public void slid(Point p)
	{
		sliding = true;

		int mi = R.GLSCALEFONTMUL(sa, getFontSize()).getX() + swing();
		int ma = R.GLSCALEFONTMUL(sb, getFontSize()).getX() - swing();
		int val = p.getX();

		if(val > ma)
		{
			val = ma;
		}

		if(val < mi)
		{
			val = mi;
		}

		setAsPercent(R.FROMFOR(val, mi, ma));
	}

	@Override
	public void uslid()
	{
		sliding = false;
	}

	public boolean isSliding()
	{
		return sliding;
	}

	@Override
	public boolean contains(Point p)
	{
		boolean c = p.isWithin(R.GLSCALEFONTMUL(sa, getFontSize()), R.GLSCALEFONTMUL(sb, getFontSize()));

		if(!c)
		{
			sliding = false;
		}

		return c;
	}

	@Override
	public int getMaxValue()
	{
		return max;
	}

	@Override
	public int getMinValue()
	{
		return min;
	}

	@Override
	public int getSliderWidth()
	{
		return ww;
	}

	@Override
	public void setMaxValue(int v)
	{
		max = v;
	}

	@Override
	public void setMinValue(int v)
	{
		min = v;
	}

	@Override
	public void setSliderWidth(int v)
	{
		ww = v;
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
	public void setValue(int v)
	{
		value = v;
	}

	@Override
	public int getValue()
	{
		return value;
	}

	@Override
	public void setNotchColor(Color color)
	{
		notchColor = color;
	}

	@Override
	public Color getNotchColor()
	{
		return notchColor;
	}

	@Override
	public void setNotchOpacity(double opacity)
	{
		setNotchColor(R.OPACIFY(getNotchColor(), opacity));
	}

	@Override
	public void setButtonShadowOpacity(double opacity)
	{
		setButtonShadowColor(R.OPACIFY(getButtonShadowColor(), opacity));
	}

	@Override
	public void setButtonShadowColor(Color color)
	{
		buttonShadowColor = color;
	}

	@Override
	public Color getButtonShadowColor()
	{
		return buttonShadowColor;
	}

	@Override
	public void setAsPercent(double pc)
	{
		int base = (max - min);
		double k = base * pc;
		double f = k + min;
		setValue((int) f);

	}
}