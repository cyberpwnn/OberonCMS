package sharedcms.gui.layouts;

import java.awt.Color;
import java.util.List;

import sharedcms.gui.component.IComponent;
import sharedcms.gui.layout.BasicLayout;
import sharedcms.gui.layout.ILayout;
import sharedcms.gui.util.Point;
import sharedcms.gui.util.R;
import sharedcms.gui.util.TextProperties;
import sharedcms.gui.util.Viewport;
import sharedcms.util.C;

public class LinearLayout extends BasicLayout
{
	public enum LinearDirection
	{
		VERTICAL,
		HORIZONTAL
	}

	private LinearDirection direction;

	public LinearLayout(LinearDirection direction)
	{
		this.direction = direction;
	}

	public LinearLayout()
	{
		this(LinearDirection.VERTICAL);
	}

	public void backup(Point offset, List<IComponent> components, boolean debug)
	{
		int l = 0;
		int s = 0;
		int x = getMargin().getMarginLeft() + offset.getX();
		int y = getMargin().getMarginUp() + offset.getY();
		Point lp = new Point(0, 0);
		
		for(IComponent i : components)
		{
			if(!i.isVisible())
			{
				continue;
			}
			
			Point initial = new Point(x, y).add(i.getOffset());
			Point after = new Point(0, 0);
			
			if(direction.equals(LinearDirection.VERTICAL))
			{
				y += s;
				s = i.getSize().getY() + getPadding().getPaddingUp();
				after = new Point(x, y).add(i.getOffset()).add(new Point(i.getSize().getX(), i.getSize().getY()));
			}
	
			if(direction.equals(LinearDirection.HORIZONTAL))
			{
				x += s;
				s = i.getSize().getX() + getPadding().getPaddingLeft();
				after = new Point(x, y).add(i.getOffset()).add(new Point(0, i.getSize().getY()));
			}
			
			Point p = new Point(x + i.getOffset().getX(), y + i.getOffset().getY());
			
			if(debug)
			{
				R.RECT(initial, after, C.getRGBShift(0.05), 0.15);
				R.LINE(initial, after, Color.black);
			}
			
			lp = p;
			
			i.draw(p);			
			l++;
		}
	}

	@Override
	public void drawComponents(Point offset, List<IComponent> components, boolean debug)
	{
		backup(offset, components, debug);
	}

	@Override
	public void drawComponents(Point offset, List<IComponent> components)
	{
		drawComponents(offset, components, ILayout.DEBUG);
	}

	public LinearDirection getDirection()
	{
		return direction;
	}

	public void setDirection(LinearDirection direction)
	{
		this.direction = direction;
	}
}
