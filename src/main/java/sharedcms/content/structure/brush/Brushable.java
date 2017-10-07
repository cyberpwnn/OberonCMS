package sharedcms.content.structure.brush;

import net.minecraft.block.Block;

public class Brushable implements IBrushable
{
	private Block block;
	private int weight;

	public Brushable(Block block, int weight)
	{
		this.block = block;
		this.weight = weight;
	}

	@Override
	public Block getBlock()
	{
		return block;
	}

	@Override
	public int getWeight()
	{
		return weight;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((block == null) ? 0 : block.hashCode());
		result = prime * result + weight;
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
		Brushable other = (Brushable) obj;
		if(block == null)
		{
			if(other.block != null)
				return false;
		}
		else if(!block.equals(other.block))
			return false;
		if(weight != other.weight)
			return false;
		return true;
	}
}
