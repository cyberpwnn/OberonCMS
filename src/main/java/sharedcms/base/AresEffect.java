package sharedcms.base;

import net.minecraft.world.World;

public abstract class AresEffect
{
	public AresEffect()
	{
		
	}
	
	public abstract void play(World w, int x, int y, int z, Object... data);
}
