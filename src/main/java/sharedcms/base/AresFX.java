package sharedcms.base;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_GREATER;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glAlphaFunc;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDepthMask;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import sharedcms.Status;
import sharedcms.util.ParticleUtils;

public class AresFX extends EntityFX
{
	public static double windX = ((Math.random() - 0.5)) / 20;
	public static double windZ = ((Math.random() - 0.5)) / 20;
	protected String texture;
	protected Color color;
	protected LightingMode lightingMode;
	public static Map<String, Integer> instanceCount = new HashMap<String, Integer>();

	public enum LightingMode
	{
		FOLLOW_RENDERER,
		BRIGHT_AS_FUCK,
		DARK_AS_FUCK;
	}

	public AresFX(String texture, World world, double x, double y, double z)
	{
		super(world, x, y, z, 0.0D, 0.0D, 0.0D);
		this.texture = "textures/particle/" + texture + ".png";
		this.motionX *= 0.10000000149011612D;
		this.motionY *= 0.10000000149011612D;
		this.motionZ *= 0.10000000149011612D;
		this.noClip = true;
		this.particleAlpha = 1;
		this.particleAge = 0;
		this.color = new Color(255, 255, 255, 255);
		this.lightingMode = LightingMode.FOLLOW_RENDERER;
		windX += ((Math.random() - 0.5)) / 20;
		windZ += ((Math.random() - 0.5)) / 20;
		windX = windX > 0.2 ? 0.2 : windX < -0.2 ? -0.2 : windX;
		windZ = windZ > 0.2 ? 0.2 : windZ < -0.2 ? -0.2 : windZ;
	}

	public static String rand(String... t)
	{
		return t[(int) (Math.random() * t.length)];
	}

	public void spawn()
	{
		if(z(this))
		{
			return;
		}

		a(this);
		Minecraft.getMinecraft().effectRenderer.addEffect(this);
	}

	public void renderParticle(Tessellator tess, float partialTicks, float par3, float par4, float par5, float par6, float par7)
	{
		ParticleUtils.bindTexture(texture);
		glDepthMask(false);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glAlphaFunc(GL_GREATER, 0.003921569f);
		float scale = 0.1f * particleScale;
		float x = (float) (prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
		float y = (float) (prevPosY + (posY - prevPosY) * partialTicks - interpPosY);
		float z = (float) (prevPosZ + (posZ - prevPosZ) * partialTicks - interpPosZ);
		tess.startDrawingQuads();
		tess.setNormal(0, 1, 0);
		tess.setBrightness(lightingMode.equals(LightingMode.FOLLOW_RENDERER) ? getBrightnessForRender(partialTicks) : (lightingMode.equals(LightingMode.BRIGHT_AS_FUCK) ? 15728640 : 0));
		tess.setColorRGBA(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		tess.addVertexWithUV(x - par3 * scale - par6 * scale, y - par4 * scale, z - par5 * scale - par7 * scale, 0, 0);
		tess.addVertexWithUV(x - par3 * scale + par6 * scale, y + par4 * scale, z - par5 * scale + par7 * scale, 1, 0);
		tess.addVertexWithUV(x + par3 * scale + par6 * scale, y + par4 * scale, z + par5 * scale + par7 * scale, 1, 1);
		tess.addVertexWithUV(x + par3 * scale - par6 * scale, y - par4 * scale, z + par5 * scale - par7 * scale, 0, 1);
		tess.draw();
		glDisable(GL_BLEND);
		glDepthMask(true);
		glAlphaFunc(GL_GREATER, 0.1f);
	}

	public int getFXLayer()
	{
		return 3;
	}

	public void onUpdate()
	{
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;

		if(this.particleAge++ >= this.particleMaxAge)
		{
			s(this);
			this.setDead();
		}
	}

	private boolean z(AresFX f)
	{
		k(f);
		return instanceCount.get(f.getClass().getSimpleName()) > 128;
	}

	private void p()
	{
		int k = 0;

		for(String i : instanceCount.keySet())
		{
			k += instanceCount.get(i);
		}

		Status.PARTICLE_USE = k;
	}

	private void k(AresFX f)
	{
		if(!instanceCount.containsKey(f.getClass().getSimpleName()))
		{
			instanceCount.put(f.getClass().getSimpleName(), 0);
		}
	}

	private void a(AresFX f)
	{
		k(f);
		instanceCount.put(f.getClass().getSimpleName(), instanceCount.get(f.getClass().getSimpleName()) + 1);
		p();
	}

	private void s(AresFX f)
	{
		k(f);
		instanceCount.put(f.getClass().getSimpleName(), instanceCount.get(f.getClass().getSimpleName()) - 1 < 0 ? 0 : (instanceCount.get(f.getClass().getSimpleName()) - 1));
		p();
	}
}
