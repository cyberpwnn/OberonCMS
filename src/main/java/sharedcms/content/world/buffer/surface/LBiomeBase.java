package sharedcms.content.world.buffer.surface;

import sharedcms.content.Content;
import sharedcns.api.biome.LudicrousBiome;

public class LBiomeBase extends LudicrousBiome
{
	public LBiomeBase()
	{
		super(Content.nextBiomeId());
	}
}
