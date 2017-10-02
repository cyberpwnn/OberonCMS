package sharedcms.gui.canvas;

import java.util.ArrayList;
import java.util.List;

import sharedcms.gui.component.ComponentRecycler;
import sharedcms.gui.component.IComponent;
import sharedcms.gui.layout.ILayout;
import sharedcms.gui.layouts.LinearLayout;
import sharedcms.gui.util.Point;
import sharedcms.gui.util.Screen;
import sharedcms.gui.util.Viewport;

public class ComponentCanvas implements ICanvas
{
	private Screen screen;
	private Viewport viewport;
	private ComponentRecycler recycler;
	private ILayout layout;

	public ComponentCanvas(int width, int height, int offsetX, int offsetY)
	{
		screen = new Screen(width, height);
		viewport = new Viewport(offsetX, offsetY, width, height);
		recycler = new ComponentRecycler();
		layout = new LinearLayout();
	}

	public ComponentCanvas(int width, int height)
	{
		this(width, height, 0, 0);
	}

	@Override
	public Screen getScreen()
	{
		return screen;
	}

	@Override
	public Viewport getViewport()
	{
		return viewport;
	}

	@Override
	public ComponentRecycler getRecycler()
	{
		return recycler;
	}

	@Override
	public ILayout getLayout()
	{
		return layout;
	}

	@Override
	public void add(IComponent component)
	{
		getRecycler().add(component);
	}

	@Override
	public void drawComponents()
	{
		Point offset = viewport.getOffset().clone().invert();
		getLayout().drawComponents(offset, getRecycler().getComponents());
	}

	@Override
	public void setLayout(ILayout layout)
	{
		this.layout = layout;
	}

	@Override
	public Point getScaledScreen(float scale)
	{
		return screen.getSize().clone().multiply(scale);
	}

	@Override
	public int getScaledX(float scale)
	{
		return getScaledScreen(scale).getX();
	}

	@Override
	public int getScaledY(float scale)
	{
		return getScaledScreen(scale).getY();
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
		int x = (getViewport().getWidth() - component.getSize().getX() + getViewport().getOffsetX()) / 2;
		component.getOffset().setX(x);
	}

	@Override
	public void centerHeight(IComponent component)
	{
		int y = (getViewport().getHeight() - component.getSize().getY() + getViewport().getOffsetY()) / 2;
		component.getOffset().setY(y);
	}

	@Override
	public List<IComponent> getEveryComponent()
	{
		List<IComponent> c = new ArrayList<IComponent>();
		
		for(IComponent i : getRecycler().getComponents())
		{
			c.addAll(getEveryComponent(i));
		}
		
		return c;
	}
	
	private List<IComponent> getEveryComponent(IComponent component)
	{
		List<IComponent> c = new ArrayList<IComponent>();
		c.add(component);
		
		for(IComponent i : component.getComponents())
		{
			c.addAll(getEveryComponent(i));
		}
		
		return c;
	}
}
