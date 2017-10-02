package sharedcms.config.graphics;

import org.apache.commons.lang3.StringUtils;

public enum GraphicsLevel
{
	BARE,
	LOW,
	MEDIUM,
	HIGH,
	ULTRA;
	
	public String toString()
	{
		return StringUtils.capitalize(name().toLowerCase());
	}
}
