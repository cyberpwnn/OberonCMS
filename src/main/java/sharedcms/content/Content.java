package sharedcms.content;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import sharedcms.base.AresBiome;
import sharedcms.base.AresBlockShrub;
import sharedcms.base.BSM;
import sharedcms.content.block.BlockAridSand;
import sharedcms.content.block.BlockAridStone;
import sharedcms.content.block.BlockCharredBrick;
import sharedcms.content.block.BlockCharredChisled;
import sharedcms.content.block.BlockCharredEarth;
import sharedcms.content.block.BlockCharredStone;
import sharedcms.content.block.BlockCharredStoneSurface;
import sharedcms.content.block.BlockColdDirt;
import sharedcms.content.block.BlockColdGrass;
import sharedcms.content.block.BlockColdSandstone;
import sharedcms.content.block.BlockColdSandstoneBrick;
import sharedcms.content.block.BlockColdStone;
import sharedcms.content.block.BlockCrispyBrick;
import sharedcms.content.block.BlockCrispyPile;
import sharedcms.content.block.BlockDeadBush;
import sharedcms.content.block.BlockDeadTwig;
import sharedcms.content.block.BlockFern;
import sharedcms.content.block.BlockFlowerAllium;
import sharedcms.content.block.BlockFlowerBlueOrchid;
import sharedcms.content.block.BlockFlowerDaisy;
import sharedcms.content.block.BlockFlowerDandelion;
import sharedcms.content.block.BlockFlowerHoustonia;
import sharedcms.content.block.BlockFlowerLotusBlack;
import sharedcms.content.block.BlockFlowerPaeonia;
import sharedcms.content.block.BlockFlowerRose;
import sharedcms.content.block.BlockFlowerTulipBlue;
import sharedcms.content.block.BlockFlowerTulipCyan;
import sharedcms.content.block.BlockFlowerTulipMagenta;
import sharedcms.content.block.BlockFlowerTulipOrange;
import sharedcms.content.block.BlockFlowerTulipPink;
import sharedcms.content.block.BlockFlowerTulipPurple;
import sharedcms.content.block.BlockFlowerTulipRed;
import sharedcms.content.block.BlockFlowerTulipWhite;
import sharedcms.content.block.BlockFlowerTulipYellow;
import sharedcms.content.block.BlockGlacialDirt;
import sharedcms.content.block.BlockGlacialGrass;
import sharedcms.content.block.BlockGlacialRock;
import sharedcms.content.block.BlockIceCrushedFrame;
import sharedcms.content.block.BlockIceFrame;
import sharedcms.content.block.BlockIceScratchedFrame;
import sharedcms.content.block.BlockIceSmooth;
import sharedcms.content.block.BlockIceSmoothFrame;
import sharedcms.content.block.BlockLamp;
import sharedcms.content.block.BlockLapisBlock;
import sharedcms.content.block.BlockLeavesAcacia;
import sharedcms.content.block.BlockLeavesCherryBlossom;
import sharedcms.content.block.BlockLeavesDarkOak;
import sharedcms.content.block.BlockLeavesGlacial;
import sharedcms.content.block.BlockLeavesJungle;
import sharedcms.content.block.BlockLeavesOak;
import sharedcms.content.block.BlockLeavesRedwood;
import sharedcms.content.block.BlockLeavesSpruce;
import sharedcms.content.block.BlockLogAcacia;
import sharedcms.content.block.BlockLogArid;
import sharedcms.content.block.BlockLogBirch;
import sharedcms.content.block.BlockLogDarkOak;
import sharedcms.content.block.BlockLogGlacial;
import sharedcms.content.block.BlockLogJungle;
import sharedcms.content.block.BlockLogOak;
import sharedcms.content.block.BlockLogRedwood;
import sharedcms.content.block.BlockLogSpruce;
import sharedcms.content.block.BlockMoldedStone;
import sharedcms.content.block.BlockPathGravelRough;
import sharedcms.content.block.BlockPathGravelWarm;
import sharedcms.content.block.BlockPathSand;
import sharedcms.content.block.BlockPathStone;
import sharedcms.content.block.BlockPathStoneBrick;
import sharedcms.content.block.BlockPathStoneBrickCracked;
import sharedcms.content.block.BlockPathStoneBrickCrushed;
import sharedcms.content.block.BlockPathStoneFrame;
import sharedcms.content.block.BlockPlanksAcacia;
import sharedcms.content.block.BlockPlanksBirch;
import sharedcms.content.block.BlockPlanksDark;
import sharedcms.content.block.BlockPlanksJungle;
import sharedcms.content.block.BlockPlanksOak;
import sharedcms.content.block.BlockPodzol;
import sharedcms.content.block.BlockRiverStone;
import sharedcms.content.block.BlockRoughDirt;
import sharedcms.content.block.BlockShortGrass;
import sharedcms.content.block.BlockSmoothDirt;
import sharedcms.content.block.BlockTallGrass;
import sharedcms.content.block.BlockThinGrass;
import sharedcms.content.effect.EffectDust;
import sharedcms.content.effect.EffectFallingLeaf;
import sharedcms.content.effect.EffectFirefly;
import sharedcms.content.tab.TabNatural;
import sharedcms.content.world.biome.BiomeDesert;
import sharedcms.content.world.biome.BiomeDesertArid;
import sharedcms.content.world.biome.BiomeForest;
import sharedcms.content.world.biome.BiomeForestHills;
import sharedcms.content.world.biome.BiomeForestHillsExtreme;
import sharedcms.content.world.biome.BiomeGlacier;
import sharedcms.content.world.biome.BiomeMountains;
import sharedcms.content.world.biome.BiomeMountainsExtreme;
import sharedcms.content.world.biome.BiomeOrientalPrairie;
import sharedcms.content.world.biome.BiomePlains;
import sharedcms.content.world.biome.BiomePlainsHilly;
import sharedcms.content.world.biome.BiomePlainsHillyExtreme;
import sharedcms.content.world.biome.BiomePlainsTrees;
import sharedcms.content.world.biome.BiomeRedwoods;
import sharedcms.content.world.biome.decorator.BiomeDecoratorDefault;
import sharedcms.content.world.biome.decorator.BiomeDecoratorDesert;
import sharedcms.content.world.biome.decorator.BiomeDecoratorDesertArid;
import sharedcms.content.world.biome.decorator.BiomeDecoratorEmpty;
import sharedcms.content.world.biome.decorator.BiomeDecoratorForest;
import sharedcms.content.world.biome.decorator.BiomeDecoratorForestHills;
import sharedcms.content.world.biome.decorator.BiomeDecoratorForestHillsExtreme;
import sharedcms.content.world.biome.decorator.BiomeDecoratorGlacier;
import sharedcms.content.world.biome.decorator.BiomeDecoratorMountains;
import sharedcms.content.world.biome.decorator.BiomeDecoratorOriental;
import sharedcms.content.world.biome.decorator.BiomeDecoratorPlains;
import sharedcms.content.world.biome.decorator.BiomeDecoratorPlainsTrees;
import sharedcms.content.world.biome.decorator.BiomeDecoratorRedwoods;
import sharedcms.content.world.generator.WorldGeneratorEmpty;
import sharedcms.content.world.type.WorldTypeAres;
import sharedcms.controller.shared.ContentController;
import sharedcms.registry.IRegistrant;
import sharedcms.voxel.VoxelRegistry;

