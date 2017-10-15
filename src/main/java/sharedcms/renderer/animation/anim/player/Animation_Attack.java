package sharedcms.renderer.animation.anim.player;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import sharedcms.content.item.ItemCrusader;
import sharedcms.content.item.ItemGladius;
import sharedcms.content.item.ItemSpada;
import sharedcms.content.weapon.WeaponType;
import sharedcms.renderer.animation.anim.Animation;
import sharedcms.renderer.animation.client.model.entity.ModelBendsPlayer;
import sharedcms.renderer.animation.data.Data_Player;
import sharedcms.renderer.animation.data.EntityData;
import sharedcms.renderer.animation.pack.BendsPack;
import sharedcms.renderer.animation.weapon.crusader.CrusaderAttack1;
import sharedcms.renderer.animation.weapon.crusader.CrusaderAttack2;
import sharedcms.renderer.animation.weapon.crusader.CrusaderAttack3;
import sharedcms.renderer.animation.weapon.crusader.CrusaderAttack4;
import sharedcms.renderer.animation.weapon.gladius.GladiusAttack1;
import sharedcms.renderer.animation.weapon.gladius.GladiusAttack2;
import sharedcms.renderer.animation.weapon.gladius.GladiusAttack3;
import sharedcms.renderer.animation.weapon.gladius.GladiusAttack4;
import sharedcms.renderer.animation.weapon.spada.SpadaAttack1;
import sharedcms.renderer.animation.weapon.spada.SpadaAttack2;
import sharedcms.renderer.animation.weapon.spada.SpadaAttack3;
import sharedcms.renderer.animation.weapon.spada.SpadaAttack4;

public class Animation_Attack extends Animation
{
	@Override
	public String getName()
	{
		return "attack";
	}

	@Override
	public void animate(EntityLivingBase argEntity, ModelBase argModel, EntityData argData)
	{
		ModelBendsPlayer model = (ModelBendsPlayer) argModel;
		Data_Player data = (Data_Player) argData;
		EntityPlayer player = (EntityPlayer) argEntity;
		
		ItemStack item = player.getCurrentEquippedItem();
		WeaponType wt = null;
		
		if(item != null)
		{
			if(item.getItem() instanceof ItemCrusader)
			{
				wt = WeaponType.CRUSADER;
			}
			
			if(item.getItem() instanceof ItemGladius)
			{
				wt = WeaponType.GLADIUS;
			}
			
			if(item.getItem() instanceof ItemSpada)
			{
				wt = WeaponType.SPADA;
			}
		}
		
		if(player.getCurrentEquippedItem() != null && wt != null)
		{
			if(wt.equals(WeaponType.CRUSADER))
			{
				if(data.ticksAfterPunch < 8.0f)
				{
					if(data.currentAttack == 1)
					{
						CrusaderAttack1.animate((EntityPlayer) argEntity, model, data);
						BendsPack.animate(model, "player", "attack_0");
					}
					
					else if(data.currentAttack == 2)
					{
						CrusaderAttack2.animate((EntityPlayer) argEntity, model, data);
						BendsPack.animate(model, "player", "attack_1");
					}
					
					else if(data.currentAttack == 3)
					{
						CrusaderAttack3.animate((EntityPlayer) argEntity, model, data);
						BendsPack.animate(model, "player", "attack_2");
					}
					
					else if(data.currentAttack == 4)
					{
						CrusaderAttack4.animate((EntityPlayer) argEntity, model, data);
						BendsPack.animate(model, "player", "attack_3");
					}
				}
				
				else if(data.ticksAfterPunch < 60.0f)
				{
					Animation_Attack_Stance.animate((EntityPlayer) argEntity, model, data);
					BendsPack.animate(model, "player", "attack_stance");
				}
			}
			
			else if(wt.equals(WeaponType.GLADIUS))
			{
				if(data.ticksAfterPunch < 11.0f)
				{
					if(data.currentAttack == 1)
					{
						GladiusAttack1.animate((EntityPlayer) argEntity, model, data);
						BendsPack.animate(model, "player", "attack_0");
					}
					
					else if(data.currentAttack == 2)
					{
						GladiusAttack2.animate((EntityPlayer) argEntity, model, data);
						BendsPack.animate(model, "player", "attack_1");
					}
					
					else if(data.currentAttack == 3)
					{
						GladiusAttack3.animate((EntityPlayer) argEntity, model, data);
						BendsPack.animate(model, "player", "attack_2");
					}
					
					else if(data.currentAttack == 4)
					{
						GladiusAttack4.animate((EntityPlayer) argEntity, model, data);
						BendsPack.animate(model, "player", "attack_3");
					}
				}
				
				else if(data.ticksAfterPunch < 60.0f)
				{
					Animation_Attack_Stance.animate((EntityPlayer) argEntity, model, data);
					BendsPack.animate(model, "player", "attack_stance");
				}
			}
			
			else if(wt.equals(WeaponType.SPADA))
			{
				if(data.ticksAfterPunch < 8.0f)
				{
					if(data.currentAttack == 1)
					{
						SpadaAttack1.animate((EntityPlayer) argEntity, model, data);
						BendsPack.animate(model, "player", "attack_0");
					}
					
					else if(data.currentAttack == 2)
					{
						SpadaAttack2.animate((EntityPlayer) argEntity, model, data);
						BendsPack.animate(model, "player", "attack_1");
					}
					
					else if(data.currentAttack == 3)
					{
						SpadaAttack3.animate((EntityPlayer) argEntity, model, data);
						BendsPack.animate(model, "player", "attack_2");
					}
					
					else if(data.currentAttack == 4)
					{
						SpadaAttack4.animate((EntityPlayer) argEntity, model, data);
						BendsPack.animate(model, "player", "attack_3");
					}
				}
				
				else if(data.ticksAfterPunch < 60.0f)
				{
					Animation_Attack_Stance.animate((EntityPlayer) argEntity, model, data);
					BendsPack.animate(model, "player", "attack_stance");
				}
			}
		}
		else if(data.ticksAfterPunch < 10.0f)
		{
			Animation_Attack_Punch.animate((EntityPlayer) argEntity, model, data);
			BendsPack.animate(model, "player", "punch");
		}
		else if(data.ticksAfterPunch < 60.0f)
		{
			Animation_Attack_PunchStance.animate((EntityPlayer) argEntity, model, data);
			BendsPack.animate(model, "player", "punch_stance");
		}
	}
}
