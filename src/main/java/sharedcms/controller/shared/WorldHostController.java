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

	public static MetaWorld getWorldMeta(World world)
	{
		if(meta.get(world) == null)
		{
			System.out.println("No meta world, creating");
			meta.put(world, new MetaWorld(world));
		}
		
		return meta.get(world);
	}
	
	public static GMap<World, MetaWorld> getMeta()
	{
		return meta;
	}
}
