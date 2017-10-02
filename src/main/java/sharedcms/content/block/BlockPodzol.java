package sharedcms.content.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import sharedcms.audio.DSound;
import sharedcms.audio.SFX;
import sharedcms.base.AresNaturalBlock;
import sharedcms.content.Content;
import sharedcms.content.particle.ParticleFirefly;
import sharedcms.util.M;
import sharedcms.util.SuperLocation;

public class BlockPodzol extends AresNaturalBlock
{
	public BlockPodzol(String unlocalizedName, Material material, SoundType type)
	{
		super(unlocalizedName, material, type);
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World w, int x, int y, int z, Random r)
	{
		if(M.r(0.01))
		{
			if(w.getWorldTime() > 12966 && w.getWorldTime() < 22916)
			{
				if(M.r(0.01))
				{
					SFX.play(new DSound("sharedcms:" + Content.SoundMaterial.AMBIENT_WOLF, 0.3f, 1f, 0.2f), new SuperLocation(x, y, z));
				}
			}
		}
	}
}
