package sharedcms.gui.base;

import sharedcms.gui.canvas.ICanvas;
import sharedcms.gui.component.ComponentRecycler;
import sharedcms.gui.layout.ILayout;
import sharedcms.gui.util.Screen;
import sharedcms.gui.util.Viewport;

public abstract class RecycledUI extends BaseUI
{
	@Override
	public abstract void redraw(int w, int h);

	@Override
	public abstract void onGuiClosed();
}