public class Content implements IRegistrant
{
	public static class Tab
	{
		public static TabNatural NATURAL = new TabNatural("tnatural");
		public static TabNatural SHRUBS = new TabNatural("tshrubs");

		public static void s()
		{

		}
	}

	public static class Block
	{
		public static BlockColdSandstone COLD_SANDSTONE = new BlockColdSandstone("sandstone", Material.rock, Sound.STONE);
		public static BlockColdSandstoneBrick COLD_SANDSTONE_BRICK = new BlockColdSandstoneBrick("sandstone_brick", Material.rock, Sound.STONE);
		public static BlockPlanksDark PLANKS_DARK = new BlockPlanksDark("plank_spruce", Material.wood, Sound.WOOD);
		public static BlockPlanksOak PLANKS_OAK = new BlockPlanksOak("planks_oak", Material.wood, Sound.WOOD);
		public static BlockPlanksAcacia PLANKS_ACACIA = new BlockPlanksAcacia("planks_acacia", Material.wood, Sound.WOOD);
		public static BlockPlanksBirch PLANKS_BIRCH = new BlockPlanksBirch("planks_birch", Material.wood, Sound.WOOD);
		public static BlockPlanksJungle PLANKS_JUNGLE = new BlockPlanksJungle("plank_jungle", Material.wood, Sound.WOOD);
		public static BlockLamp LAMP = new BlockLamp("redstone_lamp", Material.glass, Sound.GLASS);
		public static BlockMoldedStone MOLDED_STONE = new BlockMoldedStone("molded_stone", Material.rock, Sound.STONE);
		public static BlockLapisBlock LAPIS_BLOCK = new BlockLapisBlock("lapis_block", Material.rock, Sound.STONE);
		public static BlockIceSmooth IICE_SMOOTH = new BlockIceSmooth("ice", Material.rock, Sound.STONE);
		public static BlockIceSmoothFrame ICE_SMOOTH_FRAME = new BlockIceSmoothFrame("frosted_ice_frame_smooth", Material.rock, Sound.STONE);
		public static BlockIceScratchedFrame ICE_SCRATCHED_FRAME = new BlockIceScratchedFrame("frosted_ice_frame_scratched", Material.rock, Sound.STONE);
		public static BlockIceCrushedFrame ICE_CRUSHED_FRAME = new BlockIceCrushedFrame("frosted_ice_crushed_frame", Material.rock, Sound.STONE);
		public static BlockIceFrame ICE_FRAME = new BlockIceFrame("frosted_ice_frame", Material.rock, Sound.STONE);
		public static BlockCrispyBrick CRISPY_BRICK = new BlockCrispyBrick("cripsy_brick", Material.rock, Sound.STONE);
		public static BlockCrispyPile CRISPY_PILE = new BlockCrispyPile("cripsy_pile", Material.ground, Sound.GRAVEL);
		public static BlockCharredBrick CHARRED_BRICK = new BlockCharredBrick("charred_brick", Material.rock, Sound.STONE);
		public static BlockCharredChisled CHARRED_CHISLED = new BlockCharredChisled("charred_brick_chisled", Material.rock, Sound.STONE);
		public static BlockCharredEarth CHARRED_EARTH = new BlockCharredEarth("charred_earth", Material.rock, Sound.STONE);
		public static BlockCharredStone CHARRED_STONE = new BlockCharredStone("charred_stone", Material.rock, Sound.STONE);
		public static BlockCharredStoneSurface CHARRED_STONE_SURFACE = new BlockCharredStoneSurface("charred_stone_surface", Material.rock, Sound.STONE);
		public static BlockFlowerLotusBlack FLOWER_LOTUS_BLACK = new BlockFlowerLotusBlack("flower_lotus_black", Material.plants, Sound.GRASS, 0);
		public static BlockColdGrass COLD_GRASS = new BlockColdGrass("grass", Material.grass, Sound.GRASS);
		public static BlockColdDirt COLD_DIRT = new BlockColdDirt("dirt", Material.ground, Sound.GRAVEL);
		public static BlockColdStone COLD_STONE = new BlockColdStone("cold_stone", Material.rock, Sound.STONE);
		public static BlockPathStoneBrick PATH_STONE_BRICK = new BlockPathStoneBrick("path_stonebrick", Material.rock, Sound.STONE);
		public static BlockPathStoneBrickCracked PATH_STONE_BRICK_CRACKED = new BlockPathStoneBrickCracked("path_stonebrick_cracked", Material.rock, Sound.STONE);
		public static BlockPathStoneBrickCrushed PATH_STONE_BRICK_CRUSHED = new BlockPathStoneBrickCrushed("path_stonebrick_crushed", Material.rock, Sound.STONE);
		public static BlockPathGravelWarm PATH_GRAVEL_WARM = new BlockPathGravelWarm("path_gravel_warm", Material.ground, Sound.GRAVEL);
		public static BlockPathGravelRough PATH_GRAVEL_ROUGH = new BlockPathGravelRough("path_gravel_rough", Material.ground, Sound.GRAVEL);
		public static BlockPathSand PATH_SAND = new BlockPathSand("path_sand", Material.sand, Sound.SAND);
		public static BlockPathStoneFrame PATH_STONE_FRAME = new BlockPathStoneFrame("path_stone_frame", Material.rock, Sound.STONE);
		public static BlockPathStone PATH_STONE = new BlockPathStone("path_stone", Material.rock, Sound.STONE);
		public static BlockSmoothDirt SMOOTH_DIRT = new BlockSmoothDirt("smooth_dirt", Material.ground, Sound.GRAVEL);
		public static BlockRiverStone RIVER_STONE = new BlockRiverStone("river_stone", Material.rock, Sound.STONE);
		public static BlockDeadBush DEAD_BUSH = new BlockDeadBush("dead_bush", Material.plants, Sound.GRASS, 0);
		public static BlockDeadTwig DEAD_TWIG = new BlockDeadTwig("dead_twig", Material.plants, Sound.GRASS, 0);
		public static BlockTallGrass TALL_GRASS = new BlockTallGrass("tall_grass", Material.plants, Sound.GRASS, 0);
		public static BlockThinGrass THIN_GRASS = new BlockThinGrass("thin_grass", Material.plants, Sound.GRASS, 0);
		public static BlockFern FERN = new BlockFern("fern", Material.plants, Sound.GRASS, 0);
		public static BlockShortGrass SHORT_GRASS = new BlockShortGrass("short_grass", Material.plants, Sound.GRASS, 0);
		public static BlockFlowerDaisy FLOWER_DAISY = new BlockFlowerDaisy("flower_daisy", Material.plants, Sound.GRASS, 4);
		public static BlockFlowerAllium FLOWER_ALLIUM = new BlockFlowerAllium("flower_allium", Material.plants, Sound.GRASS, 2);
		public static BlockFlowerBlueOrchid FLOWER_BLUE_ORCHID = new BlockFlowerBlueOrchid("flower_blue_orchid", Material.plants, Sound.GRASS, 2);
		public static BlockFlowerDandelion FLOWER_DANDELION = new BlockFlowerDandelion("flower_dandelion", Material.plants, Sound.GRASS, 6);
		public static BlockFlowerHoustonia FLOWER_HOUSTONIA = new BlockFlowerHoustonia("flower_houstonia", Material.plants, Sound.GRASS, 4);
		public static BlockFlowerPaeonia FLOWER_PAEONIA = new BlockFlowerPaeonia("flower_paeonia", Material.plants, Sound.GRASS, 3);
		public static BlockFlowerRose FLOWER_ROSE = new BlockFlowerRose("flower_rose", Material.plants, Sound.GRASS, 8);
		public static BlockFlowerTulipBlue FLOWER_TULIP_BLUE = new BlockFlowerTulipBlue("flower_tulip_blue", Material.plants, Sound.GRASS, 7);
		public static BlockFlowerTulipCyan FLOWER_TULIP_CYAN = new BlockFlowerTulipCyan("flower_tulip_cyan", Material.plants, Sound.GRASS, 7);
		public static BlockFlowerTulipMagenta FLOWER_TULIP_MAGENTA = new BlockFlowerTulipMagenta("flower_tulip_magenta", Material.plants, Sound.GRASS, 7);
		public static BlockFlowerTulipPurple FLOWER_TULIP_PURPLE = new BlockFlowerTulipPurple("flower_tulip_purple", Material.plants, Sound.GRASS, 7);
		public static BlockFlowerTulipYellow FLOWER_TULIP_YELLOW = new BlockFlowerTulipYellow("flower_tulip_yellow", Material.plants, Sound.GRASS, 7);
		public static BlockFlowerTulipOrange FLOWER_TULIP_ORANGE = new BlockFlowerTulipOrange("flower_tulip_orange", Material.plants, Sound.GRASS, 7);
		public static BlockFlowerTulipPink FLOWER_TULIP_PINK = new BlockFlowerTulipPink("flower_tulip_pink", Material.plants, Sound.GRASS, 6);
		public static BlockFlowerTulipRed FLOWER_TULIP_RED = new BlockFlowerTulipRed("flower_tulip_red", Material.plants, Sound.GRASS, 7);
		public static BlockFlowerTulipWhite FLOWER_TULIP_WHITE = new BlockFlowerTulipWhite("flower_tulip_white", Material.plants, Sound.GRASS, 5);
		public static BlockLogArid LOG_ARID = new BlockLogArid("log_arid", Material.wood, Sound.WOOD);
		public static BlockLogOak LOG_OAK = new BlockLogOak("log_oak", Material.wood, Sound.WOOD);
		public static BlockLogBirch LOG_BIRCH = new BlockLogBirch("log_birch", Material.wood, Sound.WOOD);
		public static BlockLogDarkOak LOG_DARK = new BlockLogDarkOak("log_dark", Material.wood, Sound.WOOD);
		public static BlockLogSpruce LOG_SPRUCE = new BlockLogSpruce("log_spruce", Material.wood, Sound.WOOD);
		public static BlockLogJungle LOG_JUNGLE = new BlockLogJungle("log_jungle", Material.wood, Sound.WOOD);
		public static BlockLogAcacia LOG_ACACIA = new BlockLogAcacia("log_acacia", Material.wood, Sound.WOOD);
		public static BlockLogRedwood LOG_REDWOOD = new BlockLogRedwood("log_redwood", Material.wood, Sound.WOOD);
		public static BlockLogGlacial LOG_GLACIAL = new BlockLogGlacial("log_glacial", Material.wood, Sound.WOOD);
		public static BlockLeavesCherryBlossom LEAVES_CHERRY_BLOSSOM = new BlockLeavesCherryBlossom("leaves_cherry_blossom", Material.leaves, Sound.GRASS);
		public static BlockLeavesOak LEAVES_OAK = new BlockLeavesOak("leaves_oak", Material.leaves, Sound.GRASS);
		public static BlockLeavesDarkOak LEAVES_DARK = new BlockLeavesDarkOak("leaves_dark", Material.leaves, Sound.GRASS);
		public static BlockLeavesSpruce LEAVES_SPRUCE = new BlockLeavesSpruce("leaves_spruce", Material.leaves, Sound.GRASS);
		public static BlockLeavesJungle LEAVES_JUNGLE = new BlockLeavesJungle("leaves_jungle", Material.leaves, Sound.GRASS);
		public static BlockLeavesAcacia LEAVES_ACACIA = new BlockLeavesAcacia("leaves_acacia", Material.leaves, Sound.GRASS);
		public static BlockLeavesRedwood LEAVES_REDWOOD = new BlockLeavesRedwood("leaves_redwood", Material.leaves, Sound.GRASS);
		public static BlockLeavesGlacial LEAVES_GLACIAL = new BlockLeavesGlacial("leaves_glacial", Material.leaves, Sound.GRASS);
		public static BlockAridStone ARID_STONE = new BlockAridStone("arid_stone", Material.rock, Sound.STONE);
		public static BlockAridSand ARID_SAND = new BlockAridSand("arid_sand", Material.sand, Sound.SAND);
		public static BlockGlacialGrass GLACIAL_GRASS = new BlockGlacialGrass("glacial_grass", Material.grass, Sound.SNOW);
		public static BlockGlacialDirt GLACIAL_DIRT = new BlockGlacialDirt("glacial_dirt", Material.sand, Sound.GRAVEL);
		public static BlockGlacialRock GLACIAL_ROCK = new BlockGlacialRock("glacial_rock", Material.sand, Sound.STONE);
		public static BlockPodzol PODZOL = new BlockPodzol("podzol", Material.ground, Sound.GRAVEL);
		public static BlockRoughDirt ROUGH_DIRT = new BlockRoughDirt("rough_dirt", Material.ground, Sound.GRAVEL);

