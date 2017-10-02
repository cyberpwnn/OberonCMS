package sharedcms.gui.util;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import sharedcms.gui.base.BaseUI;

public class R
{
	public static void LINE(Point a, Point b, Color color, double opacity)
	{
		LINE(a, b, OPACIFY(color, opacity));
	}

	public static void LINE(Point a, Point b, Color color)
	{
		LINE(a.getX(), a.getY(), b.getX(), b.getY(), color.getRGB());
	}

	public static void RECT(Point a, Point b, Color color, double opacity)
	{
		RECT(a, b, OPACIFY(color, opacity));
	}

	public static void RECT(Point a, Point b, Color color)
	{
		RECT(a.getX(), a.getY(), b.getX(), b.getY(), color.getRGB());
	}

	public static void POINTS(Color color, double opacity, Point... points)
	{
		POINTS(OPACIFY(color, opacity), points);
	}

	public static void POINT(Point p, Color color, double opacity)
	{
		POINTS(OPACIFY(color, opacity), p);
	}

	public static void POINT(Point p, Color color)
	{
		POINTS(color, p);
	}

	public static void POINTS(Color color, Point... points)
	{
		for(Point i : points)
		{
			RECT(i, i.clone().add(new Point(1, 1)), color);
		}
	}

	public static void RECT(int x1, int y1, int x2, int y2, int color)
	{
		GLBEGIN(GL11.GL_QUADS, color);
		TESS().addVertex(MIN(x1, x2), MAX(y1, y2), 0);
		TESS().addVertex(MAX(x1, x2), MAX(y1, y2), 0);
		TESS().addVertex(MAX(x1, x2), MIN(y1, y2), 0);
		TESS().addVertex(MIN(x1, x2), MIN(y1, y2), 0);
		GLEND();
	}

	public static void LINE(int x1, int y1, int x2, int y2, int color)
	{
		GLBEGIN(GL11.GL_LINES, color);
		TESS().addVertex(x1, y1, 0);
		TESS().addVertex(x2, y2, 0);
		GLEND();
	}

	public static void TEXT(Point point, String text, TextProperties properties)
	{
		TEXT(point.getX(), point.getY(), text, properties, null, 0, null, 0, null, null);
	}

	public static void TEXT(Point point, String text, TextProperties properties, Color bg, int br, Color bsc, int bsd, Point a, Point b)
	{
		TEXT(point.getX(), point.getY(), text, properties, bg, br, bsc, bsd, a, b);
	}

	public static void TEXT(int x, int y, String text, TextProperties properties, Color bg, int br, Color bsc, int bsd, Point a, Point b)
	{
		x = GLSCALEFONTOF(x, properties.getFontSize());
		y = GLSCALEFONTOF(y, properties.getFontSize());
		int height = properties.getWrapper().shouldWrapText() ? (R.FONTRENDERER().splitStringWidth(text, properties.getWrapper().getWrapLength()) / 9) * properties.getFontSize() : properties.getFontSize();
		int width = properties.getWrapper().shouldWrapText() ? FONTRENDERER().getStringWidth((String) FONTRENDERER().listFormattedStringToWidth(text, properties.getWrapper().getWrapLength()).get(0)) : FONTRENDERER().getStringWidth(text);
		int dx = x;
		int dy = y;
		int dh = 0;
		int dw = 0;

		if(properties.getAlignment().equals(TextAlignment.CENTER))
		{
			dx = x - (width / 2);
			dy = y - (height / 2);
			dh = height / 2;
			dw = width / 2;
		}

		if(properties.getAlignment().equals(TextAlignment.RIGHT))
		{
			dx = x - width;
			dy = y - height;
			dh = height;
			dw = width;
		}

		GLSCALE(FONTSIZEOF(properties.getFontSize()));

		if(bg != null)
		{
			int error = GLSCALEOF(height, FONTSIZEOF(properties.getFontSize())) - 1;
			a.add(new Point(dx - br, dy - br));
			b.add(new Point(dx + width + br, dy + error + br));

			RECT(a, b, bg);

			if(bsc != null)
			{
				RECT(new Point(dx - br + bsd, dy - br + bsd), new Point(dx + bsd + width + br, dy + bsd + error + br), bsc);
			}
		}

		if(properties.getWrapper().shouldWrapText())
		{
			if(properties.hasShadow())
			{
				int off = properties.getShadowOffset();
				FONTRENDERER().drawSplitString(text, dx + off, dy + off, properties.getWrapper().getWrapLength(), properties.getShadowColor().getRGB());
			}

			FONTRENDERER().drawSplitString(text, dx, dy, properties.getWrapper().getWrapLength(), properties.getColor().getRGB());
		}

		else
		{
			if(properties.hasShadow())
			{
				int off = properties.getShadowOffset();
				FONTRENDERER().drawString(text, dx + off, dy + off, properties.getShadowColor().getRGB(), false);
			}

			FONTRENDERER().drawString(text, dx, dy, properties.getColor().getRGB(), false);
		}

		GLUNSCALE(FONTSIZEOF(properties.getFontSize()));
	}

