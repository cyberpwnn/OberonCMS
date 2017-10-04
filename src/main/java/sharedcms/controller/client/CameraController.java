package sharedcms.controller.client;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import sharedcms.asm.InjectorCamera;
import sharedcms.controllable.Controller;
import sharedcms.renderer.camera.ShoulderCamera;
import sharedcms.renderer.camera.ShoulderEvents;
import sharedcms.renderer.camera.ShoulderKeyHandler;
import sharedcms.renderer.camera.ShoulderKeybindings;
import sharedcms.renderer.camera.ShoulderLoader;
import sharedcms.renderer.camera.ShoulderSettings;
import sharedcms.renderer.camera.ShoulderTickHandler;

public class CameraController extends Controller
{
	private ShoulderKeyHandler kbh;
	private ShoulderTickHandler st;
	private static boolean enabled;
	public static Logger logger;
	
	public CameraController()
	{
		enabled = true;
	}
	
	@Override
	public void onPreInitialization()
	{
		
	}

	@Override
	public void onInitialization()
	{
		this.kbh = ShoulderKeybindings.registerKeybindings();
		FMLCommonHandler.instance().bus().register((Object) this.kbh);
	}

	@Override
	public void onPostInitialization()
	{
		int expMods;
		
		if(logger == null)
		{
			logger = LogManager.getLogger((String) "CMS Camera");
		}
		
		if(InjectorCamera.modifications != (expMods = 3))
		{
			logger.fatal("Only found " + InjectorCamera.modifications + " code injections, but expected " + expMods);
			logger.fatal("CMS Camera should be disabled!");
		}
		
		ShoulderLoader sl = new ShoulderLoader();
		sl.load();
		this.st = new ShoulderTickHandler();
		FMLCommonHandler.instance().bus().register((Object) this.st);
		MinecraftForge.EVENT_BUS.register((Object) new ShoulderEvents());
	}
}
