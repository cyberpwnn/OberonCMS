package sharedcms.controller.server;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import sharedcms.Info;
import sharedcms.controllable.Controller;

public class PlayerMovementController extends Controller
{
	public PlayerMovementController()
	{
		
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
	public void on(LivingUpdateEvent e)
	{
		if(e.entityLiving instanceof EntityPlayerMP)
		{
			if(Info.TESSELLATION_STEP)
			{
				e.entityLiving.noClip = true;
			}
		}
	}
}
