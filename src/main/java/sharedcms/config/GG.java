package sharedcms.config;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import sharedcms.util.GList;
import sharedcms.util.M;

@SideOnly(Side.CLIENT)
public enum GG
{
	OF_FOG_TYPE("ofFogType", int.class),
	OF_FOG_START("ofFogStart", float.class),
	OF_MIPMAP_TYPE("ofMipmapType", int.class),
	OF_LOAD_FAR("ofLoadFar", boolean.class),
	OF_PRELOADED_CHUNKS("ofPreloadedChunks", int.class),
	OF_OCCLUSION_FANCY("ofOcclusionFancy", boolean.class),
	OF_SMOOTH_FPS("ofSmoothFps", boolean.class),
	OF_SMOOTH_WORLD("ofSmoothWorld", boolean.class),
	OF_LAZY_CHUNK_LOADING("ofLazyChunkLoading", boolean.class),
	OF_AO_LEVEL("ofAoLevel", float.class),
	OF_AA_LEVEL("ofAaLevel", int.class),
	OF_CLOUDS("ofClouds", int.class),
	OF_CLOUDS_HEIGHT("ofCloudsHeight", float.class),
	OF_TREES("ofTrees", int.class),
	OF_GRASS("ofGrass", int.class),
	OF_RAIN("ofRain", int.class),
	OF_WATER("ofWater", int.class),
	OF_DROPPED_ITEMS("ofDroppedItems", int.class),
	OF_BETTER_GRASS("ofBetterGrass", int.class),
	OF_AUTO_SAVE_TICKS("ofAutoSaveTicks", int.class),
	OF_LAGOMETER("ofLagometer", boolean.class),
	OF_PROFILER("ofProfiler", boolean.class),
	OF_SHOW_FPS("ofShowFps", boolean.class),
	OF_WEATHER("ofWeather", boolean.class),
	OF_SKY("ofSky", boolean.class),
	OF_STARS("ofStars", boolean.class),
	OF_SUN_MOON("ofSunMoon", boolean.class),
	OF_VIGNETTE("ofVignette", int.class),
	OF_CHUNK_UPDATES("ofChunkUpdates", int.class),
	OF_CHUNK_LOADING("ofChunkLoading", int.class),
	OF_CHUNK_UPDATES_DYNAMIC("ofChunkUpdatesDynamic", boolean.class),
	OF_TIME("ofTime", int.class),
	OF_CLEAR_WATER("ofClearWater", boolean.class),
	OF_DEPTH_FOG("ofDepthFog", boolean.class),
	OF_BETTER_SNOW("ofBetterSnow", boolean.class),
	OF_FULLSCREEN_MODE("ofFullscreenMode", java.lang.String.class),
	OF_SWAMP_COLORS("ofSwampColors", boolean.class),
	OF_RANDOM_MOBS("ofRandomMobs", boolean.class),
	OF_SMOOTH_BIOMES("ofSmoothBiomes", boolean.class),
	OF_CUSTOM_FONTS("ofCustomFonts", boolean.class),
	OF_CUSTOM_COLORS("ofCustomColors", boolean.class),
	OF_CUSTOM_SKY("ofCustomSky", boolean.class),
	OF_SHOW_CAPES("ofShowCapes", boolean.class),
	OF_CONNECTED_TEXTURES("ofConnectedTextures", int.class),
	OF_NATURAL_TEXTURES("ofNaturalTextures", boolean.class),
	OF_FAST_MATH("ofFastMath", boolean.class),
	OF_FAST_RENDER("ofFastRender", boolean.class),
	OF_TRANSLUCENT_BLOCKS("ofTranslucentBlocks", int.class),
	OF_DYNAMIC_FOV("ofDynamicFov", boolean.class),
	OF_DYNAMIC_LIGHTS("ofDynamicLights", int.class),
	OF_ANIMATED_WATER("ofAnimatedWater", int.class),
	OF_ANIMATED_LAVA("ofAnimatedLava", int.class),
	OF_ANIMATED_FIRE("ofAnimatedFire", boolean.class),
	OF_ANIMATED_PORTAL("ofAnimatedPortal", boolean.class),
	OF_ANIMATED_REDSTONE("ofAnimatedRedstone", boolean.class),
	OF_ANIMATED_EXPLOSION("ofAnimatedExplosion", boolean.class),
	OF_ANIMATED_FLAME("ofAnimatedFlame", boolean.class),
	OF_ANIMATED_SMOKE("ofAnimatedSmoke", boolean.class),
	OF_VOID_PARTICLES("ofVoidParticles", boolean.class),
	OF_WATER_PARTICLES("ofWaterParticles", boolean.class),
	OF_RAIN_SPLASH("ofRainSplash", boolean.class),
	OF_PORTAL_PARTICLES("ofPortalParticles", boolean.class),
	OF_POTION_PARTICLES("ofPotionParticles", boolean.class),
	OF_DRIPPING_WATER_LAVA("ofDrippingWaterLava", boolean.class),
	OF_ANIMATED_TERRAIN("ofAnimatedTerrain", boolean.class),
	OF_ANIMATED_ITEMS("ofAnimatedItems", boolean.class),
	OF_ANIMATED_TEXTURES("ofAnimatedTextures", boolean.class),
	OF_KEY_BIND_ZOOM("ofKeyBindZoom", net.minecraft.client.settings.KeyBinding.class),

