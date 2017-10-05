package sharedcms.fx;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import sharedcms.util.Location;

public interface BlockEffect extends Effect
{
	public double getChance();
	
	public boolean qualifies(Block block);
	
	public boolean meetsConditions(World w, int x, int y, int z);
}
