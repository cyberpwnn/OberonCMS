package sharedcms.content.block;

import java.awt.Color;

import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import sharedcms.base.AresBlockOrientalShrub;

public class BlockFlowerTulipWhite extends AresBlockOrientalShrub
{
	public BlockFlowerTulipWhite(String unlocalizedName, Material material, SoundType type, int weight)
	{
		super(unlocalizedName, material, type, weight);
	}
	
	@Override
	public Color getRadiantColor()
	{
		return new Color(255, 255, 255);
	}
}
