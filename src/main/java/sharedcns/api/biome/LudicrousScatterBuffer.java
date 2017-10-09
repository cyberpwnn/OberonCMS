package sharedcns.api.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public abstract class LudicrousScatterBuffer implements IScatterBuffer
{
	private int strength;
	private double chance;
	
	public LudicrousScatterBuffer(int strength, double chance)
	{
		this.strength = strength;
		this.chance = chance;
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
}
