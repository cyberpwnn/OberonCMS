package sharedcms.content.world.biome.decorator;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import sharedcms.base.AresBiome;
import sharedcms.base.AresBiomeDecorator;
import sharedcms.content.Content;
import sharedcms.content.world.decorator.DecorateArtifact;

public class BiomeDecoratorDesert extends AresBiomeDecorator
{
	public BiomeDecoratorDesert(AresBiome biome)
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
		this.waterlilyGen = Content.WorldGenerator.EMPTY;
		this.tallGrassTypes = new Block[] {Content.Block.DEAD_BUSH, Content.Block.DEAD_TWIG};
		this.flowersPerChunk = 0;
		this.grassPerChunk = 2;
		this.grassPerChunk = 0;
		this.sandPerChunk = 0;
		this.sandPerChunk2 = 0;
		this.clayPerChunk = 0;
		this.generateLakes = false;
		this.cactiPerChunk = 3;
		this.deadBushPerChunk = 4;
		this.reedsPerChunk = 2;
		this.treesPerChunk = -999;
		
		addDecorator(new DecorateArtifact(1, 0.2, Content.Models.RUBBLE_SANDSTONE, Blocks.sand));
	}
}
