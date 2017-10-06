package sharedcms.content.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import sharedcms.content.Content;

public class ItemCrusader extends ItemSword
{
	public ItemCrusader(String unlocalizedName)
	{
		super(ToolMaterial.WOOD);
		setCreativeTab(Content.Tab.WEAPONS);
		setUnlocalizedName(unlocalizedName);
	}
}