		public static void s()
		{

		}
	}

	public static class Sound
	{
		public static SoundType ANVIL = net.minecraft.block.Block.soundTypeAnvil;
		public static SoundType CLOTH = net.minecraft.block.Block.soundTypeCloth;
		public static SoundType GLASS = net.minecraft.block.Block.soundTypeGlass;
		public static SoundType GRASS = net.minecraft.block.Block.soundTypeGrass;
		public static SoundType GRAVEL = net.minecraft.block.Block.soundTypeGravel;
		public static SoundType LADDER = net.minecraft.block.Block.soundTypeLadder;
		public static SoundType METAL = net.minecraft.block.Block.soundTypeMetal;
		public static SoundType PISTON = net.minecraft.block.Block.soundTypePiston;
		public static SoundType SAND = net.minecraft.block.Block.soundTypeSand;
		public static SoundType SNOW = net.minecraft.block.Block.soundTypeSnow;
		public static SoundType STONE = net.minecraft.block.Block.soundTypeStone;
		public static SoundType WOOD = net.minecraft.block.Block.soundTypeWood;

		public static void s()
		{

		}
	}

	public static class BlockSound
	{
		public static BSM ROCK = new BSM(SoundMaterial.STONE_WALK, SoundMaterial.STONE_WANDER, SoundMaterial.STONE_WANDER, SoundMaterial.STONE_WANDER, SoundMaterial.STONE_RUN);
		public static BSM SNOW = new BSM(SoundMaterial.AMBIENT_JUMP, SoundMaterial.SNOW_WALK, SoundMaterial.SNOW_WALK, SoundMaterial.SNOW_WANDER, SoundMaterial.SNOW_RUN);
		public static BSM GRASS = new BSM(SoundMaterial.AMBIENT_JUMP, SoundMaterial.DIRT_LAND, SoundMaterial.GRASS_WALK, SoundMaterial.GRASS_WANDER, SoundMaterial.GRASS_RUN);
		public static BSM DIRT = new BSM(SoundMaterial.AMBIENT_JUMP, SoundMaterial.DIRT_LAND, SoundMaterial.DIRT_WALK, SoundMaterial.DIRT_WANDER, SoundMaterial.DIRT_RUN);
		public static BSM GRAVEL = new BSM(SoundMaterial.AMBIENT_JUMP, SoundMaterial.GRAVEL_LAND, SoundMaterial.GRAVEL_LAND, SoundMaterial.GRAVEL_WANDER, SoundMaterial.GRAVEL_LAND);
		public static BSM SAND = new BSM(SoundMaterial.AMBIENT_JUMP, SoundMaterial.GRAVEL_LAND, SoundMaterial.GRAVEL_LAND, SoundMaterial.GRAVEL_WANDER, SoundMaterial.GRAVEL_LAND);
		public static BSM WOOD = new BSM(SoundMaterial.AMBIENT_JUMP, SoundMaterial.WOOD_WALK, SoundMaterial.WOOD_WALK, SoundMaterial.BLUNTWOOD_WANDER, SoundMaterial.DECKWOOD_RUN);
		public static BSM PLANKS = new BSM(SoundMaterial.AMBIENT_JUMP, SoundMaterial.SQUEAKYWOOD_WALK, SoundMaterial.SQUEAKYWOOD_WALK, SoundMaterial.SQUEAKYWOOD_WANDER, SoundMaterial.DECKWOOD_RUN);
		public static BSM METALBLOCK = new BSM(SoundMaterial.AMBIENT_JUMP, SoundMaterial.METALBOX_WALK, SoundMaterial.METALBOX_WALK, SoundMaterial.METALBOX_WANDER, SoundMaterial.METALBOX_RUN);
		public static BSM METALBAR = new BSM(SoundMaterial.AMBIENT_JUMP, SoundMaterial.METALBAR_WALK, SoundMaterial.METALBAR_WALK, SoundMaterial.METALBAR_WANDER, SoundMaterial.METALBAR_RUN);
		public static BSM WOOL = new BSM(SoundMaterial.AMBIENT_JUMP, SoundMaterial.RUG_WALK, SoundMaterial.RUG_WALK, SoundMaterial.RUG_WALK, SoundMaterial.RUG_WALK);
		public static BSM GLASS = new BSM(SoundMaterial.AMBIENT_JUMP, SoundMaterial.GLASS_HARD, SoundMaterial.GLASS_HARD, SoundMaterial.GLASS_HARD, SoundMaterial.GLASS_HARD);
		public static BSM MUD = new BSM(SoundMaterial.AMBIENT_JUMP, SoundMaterial.MUD_WALK, SoundMaterial.MUD_WALK, SoundMaterial.MUD_WANDER, SoundMaterial.MUD_WALK);

