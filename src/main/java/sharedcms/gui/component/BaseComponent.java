package sharedcms.gui.component;

import java.util.List;

import sharedcms.gui.layout.ILayout;
import sharedcms.gui.layouts.LinearLayout;
import sharedcms.gui.util.Point;
import sharedcms.gui.util.TextAlignment;

public abstract class BaseComponent implements IComponent
{
	private boolean visible;
	private Point off;
	private Point size;
	private ILayout layout;
	private ComponentRecycler recycler;
	private Point compiledPoint;

	public BaseComponent(int w, int h)
	{
		visible = true;
		size = new Point(w, h);
		off = new Point(0, 0);
		layout = new LinearLayout();
		recycler = new ComponentRecycler();
		compiledPoint = off.clone();
	}

	@Override
	public Point getSize()
	{
		return size;
	}

	@Override
	public ILayout getLayout()
	{
		return layout;
	}

	@Override
	public void setLayout(ILayout layout)
	{
		this.layout = layout;
	}

	@Override
	public List<IComponent> getComponents()
	{
		return recycler.getComponents();
	}

	@Override
	public void add(IComponent component)
	{
		recycler.add(component);
	}

	@Override
	public void draw(Point offset)
	{
		onDraw(offset);
		layout.drawComponents(offset, getComponents());
		compiledPoint = offset.clone();
	}

	@Override
	public Point getOffset()
	{
		return off;
	}

	@Override
	public void center(IComponent component)
	{
		centerWidth(component);
		centerHeight(component);
	}
	
	@Override
	public void centerWidth(IComponent component)
	{
		if(component instanceof ITextual)
		{
			((ITextual)component).setTextAlignment(TextAlignment.CENTER);
		}
		
		int x = (getSize().getX() - component.getSize().getX() + off.getX()) / 2;
		component.getOffset().setX(x);
	}
	
	@Override
	public void centerHeight(IComponent component)
	{
		int y = (getSize().getY() - component.getSize().getY() + off.getY()) / 2;
		component.getOffset().setY(y);
	}

	@Override
	public boolean isVisible()
	{
		return visible;
	}

	@Override
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}
	
	@Override
	public Point getCompiledOffset()
	{
		return compiledPoint;
	}

	@Override
	public boolean contains(Point p)
	{
		return p.isWithin(getCompiledOffset(), getCompiledOffset().clone().add(getSize()));
	}
	
	@Override
	public abstract void onDraw(Point offset);
}
