package sharedcms.gui.base;

import java.awt.MouseInfo;
import java.util.List;

import org.lwjgl.input.Mouse;

import sharedcms.gui.canvas.ComponentCanvas;
import sharedcms.gui.canvas.ICanvas;
import sharedcms.gui.component.IButton;
import sharedcms.gui.component.IComponent;
import sharedcms.gui.component.ISlider;
import sharedcms.gui.util.MouseEvent;
import sharedcms.gui.util.Point;
import sharedcms.gui.util.RenderMetadata;
import sharedcms.util.GList;
import sharedcms.util.MouseAdapter;
import sharedcms.util.MouseButton;
import sharedcms.util.MouseManager;
import sharedcms.util.MouseState;

public abstract class LUI extends RecycledUI implements MouseAdapter
{
	private Point lastSize;
	protected ICanvas canvas;
	private int ww = 0;
	private int hh = 0;

	public LUI()
	{
		lastSize = new Point(0, 0);
		MouseManager.registerMouseAdapter(this);
	}

	public abstract void reconstruct(int w, int h);

	public abstract void draw();
	
	@Override
	public void onMouseStateChanged()
	{
		try
		{
			Point cursor = MouseManager.getPointer(ww, hh);
			
			GList<IComponent> k = new GList<IComponent>(canvas.getEveryComponent());
			GList<IComponent> v = k.copy();

			for(IComponent i : k.copy())
			{
				if(!i.contains(cursor) || !i.isVisible())
				{
					k.remove(i);
				}
			}
			
			for(IComponent i : k.copy())
			{
				if(!i.isVisible())
				{
					v.remove(i);
				}
			}

			if(MouseManager.STATE.equals(MouseState.CLICKING_DOWN))
			{
				for(IComponent i : k)
				{
					if(i instanceof IButton)
					{
						((IButton) i).setClicked(true);
					}

					if(i instanceof ISlider)
					{
						((ISlider) i).slid(cursor);
					}
				}
			}

			else if(MouseManager.STATE.equals(MouseState.CLICKING_UP))
			{
				for(IComponent i : k)
				{
					if(i instanceof IButton)
					{
						((IButton) i).setClicked(false);
						((IButton) i).clicked();
					}

					if(i instanceof ISlider)
					{
						((ISlider) i).slid(cursor);
					}
				}

				for(IComponent i : v)
				{
					if(i instanceof IButton)
					{
						if(((IButton) i).isClicked())
						{
							((IButton) i).setClicked(false);
						}
					}

					if(i instanceof ISlider)
					{
						if(((ISlider)i).isSliding())
						{
							((ISlider) i).uslid();
						}
					}
				}
			}
			
			else if(MouseManager.STATE.equals(MouseState.DRAGGING))
			{
				for(IComponent i : k)
				{
					if(i instanceof ISlider)
					{
						((ISlider) i).slid(cursor);
					}
				}
			}

			else if(MouseManager.STATE.equals(MouseState.HOVERING))
			{
				for(IComponent i : v)
				{
					if(i instanceof IButton)
					{
						((IButton) i).setHovered(i.contains(cursor));

						if(((IButton) i).isClicked() && !i.contains(cursor))
						{
							((IButton) i).setClicked(false);
						}
					}
				}
			}
		}
		
		catch(Exception e)
		{
			
		}
	}

	@Override
	public void redraw(int w, int h)
	{
		ww = w;
		hh = h;
		
		if(canvas == null)
		{
			canvas = new ComponentCanvas(w, h);
		}

		

		if(lastSize.getX() != w || lastSize.getY() != h)
		{
			lastSize.setX(w);
			lastSize.setY(h);
			canvas = new ComponentCanvas(w, h);
			reconstruct(w, h);
		}

		draw();
		canvas.drawComponents();
	}

	@Override
	public void onGuiClosed()
	{
		MouseManager.removeMouseAdapter(this);
		onGuiClose();
	}
	
	public abstract void onGuiClose();
}