		public static void s()
		{

		}
	}

	public static class SoundMaterial
	{
		public static String BLUNTWOOD_WALK = "fs.bluntwood.bluntwood_walk";
		public static String BLUNTWOOD_WANDER = "fs.bluntwood.bluntwood_wander";
		public static String BRUSH_THROUGH = "fs.brush.brush_through";
		public static String CONCRETE_RUN = "fs.concrete.concrete_run";
		public static String CONCRETE_WALK = "fs.concrete.concrete_walk";
		public static String CONCRETE_WANDER = "fs.concrete.concrete_wander";
		public static String DECKWOOD_RUN = "fs.deckwood.deckwood_run";
		public static String DIRT_LAND = "fs.dirt.dirt_land";
		public static String DIRT_RUN = "fs.dirt.dirt_run";
		public static String DIRT_WALK = "fs.dirt.dirt_walk";
		public static String DIRT_WANDER = "fs.dirt.dirt_wander";
		public static String GLASS_HARD = "fs.glass.glass_hard";
		public static String GLASS_HIT = "fs.glass.glass_hit";
		public static String GRASS_RUN = "fs.grass.grass_run";
		public static String GRASS_WALK = "fs.grass.grass_walk";
		public static String GRASS_WANDER = "fs.grass.grass_wander";
		public static String GRAVEL_LAND = "fs.gravel.gravel_land";
		public static String GRAVEL_RUN = "fs.gravel.gravel_run";
		public static String GRAVEL_WALK = "fs.gravel.gravel_walk";
		public static String GRAVEL_WANDER = "fs.gravel.gravel_wander";
		public static String LEAVES_THROUGH = "fs.leaves.leaves_through";
		public static String LINO_RUN = "fs.lino.lino_run";
		public static String LINO_WALK = "fs.lino.lino_walk";
		public static String MARBLE_RUN = "fs.marble.marble_run";
		public static String MARBLE_WALK = "fs.marble.marble_walk";
		public static String MARBLE_WANDER = "fs.marble.marble_wander";
		public static String METALBAR_LAND = "fs.metalbar.metalbar_land";
		public static String METALBAR_RUN = "fs.metalbar.metalbar_run";
		public static String METALBAR_WALK = "fs.metalbar.metalbar_walk";
		public static String METALBAR_WANDER = "fs.metalbar.metalbar_wander";
		public static String METALBOX_RUN = "fs.metalbox.metalbox_run";
		public static String METALBOX_WALK = "fs.metalbox.metalbox_walk";
		public static String METALBOX_WANDER = "fs.metalbox.metalbox_wander";
		public static String MUD_WALK = "fs.mud.mud_walk";
		public static String MUD_WANDER = "fs.mud.mud_wander";
		public static String MUIFFLEDLEDICE_WALK = "fs.muffledice.muffledice_walk";
		public static String QUICKSAND_WALK = "fs.quicksand.quicksand_walk";
		public static String RUG_WALK = "fs.rug.rug_walk";
		public static String SAND_RUN = "fs.sand.sand_run";
		public static String SAND_WALK = "fs.sand.sand_walk";
		public static String SNOW_RUN = "fs.snow.snow_run";
		public static String SNOW_WALK = "fs.snow.snow_walk";
		public static String SNOW_WANDER = "fs.snow.snow_wander";
		public static String SQUEAKYWOOD_WALK = "fs.squeakywood.squeakywood_walk";
		public static String SQUEAKYWOOD_WANDER = "fs.squeakywood.squeakywood_wander";
		public static String STONE_RUN = "fs.stone.stone_run";
		public static String STONE_WALK = "fs.stone.stone_walk";
		public static String STONE_WANDER = "fs.stone.stone_wander";
		public static String WATER_THROUGH = "fs.water_stereofix.water_through";
		public static String WATER_WANDER = "fs.water_stereofix.water_wander";
		public static String WEAKICE_WALK = "fs.weakice.weakice_walk";
		public static String WOOD_WALK = "fs.wood.wood_walk";
		public static String WEATHER_RAIN = "rain";
		public static String WEATHER_DUST = "dust";
		public static String AMBIENT_ICE = "ice";
		public static String AMBIENT_FROG = "frog";
		public static String AMBIENT_WIND = "wind";
		public static String AMBIENT_CRICKETS = "crickets";
		public static String AMBIENT_FOREST = "forest";
		public static String AMBIENT_BEACH = "beach";
		public static String AMBIENT_RIVER = "river";
		public static String AMBIENT_PLAINS = "plains";
		public static String AMBIENT_JUNGLE = "jungle";
		public static String AMBIENT_SOULSAND = "soulsand";
		public static String AMBIENT_END = "theend";
		public static String AMBIENT_BIRD = "bird";
		public static String AMBIENT_WOODPECKER = "woodpecker";
		public static String AMBIENT_OWL = "owl";
		public static String AMBIENT_UNDERGROUND = "underground";
		public static String AMBIENT_ROCKFALL = "rockfall";
		public static String AMBIENT_BIGROCKFALL = "bigrockfall";
		public static String AMBIENT_MONSTERGROWL = "monstergrowl";
		public static String AMBIENT_WATERDROPS = "waterdrops";
		public static String AMBIENT_SEAGULLS = "seagulls";
		public static String AMBIENT_CYOTE = "coyote";
		public static String AMBIENT_WOLF = "wolf";
		public static String AMBIENT_UNDERDEEPOCEAN = "underdeepocean";
		public static String AMBIENT_UNDEROCEAN = "underocean";
		public static String AMBIENT_UNDERRIVER = "underriver";
		public static String AMBIENT_UNDERWATER = "underwater";
		public static String AMBIENT_WHALE = "whale";
		public static String AMBIENT_HEARTBEAT = "heartbeat";
		public static String AMBIENT_TUMMY = "tummy";
		public static String AMBIENT_FLOORSQUEAK = "floorsqueak";
		public static String AMBIENT_BREATHING = "breathing";
		public static String AMBIENT_JUMP = "jump";
		public static String AMBIENT_BEES = "bees";
		public static String AMBIENT_INSECTCRAWL = "insectcrawl";
		public static String AMBIENT_SWOOSH = "swoosh";
		public static String AMBIENT_INSECTBUZZ = "insectbuzz";
		public static String AMBIENT_GNATT = "gnatt";
		public static String AMBIENT_GRASSHOPPER = "grasshopper";
		public static String AMBIENT_CROCODILE = "crocodile";
		public static String AMBIENT_RATTLESNAKE = "rattlesnake";
		public static String AMBIENT_ELEPHANT = "elephant";
		public static String AMBIENT_HISS = "hiss";
		public static String AMBIENT_NEWSPRINT = "newsprint";
		public static String AMBIENT_PAGEFLIP = "pageflip";
		public static String AMBIENT_PAGEFLIPHEAVY = "pageflipheavy";
		public static String AMBIENT_BOOKSHELF = "bookshelf";
		public static String AMBIENT_CRAFTING = "crafting";
		public static String AMBIENT_BISON = "bison";
		public static String AMBIENT_PRIMATES = "primates";
		public static String AMBIENT_BOWPULL = "bowpull";

