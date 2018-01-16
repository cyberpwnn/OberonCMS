package sharedcms.content.world.generator;

import sharedcms.util.GEN;
import sharedcms.util.GList;
import sharedcms.util.SimplexProperties;

public class SelectorGenerator
{
	private GList<Long> allowed;
	private long seed;
	private boolean has;
	
	public SelectorGenerator(int initialBuffer, long seed)
	{
		seed = seed * 100000;
		has = false;
	}
	
	public boolean has(int x, int z)
	{
		if(!has)
		{
			GEN.addGenerator(new SimplexProperties("selectorkf-" + seed, seed, 100));
		}
		
		has = true;
		return GEN.getNoise("selectorkf-" + seed, x, z) > 0.5 && GEN.getNoise("selectorkf-" + seed, x * 2, z * 2) > 0.56;
	}
}
