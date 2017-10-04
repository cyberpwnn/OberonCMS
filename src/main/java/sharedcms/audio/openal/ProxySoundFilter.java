/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.Mod$Instance
 *  cpw.mods.fml.common.SidedProxy
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.server.integrated.IntegratedServer
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.common.config.Property
 *  paulscode.sound.SoundSystemConfig
 *  paulscode.sound.SoundSystemException
 */
package sharedcms.audio.openal;

import java.util.Random;
import java.util.TreeSet;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import sharedcms.Info;
import sharedcms.audio.filter.FilterLowPass;
import sharedcms.audio.filter.FilterReverb;
import sharedcms.proxy.IProxy;

public class ProxySoundFilter
{
	public static OpenALClientHandler proxy;
	@SideOnly(value = Side.CLIENT)
	private static Minecraft mc;
	private static Random rand;
	public static boolean loaded;
	public static int profileSize;
	public static boolean doReverb;
	public static boolean doLowPass;
	public static boolean doOcclusion;
	public static FilterLowPass lowPassFilter;
	public static FilterReverb reverbFilter;
	public static TreeSet<Integer> highOcclusionSet;
	public static TreeSet<Integer> highReverbSet;
	public static TreeSet<Integer> midReverbSet;
	public static TreeSet<Integer> lowReverbSet;
	public static boolean DEBUG;

	public void onPreInit()
	{
		proxy = new OpenALClientHandler();
		DEBUG = false;
		doReverb = true;
		doLowPass = true;
		doOcclusion = true;
		profileSize = Info.ROOM_SCAN_SIZE;
		proxy.registerTickHandlers();
	}

	public void onPostInit()
	{
		ProxySoundFilter.reverbFilter.density = 0.15f;
		ProxySoundFilter.reverbFilter.diffusion = 0.54f;
		ProxySoundFilter.reverbFilter.gain = 0.15f;
		ProxySoundFilter.reverbFilter.gainHF = 0.2f;
		ProxySoundFilter.reverbFilter.decayTime = 1.3f;
		ProxySoundFilter.reverbFilter.decayHFRatio = 0.7f;
		ProxySoundFilter.reverbFilter.reflectionsGain = 0.2f;
		ProxySoundFilter.reverbFilter.reflectionsDelay = 0.0f;
		ProxySoundFilter.reverbFilter.lateReverbGain = 0.00f;
		ProxySoundFilter.reverbFilter.lateReverbDelay = 0.0f;
		ProxySoundFilter.reverbFilter.airAbsorptionGainHF = 0.99f;
		ProxySoundFilter.reverbFilter.roomRolloffFactor = 0.0f;
	}

	public static boolean isGamePaused()
	{
		return mc.isSingleplayer() && ProxySoundFilter.mc.currentScreen != null && ProxySoundFilter.mc.currentScreen.doesGuiPauseGame() && !mc.getIntegratedServer().getPublic();
	}

	static
	{
		mc = Minecraft.getMinecraft();
		rand = new Random();
		loaded = false;
		profileSize = 1024;
		doReverb = true;
		doLowPass = true;
		doOcclusion = true;
		lowPassFilter = new FilterLowPass();
		reverbFilter = new FilterReverb();
		highOcclusionSet = new TreeSet();
		highReverbSet = new TreeSet();
		midReverbSet = new TreeSet();
		lowReverbSet = new TreeSet();
		DEBUG = false;
				
		if(SoundSystemConfig.getLibraries() != null)
		{
			SoundSystemConfig.getLibraries().clear();
		}
				
		try
		{
			SoundSystemConfig.addLibrary(ModifiedLWJGLOpenALLibrary.class);
		}
		
		catch(SoundSystemException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			SoundSystemConfig.setNumberNormalChannels(Info.MAX_CHANNELS);
			System.out.println("Sound Channels set to " + SoundSystemConfig.getNumberNormalChannels());
		}
		
		catch(Exception e)
		{
			
		}
		
		System.out.println("[Sound Filters] Loaded modified library.");
	}

	public void onInit()
	{
		
	}
}
