package sharedcms.base;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class AresTab extends CreativeTabs
{
	private Item icon;
	private String unlocalizedName;
	private String localName;
	private boolean search;
	private int swid;
	
	public AresTab(String unlocalizedName)
	{
		super(getNextID(), unlocalizedName);
		
		localName = null;
		this.unlocalizedName = unlocalizedName;
		this.icon = Items.stick;
		search = false;
		swid = tabAllSearch.getSearchbarWidth();
	}
	
	public void setLocalName(String name)
	{
		localName = name;
	}
	
	public void setSearchBar(boolean has)
	{
		search = has;
	}
	
	public void setSearchBarWidth(int width)
	{
		swid = width;
	}
	
	@Override
	public boolean hasSearchBar()
	{
		return search;
	}
	
	@Override
	public int getSearchbarWidth()
	{
		return swid;
	}

	@Override
	public String getTranslatedTabLabel()
	{
		return localName == null ? super.getTranslatedTabLabel() : localName;
	}
	
	@Override
	public Item getTabIconItem()
	{
		return icon;
	}
	
	public void setIcon(Item icon)
	{
		this.icon = icon;
	}
	
	public String getUnlocalizedId()
	{
		return unlocalizedName;
	}
}
