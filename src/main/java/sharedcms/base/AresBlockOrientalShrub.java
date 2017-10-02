package sharedcms.base;

import net.minecraft.block.material.Material;

public abstract class AresBlockOrientalShrub extends AresBlockShrub
{
	public AresBlockOrientalShrub(String unlocalizedName, Material material, SoundType type, int weight)
	{
		super(unlocalizedName, material, type, weight);
		setOriental(true);
	}
}
