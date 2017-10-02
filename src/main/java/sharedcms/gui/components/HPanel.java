package sharedcms.gui.components;

import java.awt.Color;

import sharedcms.gui.component.BaseComponent;
import sharedcms.gui.component.IColorable;
import sharedcms.gui.util.Point;
import sharedcms.gui.util.R;

public class HPanel extends BaseComponent implements IColorable
{
	private Color color;

	public HPanel(int w, int h)
	{
		super(w, h);
		setColor(Color.WHITE);
		setOpacity(0.25);
	}

	@Override
	public void onDraw(Point offset)
	{
		R.RECT(offset, offset.cadd(getSize()), getColor());
	}

	@Override
	public Color getColor()
	{
		return color;
	}

	@Override
	public void setColor(Color color)
	{
		this.color = color;
	}

	@Override
	public void setOpacity(double opacity)
	{
		setColor(R.OPACIFY(getColor(), opacity));
	}
}