	MOUSE_SENSITIVITY("mouseSensitivity", "field_74341_c", float.class),
	INVERT_MOUSE("invertMouse", "field_74338_d", boolean.class),
	RENDER_DISTANCE_CHUNKS("renderDistanceChunks", "field_151451_c", int.class),
	VIEW_BOBBING("viewBobbing", "field_74336_f", boolean.class),
	ANAGLYPH("anaglyph", "field_74337_g", boolean.class),
	ADVANCED_OPENGL("advancedOpengl", "field_74349_h", boolean.class),
	FBO_ENABLE("fboEnable", "field_151448_g", boolean.class),
	LIMIT_FRAMERATE("limitFramerate", "field_74350_i", int.class),
	FANCY_GRAPHICS("fancyGraphics", "field_74347_j", boolean.class),
	AMBIENT_OCCLUSION("ambientOcclusion", "field_74348_k", int.class),
	CLOUDS("clouds", "field_74345_l", boolean.class),
	RESOURCE_PACKS("resourcePacks", "field_151453_l", java.util.List.class),
	CHAT_VISIBILITY("chatVisibility", "field_74343_n", net.minecraft.entity.player.EntityPlayer.EnumChatVisibility.class),
	CHAT_COLOURS("chatColours", "field_74344_o", boolean.class),
	CHAT_LINKS("chatLinks", "field_74359_p", boolean.class),
	CHAT_LINKS_PROMPT("chatLinksPrompt", "field_74358_q", boolean.class),
	CHAT_OPACITY("chatOpacity", "field_74357_r", float.class),
	SNOOPER_ENABLED("snooperEnabled", "field_74355_t", boolean.class),
	FULL_SCREEN("fullScreen", "field_74353_u", boolean.class),
	ENABLE_VSYNC("enableVsync", "field_74352_v", boolean.class),
	HIDE_SERVER_ADDRESS("hideServerAddress", "field_80005_w", boolean.class),
	ADVANCED_ITEM_TOOLTIPS("advancedItemTooltips", "field_82882_x", boolean.class),
	PAUSE_ON_LOST_FOCUS("pauseOnLostFocus", "field_82881_y", boolean.class),
	SHOW_CAPE("showCape", "field_82880_z", boolean.class),
	TOUCHSCREEN("touchscreen", "field_85185_A", boolean.class),
	OVERRIDE_WIDTH("overrideWidth", "field_92118_B", int.class),
	OVERRIDE_HEIGHT("overrideHeight", "field_92119_C", int.class),
	HELD_ITEM_TOOLTIPS("heldItemTooltips", "field_92117_D", boolean.class),
	CHAT_SCALE("chatScale", "field_96691_E", float.class),
	CHAT_WIDTH("chatWidth", "field_96692_F", float.class),
	CHAT_HEIGHT_UNFOCUSED("chatHeightUnfocused", "field_96693_G", float.class),
	CHAT_HEIGHT_FOCUSED("chatHeightFocused", "field_96694_H", float.class),
	SHOW_INVENTORY_ACHIEVEMENT_HINT("showInventoryAchievementHint", "field_151441_H", boolean.class),
	MIPMAP_LEVELS("mipmapLevels", "field_151442_I", int.class),
	ANISOTROPIC_FILTERING("anisotropicFiltering", "field_151443_J", int.class),
	MAP_SOUND_LEVELS("mapSoundLevels", "field_151446_aD", java.util.Map.class),
	KEY_BIND_FORWARD("keyBindForward", "field_74351_w", net.minecraft.client.settings.KeyBinding.class),
	KEY_BIND_LEFT("keyBindLeft", "field_74370_x", net.minecraft.client.settings.KeyBinding.class),
	KEY_BIND_BACK("keyBindBack", "field_74368_y", net.minecraft.client.settings.KeyBinding.class),
	KEY_BIND_RIGHT("keyBindRight", "field_74366_z", net.minecraft.client.settings.KeyBinding.class),
	KEY_BIND_JUMP("keyBindJump", "field_74314_A", net.minecraft.client.settings.KeyBinding.class),
	KEY_BIND_SNEAK("keyBindSneak", "field_74311_E", net.minecraft.client.settings.KeyBinding.class),
	KEY_BIND_INVENTORY("keyBindInventory", "field_151445_Q", net.minecraft.client.settings.KeyBinding.class),
	KEY_BIND_USE_ITEM("keyBindUseItem", "field_74313_G", net.minecraft.client.settings.KeyBinding.class),
	KEY_BIND_DROP("keyBindDrop", "field_74316_C", net.minecraft.client.settings.KeyBinding.class),
	KEY_BIND_ATTACK("keyBindAttack", "field_74312_F", net.minecraft.client.settings.KeyBinding.class),
	KEY_BIND_PICK_BLOCK("keyBindPickBlock", "field_74322_I", net.minecraft.client.settings.KeyBinding.class),
	KEY_BIND_SPRINT("keyBindSprint", "field_151444_V", net.minecraft.client.settings.KeyBinding.class),
	KEY_BIND_CHAT("keyBindChat", "field_74310_D", net.minecraft.client.settings.KeyBinding.class),
	KEY_BIND_PLAYER_LIST("keyBindPlayerList", "field_74321_H", net.minecraft.client.settings.KeyBinding.class),
	KEY_BIND_COMMAND("keyBindCommand", "field_74323_J", net.minecraft.client.settings.KeyBinding.class),
	KEY_BIND_SCREENSHOT("keyBindScreenshot", "field_151447_Z", net.minecraft.client.settings.KeyBinding.class),
	KEY_BIND_TOGGLE_PERSPECTIVE("keyBindTogglePerspective", "field_151457_aa", net.minecraft.client.settings.KeyBinding.class),
	KEY_BIND_SMOOTH_CAMERA("keyBindSmoothCamera", "field_151458_ab", net.minecraft.client.settings.KeyBinding.class),
	KEY_BINDS_HOTBAR("keyBindsHotbar", "field_151456_ac", net.minecraft.client.settings.KeyBinding[].class),
	KEY_BINDINGS("keyBindings", "field_74324_K", net.minecraft.client.settings.KeyBinding[].class),
	OPTIONS_FILE("optionsFile", "field_74354_ai", java.io.File.class),
	DIFFICULTY("difficulty", "field_74318_M", net.minecraft.world.EnumDifficulty.class),
	HIDE_GUI("hideGUI", "field_74319_N", boolean.class),
	THIRD_PERSON_VIEW("thirdPersonView", "field_74320_O", int.class),
	SHOW_DEBUG_INFO("showDebugInfo", "field_74330_P", boolean.class),
	SHOW_DEBUG_PROFILER_CHART("showDebugProfilerChart", "field_74329_Q", boolean.class),
	LAST_SERVER("lastServer", "field_74332_R", java.lang.String.class),
	NOCLIP("noclip", "field_74331_S", boolean.class),
	SMOOTH_CAMERA("smoothCamera", "field_74326_T", boolean.class),
	DEBUG_CAM_ENABLE("debugCamEnable", "field_74325_U", boolean.class),
	NOCLIP_RATE("noclipRate", "field_74328_V", float.class),
	DEBUG_CAM_RATE("debugCamRate", "field_74327_W", float.class),
	FOV_SETTING("fovSetting", "field_74334_X", float.class),
	GAMMA_SETTING("gammaSetting", "field_74333_Y", float.class),
	SATURATION("saturation", "field_151452_as", float.class),
	GUI_SCALE("guiScale", "field_74335_Z", int.class),
	PARTICLE_SETTING("particleSetting", "field_74362_aa", int.class),
	LANGUAGE("language", "field_74363_ab", java.lang.String.class),
	FORCE_UNICODE_FONT("forceUnicodeFont", "field_151455_aw", boolean.class);

