package sharedcms.base;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import sharedcms.content.Content;

public class AresStructureBlock extends AresBlock
{
	public AresStructureBlock(String unlocalizedName, Material material, SoundType type)
	{
		super(unlocalizedName, material, Content.Tab.NATURAL, type, true);
	}
}
