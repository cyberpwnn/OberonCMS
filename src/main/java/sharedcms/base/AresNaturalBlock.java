package sharedcms.base;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import sharedcms.content.Content;

public class AresNaturalBlock extends AresBlock
{
	public AresNaturalBlock(String unlocalizedName, Material material, SoundType type)
	{
		super(unlocalizedName, material, Content.Tab.NATURAL, type, true);
	}
}
