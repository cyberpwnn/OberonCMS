/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.IConfigElement
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.common.config.ConfigCategory
 *  net.minecraftforge.common.config.ConfigElement
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.common.config.Property
 */
package sharedcms.renderer.world;

import cpw.mods.fml.client.config.IConfigElement;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import java.io.File;
import java.util.List;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ChunkAnimatorConfig
{
	Property propertyMode;
	int mode;
	Property propertyAnimationDuration;
	int animationDuration;
	Configuration config;

	public void preInit()
	{
		this.config = new Configuration(new File("f"));
		this.propertyMode = this.config.get("settings", "Mode", 0, "How should the chunks be animated?\n 0: Chunks always appear from below\n 1: Chunks always appear from above\n 2: Chunks appear from below if they are lower than the Horizon and from above if they are higher than the Horizon");
		this.propertyAnimationDuration = this.config.get("settings", "AnimationDuration", 2527, "How long should the animation last? (In milliseconds)");
		this.syncConfig();
	}

	public void syncConfig()
	{
		this.mode = this.propertyMode.getInt();
		this.animationDuration = this.propertyAnimationDuration.getInt();
	}

	public int getMode()
	{
		return this.mode;
	}

	public int getAnimationDuration()
	{
		return this.animationDuration;
	}

	public String getString()
	{
		return this.config.toString();
	}

	public List<IConfigElement> getConfigElements()
	{
		return new ConfigElement(this.config.getCategory("settings")).getChildElements();
	}
}
