package sharedcms.content.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import sharedcms.audio.DSound;
import sharedcms.audio.SFX;
import sharedcms.base.AresNaturalBlock;
import sharedcms.content.Content;
import sharedcms.util.Location;
import sharedcms.util.M;

public class BlockLogBase extends AresNaturalBlock
{
	public BlockLogBase(String unlocalizedName, Material material, SoundType type)
	{
		super(unlocalizedName, material, type);
	}
}