	private String fieldName;
	private String obfName;
	private Class<?> clazz;
	private boolean obfuscated;
	private boolean invalid;
	private static long lastRefresh = M.ms();

	private GG(String name, Class<?> clazz)
	{
		this(name, name, clazz);
	}

	private GG(String name, String obf, Class<?> clazz)
	{
		this.fieldName = name;
		this.obfName = obf;
		this.clazz = clazz;

		try
		{
			if(GConfig.g().getClass().getDeclaredField(name) != null)
			{
				System.out.println("found settings." + name + " > " + name);

				obfuscated = false;
				invalid = false;
				return;
			}
		}

		catch(Exception e)
		{
			try
			{
				if(GConfig.g().getClass().getDeclaredField(obf) != null)
				{
					System.out.println("found settings." + name + " > " + obf);

					obfuscated = true;
					invalid = false;
					return;
				}
			}

			catch(Exception ex)
			{
				System.out.println("ERROR INVALID settings." + name + " > INVALID!!!");
				obfuscated = false;
				invalid = true;
			}
		}
	}

	public String toString()
	{
		if(isInvalid())
		{
			System.out.println("WARNING, INVALID NODE WILL LEAD TO EXCEPTIONS");
		}

		if(isObfuscated())
		{
			return getObfName();
		}

		return getFieldName();
	}

