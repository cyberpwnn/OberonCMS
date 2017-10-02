package sharedcms.config;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Properties;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;

import cpw.mods.fml.common.ClassNameUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import sharedcms.Info;
import sharedcms.util.GList;
import sharedcms.util.GMap;
import sharedcms.util.M;

@SideOnly(Side.CLIENT)
public class GConfig
{
	private static long lastSave = M.ms();
	private static GMap<String, Object> settings = new GMap<String, Object>();

	public static void set(GG o, Object b)
	{
		if(settings.containsKey(o.toString()))
		{
			settings.put(o.toString(), b);
			push();
		}

		else
		{
			System.out.println("WARNING: NULL ON SET OPERATION FOR " + o.toString());
		}
	}

	public static void forceDefaults()
	{
		GG.FBO_ENABLE.set(true);
		GG.FORCE_UNICODE_FONT.set(false);
		GG.NOCLIP.set(false);
		GG.GUI_SCALE.set(3);
		GG.OF_AA_LEVEL.set(0);
		GG.OF_ANIMATED_EXPLOSION.set(true);
		GG.OF_ANIMATED_EXPLOSION.set(true);
		GG.OF_ANIMATED_FIRE.set(true);
		GG.OF_ANIMATED_FLAME.set(true);
		GG.OF_ANIMATED_ITEMS.set(true);
		GG.OF_ANIMATED_LAVA.set(true);
		GG.OF_ANIMATED_PORTAL.set(true);
		GG.OF_ANIMATED_REDSTONE.set(true);
		GG.OF_ANIMATED_SMOKE.set(true);
		GG.OF_ANIMATED_TERRAIN.set(true);
		GG.OF_ANIMATED_TEXTURES.set(true);
		GG.OF_ANIMATED_WATER.set(true);
		GG.OF_AO_LEVEL.set(0);
		GG.OF_AUTO_SAVE_TICKS.set(2400);
		GG.OF_BETTER_SNOW.set(false);
		GG.OF_BETTER_GRASS.set(false);
		GG.OF_CHUNK_LOADING.set(1);
		GG.OF_CHUNK_UPDATES.set(1);
		GG.OF_CHUNK_UPDATES_DYNAMIC.set(true);
		GG.OF_CLEAR_WATER.set(true);
		GG.OF_CLOUDS.set(2);
		GG.OF_CLOUDS_HEIGHT.set(255);
		GG.OF_CONNECTED_TEXTURES.set(2);
		GG.OF_CUSTOM_COLORS.set(true);
		GG.OF_CUSTOM_FONTS.set(true);
		GG.OF_CUSTOM_SKY.set(true);
		GG.OF_DEPTH_FOG.set(true);
		GG.OF_DRIPPING_WATER_LAVA.set(true);
		GG.OF_DROPPED_ITEMS.set(0);
		GG.OF_DYNAMIC_FOV.set(true);
		GG.OF_DYNAMIC_LIGHTS.set(1);
		GG.OF_FAST_MATH.set(true);
		GG.OF_FAST_RENDER.set(false);
		GG.OF_FULLSCREEN_MODE.set("Default");
		GG.OF_GRASS.set(0);
		GG.OF_KEY_BIND_ZOOM.set(46);
		GG.OF_LAGOMETER.set(true);
		GG.OF_LAZY_CHUNK_LOADING.set(true);
		GG.OF_LOAD_FAR.set(false);
		GG.OF_MIPMAP_TYPE.set(0);
		GG.OF_NATURAL_TEXTURES.set(true);
		GG.OF_OCCLUSION_FANCY.set(false);
		GG.OF_PORTAL_PARTICLES.set(true);
		GG.OF_POTION_PARTICLES.set(true);
		GG.OF_PRELOADED_CHUNKS.set(0);
		GG.OF_PROFILER.set(true);
		GG.OF_RAIN.set(0);
		GG.OF_RAIN_SPLASH.set(true);
		GG.OF_RANDOM_MOBS.set(true);
		GG.OF_SHOW_CAPES.set(false);
		GG.OF_SHOW_FPS.set(false);
		GG.OF_SMOOTH_WORLD.set(true);
		GG.OF_SMOOTH_FPS.set(true);
		GG.OF_STARS.set(true);
		GG.OF_SUN_MOON.set(true);
		GG.OF_SWAMP_COLORS.set(true);
		GG.OF_TIME.set(0);
		GG.OF_TRANSLUCENT_BLOCKS.set(2);
		GG.OF_TREES.set(0);
		GG.OF_VIGNETTE.set(0);
		GG.OF_VOID_PARTICLES.set(true);
		GG.OF_WATER.set(0);
		GG.OF_WATER_PARTICLES.set(true);
		GG.OF_WEATHER.set(true);
	}

	public static Object get(GG o)
	{
		pull();

		if(settings.containsKey(o.toString()))
		{
			return settings.get(o.toString());
		}

		System.out.println("WARNING: NULL ON GET OPERATION FOR " + o.toString());
		return null;
	}

