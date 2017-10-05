package sharedcms.fx;

import net.minecraft.world.World;
import sharedcms.util.Location;

public interface Effect
{
	public void play(World w, int x, int y, int z);
	
	public void onPlay(World w, int x, int y, int z);
}
