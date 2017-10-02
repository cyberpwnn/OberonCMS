package sharedcms.gui.layout;

import java.util.List;

import sharedcms.gui.component.IComponent;
import sharedcms.gui.util.Point;
import sharedcms.gui.util.Viewport;

public interface ILayout extends IMarginedLayout, IPaddedLayout
{
	public static boolean DEBUG = false;
	
	public void drawComponents(Point offset, List<IComponent> components, boolean debug);
	
	public void drawComponents(Point offset, List<IComponent> components);
}
