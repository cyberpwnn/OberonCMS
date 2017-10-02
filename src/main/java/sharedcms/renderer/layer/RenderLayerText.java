package sharedcms.renderer.layer;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.FontRenderer;

public class RenderLayerText extends RenderLayer
{
	public RenderLayerText(String text, Color color, SuperPosition position, float size, boolean centered)
	{
		if(centered)
		{
			glDrawCenterString(mc.fontRenderer, text, position, color, size);
		}
		
		else
		{
			glDrawString(mc.fontRenderer, text, position, color, size);
		}
	}

	public RenderLayerText(String text, Color color, SuperPosition position, float size)
	{
		this(text, color, position, size, false);
	}

	public void glDrawCenterString(FontRenderer r, String text, SuperPosition pos, Color c, float size)
	{
		GL11.glScalef(size, size, size);
		pos.x /= size;
		pos.y /= size;
		drawCenteredString(mc.fontRenderer, text, pos.x, pos.y, c.getRGB());
		GL11.glScalef(1f / size, 1f / size, 1f / size);
	}

	public void glDrawString(FontRenderer r, String text, SuperPosition pos, Color c, float size)
	{
		GL11.glScalef(size, size, size);
		pos.x /= size;
		pos.y /= size;
		drawString(mc.fontRenderer, text, pos.x, pos.y, c.getRGB());
		GL11.glScalef(1f / size, 1f / size, 1f / size);
	}
}
