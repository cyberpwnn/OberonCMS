package sharedcms.mutex.server;

import java.util.UUID;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import sharedcms.mutex.shared.SharedHostProxy;
import sharedcms.mutex.shared.object.ICharacter;

public class CombatEnvironment
{
	public CombatEnvironment()
	{
		
	}
	
	public ICharacter getPlayer(UUID id)
	{
		return ServerHostProxy.characterController.getPlayer(id);
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = false)
	public void on(LivingHurtEvent e)
	{
		if(e.entityLiving instanceof EntityPlayer)
		{
			ICharacter p = getPlayer(e.entityLiving.getUniqueID());
			p.onRawDamageTaken(e.source, e.ammount);
			e.setCanceled(true);
			e.ammount = 0;
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = false)
	public void on(LivingAttackEvent e)
	{
		if(e.entityLiving instanceof EntityPlayer)
		{
			on(new LivingHurtEvent(e.entityLiving, e.source, e.ammount));
			e.setCanceled(true);
			return;
		}

		e.entityLiving.setHealth(e.entityLiving.getHealth() - e.ammount);
		e.entityLiving.worldObj.playSoundEffect(e.entityLiving.posX, e.entityLiving.posY, e.entityLiving.posZ, "game.neutral.hurt", 0.6f, (float) (1f + ((Math.random() - 1.5) * 2) * 0.3));
		e.setCanceled(true);
	}
}
