package sharedcms.base;

import net.minecraft.entity.Entity;

public class AresEntitySet
{
	private Class<? extends Entity> e;
	private String backgroundColor;
	private String spotColor;
	private String name;
	
	public AresEntitySet(Class<? extends Entity> e, String name, String backgroundColor, String spotColor)
	{
		super();
		this.e = e;
		this.name = name;
		this.backgroundColor = backgroundColor;
		this.spotColor = spotColor;
	}
	
	public String getName()
	{
		return name;
	}

	public Class<? extends Entity> getE()
	{
		return e;
	}
	
	public String getBackgroundColor()
	{
		return backgroundColor;
	}
	
	public String getSpotColor()
	{
		return spotColor;
	}
}
