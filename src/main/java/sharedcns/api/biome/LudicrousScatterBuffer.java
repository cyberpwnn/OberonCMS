package sharedcns.api.biome;

import java.util.HashSet;
import java.util.Set;

public abstract class LudicrousScatterBuffer implements IScatterBuffer
{
	private int strength;
	private double chance;
	private DecorationPass pass;
	private Set<BiomeTemperature> temperatures;
	private Set<BiomeHumidity> humidities;

	public LudicrousScatterBuffer(int strength, double chance)
	{
		this.strength = strength;
		this.chance = chance;
		this.pass = DecorationPass.PASS_1;
		temperatures = new HashSet<BiomeTemperature>();
		humidities = new HashSet<BiomeHumidity>();
	}
	
	public boolean isTemperatureBound(BiomeTemperature temp)
	{
		return temperatures.contains(temp);
	}
	
	public boolean isHumidityBound(BiomeHumidity humidity)
	{
		return humidities.contains(humidity);
	}

	public void bindTemperature(BiomeTemperature temp)
	{
		temperatures.add(temp);
	}

	public void bindHumidity(BiomeHumidity hum)
	{
		humidities.add(hum);
	}
	
	public void unbindTemperature(BiomeTemperature temp)
	{
		temperatures.remove(temp);
	}

	public void unbindHumidity(BiomeHumidity hum)
	{
		humidities.remove(hum);
	}

	public void bindAllTemperatures()
	{
		for(BiomeTemperature i : BiomeTemperature.values())
		{
			bindTemperature(i);
		}
	}

	public void bindAllHumidities()
	{
		for(BiomeHumidity i : BiomeHumidity.values())
		{
			bindHumidity(i);
		}
	}
	
	public void unbindAllTemperatures()
	{
		for(BiomeTemperature i : BiomeTemperature.values())
		{
			unbindTemperature(i);
		}
	}

	public void unbindAllHumidities()
	{
		for(BiomeHumidity i : BiomeHumidity.values())
		{
			unbindHumidity(i);
		}
	}

	@Override
	public int getStrength()
	{
		return strength;
	}

	@Override
	public double getChance()
	{
		return chance;
	}

	@Override
	public DecorationPass getDecorationPass()
	{
		return pass;
	}

	@Override
	public void setDecorationPass(DecorationPass pass)
	{
		this.pass = pass;
	}
}
