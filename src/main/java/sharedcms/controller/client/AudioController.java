package sharedcms.controller.client;

import java.lang.reflect.Field;

import com.google.common.collect.HashBiMap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import sharedcms.Info;
import sharedcms.Status;
import sharedcms.audio.DSound;
import sharedcms.audio.QSound;
import sharedcms.audio.openal.ProxySoundFilter;
import sharedcms.controllable.Controller;
import sharedcms.util.GList;
import sharedcms.util.Location;

public class AudioController extends Controller
{
	private ProxySoundFilter filters;
	private GList<QSound> sounds;
	public static AudioController instance;

	public AudioController()
	{
		instance = this;
		filters = new ProxySoundFilter();
		sounds = new GList<QSound>();
	}

	@Override
	public void onPreInitialization()
	{
		filters.onPreInit();
	}

	@Override
	public void onInitialization()
	{
		filters.onInit();
	}

	@Override
	public void onPostInitialization()
	{
		filters.onPostInit();
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
