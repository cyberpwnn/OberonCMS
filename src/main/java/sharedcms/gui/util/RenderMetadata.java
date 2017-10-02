package sharedcms.gui.util;

import java.util.List;

import sharedcms.gui.component.IComponent;

public class RenderMetadata
{
	private MouseEvent mouse;
	private List<IComponent> components;

	public RenderMetadata(MouseEvent mouse, List<IComponent> components)
	{
		this.mouse = mouse;
		this.components = components;
	}

	public MouseEvent getMouse()
	{
		return mouse;
	}

	public List<IComponent> getComponents()
	{
		return components;
	}
}
