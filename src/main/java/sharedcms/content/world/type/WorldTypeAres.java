package sharedcms.content.world.type;

import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerZoom;
import sharedcms.base.AresWorldType;
import sharedcms.content.world.chunk.manager.BasicChunkManager;
import sharedcms.content.world.chunk.provider.ChunkProviderSimple;
import sharedcms.content.world.genlayer.BasicGenLayerBiome;
import sharedcms.content.world.genlayer.BasicGenLayerZoom;

public class WorldTypeAres extends AresWorldType
{
	public WorldTypeAres(String name, int id)
	{
		super(name, id);
	}

	@Override
	public WorldChunkManager getChunkManager(World world)
	{
		return new BasicChunkManager(world);
	}

	@Override
	public IChunkProvider getChunkGenerator(World world, String generatorOptions)
	{
		return new ChunkProviderSimple(world, world.getSeed(), true);
	}
}