	public String getFieldName()
	{
		return fieldName;
	}

	public Class<?> getClazz()
	{
		return clazz;
	}

	public String getObfName()
	{
		return obfName;
	}

	public boolean isObfuscated()
	{
		return obfuscated;
	}

	public boolean isInvalid()
	{
		return invalid;
	}

	public boolean isOptifined()
	{
		return name().startsWith("OF_");
	}

	public boolean isMinecraft()
	{
		return !isOptifined();
	}

	public void print()
	{
		System.out.println(printToString());
	}

	public String printToString()
	{
		if(isInvalid())
		{
			return name() + "[??]<UNKNOWN> = (null) FUCKING NULL";
		}

		return name() + "[" + (isMinecraft() ? "@MC" : "@OF") + "]<" + toString() + ">" + " = (" + get().getClass().getSimpleName() + ") " + get().toString();
	}

	public Object get()
	{
		if(isInvalid())
		{
			return null;
		}

		return GConfig.get(this);
	}

	public void set(Object o)
	{
		if(isInvalid())
		{
			return;
		}

		GConfig.set(this, o);
	}
	
	public static void refreshChunks()
	{
		if(M.ms() - lastRefresh > 1000)
		{
			forceRefreshChunks();
		}
	}
	
	public static void forceRefreshChunks()
	{
		lastRefresh = M.ms();
		Minecraft.getMinecraft().renderGlobal.loadRenderers();
	}

	public static GList<GG> gg()
	{
		GList<GG> gg = new GList<GG>();

		for(GG g : GG.values())
		{
			if(!g.isInvalid())
			{
				gg.add(g);
			}
		}

		return gg;
	}

	public static GList<GG> ii()
	{
		GList<GG> gg = new GList<GG>();

		for(GG g : GG.values())
		{
			if(g.isInvalid())
			{
				gg.add(g);
			}
		}

		return gg;
	}
}
