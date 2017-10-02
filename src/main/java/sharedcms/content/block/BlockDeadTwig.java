package sharedcms.content.block;

import java.awt.Color;

import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import sharedcms.base.AresBlockShrub;

public class BlockDeadTwig extends AresBlockShrub
{
	public BlockDeadTwig(String unlocalizedName, Material material, SoundType type, int weight)
	{
		super(unlocalizedName, material, type, weight);
	}

	@Override
	public Color getRadiantColor()
	{
		return new Color(255, 120, 0);
	}
}
