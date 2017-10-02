package sharedcms.renderer.blur;

import java.awt.Color;
import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.base.Throwables;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderLinkHelper;
import net.minecraft.client.shader.ShaderUniform;
import net.minecraft.client.util.JsonException;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import sharedcms.Info;

public class Blur
{
	public static Blur instance;
	private String[] blurExclusions;
	private Field _listShaders;
	private long start;
	private int fadeTime;
	public int radius;
	private int colorFirst;
	private int colorSecond;
	@Nonnull
	private ShaderResourcePack dummyPack = new ShaderResourcePack();

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		instance = this;
		MinecraftForge.EVENT_BUS.register((Object) this);
		FMLCommonHandler.instance().bus().register((Object) this);
		((List) ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), (String[]) new String[] {"field_110449_ao", "defaultResourcePacks"})).add(this.dummyPack);
		((SimpleReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener((IResourceManagerReloadListener) this.dummyPack);
		this.saveConfig();
	}

	private void saveConfig()
	{
		this.blurExclusions = new String[] {GuiChat.class.getName()};
		this.fadeTime = Info.SHADER_BLUR_FADE;
		this.colorFirst = Info.SHADER_BLUR_START.getRGB();
		this.colorSecond = Info.SHADER_BLUR_END.getRGB();
		this.radius = Info.SHADER_BLUR_RADIUS;
	}

	@SubscribeEvent
	public void onGuiChange(GuiOpenEvent event) throws JsonException
	{
		if(this._listShaders == null)
		{
			this._listShaders = ReflectionHelper.findField(ShaderGroup.class, (String[]) new String[] {"field_148031_d", "listShaders"});
		}

		if(Minecraft.getMinecraft().theWorld != null && ShaderLinkHelper.getStaticShaderLinkHelper() != null)
		{
			EntityRenderer er = Minecraft.getMinecraft().entityRenderer;

			if(!er.isShaderActive() && event.gui != null && !ArrayUtils.contains((Object[]) this.blurExclusions, (Object) event.gui.getClass().getName()))
			{
				Minecraft mc = Minecraft.getMinecraft();
				er.theShaderGroup = new ShaderGroup(mc.getTextureManager(), mc.getResourceManager(), mc.getFramebuffer(), new ResourceLocation("shaders/post/fade_in_blur.json"));
				er.updateShaderGroupSize(mc.displayWidth, mc.displayHeight);
				this.start = System.currentTimeMillis();
			}

			else if(er.isShaderActive() && event.gui == null)
			{
				er.deactivateShader();
			}
		}
	}

	private float getProgress()
	{
		float p = (float) (System.currentTimeMillis() - this.start);
		float f = p / (float) this.fadeTime;
		return Math.min(f, 1.0f);
	}

	@SubscribeEvent
	public void onRenderTick(TickEvent.RenderTickEvent event)
	{
		if(event.phase == TickEvent.Phase.END && Minecraft.getMinecraft().currentScreen != null && Minecraft.getMinecraft().entityRenderer.isShaderActive())
		{
			ShaderGroup sg = Minecraft.getMinecraft().entityRenderer.getShaderGroup();
			try
			{
				List shaders = (List) this._listShaders.get((Object) sg);
				for(Object s : shaders)
				{
					ShaderUniform su = ((Shader) s).getShaderManager().func_147991_a("Progress");
					if(su == null)
					{
						continue;
					}
					su.func_148090_a(this.getProgress());
				}
			}
			catch(Exception e)
			{
				Throwables.propagate((Throwable) e);
			}
		}
	}

	public static int getBackgroundColor(boolean second)
	{
		int color = second ? Blur.instance.colorSecond : Blur.instance.colorFirst;
		int a = color >>> 24;
		int r = color >> 16 & 255;
		int b = color >> 8 & 255;
		int g = color & 255;
		float prog = instance.getProgress();
		a = (int) ((float) a * prog);
		r = (int) ((float) r * prog);
		g = (int) ((float) g * prog);
		b = (int) ((float) b * prog);
		return a << 24 | r << 16 | b << 8 | g;
	}
}
