package sharedcms.controller.client;

import java.io.File;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import sharedcms.Ares;
import sharedcms.Info;
import sharedcms.controllable.Controller;
import sharedcms.renderer.animation.AnimatedEntity;
import sharedcms.renderer.animation.CommonProxy;
import sharedcms.renderer.animation.client.ClientProxy;
import sharedcms.renderer.animation.pack.BendsPack;
import sharedcms.renderer.animation.settings.SettingsBoolean;
import sharedcms.renderer.animation.settings.SettingsNode;

public class AnimationController extends Controller
{
	public static final String MODID = Info.ID;
	public static final String MODNAME = Info.NAME;
	public static final String VERSION = Info.VERSION;
	public static ClientProxy proxy;
	public static Ares instance;
	public static File configFile;
	public static int refreshModel;

	public AnimationController()
	{
		instance = Ares.instance;
		proxy = new ClientProxy();
		refreshModel = 0;
	}

	@Override
	public void onPreInitialization()
	{
		configFile = new File("f");
		Configuration config = new Configuration(configFile);
		saveConfig();
		proxy.preInit(config);
	}

	@Override
	public void onInitialization()
	{
		
	}

	@Override
	public void onPostInitialization()
	{
		
	}

	public static void saveConfig()
	{
		Configuration config = new Configuration(configFile);

		for(int i = 0; i < AnimatedEntity.animatedEntities.length; ++i)
		{
			config.get("Animate", AnimatedEntity.animatedEntities[i].id, false).setValue(AnimatedEntity.animatedEntities[i].animate);
		}

		config.get("General", "Sword Trail", true).setValue(((SettingsBoolean) SettingsNode.getSetting((String) "swordTrail")).data);
		config.get("General", "Current Pack", true).setValue(BendsPack.currentPack);
	}
}
