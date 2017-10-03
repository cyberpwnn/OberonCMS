package sharedcms.content.block;

import java.awt.Color;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;
import sharedcms.audio.DSound;
import sharedcms.audio.SFX;
import sharedcms.base.AresBlock;
import sharedcms.base.AresNaturalBlock;
import sharedcms.content.Content;
import sharedcms.util.M;

public class BlockColdSandstoneBrick extends AresNaturalBlock
{
	public BlockColdSandstoneBrick(String unlocalizedName, Material material, SoundType type)
	{
		super(unlocalizedName, material, type);
	}
}
