package sharedcms.content.structure.brush;

import java.util.List;

public interface IBrush
{
	public List<IBrushable> getPalette();
	
	public IBrushable pick();
}
