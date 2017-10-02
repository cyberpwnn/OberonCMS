package sharedcms.base;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class TabIconChange
{
	private AresTab tab;
	private Block block;
	private Item item;
	
	public TabIconChange(AresTab tab, Block block)
	{
		this.tab = tab;
		this.block = block;
		item = null;
	}
	
	public TabIconChange(AresTab tab, Item item)
	{
		this.tab = tab;
		this.item = item;
		block = null;
	}

	public AresTab getTab()
	{
		return tab;
	}

	public Block getBlock()
	{
		return block;
	}

	public Item getItem()
	{
		return item;
	}
	
	public void apply()
	{
		if(item == null)
		{
			item = Item.getItemFromBlock(block);
		}
		
		tab.setIcon(item);
	}
}
