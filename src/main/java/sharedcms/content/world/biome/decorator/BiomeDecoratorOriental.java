package sharedcms.content.world.biome.decorator;

import net.minecraft.block.Block;
import sharedcms.base.AresBiome;
import sharedcms.base.AresBiomeDecorator;
import sharedcms.content.Content;
import sharedcms.content.world.decorator.DecorateArtifact;

public class BiomeDecoratorOriental extends AresBiomeDecorator
{
	public BiomeDecoratorOriental(AresBiome biome)
	{
		super(biome);
		this.coalGen = Content.WorldGenerator.EMPTY;
		this.ironGen = Content.WorldGenerator.EMPTY;
		this.goldGen = Content.WorldGenerator.EMPTY;
		this.redstoneGen = Content.WorldGenerator.EMPTY;
		this.diamondGen = Content.WorldGenerator.EMPTY;
		this.lapisGen = Content.WorldGenerator.EMPTY;
		this.mushroomBrownGen = Content.WorldGenerator.EMPTY;
		this.mushroomRedGen = Content.WorldGenerator.EMPTY;
		this.bigMushroomGen = Content.WorldGenerator.EMPTY;
		this.reedGen = Content.WorldGenerator.EMPTY;
		this.cactusGen = Content.WorldGenerator.EMPTY;
		this.flowersPerChunk = 24;
		this.grassPerChunk = 1;
		this.sandPerChunk = 0;
		this.sandPerChunk2 = 0;
		this.clayPerChunk = 0;
		this.generateLakes = false;
		this.treesPerChunk = 1;
		this.waterlilyPerChunk = 32;
		this.tallGrassTypes = new Block[] {Content.Block.TALL_GRASS, Content.Block.SHORT_GRASS, Content.Block.THIN_GRASS, Content.Block.FERN, Content.Block.FLOWER_ALLIUM, Content.Block.FLOWER_ALLIUM, Content.Block.FLOWER_ALLIUM, Content.Block.FLOWER_BLUE_ORCHID, Content.Block.FLOWER_BLUE_ORCHID, Content.Block.FLOWER_BLUE_ORCHID, Content.Block.FLOWER_TULIP_PINK, Content.Block.FLOWER_BLUE_ORCHID, Content.Block.FLOWER_BLUE_ORCHID};
		
		addDecorator(new DecorateArtifact(1, 0.12, Content.Models.RUBBLE_ROCK, Content.Block.COLD_GRASS));
		addDecorator(new DecorateArtifact(1, 0.19, Content.Models.RUBBLE_DIRT, Content.Block.COLD_GRASS));
		addDecorator(new DecorateArtifact(1, 0.19, Content.Models.RUBBLE_DIRT, Content.Block.COLD_STONE));
	}
}
