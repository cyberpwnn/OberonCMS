package sharedcms.gui.component;

import java.util.List;

import sharedcms.gui.canvas.ICanvas;
import sharedcms.gui.layout.ILayout;
import sharedcms.gui.util.Point;
import sharedcms.gui.util.Viewport;

public interface IComponent
{
	public boolean isVisible();
	
	public void setVisible(boolean visible);
	
	public Point getOffset();
	
	public Point getSize();
	
	public ILayout getLayout();

	public void setLayout(ILayout layout);

	public List<IComponent> getComponents();

	public void add(IComponent component);
	
	public void onDraw(Point offset);
	
	public void draw(Point offset);
	
	public void center(IComponent c);

	public void centerWidth(IComponent component);

	public void centerHeight(IComponent component);
	
	public Point getCompiledOffset();
	
	public boolean contains(Point p);
}
