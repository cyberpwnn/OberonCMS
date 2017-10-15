package sharedcns.api.biome;

import net.minecraft.world.World;
import sharedcms.util.GEN;
import sharedcms.util.Location;
import sharedcms.util.SimplexProperties;

public class LudicrousBiome extends EnhancedBiome
{
	protected float height;
	protected float variation;

	public LudicrousBiome(int id)
	{
		super(id);

		height = 0.2f;
		variation = 0.1f;
		GEN.addGenerator(new SimplexProperties("biome-" + id, id * 16646 + id, 214 + (id * 2) + (Math.random() * 5)));
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
	
	@Override
	public float getHeightVariation()
	{
		return variation;
	}

	@Override
	public float getHeight()
	{
		return height;
	}
}
