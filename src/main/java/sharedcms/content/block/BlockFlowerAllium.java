package sharedcms.content.block;

import java.awt.Color;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import sharedcms.base.AresBlockOrientalShrub;
import sharedcms.base.AresBlockShrub;

public class BlockFlowerAllium extends AresBlockOrientalShrub
{
	public BlockFlowerAllium(String unlocalizedName, Material material, SoundType type, int weight)
	{
		super(unlocalizedName, material, type, weight);
	}
	
	@Override
	public Color getRadiantColor()
	{
		return new Color(187, 0, 255);
	}
}
