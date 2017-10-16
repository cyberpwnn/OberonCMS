package sharedcns.api.biome;

import javax.swing.JFrame;

public class Drawer
{
	private ImageDrawer drawer;
	
	public Drawer(ImageDrawer drawer)
	{
		JFrame frame = new JFrame("Noise");
		frame.add(drawer);
		frame.setSize(drawer.w, drawer.h);
		frame.setVisible(true);
	}
}
