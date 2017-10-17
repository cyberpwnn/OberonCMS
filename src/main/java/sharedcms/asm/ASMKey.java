package sharedcms.asm;

public class ASMKey
{
	public static final String CLASS_BOXEL = "sharedcms/controller/shared/BoxelController";
	public static final String CLASS_BLOCK_O = "aji";
	public static final String CLASS_BLOCK_D = "net.minecraft.block.Block";
	public static final String CLASS_BLOCKRENDERER = "sharedcms/voxel/SoftBlockRenderer";
	public static final String CLASS_BLOCKCACTUS_O = "ajt";
	public static final String CLASS_BLOCKCACTUS_D = "net.minecraft.block.BlockCactus";
	public static final String CLASS_MINECRAFT_D = "net.minecraft.client.Minecraft";
	public static final String CLASS_MINECRAFT_O = "bao";
	public static final String CLASS_GUISCREEN_D = "net.minecraft.client.gui.GuiScreen";
	public static final String CLASS_GUISCREEN_DM = "net/minecraft/client/gui/GuiScreen";
	public static final String CLASS_GUICONTROLLER = "sharedcms.controller.client.GuiController";
	public static final String CLASS_GUICONTROLLER_M = "sharedcms/controller/client/GuiController";
	public static final String CLASS_GUISCREEN_O = "bdw";
	public static final String CLASS_GUIMAINMENU_D = "net/minecraft/client/gui/GuiMainMenu";
	public static final String CLASS_GUIMAINMENU_O = "bee";
	public static final String CLASS_BACKGROUNDBLURCONTROLLER = "sharedcms/controller/client/BackgroundBlurController";
	public static final String CLASS_UIMAINMENU = "sharedcms/gui/UIMainMenu";

	public static final String SIGNATURE_BLOCK_COLLISION = "(Lahb;IIILazt;Ljava/util/List;Lsa;)V";
	public static final String SIGNATURE_BOXEL_CHECK_O = "(Laji;)Z";
	public static final String SIGNATURE_BOXEL_CHECK_D = "(Lnet/minecraft/block/Block;)Z";
	public static final String SIGNATURE_BLOCKRENDERER_INJECT_O = "(Laji;Lahb;IIILazt;Ljava/util/List;Lsa;)V";
	public static final String SIGNATURE_BLOCKRENDERER_INJECT_D = "(Lnet/minecraft/block/Block;Lnet/minecraft/world/World;IIILnet/minecraft/util/AxisAlignedBB;Ljava/util/List;Lnet/minecraft/entity/Entity;)V";
	public static final String SIGNATURE_BLOCKCACTUS_O = "(Lahb;IIILsa;)V";
	public static final String SIGNATURE_BLOCKCACTUS_D = "(Lnet/minecraft/world/World;IIILnet/minecraft/entity/Entity;)V";
	public static final String SIGNATURE_GUISCREEN_BACKGROUND = "(Z)I";
	public static final String SIGNATURE_MINECRAFT_STARTGAME = "()V";
	public static final String SIGNATURE_GUICONTROLLER_RENDERBACKGROUND = "()V";
	public static final String SIGNATURE_GUICONTROLLER_RENDERBG = "(I)V";
	public static final String SIGNATURE_MINECRAFT_GETLIMITFRAMERATE = "()I";

	public static final String METHOD_GUISCREEN_DRAWBG_D = "drawBackground";
	public static final String METHOD_GUISCREEN_DRAWBG_O = "c";
	public static final String METHOD_GUICONTROLLER_RENDERBACKGROUND = "renderBackgroundNoWorld";
	public static final String METHOD_GUICONTROLLER_RENDERBACKGROUND_PRE = "renderBackgroundNoWorldPre";
	public static final String METHOD_MINECRAFT_GETLIMITFRAMERATE_D = "getLimitFramerate";
	public static final String METHOD_MINECRAFT_GETLIMITFRAMERATE_O = "h";
	public static final String METHOD_MINECRAFT_STARTGAME_O = "ag";
	public static final String METHOD_MINECRAFT_STARTGAME_D = "startGame";
	public static final String METHOD_BLOCK_COLLISION_O = "a";
	public static final String METHOD_BLOCK_COLLISION_D = "addCollisionBoxesToList";
	public static final String METHOD_BOXEL_CHECK = "isBlockSoftForCollision";
	public static final String METHOD_BLOCKRENDERER_INJECT = "inject";
	public static final String METHOD_BLOCKCACTUS_COLLISION_O = "a";
	public static final String METHOD_BLOCKCACTUS_COLLISION_D = "onEntityCollidedWithBlock";
	public static final String METHOD_GUISCREEN_DRAWBACKGROUND_D = "drawWorldBackground";
	public static final String METHOD_GUISCREEN_DRAWBACKGROUND_O = "func_146270_b";
	public static final String METHOD_BGC_BACKGROUND_D = "getBackgroundColor";
}
