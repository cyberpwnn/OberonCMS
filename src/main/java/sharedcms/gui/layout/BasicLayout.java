package sharedcms.gui.layout;

import sharedcms.gui.util.Point;
import sharedcms.gui.util.Viewport;

import java.util.List;

import sharedcms.gui.component.IComponent;

public abstract class BasicLayout implements ILayout
{
	private LayoutPadding padding;
	private LayoutMargin margin;
	
	public BasicLayout()
	{
		padding = new LayoutPadding();
		margin = new LayoutMargin();
	}
	
	@Override
	public LayoutPadding getPadding()
	{
		return padding;
	}

	@Override
	public LayoutMargin getMargin()
	{
		return margin;
	}

	@Override
	public abstract void drawComponents(Point offset, List<IComponent> components);
}
