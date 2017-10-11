package sharedcms.content.world.type;

import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import sharedcms.base.AresWorldType;
import sharedcms.content.Content;
import sharedcms.content.world.chunk.manager.BasicChunkManager;
import sharedcms.content.world.chunk.provider.ChunkProviderSimple;
import sharedcns.api.biome.BiomeBuffer;
import sharedcns.api.biome.BiomeOperator;

public class WorldTypeAres extends AresWorldType
{
	public WorldTypeAres(String name, int id)
	{
		super(name, id, new BiomeOperator(new BiomeBuffer(Content.biomes())));
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
