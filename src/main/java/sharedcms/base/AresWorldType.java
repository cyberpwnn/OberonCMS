package sharedcms.base;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.layer.GenLayer;
import sharedcns.api.biome.IBiomeOperator;

public abstract class AresWorldType extends WorldType
{
	private int worldTypeId;
	private IBiomeOperator biomeOperator;

	public AresWorldType(String name, int id, IBiomeOperator operator)
	{
		super(name);

		this.biomeOperator = operator;
		worldTypeId = id;
	}

	public int getWorldTypeId()
	{
		return worldTypeId;
	}

	public IBiomeOperator getBiomeOperator()
	{
		return biomeOperator;
	}

	@Override
	public int getGeneratorVersion()
	{
		return 0;
	}

	@Override
	public WorldType getWorldTypeForGeneratorVersion(int v)
	{
		return this;
	}

	@Override
	public boolean getCanBeCreated()
	{
		return true;
	}

	@Override
	public boolean isVersioned()
	{
		return false;
	}

	@Override
	public int getWorldTypeID()
	{
		return worldTypeId;
	}

	@Override
	public boolean showWorldInfoNotice()
	{
		return true;
	}

	@Override
	public abstract WorldChunkManager getChunkManager(World world);

	@Override
	public abstract IChunkProvider getChunkGenerator(World world, String generatorOptions);

	@Override
	public int getMinimumSpawnHeight(World world)
	{
		return 64;
	}

	@Override
	public double getHorizon(World world)
	{
		return 63;
	}

	@Override
	public boolean hasVoidParticles(boolean flag)
	{
		return true;
	}

	@Override
	public double voidFadeMagnitude()
	{
		return 0.03125;
	}

	@Override
	public boolean handleSlimeSpawnReduction(Random random, World world)
	{
		return false;
	}

	@Override
	public void onGUICreateWorldPress()
	{

	}

	@Override
	public int getSpawnFuzz()
	{
		return 24;
	}

	@Override
	public void onCustomizeButton(Minecraft instance, GuiCreateWorld guiCreateWorld)
	{
		super.onCustomizeButton(instance, guiCreateWorld);
	}

	@Override
	public boolean isCustomizable()
	{
		return false;
	}

	@Override
	public float getCloudHeight()
	{
		return 256f;
	}

	@Override
	public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer)
	{
		return super.getBiomeLayer(worldSeed, parentLayer);
	}
}
