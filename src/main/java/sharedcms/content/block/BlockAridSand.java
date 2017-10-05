package sharedcms.content.block;

import java.awt.Color;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;
import sharedcms.base.AresBlock;
import sharedcms.base.AresNaturalBlock;
import sharedcms.content.Content;

public class BlockAridSand extends AresNaturalBlock
{
	public BlockAridSand(String unlocalizedName, Material material, SoundType type)
	{
		super(unlocalizedName, material, type);
	}
}
