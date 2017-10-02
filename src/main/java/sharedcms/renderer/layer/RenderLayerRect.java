package sharedcms.renderer.layer;

import java.awt.Color;

public class RenderLayerRect extends RenderLayer
{
	public RenderLayerRect(SuperPosition a, SuperPosition b, Color c)
	{
		drawRect(a.x, a.y, b.x, b.y, c.getRGB());
	}
}
