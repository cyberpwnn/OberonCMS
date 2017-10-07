package sharedcms.content.world.biome.decorator;

import net.minecraft.block.Block;
import sharedcms.base.AresBiome;
import sharedcms.base.AresBiomeDecorator;
import sharedcms.content.Content;
import sharedcms.content.world.decorator.DecorateArtifact;

public class BiomeDecoratorDesertArid extends AresBiomeDecorator
{
	public BiomeDecoratorDesertArid(AresBiome biome)
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
		this.tallGrassTypes = new Block[] {Content.Block.DEAD_BUSH, Content.Block.DEAD_TWIG};
		this.flowersPerChunk = 0;
		this.grassPerChunk = 2;
		this.sandPerChunk = 0;
		this.sandPerChunk2 = 0;
		this.clayPerChunk = 0;
		this.generateLakes = false;
		this.cactiPerChunk = 0;
		this.deadBushPerChunk = 0;
		this.reedsPerChunk = 0;
		this.treesPerChunk = 2;
		
		addDecorator(new DecorateArtifact(1, 0.2, Content.Models.RUBBLE_SANDSTONE, Content.Block.ARID_SAND));
	}
}
