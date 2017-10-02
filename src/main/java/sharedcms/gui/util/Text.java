package sharedcms.gui.util;

public class Text
{
	private String text;
	private TextProperties properties;
	
	public Text(String text)
	{
		this.text = text;
		this.properties = new TextProperties();
	}

	public String get()
	{
		return text;
	}

	public void set(String text)
	{
		this.text = text;
	}

	public TextProperties getProperties()
	{
		return properties;
	}

	public void setProperties(TextProperties properties)
	{
		this.properties = properties;
	}
}
