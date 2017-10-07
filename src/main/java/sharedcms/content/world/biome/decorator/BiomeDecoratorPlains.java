package sharedcms.content.world.biome.decorator;

import sharedcms.base.AresBiome;
import sharedcms.base.AresBiomeDecorator;
import sharedcms.content.Content;
import sharedcms.content.world.decorator.DecorateArtifact;

public class BiomeDecoratorPlains extends AresBiomeDecorator
{
	public BiomeDecoratorPlains(AresBiome biome)
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
		this.waterlilyGen = Content.WorldGenerator.EMPTY;
		this.flowersPerChunk = 3;
		this.grassPerChunk = 5;
		this.sandPerChunk = 0;
		this.sandPerChunk2 = 0;
		this.clayPerChunk = 0;
		this.generateLakes = false;
		this.treesPerChunk = -999;

		addDecorator(new DecorateArtifact(1, 0.02, Content.Models.RUBBLE_ROCK, Content.Block.COLD_GRASS));
		addDecorator(new DecorateArtifact(1, 0.09, Content.Models.RUBBLE_DIRT, Content.Block.COLD_GRASS));
		addDecorator(new DecorateArtifact(1, 0.09, Content.Models.RUBBLE_DIRT, Content.Block.COLD_STONE));
	}
}
