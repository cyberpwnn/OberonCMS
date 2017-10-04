package sharedcms.controller.client;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import sharedcms.Info;
import sharedcms.L;
import sharedcms.controllable.Controller;
import sharedcms.voxel.SoftBlockRenderer;
import sharedcms.voxel.VoxelRegistry;

public class BoxelController extends Controller
{
	private boolean isOutdated = false;
	public static SoftBlockRenderer softBlockRenderer;
	private static List softBlockList;
	private static List liquidBlockList;
	private static List leavesBlockList;
	public static boolean isNoCubesEnabled;
	public static boolean isAutoStepEnabled;
	protected static List<String> list;
	protected static List<Block> ListBlack;
	protected static boolean isblackListing;
	protected static boolean autoDetection;
	protected static boolean autoDirt;
	protected static boolean autoStone;
	protected static boolean autoGravel;
	protected static boolean autoLeaves;
	protected static boolean autoSnow;
	protected static boolean autoLiquid;
	protected static boolean autoIce;

	public BoxelController()
	{
		softBlockList = new ArrayList();
		list = new ArrayList<String>();
		ListBlack = new ArrayList<Block>();
		isblackListing = false;
		autoDetection = true;
		autoIce = true;
		softBlockRenderer = new SoftBlockRenderer();
		liquidBlockList = new ArrayList();
		leavesBlockList = new ArrayList();
		liquidBlockList.add(Blocks.water);
		liquidBlockList.add(Blocks.flowing_water);
		liquidBlockList.add(Blocks.lava);
		liquidBlockList.add(Blocks.flowing_lava);
		leavesBlockList.add(Blocks.leaves);
		leavesBlockList.add(Blocks.leaves2);
	}

	@Override
	public void onPreInitialization()
	{
		autoDetection = true;
		isNoCubesEnabled = true;
		isAutoStepEnabled = Info.TESSELLATION_STEP;
		loadBlocks();
		loadAutoDetection();
		
		System.out.println("Well it fucking loaded, whats the god damn problem.Z");
		try
		{
			Thread.sleep(5000);
		}
		
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onInitialization()
	{

	}

	@Override
	public void onPostInitialization()
	{

	}

	public static void loadAutoDetection()
	{
		autoDirt = true;
		autoGravel = true;
		autoLeaves = true;
		autoStone = false;
		autoSnow = true;
		autoIce = true;
		autoLiquid = true;
		String[] strautoblacklist = new String[] {};
		ArrayList<Block> autoblacklist = new ArrayList<Block>();

		for(String str : strautoblacklist)
		{
			autoblacklist.add(createBlock(str));
		}

		for(Object gblock : GameData.getBlockRegistry())
		{
			Block block = (Block) gblock;

			if(block.getMaterial() == Material.plants || autoblacklist.contains((Object) block))
			{
				continue;
			}

			if(autoDirt && block.getMaterial() == Material.ground && block.isOpaqueCube() || autoDirt && block.getMaterial() == Material.grass && block.isOpaqueCube())
			{
				softBlockList.add(block);
			}

			if(autoGravel && block.getMaterial() == Material.sand && block.isOpaqueCube())
			{
				softBlockList.add(block);
			}

			if(autoStone && block.getMaterial() == Material.rock && block.isOpaqueCube())
			{
				softBlockList.add(block);
			}

			if(autoSnow && block.getMaterial() == Material.snow || autoSnow && block.getMaterial() == Material.craftedSnow)
			{
				softBlockList.add(block);
			}

			if(autoIce && block.getMaterial() == Material.ice || autoIce && block.getMaterial() == Material.packedIce)
			{
				softBlockList.add(block);
			}

			if(autoLeaves && block.getMaterial() == Material.leaves)
			{
				leavesBlockList.add(block);
			}

			if(!(autoLiquid && block.getMaterial() == Material.lava || autoLiquid && block.getMaterial() == Material.water) && (!autoLiquid || !(block instanceof BlockLiquid)))
			{
				continue;
			}

			liquidBlockList.add(block);
		}

		softBlockList.add(Blocks.stained_hardened_clay);
		softBlockList.add(Blocks.sandstone);
		softBlockList.add(Blocks.glowstone);
		softBlockList.addAll(VoxelRegistry.tessellate);

		for(Block i : VoxelRegistry.detessellate)
		{
			softBlockList.remove(i);
		}

		for(Object i : softBlockList)
		{
			Block b = (Block) i;

			String f = Info.TESSELLATION_RATE_WIDTH + ":" + Info.TESSELLATION_RATE_HEIGHT;
		}

		L.l("Setting " + softBlockList.size() + " blocks to tessellate.");
	}

	public static void loadBlocks()
	{
		String[] whitelist = new String[] {"minecraft:grass", "minecraft:dirt", "minecraft:sand", "minecraft:gravel", "minecraft:clay", "minecraft:farmland", "minecraft:mycelium", "minecraft:snow_layer", "minecraft:stone", "minecraft:coal_ore", "minecraft:iron_ore", "minecraft:gold_ore", "minecraft:diamond_ore", "minecraft:redstone_ore", "minecraft:emerald_ore", "minecraft:bedrock", "minecraft:netherrack", "minecraft:soul_sand", "minecraft:soul_sand", "minecraft:end_stone"};

		for(String ss : whitelist)
		{
			softBlockList.add(createBlock(ss));

			if(!ss.equals("minecraft:redstone_ore"))
			{
				continue;
			}

			softBlockList.add(Blocks.lit_redstone_ore);
		}
	}

	public static Block createBlock(String s)
	{
		s = s.replaceFirst(":", "\u00a9");
		String[] parts = s.split("\u00a9");

		return GameRegistry.findBlock((String) parts[0], (String) parts[1]);
	}

	public static boolean isBlockSoft(Block block)
	{
		if(VoxelRegistry.tessellate.contains(block))
		{
			return true;
		}

		if(!isblackListing)
		{
			return softBlockList.contains((Object) block);
		}

		return !ListBlack.contains((Object) block);
	}

	public static boolean isBlockSoftForCollision(Block block)
	{
		return (VoxelRegistry.tessellate.contains(block) || softBlockList.contains((Object) block)) && isAutoStepEnabled;
	}

	public static boolean isBlockLiquid(Block block)
	{
		return liquidBlockList.contains((Object) block);
	}

	public static void renderBlockSoft(Block block)
	{
		softBlockList.add(block);
	}

	public static void registerAsLiquid(Block block)
	{
		liquidBlockList.add(block);
	}

	public static void registerAsLeaves(Block block)
	{
		leavesBlockList.add(block);
	}
}
