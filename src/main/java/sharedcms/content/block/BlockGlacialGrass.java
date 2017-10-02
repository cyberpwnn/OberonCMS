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
import sharedcms.util.SuperLocation;

public class BlockGlacialGrass extends AresNaturalBlock
{
	public BlockGlacialGrass(String unlocalizedName, Material material, SoundType type)
	{
		super(unlocalizedName, material, type);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World w, int x, int y, int z, Random r)
	{
		Content.Effect.DUST.play(w, x, y, z, new Color(240, 240, 255));

		if(M.r(0.00015))
		{
			SFX.play(new DSound("sharedcms:" + Content.SoundMaterial.AMBIENT_WIND, 5.9f, 1f, 0.2f), new SuperLocation(x, y, z));
		}
	}
}
