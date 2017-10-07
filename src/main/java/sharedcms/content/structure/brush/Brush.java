package sharedcms.content.structure.brush;

import java.util.List;

import net.minecraft.block.Block;
import sharedcms.util.GList;

public class Brush implements IBrush
{
	private List<IBrushable> palette;

	public Brush()
	{
		palette = new GList<IBrushable>();
	}
	
	public Brush(IBrushable... brushables)
	{
		palette = new GList<IBrushable>(brushables);
	}
	
	public Brush(Block... brushables)
	{
		palette = new GList<IBrushable>();
		
		for(Block i : brushables)
		{
			palette.add(new Brushable(i, 1));
		}
	}
	
	@Override
	public List<IBrushable> getPalette()
	{
		return palette;
	}

	@Override
	public IBrushable pick()
	{
		int totalWeight = 0;

		for(IBrushable i : getPalette())
		{
			totalWeight += i.getWeight();
		}

		int pick = (int) (Math.random() * totalWeight);

		int currentWeight = 0;

		for(IBrushable i : getPalette())
		{
			if(currentWeight > pick)
			{
				return i;
			}

			currentWeight += i.getWeight();
		}

		return getPalette().toArray(new IBrushable[getPalette().size()])[(int) (Math.random() * getPalette().size())];
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((palette == null) ? 0 : palette.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Brush other = (Brush) obj;
		if(palette == null)
		{
			if(other.palette != null)
				return false;
		}
		else if(!palette.equals(other.palette))
			return false;
		return true;
	}
	
	
}
