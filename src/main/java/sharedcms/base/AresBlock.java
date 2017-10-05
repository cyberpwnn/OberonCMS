package sharedcms.base;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings.GameType;
import sharedcms.Info;
import sharedcms.controller.client.FXController;
import sharedcms.util.PlayerUtils;
import sharedcms.voxel.VoxelRegistry;

public class AresBlock extends Block
{
	public AresBlock(String unlocalizedName, Material material, CreativeTabs tab, SoundType type, boolean tessellate)
	{
		super(material);
		setBlockName(unlocalizedName);
		setBlockTextureName(Info.ID + ":" + getUnlocalizedName().substring(5));
		setCreativeTab(tab);
		setStepSound(type);
		setBlockUnbreakable();

		if(tessellate)
		{
			VoxelRegistry.registerForTessellator(this);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World w, int x, int y, int z, Random r)
	{
		FXController.tick(w, x, y, z, r, this);
	}

	public void playSound(World w, int x, int y, int z, String sound, float v, float p, float div)
	{
		w.playSound(x, y, z, "sharedcms:" + sound, v, (float) (p + ((Math.random() - 0.5) * 2) * div), false);
	}
}
