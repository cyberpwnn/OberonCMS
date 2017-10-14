package sharedcns.api.biome;

public class BiomeSimplexEffect extends BiomeEffect
{
	private double scale;
	private double swirl;
	private BiomeEffectType effect;
	private long uid;
	private double mass;

	public BiomeSimplexEffect(IBiome biome, long uid, int levelMin, int levelMax, BiomeEffectType effect, double scale, double swirl, double mass)
	{
		super(biome, levelMin, levelMax);

		this.uid = uid;
		this.scale = scale;
		this.swirl = swirl;
		this.effect = effect;
		this.mass = mass;
	}

	public BiomeEffectType getEffect()
	{
		return effect;
	}

	public double getScale()
	{
		return scale;
	}

	public double getSwirl()
	{
		return swirl;
	}

	public long getUid()
	{
		return uid;
	}

	public double getMass()
	{
		return mass;
	}
}
