/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.EventBus
 */
package sharedcms.audio.openal;

import cpw.mods.fml.common.FMLCommonHandler;

public class OpenALClientHandler
{
	public void registerTickHandlers()
	{
		FMLCommonHandler.instance().bus().register((Object) new SoundTickHandler());
	}
}
