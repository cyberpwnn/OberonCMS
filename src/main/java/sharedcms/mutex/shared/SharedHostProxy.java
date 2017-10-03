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
	private static SimplexNoiseGenerator simplex;

	@Override
	public void onPreInit(FMLPreInitializationEvent e)
	{
		meta = new GMap<World, MetaWorld>();
		simplex = new SimplexNoiseGenerator(69696969);
	}

	@Override
	public void onInit(FMLInitializationEvent e)
	{

	}

	@Override
	public void onPostInit(FMLPostInitializationEvent e)
	{
		System.out.println("SHARED HOST ONLINE");
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
	
	public static int getLevel(Location l)
	{
		return getBaseLevel(l) + getLevelShift(l);
	}
	
	public static int getLevelShift(Location l)
	{
		Location ll = l.clone();
		ll.y = 150;
		double simp1 = simplex.noise(ll.z / 5, 250, ll.x / 5) * 3;
		double simp2 = simplex.noise(ll.x / 10, 100, ll.z / 10) * 50;
		double simp3 = simplex.noise(ll.z / 55, 250, ll.x / 55) * 100;
		double distance = ll.distance(new Location()) + simp1 + simp2 + simp3;
		double shift = distance * Info.LEVEL_DISTANCE_RANDOM_MULTIPLIER;
		double rshift = simplex.noise(ll.x / 2, 100, ll.z / 2) * shift;
		
		return (int) Math.abs(rshift);
	}
	
	public static int getBaseLevel(Location l)
	{
		Location ll = l.clone();
		ll.y = 100;
		double simp1 = simplex.noise(ll.z / 5, 250, ll.x / 5) * 3;
		double simp2 = simplex.noise(ll.x / 10, 100, ll.z / 10) * 50;
		double simp3 = simplex.noise(ll.z / 55, 250, ll.x / 55) * 100;
		double distance = ll.distance(new Location()) + simp1 + simp2 + simp3;
		double level = distance * Info.LEVEL_DISTANCE_MULTIPLIER;
		
		return (int) Math.abs(level);
	}
}
