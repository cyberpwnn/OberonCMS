package sharedcms.renderer.animation;

import java.io.File;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import sharedcms.Ares;
import sharedcms.Info;
import sharedcms.proxy.IProxy;
import sharedcms.renderer.animation.pack.BendsPack;
import sharedcms.renderer.animation.settings.SettingsBoolean;
import sharedcms.renderer.animation.settings.SettingsNode;

public class MoBends implements IProxy
{
	public static final String MODID = Info.ID;
	public static final String MODNAME = Info.NAME;
	public static final String VERSION = Info.VERSION;
	@SidedProxy(serverSide = "sharedcms.renderer.animation.CommonProxy", clientSide = "sharedcms.renderer.animation.client.ClientProxy")
	public static CommonProxy proxy;
	@Mod.Instance(value = Info.ID)
	public static Ares instance;
	public static File configFile;
	public static int refreshModel;

	@Mod.EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{
		configFile = event.getSuggestedConfigurationFile();
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		proxy.preInit(config);
		config.save();
	}

	public static void saveConfig()
	{
		Configuration config = new Configuration(configFile);
		config.load();
		
		for(int i = 0; i < AnimatedEntity.animatedEntities.length; ++i)
		{
			config.get("Animate", AnimatedEntity.animatedEntities[i].id, false).setValue(AnimatedEntity.animatedEntities[i].animate);
		}
		
		config.get("General", "Sword Trail", true).setValue(((SettingsBoolean) SettingsNode.getSetting((String) "swordTrail")).data);
		config.get("General", "Current Pack", true).setValue(BendsPack.currentPack);
		config.save();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
	}

	static
	{
		refreshModel = 0;
	}

	@Override
	public void onInit(FMLInitializationEvent e)
	{
		
	}

	@Override
	public void onPostInit(FMLPostInitializationEvent e)
	{
		
	}
}
