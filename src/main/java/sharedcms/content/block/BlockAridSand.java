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
	
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World w, int x, int y, int z, Random r)
	{
		Content.Effect.DUST.play(w, x, y, z, new Color(255, 227, 137));
	}
}
