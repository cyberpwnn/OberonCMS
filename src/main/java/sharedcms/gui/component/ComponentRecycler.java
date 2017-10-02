package sharedcms.gui.component;

import java.util.ArrayList;
import java.util.List;

public class ComponentRecycler
{
	private List<IComponent> components;
	
	public ComponentRecycler()
	{
		components = new ArrayList<IComponent>();
	}
	
	public void add(IComponent component)
	{
		components.add(component);
	}
	
	public List<IComponent> getComponents()
	{
		return components;
	}
}
