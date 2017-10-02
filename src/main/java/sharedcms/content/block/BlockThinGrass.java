package sharedcms.content.block;

import java.awt.Color;

import net.minecraft.block.material.Material;
import sharedcms.base.AresBlockColoredShrub;

public class BlockThinGrass extends AresBlockColoredShrub
{
	public BlockThinGrass(String unlocalizedName, Material material, SoundType type, int weight)
	{
		super(unlocalizedName, material, type, weight);
	}
	
	@Override
	public Color getRadiantColor()
	{
		return new Color(187, 255, 0);
	}
}
