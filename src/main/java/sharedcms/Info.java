package sharedcms;

import java.awt.Color;
import java.io.File;

import sharedcms.config.cluster.DataCluster;
import sharedcms.gui.util.R;

public class Info
{
	public static final String NAME = "Shared CMS";
	public static final String ID = "sharedcms";
	public static final String VERSION = "1.0";
	public static final String PROXY_MOD = "sharedcms.SharedCMS";
	public static final String PROXY_COMMON = "sharedcms.proxy.ProxyCommon";
	public static final String PROXY_CLIENT = "sharedcms.proxy.ProxyClient";
	public static final String PROXY_SERVER = "sharedcms.proxy.ProxyServer";
	public static final String PROXY_CMS = "sharedcms.proxy.ProxyCMS";
	public static final float LEVEL_DISTANCE_MULTIPLIER = 0.03f;
	public static final float LEVEL_DISTANCE_RANDOM_MULTIPLIER = 0.01f;
	public static final float LEVEL_HP_MULTIPLIER = 2.5f;
	public static final float TESSELLATION_RATE_HEIGHT = 0.16f;
	public static final float TESSELLATION_RATE_WIDTH = 0.16f;
	public static final float TESSELLATION_SHIFT_X = 0.87f;
	public static final float TESSELLATION_SHIFT_Y = 0.25f;
	public static final float TESSELLATION_SHIFT_Z = 0.87f;
	public static final float TESSELLATION_RAD_X = 0.88f;
	public static final float TESSELLATION_RAD_Y = 0.65f;
	public static final float TESSELLATION_RAD_Z = 0.88f;
	public static final float TESSELLATION_SIMPLEX_X = 7f;
	public static final float TESSELLATION_SIMPLEX_Y = 22f;
	public static final float TESSELLATION_SIMPLEX_Z = 7f;
	public static final float TESSELLATION_PERLIN_X = 17f;
	public static final float TESSELLATION_PERLIN_Y = 40f;
	public static final float TESSELLATION_PERLIN_Z = 17f;
	public static final boolean TESSELLATION_STEP = true;
	public static final boolean WEAPON_TRAIL = false;
	public static final int MAX_CHANNELS = 48;
	public static final int ROOM_SCAN_SIZE = 1024;
	public static final float REVERB_DECAY = 2f;
	public static final float REVERB_GAIN = 0.12f;
	public static final float REVERB_DELAY = 0.01f;
	public static final float REVERB_REFLECTOR = 0.015f;
	public static final float REVERB_DIFFUSION = 0.14f;
	public static final float REVERB_ROLLOFF = 0.1f;
	public static int SHADER_BLUR_RADIUS = 28;
	public static final int SHADER_BLUR_FADE = 500;
	public static final Color SHADER_BLUR_START = new Color(0, 0, 0, 0);
	public static final Color SHADER_BLUR_END = new Color(0, 136, 178, 30);
	public static boolean TESSELLATION_VERTEX = true;
	public static float TESSELLATION_VERTEX_MODIFIER = 1f;
	public static boolean TESSELLATION_SIMPLEX = true;
	public static float TESSELLATION_SIMPLEX_MODIFIER = 1f;
	public static int GRAPHICS_LEVEL = 4;

	public static void save()
	{
		DataCluster cc = new DataCluster();
		cc.put("vertex", TESSELLATION_VERTEX);
		cc.put("blur-radius", SHADER_BLUR_RADIUS);
		cc.put("simplex", TESSELLATION_SIMPLEX);
		cc.put("vertex-multiplier", TESSELLATION_VERTEX_MODIFIER);
		cc.put("simplex-multiplier", TESSELLATION_SIMPLEX_MODIFIER);
		cc.put("graphics", GRAPHICS_LEVEL);
		cc.write(new File("optionscms.json"));
	}

	public static void load()
	{
		File f = new File("optionscms.json");
		
		try
		{
			if(!f.exists())
			{
				save();
			}
			
			DataCluster cc = new DataCluster();
			cc.read(f);
			SHADER_BLUR_RADIUS = cc.getInt("blur-radius");
			GRAPHICS_LEVEL = cc.getInt("graphics");
			TESSELLATION_VERTEX = cc.getBoolean("vertex");
			TESSELLATION_SIMPLEX = cc.getBoolean("simplex");
			TESSELLATION_VERTEX_MODIFIER = (float) cc.getDouble("vertex-multiplier");
			TESSELLATION_SIMPLEX_MODIFIER = (float) cc.getDouble("simplex-multiplier");
			TESSELLATION_VERTEX_MODIFIER = R.CLIP(TESSELLATION_VERTEX_MODIFIER, 0f, 2.5f);
			TESSELLATION_SIMPLEX_MODIFIER = R.CLIP(TESSELLATION_VERTEX_MODIFIER, 0f, 2f);
		}
		
		catch(Exception e)
		{
			f.delete();
			load();
		}
	}
}
