package sharedcms.voxel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import sharedcms.L;

public class VoxelRegistry
{
	public static List<Block> tessellate = new ArrayList<Block>();
	public static List<Block> detessellate = new ArrayList<Block>();
	public static Map<Block, Double> widthTessellator = new HashMap<Block, Double>();
	public static Map<Block, Double> heightTessellator = new HashMap<Block, Double>();
	
	public static void registerForTessellator(Block block)
	{
		tessellate.add(block);
	}
	
	public static void unregisterForTessellator(Block block)
	{
		detessellate.add(block);
		L.l("UNRegistering Block for Tessellation: " + block.getUnlocalizedName());
	}
	
	public static void registerTessellationWidth(Block block, double width)
	{
		widthTessellator.put(block, width);
	}
	
	public static void registerTessellationHeight(Block block, double height)
	{
		widthTessellator.put(block, height);
	}
	
	public static void registerTessellationScale(Block block, double width, double height)
	{
		registerTessellationWidth(block, width);
		registerTessellationHeight(block, height);
		L.l("Registering Block Tessellation: " + block.getUnlocalizedName() + "(" + width + "::" + height + ")");
	}
	
	public static void registerTessellationScale(Block block, double widthAndHeight)
	{
		registerTessellationScale(block, widthAndHeight, widthAndHeight);
	}
}
