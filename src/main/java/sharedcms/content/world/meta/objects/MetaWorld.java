package sharedcms.content.world.meta.objects;

import net.minecraft.world.World;
import sharedcms.content.Content;
import sharedcms.content.world.type.WorldTypeAres;
import sharedcms.util.GEN;
import sharedcms.util.SimplexProperties;
import sharedcns.api.biome.BiomeHumidity;
import sharedcns.api.biome.BiomeTemperature;

public class MetaWorld
{
	private World world;
	private String genTemperatureKey;
	private String genHumidityKey;

	public MetaWorld(World world)
	{
		this.world = world;

		if(world.provider.terrainType.equals(Content.WorldType.ARES))
		{
			genTemperatureKey = "ares-" + world.getSeed() + "-temperature";
			genHumidityKey = "ares-" + world.getSeed() + "-humidity";

			GEN.addGenerator(new SimplexProperties(genTemperatureKey, world.getSeed(), 170));
			GEN.addGenerator(new SimplexProperties(genHumidityKey, world.getSeed(), 150));
			GEN.addGenerator(new SimplexProperties(genTemperatureKey + "-2", world.getSeed(), 31));
			GEN.addGenerator(new SimplexProperties(genHumidityKey + "-2", world.getSeed(), 23));
		}
	}

	public double getTemperature(BiomeTemperature base, double x, double z)
	{
		double noiseBase = GEN.getNoise(genTemperatureKey, x, z, 1, 1) * 3;
		noiseBase += GEN.getNoise(genTemperatureKey + "-2", x, z, 1, 1);
		double temp = base.getTemperature();

		return (temp + noiseBase) / 5;
	}

	public double getHumidity(BiomeHumidity base, double x, double z)
	{
		double noiseBase = GEN.getNoise(genHumidityKey, x, z, 0.5, 1) * 3;
		noiseBase += GEN.getNoise(genHumidityKey + "-2", x, z, 0.5, 1);

		double humidity = base.getHumidity();

		return (humidity + noiseBase) / 5;
	}

	public BiomeTemperature getTemperatureEnum(BiomeTemperature base, double x, double z)
	{
		return (BiomeTemperature) BiomeTemperature.of((int) ((getTemperature(base, x, z) / 2) * (double) BiomeTemperature.length()));
	}

	public BiomeHumidity getHumidityEnum(BiomeHumidity base, double x, double z)
	{
		return (BiomeHumidity) BiomeHumidity.of((int) (getHumidity(base, x, z) * (double) BiomeHumidity.length()));
	}

	public World getWorld()
	{
		return world;
	}
}
