package sharedcms.fx;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import sharedcms.util.Location;

public abstract class SimpleBlockEffect implements BlockEffect
{
	@Override
	public void play(World w, int x, int y, int z)
	{
		if(meetsConditions(w, x, y, z))
		{
			onPlay(w, x, y, z);
		}
	}
}
