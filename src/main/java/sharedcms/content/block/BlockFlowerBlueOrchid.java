package sharedcms.content.block;

import java.awt.Color;

import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import sharedcms.base.AresBlockOrientalShrub;

public class BlockFlowerBlueOrchid extends AresBlockOrientalShrub
{
	public BlockFlowerBlueOrchid(String unlocalizedName, Material material, SoundType type, int weight)
	{
		super(unlocalizedName, material, type, weight);
	}
	
	@Override
	public Color getRadiantColor()
	{
		return new Color(0, 195, 255);
	}
}
