/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.Mod$Instance
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.common.config.Property
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package sharedcms.renderer.camera;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import sharedcms.asm.InjectorCamera;
import sharedcms.proxy.IProxy;

public class ShoulderSurfing implements IProxy
{
	private ShoulderKeyHandler kbh;
	private ShoulderTickHandler st;
	public static Configuration config;
	private static boolean enabled;
	public static Logger logger;

	@Mod.EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		ShoulderSurfing.readConfig();
	}

	@Mod.EventHandler
	public void onInit(FMLInitializationEvent event)
	{
		this.kbh = ShoulderKeybindings.registerKeybindings();
		FMLCommonHandler.instance().bus().register((Object) this.kbh);
	}

	@Mod.EventHandler
	public void onPostInit(FMLPostInitializationEvent event)
	{
		int expMods;
		if(logger == null)
		{
			logger = LogManager.getLogger((String) "ShoulderSurfing");
		}
		if(InjectorCamera.modifications != (expMods = 3))
		{
			logger.fatal("Only found " + InjectorCamera.modifications + " code injections, but expected " + expMods);
			logger.fatal("ShoulderSurfing should be disabled!");
		}
		else
		{
			logger.info("Loaded " + InjectorCamera.modifications + " code injections, ShoulderSurfing good to go!");
		}
		ShoulderLoader sl = new ShoulderLoader();
		sl.load();
		this.st = new ShoulderTickHandler();
		FMLCommonHandler.instance().bus().register((Object) this.st);
		MinecraftForge.EVENT_BUS.register((Object) new ShoulderEvents());
	}

	public static void readConfig()
	{
		ShoulderSettings.IS_DYNAMIC_CROSSHAIR_ENABLED = config.get("general", "isCrosshairDynamic", ShoulderSettings.IS_DYNAMIC_CROSSHAIR_ENABLED, "If enabled, then the crosshair moves around to line up with the block you are facing.").getBoolean(ShoulderSettings.IS_DYNAMIC_CROSSHAIR_ENABLED);
		ShoulderCamera.SHOULDER_ROTATION = (float) config.get("general", "rotationOffset", (double) ShoulderCamera.SHOULDER_ROTATION, "Third person camera rotation").getDouble((double) ShoulderCamera.SHOULDER_ROTATION);
		ShoulderCamera.SHOULDER_ZOOM_MOD = (float) config.get("general", "zoomOffset", (double) ShoulderCamera.SHOULDER_ZOOM_MOD, "Third person camera zoom").getDouble((double) ShoulderCamera.SHOULDER_ZOOM_MOD);
		ShoulderSettings.IS_ROTATION_UNLIMITED = config.get("general", "isRotationUnlimited", ShoulderSettings.IS_ROTATION_UNLIMITED, "Whether or not rotation adjustment has limits").getBoolean(ShoulderSettings.IS_ROTATION_UNLIMITED);
		ShoulderSettings.ROTATION_MAXIMUM = (float) config.get("general", "rotationMaximum", (double) ShoulderSettings.ROTATION_MAXIMUM, "If rotation is limited this is the maximum amount").getDouble((double) ShoulderSettings.ROTATION_MAXIMUM);
		ShoulderSettings.ROTATION_MINIMUM = (float) config.get("general", "rotationMinimum", (double) ShoulderSettings.ROTATION_MINIMUM, "If rotation is limited this is the minimum amount").getDouble((double) ShoulderSettings.ROTATION_MINIMUM);
		ShoulderSettings.IS_ZOOM_UNLIMITED = config.get("general", "isZoomUnlimited", ShoulderSettings.IS_ZOOM_UNLIMITED, "Whether or not zoom adjustment has limits").getBoolean(ShoulderSettings.IS_ZOOM_UNLIMITED);
		ShoulderSettings.ZOOM_MAXIMUM = (float) config.get("general", "zoomMaximum", (double) ShoulderSettings.ZOOM_MAXIMUM, "If zoom is limited this is the maximum amount").getDouble((double) ShoulderSettings.ZOOM_MAXIMUM);
		ShoulderSettings.ZOOM_MINIMUM = (float) config.get("general", "zoomMinimum", (double) ShoulderSettings.ZOOM_MINIMUM, "If zoom is limited this is the minimum amount").getDouble((double) ShoulderSettings.ZOOM_MINIMUM);
		ShoulderSettings.TRACE_TO_HORIZON_LAST_RESORT = config.get("general", "alwaysHaveCrosshair", ShoulderSettings.TRACE_TO_HORIZON_LAST_RESORT, "Whether or not to show a crosshair in the center of the screen if nothing is in range of you").getBoolean(ShoulderSettings.TRACE_TO_HORIZON_LAST_RESORT);
		ShoulderSettings.USE_CUSTOM_RAYTRACE_DISTANCE = config.get("general", "showCrosshairFarther", ShoulderSettings.USE_CUSTOM_RAYTRACE_DISTANCE, "Whether or not to show the crosshairs farther than normal").getBoolean(ShoulderSettings.USE_CUSTOM_RAYTRACE_DISTANCE);
		ShoulderSettings.HIDE_PLAYER_IF_TOO_CLOSE_TO_CAMERA = config.get("general", "keepCameraOutOfHead", ShoulderSettings.HIDE_PLAYER_IF_TOO_CLOSE_TO_CAMERA, "Whether or not to hide the player model if the camera gets too close to it").getBoolean(ShoulderSettings.HIDE_PLAYER_IF_TOO_CLOSE_TO_CAMERA);
		config.save();
	}

	public static void writeConfig()
	{
		config.get("general", "rotationOffset", (double) ShoulderCamera.SHOULDER_ROTATION, "Third person camera rotation").set(Float.toString(ShoulderCamera.SHOULDER_ROTATION));
		config.get("general", "zoomOffset", (double) ShoulderCamera.SHOULDER_ZOOM_MOD, "Third person camera zoom").set(Float.toString(ShoulderCamera.SHOULDER_ZOOM_MOD));
		config.save();
	}

	static
	{
		enabled = true;
	}
}
