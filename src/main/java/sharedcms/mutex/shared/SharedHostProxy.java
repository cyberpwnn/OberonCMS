package sharedcms.mutex.shared;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import sharedcms.Info;
import sharedcms.content.world.generator.SimplexNoiseGenerator;
import sharedcms.content.world.meta.objects.MetaWorld;
import sharedcms.proxy.IProxy;
import sharedcms.util.GMap;
import sharedcms.util.Location;

public class SharedHostProxy implements IProxy
{
	public static GMap<World, MetaWorld> meta;

	@Override
	public void onPreInit(FMLPreInitializationEvent e)
	{
		meta = new GMap<World, MetaWorld>();
	}

	@Override
	public void onInit(FMLInitializationEvent e)
	{

	}

	@Override
	public void onPostInit(FMLPostInitializationEvent e)
	{

	}

	@SubscribeEvent
	public void on(WorldEvent.Load e)
	{
		meta.put(e.world, new MetaWorld(e.world));
	}

	@SubscribeEvent
	public void on(WorldEvent.Save e)
	{
		meta.get(e.world).save();
	}

	public static MetaWorld getWorldMeta(World world)
	{
		return meta.get(world);
	}
	
	public static GMap<World, MetaWorld> getMeta()
	{
		return meta;
	}
}
