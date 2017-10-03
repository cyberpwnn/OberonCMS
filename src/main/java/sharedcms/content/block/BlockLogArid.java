package sharedcms.content.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

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
