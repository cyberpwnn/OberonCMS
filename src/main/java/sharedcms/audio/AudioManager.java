package sharedcms.audio;

import java.lang.reflect.Field;

import com.google.common.collect.HashBiMap;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import sharedcms.Info;
import sharedcms.Status;
import sharedcms.audio.openal.ProxySoundFilter;
import sharedcms.proxy.IProxy;
import sharedcms.util.GList;
import sharedcms.util.Location;

public class AudioManager implements IProxy
{
	private ProxySoundFilter filters;
	private GList<QSound> sounds;
	public static AudioManager instance;

	@Override
	public void onPreInit(FMLPreInitializationEvent e)
	{
		instance = this;
		filters = new ProxySoundFilter();
		filters.onPreInit(e);
		sounds = new GList<QSound>();
	}

	@Override
	public void onInit(FMLInitializationEvent e)
	{
		filters.onInit(e);
	}

	@Override
	public void onPostInit(FMLPostInitializationEvent e)
	{
		filters.onPostInit(e);
	}

	@SideOnly(Side.CLIENT)
	public static HashBiMap<String, ISound> getPlayingSounds()
	{
		SoundManager mgr = getSoundManager();

		for(Field i : mgr.getClass().getDeclaredFields())
		{
			if(i.getName().equals("playingSounds") || i.getName().equals("field_148629_h"))
			{
				i.setAccessible(true);

				try
				{
					return (HashBiMap<String, ISound>) i.get(mgr);
				}

				catch(IllegalArgumentException e)
				{
					e.printStackTrace();
				}

				catch(IllegalAccessException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}

	@SideOnly(Side.CLIENT)
	public static SoundManager getSoundManager()
	{
		Minecraft mc = Minecraft.getMinecraft();
		SoundHandler h = mc.getSoundHandler();

		for(Field i : h.getClass().getDeclaredFields())
		{
			if(i.getType().equals(SoundManager.class))
			{
				i.setAccessible(true);

				try
				{
					return (SoundManager) i.get(h);
				}

				catch(IllegalArgumentException e)
				{
					e.printStackTrace();
				}

				catch(IllegalAccessException e)
				{
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	public static void flush()
	{
		Status.CHANNEL_MAX = Info.MAX_CHANNELS;
		Status.CHANNEL_USE = getPlayingSounds().size();
		
		for(QSound i : instance.sounds.copy())
		{
			i.play();
		}

		instance.sounds.clear();
	}

	public static void playSound(DSound sound, Location location)
	{
		instance.sounds.add(new QSound(sound, location));
	}

	public static void playSound(DSound sound)
	{
		playSound(sound, null);
	}
}