	public static void pull()
	{
		for(GG i : GG.values())
		{
			if(i.isInvalid())
			{
				continue;
			}

			settings.put(i.toString(), g(i.toString()));
		}
		
		Info.load();
	}

	public static void push()
	{
		for(GG i : GG.values())
		{
			if(i.isInvalid())
			{
				continue;
			}

			s(i, i.toString(), settings.get(i.toString()));
		}

		saveALL();
	}

	public static void saveMC()
	{
		if(shouldSave())
		{
			forceSaveMC();
		}
	}

	public static void saveALL()
	{
		if(shouldSave())
		{
			forceSaveALL();
		}
	}

	public static void saveOF()
	{
		if(shouldSave())
		{
			forceSaveOF();
		}
	}

	public static boolean shouldSave()
	{
		return M.ms() - lastSave > 5000;
	}

	public static void forceSaveALL()
	{
		lastSave = M.ms();
		forceDefaults();
		saveMC();
		saveOF();
		saveShaderSettings();
		Info.save();
	}

	public static void forceSaveMC()
	{
		lastSave = M.ms();
		g().saveOptions();
		System.out.println("Saved MC Settings");
	}

	public static void forceSaveOF()
	{
		lastSave = M.ms();
		e("saveOfOptions");
		System.out.println("Saved OF Settings");
	}

	public static void saveShaderSettings()
	{
		try
		{
			gprop().setProperty("renderResMul", "0.787");
			gprop().setProperty("shadowResMul", "0.662");
			Class<?> f = Class.forName("shadersmod.client.Shaders");
			f.getMethod("storeConfig").invoke(null);
		}

		catch(Exception e)
		{

		}
	}

	public static void setShaderPack(String shaderpack)
	{
		try
		{
			Class<?> f = Class.forName("shadersmod.client.Shaders");
			f.getMethod("setShaderPack", String.class).invoke(null, shaderpack);
			f.getMethod("uninit").invoke(null);
		}

		catch(Exception e)
		{
			System.out.println("????????????????????????????????");
			e.printStackTrace();
		}
	}

	public static Properties gprop()
	{
		try
		{
			Class<?> f = Class.forName("shadersmod.client.Shaders");
			Field sh = f.getField("shadersConfig");
			sh.setAccessible(true);

			return (Properties) sh.get(null);
		}

		catch(Exception e)
		{

		}

		return null;
	}

	public static GameSettings g()
	{
		return Minecraft.getMinecraft().gameSettings;
	}

	public static Object g(String field)
	{
		try
		{
			Field f = g().getClass().getDeclaredField(field);

			if(!f.isAccessible())
			{
				f.setAccessible(true);
			}

			if(Modifier.isStatic(f.getModifiers()))
			{
				return f.get(null);
			}

			else
			{
				return f.get(g());
			}
		}

		catch(Exception e)
		{
			System.out.println("\n===========================\n--------------------\n");
			e.printStackTrace();
			System.out.println("\n===========================\n--------------------");
		}

		return null;
	}

	public static void s(GG i, String field, Object object)
	{
		try
		{
			Field f = g().getClass().getDeclaredField(field);

			if(!f.isAccessible())
			{
				f.setAccessible(true);
			}

			Object k = null;

			if(!Modifier.isStatic(f.getModifiers()))
			{
				k = g();
			}

			if(ClassUtils.isPrimitiveWrapper(object.getClass()))
			{
				if(object instanceof Integer)
				{
					f.set(k, ((Integer) object).intValue());
				}

				if(object instanceof Double)
				{
					f.set(k, ((Double) object).doubleValue());
				}

				if(object instanceof Float)
				{
					f.set(k, ((Float) object).floatValue());
				}

				if(object instanceof Boolean)
				{
					f.set(k, ((Boolean) object).booleanValue());
				}

				if(object instanceof Long)
				{
					f.set(k, ((Long) object).longValue());
				}
			}

			else
			{
				f.set(k, object);
			}
		}

		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public static void e(String method, Object... args)
	{
		GList<Class<?>> l = new GList<Class<?>>();

		for(Object i : args)
		{
			l.add(i.getClass());
		}

		try
		{
			e(g().getClass().getDeclaredMethod(method, l.toArray(new Class<?>[l.size()])), args);
		}

		catch(Exception e)
		{
			System.out.println("\n===========================\n--------------------\n");
			e.printStackTrace();
			System.out.println("\n===========================\n--------------------");
		}
	}

	public static void e(Method m, Object... arg)
	{
		try
		{
			if(!m.isAccessible())
			{
				m.setAccessible(true);
			}

			if(Modifier.isStatic(m.getModifiers()))
			{
				m.invoke(null, arg);
			}

			else
			{
				m.invoke(g(), arg);
			}
		}

		catch(Exception e)
		{
			System.out.println("\n===========================\n--------------------\n");
			e.printStackTrace();
			System.out.println("\n===========================\n--------------------");
		}
	}
}