		public static void s()
		{

		}
	}

	public static class Item
	{
		public static void s()
		{

		}
	}

	public static class WorldType
	{
		public static WorldTypeAres ARES = new WorldTypeAres("ares", 1337);

		public static void s()
		{

		}
	}

	public static class Effect
	{
		public static EffectFirefly FIREFLY = new EffectFirefly();
		public static EffectFallingLeaf FALLING_LEAF = new EffectFallingLeaf();
		public static EffectDust DUST = new EffectDust();

		public static void s()
		{

		}
	}

	public static class WorldGenerator
	{
		public static WorldGeneratorEmpty EMPTY = new WorldGeneratorEmpty();

		public static void s()
		{

		}
	}

	public static class Biome
	{
		public static BiomePlains PLAINS = new BiomePlains(100);
		public static BiomePlainsHilly PLAINS_HILLY = new BiomePlainsHilly(101);
		public static BiomePlainsHillyExtreme PLAINS_HILLY_EXTREME = new BiomePlainsHillyExtreme(102);
		public static BiomeForest FOREST = new BiomeForest(103);
		public static BiomeMountains MOUNTAINS = new BiomeMountains(104);
		public static BiomeMountainsExtreme MOUNTAINS_EXTREME = new BiomeMountainsExtreme(105);
		public static BiomeDesert DESERT = new BiomeDesert(105);
		public static BiomeDesertArid DESERT_ARID = new BiomeDesertArid(106);
		public static BiomeForestHills FOREST_HILLS = new BiomeForestHills(107);
		public static BiomeForestHillsExtreme FOREST_HILLS_EXTREME = new BiomeForestHillsExtreme(108);
		public static BiomeRedwoods REDWOODS = new BiomeRedwoods(109);
		public static BiomeOrientalPrairie ORIENTAL_PRAIRIE = new BiomeOrientalPrairie(110);
		public static BiomePlainsTrees PLAINS_TREES = new BiomePlainsTrees(111);
		public static BiomeGlacier GLACIER = new BiomeGlacier(112);

