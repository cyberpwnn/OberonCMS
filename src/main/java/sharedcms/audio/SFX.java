package sharedcms.audio;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import sharedcms.content.Content;
import sharedcms.controller.client.AudioController;
import sharedcms.util.Location;
import sharedcms.util.M;

public class SFX
{
	public static long lastMs = M.ms();
	
	public static void play(DSound sound)
	{
		AudioController.playSound(sound);
	}
	
	public static void play(DSound sound, Location s)
	{
		AudioController.playSound(sound, s);
	}
	
	public static void playArmorRustleSounds(EntityPlayer ep)
	{
		if(ep == null)
		{
			return;
		}
		
		if(M.ms() - lastMs < (170))
		{
			return;
		}
				
		lastMs = M.ms();
		
		boolean metal = false;
		boolean leather = false;
		
		if(ep != null)
		{
			for(int x = 0; x < 4; x++)
			{
				ItemStack i = ep.inventory.armorItemInSlot(x);
				
				if(i == null)
				{
					continue;
				}
				
				System.out.println(i.getUnlocalizedName());
				
				if(i.getUnlocalizedName().toLowerCase().contains("cloth"))
				{
					leather = true;
				}
				
				if(i.getUnlocalizedName().toLowerCase().contains("iron"))
				{
					metal = true;
				}
				
				if(i.getUnlocalizedName().toLowerCase().contains("diamond"))
				{
					metal = true;
				}
				
				if(i.getUnlocalizedName().toLowerCase().contains("gold"))
				{
					metal = true;
				}
			}
		}
		
		if(metal)
		{
			SFX.play(new DSound("sharedcms:" + Content.SoundMaterial.ARMOR_METAL, 0.35f, 1.4f, 0.3f), new Location(ep));
		}
		
		if(leather)
		{
			SFX.play(new DSound("sharedcms:" + Content.SoundMaterial.ARMOR_LEATHER, 0.35f, 0.6f, 0.3f), new Location(ep));
		}
	}
}
