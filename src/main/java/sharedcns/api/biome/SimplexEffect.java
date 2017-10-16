package sharedcns.api.biome;

import sharedcms.util.GEN;
import sharedcms.util.SimplexProperties;

public class SimplexEffect
{
	private String generator;
	private SimplexModifier modifier;
	private SimplexEffect parent;
	private double factor;
	
	public SimplexEffect(SimplexEffect parent, SimplexModifier modifier, String name, long seed, double scale, double factor)
	{
		this.parent = parent;
		this.modifier = modifier;
		this.generator = name;
		this.factor = factor;
		GEN.addGenerator(new SimplexProperties(name, seed, scale));
	}
	
	public double getFNoise(double x, double z)
	{
		return GEN.getNoise(generator, x, z, factor, 0);
	}
	
	public double getNoise(double x, double z)
	{
		if(modifier.equals(SimplexModifier.REBASE))
		{
			return getFNoise(x + parent.getNoise(x, z), z + parent.getNoise(z, x));
		}
		
		if(modifier.equals(SimplexModifier.COMPOUND))
		{
			return getFNoise(x, z);
		}
		
		return 0;
	}
}
