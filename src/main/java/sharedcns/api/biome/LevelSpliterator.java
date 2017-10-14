package sharedcns.api.biome;

public class LevelSpliterator
{
	private int[] levels;
	private int min;
	private int max;
	private int next;

	public LevelSpliterator(int size)
	{
		levels = new int[size];
		next = 0;
	}

	public void add(int level)
	{
		levels[next++] = level;

		if(next == size())
		{
			recalculateMaxs();
		}
	}

	public int size()
	{
		return levels.length;
	}

	public int getSplitLevel(int level)
	{
		if(level >= max)
		{
			return max;
		}

		if(level <= min)
		{
			return min;
		}

		int distance = max * 4;
		int target = max;

		for(int i = 0; i < size(); i++)
		{
			if(distance(levels[i], level) < distance)
			{
				distance = distance(levels[i], level);
				target = levels[i];
			}
		}

		return target;
	}

	private int distance(int a, int b)
	{
		return Math.abs(Math.min(a, b) - Math.max(a, b));
	}

	public int min()
	{
		return min;
	}

	public int max()
	{
		return max;
	}

	private void recalculateMaxs()
	{
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;

		for(int i = 0; i < size(); i++)
		{
			if(levels[i] < min)
			{
				min = levels[i];
			}

			if(levels[i] > max)
			{
				max = levels[i];
			}
		}
	}
}
