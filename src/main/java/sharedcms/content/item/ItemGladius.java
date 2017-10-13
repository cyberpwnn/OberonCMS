package sharedcms.content.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import sharedcms.content.Content;

public class ItemGladius extends ItemSword
{
	public ItemGladius(String unlocalizedName)
	{
		super(ToolMaterial.WOOD);
		setCreativeTab(Content.Tab.WEAPONS);
		setUnlocalizedName(unlocalizedName);
	}
}
