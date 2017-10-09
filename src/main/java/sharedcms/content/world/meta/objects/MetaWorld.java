package sharedcms.content.world.meta.objects;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.World;
import sharedcms.content.Content;
import sharedcms.renderer.layer.SuperPosition;
import sharedcns.api.biome.ConditionalSimplexNoiseGenerator;

public class MetaWorld
{
	private World world;
	private ConditionalSimplexNoiseGenerator csx;

	public MetaWorld(World world)
	{
		this.world = world;
		csx = new ConditionalSimplexNoiseGenerator(world);
		Content.constructGeneratorSimplexConditions(csx, world.provider.terrainType);
	}

	public World getWorld()
	{
		return world;
	}

	public ConditionalSimplexNoiseGenerator getCsx()
	{
		return csx;
	}
}
