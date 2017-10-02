package sharedcms.renderer.layer;

import java.util.List;

public class RenderLayerMultiText extends RenderLayer
{
	public RenderLayerMultiText(List<TextElement> elements, SuperPosition p, float size)
	{
		int k = 0;
		
		for(TextElement i : elements)
		{
			new RenderLayerText(i.getText(), i.getColor(), new SuperPosition(p.x, p.y + (k * 10)), size);
			k++;
		}
	}
}
