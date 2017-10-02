package sharedcms.gui.components;

import java.awt.Color;

import sharedcms.gui.component.IButton;
import sharedcms.gui.component.ITextual;
import sharedcms.gui.layout.IToggle;
import sharedcms.gui.util.Point;
import sharedcms.gui.util.R;
import sharedcms.gui.util.Text;
import sharedcms.gui.util.TextAlignment;

public class HCheckBox extends HPanel implements ITextual, IButton, IToggle
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
	protected boolean toggle;

	public HCheckBox(Text text)
	{
		super(0, 0);

		this.toggle = false;
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
		shadowColor = R.OPACIFY(Color.black, 0.25);
		sa = new Point(0, 0);
		sb = new Point(0, 0);
	}

	public HCheckBox(String text)
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
		setTextAlignment(TextAlignment.LEFT);
		Point fs = new Point(getFontSize(), getFontSize()).multiply(0.5);
		Point of = new Point(getFontSize(), getFontSize() / 2).multiply(0.5);
		Point fa = offset.clone().add(fs.clone().invert()).add(of);
		Point fb = offset.clone().add(fs).add(of);
		Point sh = offset.clone().add(new Point(fs.getX() + fs.getX(), -fs.getY() + (fs.getY() / 4)));
		Point k = new Point(getShadowDistance(), getShadowDistance());
		Point v = new Point(getShadowDistance(), getShadowDistance() * 2);
		Color sha = getButtonShadowColor();
		Color col = getColor();
		Color f = null;
		fa = R.GLSCALEFONTOF(fa, getFontSize());
		fb = R.GLSCALEFONTOF(fb, getFontSize());
		sa = fa;
		sb = fb;

		if(isHovered())
		{
			col = R.OPACIFY(col, R.CLIP((float) (R.OPACITY(col) + 0.17), 0f, 1f));
		}

		if(isClicked() || getToggleState())
		{
			f = col;
			col = sha;
			sha = f;
		}
		
		R.GLSCALE(R.FONTSIZEOF(getFontSize()));

		if(getToggleState())
		{
			sha = R.OPACIFY(sha, R.CLIP((float) (R.OPACITY(col) / 2), 0f, 1f));
		}

		R.RECT(fa, fb, col);
		R.RECT(fa.clone().add(k), fb.clone().add(k), sha);
		R.GLUNSCALE(R.FONTSIZEOF(getFontSize()));
		R.TEXT(sh.clone().add(of), text.get(), getTextObject().getProperties());
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
	public boolean getToggleState()
	{
		return toggle;
	}

	@Override
	public void setToggleState(boolean toggle)
	{
		this.toggle = toggle;
	}

	@Override
	public void invertToggleState()
	{
		setToggleState(!getToggleState());
	}

	@Override
	public void clicked()
	{
		invertToggleState();
	}
}
