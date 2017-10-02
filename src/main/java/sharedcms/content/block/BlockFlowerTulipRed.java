package sharedcms.content.block;

import java.awt.Color;

import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import sharedcms.base.AresBlockOrientalShrub;
import sharedcms.base.AresBlockShrub;

public class BlockFlowerTulipRed extends AresBlockOrientalShrub
{
	public BlockFlowerTulipRed(String unlocalizedName, Material material, SoundType type, int weight)
	{
		super(unlocalizedName, material, type, weight);
	}
	
	@Override
	public Color getRadiantColor()
	{
		return new Color(255, 0, 0);
	}
}
