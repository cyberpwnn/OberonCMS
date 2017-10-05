package sharedcms.controller.client;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import sharedcms.controllable.Controller;
import sharedcms.fx.BlockEffect;
import sharedcms.util.GList;
import sharedcms.util.Location;

public class FXController extends Controller
{
	private static GList<BlockEffect> effects;
	
	public FXController()
	{
		effects = new GList<BlockEffect>();
	}
	
	public static void addEffect(BlockEffect effect)
	{
		effects.add(effect);
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
	
	public static void tick(World w, int x, int y, int z, Random r, Block b)
	{
		for(BlockEffect i : effects)
		{
			if(i.qualifies(b) && r.nextDouble() <= i.getChance())
			{
				i.play(w, x, y, z);
			}
		}
	}
}
