package sharedcms.gui.layout;

public interface IToggle
{
	public boolean getToggleState();
	
	public void setToggleState(boolean toggle);
	
	public void invertToggleState();
}
