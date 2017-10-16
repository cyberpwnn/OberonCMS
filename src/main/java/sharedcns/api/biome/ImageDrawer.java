package sharedcns.api.biome;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class ImageDrawer extends JPanel
{
	public int w;
	public int h;
	
	public ImageDrawer(int w, int h)
	{
		this.w = w;
		this.h = h;
		setPreferredSize(new Dimension(w, h));
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.setColor(Color.white);
		g.drawRect(0, 0, 640, 480);
		draw(g);
	}
	
	public abstract void draw(Graphics g);
}
