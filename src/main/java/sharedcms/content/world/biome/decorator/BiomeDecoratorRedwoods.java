package sharedcms.content.world.biome.decorator;

import sharedcms.base.AresBiome;
import sharedcms.base.AresBiomeDecorator;
import sharedcms.content.Content;
import sharedcms.content.world.decorator.DecorateArtifact;
import sharedcms.content.world.decorator.DecoratePodzolSludge;
import sharedcms.content.world.decorator.DecorateWrappedGenerator;
import sharedcms.content.world.generator.WorldTreeGenTops;

public class BiomeDecoratorRedwoods extends AresBiomeDecorator
{
	public BiomeDecoratorRedwoods(AresBiome biome)
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
		this.flowersPerChunk = 14;
		this.grassPerChunk = 7;
		this.sandPerChunk = 0;
		this.sandPerChunk2 = 0;
		this.clayPerChunk = 0;
		this.generateLakes = false;
		this.treesPerChunk = 7;
		
		addDecorator(new DecorateWrappedGenerator(17, new WorldTreeGenTops(Content.Block.LOG_REDWOOD, Content.Block.LEAVES_REDWOOD, 27, 5, 1)));
		addDecorator(new DecoratePodzolSludge(9));
		addDecorator(new DecorateArtifact(1, 0.07, Content.Models.RUBBLE_ROCK, Content.Block.PODZOL));
		addDecorator(new DecorateArtifact(1, 0.19, Content.Models.RUBBLE_DIRT, Content.Block.PODZOL));
		addDecorator(new DecorateArtifact(1, 0.19, Content.Models.RUBBLE_DIRT, Content.Block.COLD_STONE));
	}
}
