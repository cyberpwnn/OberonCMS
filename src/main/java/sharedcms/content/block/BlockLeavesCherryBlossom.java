package sharedcms.content.block;

import java.awt.Color;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import sharedcms.base.AresNaturalBlock;
import sharedcms.content.Content;

public class BlockLeavesCherryBlossom extends AresNaturalBlock
{
	public BlockLeavesCherryBlossom(String unlocalizedName, Material material, SoundType type)
	{
		super(unlocalizedName, material, type);
		this.setTickRandomly(false);
		this.setHardness(0.2F);
		this.setLightOpacity(1);
	}
}
