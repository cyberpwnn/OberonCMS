package sharedcms.controller.shared;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import sharedcms.content.world.meta.objects.MetaWorld;
import sharedcms.controllable.Controller;
import sharedcms.util.GMap;

public class WorldHostController extends Controller
{
	public static GMap<World, MetaWorld> meta;
	
	public WorldHostController()
	{
		meta = new GMap<World, MetaWorld>();
	}

	@Override
	public void onPreInitialization()
	{
		
	}

	@Override
	public void onInitialization()
	{
		
	}

	@Override
	public void onPostInitialization()
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