		public static void s()
		{

		}
	}

	public static class BiomeDecorator
	{
		public static BiomeDecoratorDefault DEFAULT = new BiomeDecoratorDefault(Biome.PLAINS);
		public static BiomeDecoratorEmpty EMPTY = new BiomeDecoratorEmpty(Biome.PLAINS);
		public static BiomeDecoratorPlains PLAINS = new BiomeDecoratorPlains(Biome.PLAINS);
		public static BiomeDecoratorDesert DESERT = new BiomeDecoratorDesert(Biome.DESERT);
		public static BiomeDecoratorDesertArid DESERT_ARID = new BiomeDecoratorDesertArid(Biome.DESERT_ARID);
		public static BiomeDecoratorMountains MOUNTAINS = new BiomeDecoratorMountains(Biome.MOUNTAINS);
		public static BiomeDecoratorForest FOREST = new BiomeDecoratorForest(Biome.FOREST);
		public static BiomeDecoratorForestHills FOREST_HILLS = new BiomeDecoratorForestHills(Biome.FOREST_HILLS);
		public static BiomeDecoratorForestHillsExtreme FOREST_HILLS_EXTREME = new BiomeDecoratorForestHillsExtreme(Biome.FOREST_HILLS_EXTREME);
		public static BiomeDecoratorRedwoods REDWOODS = new BiomeDecoratorRedwoods(Biome.REDWOODS);
		public static BiomeDecoratorOriental ORIENTAL = new BiomeDecoratorOriental(Biome.ORIENTAL_PRAIRIE);
		public static BiomeDecoratorPlainsTrees PLAINS_TREES = new BiomeDecoratorPlainsTrees(Biome.PLAINS_TREES);
		public static BiomeDecoratorGlacier GLACIER = new BiomeDecoratorGlacier(Biome.GLACIER);

