package sharedcms.content.block;

import java.awt.Color;

import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import sharedcms.base.AresBlockShrub;

public class BlockFlowerPaeonia extends AresBlockShrub
{
	public BlockFlowerPaeonia(String unlocalizedName, Material material, SoundType type, int weight)
	{
		super(unlocalizedName, material, type, weight);
	}
	
	@Override
	public Color getRadiantColor()
	{
		return new Color(250, 0, 255);
	}
}
