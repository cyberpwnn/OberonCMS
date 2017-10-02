package sharedcms.content.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;
import sharedcms.audio.DSound;
import sharedcms.audio.SFX;
import sharedcms.base.AresBlock;
import sharedcms.base.AresNaturalBlock;
import sharedcms.content.Content;
import sharedcms.util.M;
import sharedcms.util.SuperLocation;

public class BlockLogArid extends BlockLogBase
{
	public BlockLogArid(String unlocalizedName, Material material, SoundType type)
	{
		super(unlocalizedName, material, type);
	}
	
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World w, int x, int y, int z, Random r)
	{
		
	}
}