		public static void s()
		{

		}
	}

	public static List<AresBlockShrub> flowers()
	{
		List<AresBlockShrub> b = new ArrayList<AresBlockShrub>();

		for(Field i : Block.class.getDeclaredFields())
		{
			try
			{
				b.add((AresBlockShrub) i.get(null));
			}

			catch(Exception e)
			{

			}
		}

		return b;
	}

	public static List<AresBiome> biomes()
	{
		List<AresBiome> b = new ArrayList<AresBiome>();

		if(hasExclusiveBiomes())
		{
			for(Field i : Biome.class.getDeclaredFields())
			{
				if(isExclusive(i))
				{
					try
					{
						b.add((AresBiome) i.get(null));
					}

					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}

		else
		{
			for(Field i : Biome.class.getDeclaredFields())
			{
				try
				{
					b.add((AresBiome) i.get(null));
				}

				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}

		return b;
	}

	private static boolean isExclusive(Field f)
	{
		return f.isAnnotationPresent(DebugExclusive.class);
	}

	private static boolean hasExclusiveBiomes()
	{
		for(Field i : Biome.class.getDeclaredFields())
		{
			if(isExclusive(i))
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public void onPreRegister(ContentController cms, Side side)
	{
		VoxelRegistry.registerForTessellator(Blocks.cobblestone);
		VoxelRegistry.registerForTessellator(Blocks.mossy_cobblestone);

		try
		{
			for(Field i : Block.class.getDeclaredFields())
			{
				cms.register(i.get(null));
			}

			for(Field i : Item.class.getDeclaredFields())
			{
				cms.register(i.get(null));
			}

			for(Field i : Tab.class.getDeclaredFields())
			{
				cms.register(i.get(null));
			}

			for(Field i : WorldType.class.getDeclaredFields())
			{
				cms.register(i.get(null));
			}

			for(Field i : Biome.class.getDeclaredFields())
			{
				cms.register(i.get(null));
			}

			for(Field i : BiomeDecorator.class.getDeclaredFields())
			{
				cms.register(i.get(null));
			}

			for(Field i : WorldGenerator.class.getDeclaredFields())
			{
				cms.register(i.get(null));
			}

			for(Field i : Effect.class.getDeclaredFields())
			{
				cms.register(i.get(null));
			}
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void init()
	{
		Content.Tab.s();
		Content.Sound.s();
		Content.Block.s();
		Content.Item.s();
		Content.WorldType.s();
		Content.BiomeDecorator.s();
		Content.Biome.s();
		Content.Effect.s();
		Content.SoundMaterial.s();
	}
}
