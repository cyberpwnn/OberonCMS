package sharedcms.renderer.layer;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class RenderLayer extends Gui
{
	protected Minecraft mc;
	protected ScaledResolution resolution;
	protected int width;
	protected int height;

	public RenderLayer()
	{
		mc = Minecraft.getMinecraft();
		resolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		width = resolution.getScaledWidth();
		height = resolution.getScaledHeight();
	}
	
	public void drawArc()
	{
		
	}

	public SuperPosition getCenter()
	{
		return new SuperPosition(width / 2, height / 2);
	}

	public int g(int v, float s)
	{
		return (int) (v / s);
	}

	public void glScale(float x, float y, float z)
	{
		GL11.glScalef(x, y, z);
	}

	public void glUnScale(float x, float y, float z)
	{
		GL11.glScalef(1f / x, 1f / y, 1f / z);
	}

	public void glScale(float size)
	{
		glScale(size, size, size);
	}

	public void glUnScale(float size)
	{
		glUnScale(size, size, size);
	}

	public Color fromTo(Color from, Color to, double percent)
	{
		double p = percent * 4;

		if(p > 1)
		{
			p = 1;
		}

		double fr = from.getRed();
		double fg = from.getGreen();
		double fb = from.getBlue();
		double tr = to.getRed();
		double tg = to.getGreen();
		double tb = to.getBlue();
		int ar = (int) fromTo(fr, tr, p);
		int ag = (int) fromTo(fg, tg, p);
		int ab = (int) fromTo(fb, tb, p);

		return new Color(ar, ag, ab);
	}

	public double fromTo(double from, double to, double percent)
	{
		return (percent * (to - from)) + from;
	}
}
