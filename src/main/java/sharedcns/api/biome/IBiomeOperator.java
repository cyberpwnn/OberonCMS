package sharedcns.api.biome;

import net.minecraft.world.WorldType;

public interface IBiomeOperator
{
	public BiomeBuffer getBiomes();
	
	public BiomeBuffer getBiomes(int x, int z);
}
