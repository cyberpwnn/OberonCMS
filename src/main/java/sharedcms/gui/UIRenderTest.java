package sharedcms.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import sharedcms.gui.base.RecycledUI;
import sharedcms.gui.util.Point;
import sharedcms.gui.util.R;
import sharedcms.gui.util.TextAlignment;
import sharedcms.gui.util.TextProperties;
import sharedcms.util.C;

public class UIRenderTest extends RecycledUI
{
	private int ix = 1;

	@Override
	public void redraw(int w, int h)
	{
		ix++;
		int kx = (int) ((double) ((1 + (Math.sin((double) ix / 40))) / 2) * 16.0);
		float a = (float) ((double) (((Math.sin((double) ix / 40))) / 2) * 360.0);
		int ks = 6 + (int) ((double) ((1 + (Math.sin((double) ix / 80))) / 2) * 12.0);
		int km = 80 + (int) ((double) ((1 + (Math.sin((double) ix / 100))) / 2) * 256.0);
		List<Point> points = new ArrayList<Point>();

		for(int i = (w / 2) - 22; i < (w / 2) + 22; i++)
		{
			for(int j = (h / 2) - 22; j < (h / 2) + 22; j++)
			{
				if((j * i) % (kx + 1) == 0)
				{
					points.add(new Point(i, j));
				}
			}
		}

		int fa = ks;
		int fb = ks;
		TextProperties prop1 = new TextProperties();
		prop1.setAlignment(TextAlignment.CENTER);
		prop1.setColor(C.getRGBShift(0.014));
		prop1.setFontSize(fa);
		prop1.setShadowColor(Color.BLACK);
		prop1.setShadowOffset(1);
		prop1.setShadow(true);

		TextProperties prop2 = new TextProperties();
		prop2.setAlignment(TextAlignment.CENTER);
		prop2.setColor(C.getRGBShift(0.006));
		prop2.setFontSize(fb);
		prop2.getWrapper().wrapText(true);
		prop2.getWrapper().setWrapLength(km);
		prop2.setShadowColor(C.getRGBShift(0.014));
		prop2.setShadowOffset(1);
		prop2.setShadow(true);

		TextProperties prop3 = new TextProperties();
		prop3.setAlignment(TextAlignment.LEFT);
		prop3.setColor(Color.BLACK);
		prop3.setFontSize(fb);
		prop3.getWrapper().wrapText(true);
		prop3.getWrapper().setWrapLength(km);
		prop3.setShadowColor(C.getRGBShift(0.14));
		prop3.setShadowOffset(1);
		prop3.setShadow(true);

		TextProperties prop4 = new TextProperties();
		prop4.setAlignment(TextAlignment.RIGHT);
		prop4.setColor(Color.BLACK);
		prop4.setFontSize(fb);
		prop4.getWrapper().wrapText(true);
		prop4.getWrapper().setWrapLength(km);

		R.RECT(new Point(20, 20), new Point(w - 20, h - 20), C.getRGBShift(0.004), 0.65);
		R.LINE(new Point(20, 20), new Point(w - 20, h - 20), C.getRGBShift(0.014), 0.51);
		R.LINE(new Point(w - 20, 20), new Point(20, h - 20), C.getRGBShift(0.017), 0.51);
		R.LINE(new Point(w - 20, (h / 2) - (fb * 4)), new Point(20, (fb * 4) + h / 2), C.getRGBShift(0.024), 0.51);
		R.LINE(new Point((w / 2) - (fb * 4), 20), new Point((fb * 4) + w / 2, h - 20), C.getRGBShift(0.027), 0.51);
		R.POINTS(C.getRGBShift(0.009), 0.51, points.toArray(new Point[points.size()]));
		R.GLROT(a, w / 2, h / 2);
		R.TEXT(new Point(w / 2, h / 2), "Centered To Center", prop1);
		R.GLUROT(a, w / 2, h / 2);
		R.TEXT(new Point(w / 2, (h / 2) + 40), "Centered HARD word wrapping to " + km + " pixels... Lorem Ipsum... I forgot the rest of this shit.", prop2);
		R.TEXT(new Point(w / 2, (h / 2) + 80), "Left aligned wrapped text, at " + km, prop3);
		R.TEXT(new Point(w / 2, (h / 2) - 80), "Right aligned wrapped text, wrapping at " + km + " pixels...", prop4);
	}

	@Override
	public void onGuiClosed()
	{

	}
}
