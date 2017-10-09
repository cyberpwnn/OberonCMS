package sharedcms.controller.client;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import sharedcms.controllable.Controller;
import sharedcms.renderer.world.AnimationHandler;
import sharedcms.renderer.world.ChunkAnimatorConfig;

public class ChunkAnimationController extends Controller
{
	public static ChunkAnimationController INSTANCE;
	public AnimationHandler animationHandler;
	public ChunkAnimatorConfig config;

	public ChunkAnimationController()
	{
		INSTANCE = this;
	}

	@Override
	public void onPreInitialization()
	{
		this.animationHandler = new AnimationHandler();
		FMLCommonHandler.instance().bus().register((Object) this);
		this.config = new ChunkAnimatorConfig();
		this.config.preInit();
	}

	@Override
	public void onInitialization()
	{

	}

	@Override
	public void onPostInitialization()
	{

	}
}
