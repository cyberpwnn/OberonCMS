package sharedcns.api.biome;

import net.minecraft.world.World;

public class LudicrousBiome extends EnhancedBiome
{
	public LudicrousBiome(int id)
	{
		super(id);
	}

	public String toString()
	{
		return biomeName;
	}

	public int distanceToBorder(World w, int x, int z, int cap)
	{
		for(int i = 1;; i++)
		{
			if(!w.getBiomeGenForCoords(x + i, z + i).equals(this))
			{
				return i;
			}

			if(!w.getBiomeGenForCoords(x + i, z - i).equals(this))
			{
				return i;
			}

			if(!w.getBiomeGenForCoords(x - i, z + i).equals(this))
			{
				return i;
			}

			if(!w.getBiomeGenForCoords(x - i, z - i).equals(this))
			{
				return i;
			}

			if(!w.getBiomeGenForCoords(x + i, z).equals(this))
			{
				return i;
			}

			if(!w.getBiomeGenForCoords(x - i, z).equals(this))
			{
				return i;
			}

			if(!w.getBiomeGenForCoords(x, z - i).equals(this))
			{
				return i;
			}

			if(!w.getBiomeGenForCoords(x, z + i).equals(this))
			{
				return i;
			}
			
			if(i > cap)
			{
				return cap;
			}
		}
	}
}