	public static float FONTSIZEOF(int size)
	{
		return (float) ((double) size / (double) FONTRENDERER().FONT_HEIGHT);
	}

	public static FontRenderer FONTRENDERER()
	{
		return MC().fontRenderer;
	}

	public static Minecraft MC()
	{
		return Minecraft.getMinecraft();
	}

	public static Tessellator TESS()
	{
		return Tessellator.instance;
	}

	public static void GLBEGIN(int mode, int color)
	{
		float f3 = (float) (color >> 24 & 255) / 255.0F;
		float f = (float) (color >> 16 & 255) / 255.0F;
		float f1 = (float) (color >> 8 & 255) / 255.0F;
		float f2 = (float) (color & 255) / 255.0F;
		Tessellator t = TESS();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glColor4f(f, f1, f2, f3);
		t.startDrawing(mode);
	}

	public static void GLEND()
	{
		TESS().draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
	}

	public static int CLIP(int value, int min, int max)
	{
		return MAX(min, MIN(max, value));
	}

	public static float CLIP(float value, float min, float max)
	{
		return MAX(min, MIN(max, value));
	}

	public static int MIN(int... ints)
	{
		int m = Integer.MAX_VALUE;

		for(int i : ints)
		{
			if(i < m)
			{
				m = i;
			}
		}

		return m;
	}

	public static int MAX(int... ints)
	{
		int m = Integer.MIN_VALUE;

		for(int i : ints)
		{
			if(i > m)
			{
				m = i;
			}
		}

		return m;
	}

	public static float MIN(float... ints)
	{
		float m = Float.MAX_VALUE;

		for(float i : ints)
		{
			if(i < m)
			{
				m = i;
			}
		}

		return m;
	}

	public static float MAX(float... ints)
	{
		float m = Float.MIN_VALUE;

		for(float i : ints)
		{
			if(i > m)
			{
				m = i;
			}
		}

		return m;
	}

	public static void SWAP(int a, int b)
	{
		int c = a;
		a = b;
		b = c;
	}

	public static double OPACITY(Color color)
	{
		return (double) color.getAlpha() / 255.0;
	}

	public static Color OPACIFY(Color color, double opacity)
	{
		return new Color(color.getRed(), color.getGreen(), color.getBlue(), CLIP((int) ((double) opacity * 255.0), 0, 255));
	}

	public static int GLSCALEOF(int v, float s)
	{
		return (int) ((float) v / s);
	}

	public static int GLSCALEMUL(int v, float s)
	{
		return (int) ((float) v * s);
	}

	public static Point GLSCALEOF(Point p, float s)
	{
		return new Point(GLSCALEOF(p.getX(), s), GLSCALEOF(p.getY(), s));
	}

	public static Point GLSCALEMUL(Point p, float s)
	{
		return new Point(GLSCALEMUL(p.getX(), s), GLSCALEMUL(p.getY(), s));
	}

	public static int GLSCALEFONTOF(int v, int s)
	{
		return GLSCALEOF(v, (float) FONTSIZEOF(s));
	}

	public static int GLSCALEFONTMUL(int v, int s)
	{
		return GLSCALEMUL(v, (float) FONTSIZEOF(s));
	}

	public static Point GLSCALEFONTOF(Point v, int s)
	{
		return GLSCALEOF(v, (float) FONTSIZEOF(s));
	}

	public static Point GLSCALEFONTMUL(Point v, int s)
	{
		return GLSCALEMUL(v, (float) FONTSIZEOF(s));
	}

	public static void GLSCALE(float x, float y, float z)
	{
		GL11.glScalef(x, y, z);
	}

	public static void GLUNSCALE(float x, float y, float z)
	{
		GL11.glScalef(1f / x, 1f / y, 1f / z);
	}

	public static void GLSCALE(float size)
	{
		GLSCALE(size, size, size);
	}

	public static void GLUNSCALE(float size)
	{
		GLUNSCALE(size, size, size);
	}

	public static void GLTRANSLATE(int x, int y)
	{
		GL11.glTranslatef(x, y, 0);
	}

	public static void GLUTRANSLATE(int x, int y)
	{
		GL11.glTranslatef(-x, -y, 0);
	}

	public static void GLROT(float angle, int x, int y)
	{
		GLTRANSLATE(x, y);
		GL11.glRotatef(angle, 0, 0, 1);
		GLUTRANSLATE(x, y);
	}

	public static void GLUROT(float angle, int x, int y)
	{
		GLROT(-angle, x, y);
	}

	public static double FROMTO(double from, double to, double percent)
	{
		return (percent * (to - from)) + from;
	}

	public static double FROMFOR(double value, double from, double to)
	{
		return ((value - from) * 100) / (to - from) / 100.0;
	}

	public static double FROMFOR(int value, int from, int to)
	{
		value = CLIP(value, from, to);
		to = to - from == 0 ? 1 : to;

		return ((double) ((value - from) * 100.0) / (double) (to - from)) / 100;
	}
}
