package sharedcms.gui.canvas;

import java.util.List;

import sharedcms.gui.component.ComponentRecycler;
import sharedcms.gui.component.IComponent;
import sharedcms.gui.layout.ILayout;
import sharedcms.gui.util.Point;
import sharedcms.gui.util.Screen;
import sharedcms.gui.util.Viewport;

public interface ICanvas
{
	public Screen getScreen();

	public Viewport getViewport();

	public ComponentRecycler getRecycler();

	public ILayout getLayout();

	public void setLayout(ILayout layout);

	public void add(IComponent component);

	public void drawComponents();

	public Point getScaledScreen(float scale);

	public int getScaledX(float scale);

	public int getScaledY(float scale);

	public void center(IComponent component);

	public void centerWidth(IComponent component);

	public void centerHeight(IComponent component);
	
	public List<IComponent> getEveryComponent();
}
