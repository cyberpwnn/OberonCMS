package sharedcms.content.world.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import sharedcms.content.Content;
import sharedcms.content.world.meta.objects.MetaWorld;
import sharedcms.controller.shared.WorldHostController;
import sharedcns.api.biome.BiomeTemperature;
import sharedcns.api.biome.LudicrousBiome;

public class LBiomeBase extends LudicrousBiome
{
	public LBiomeBase(int level)
	{
		super(Content.nextBiomeId());
		setLevel(level);
		setBiomeName(getClass().getSimpleName().replaceAll("Biome", ""));
	}
}
