package sharedcms.util;

import net.minecraft.client.Minecraft;
import net.minecraft.world.WorldSettings.GameType;

public class PlayerUtils
{
	public static GameType getGameMode()
	{
		Minecraft mc = Minecraft.getMinecraft();

		if(mc.playerController.isInCreativeMode())
		{
			return GameType.CREATIVE;
		}

		if(mc.playerController.gameIsSurvivalOrAdventure())
		{
			return GameType.SURVIVAL;
		}

		return GameType.ADVENTURE;
	}
	
	public static void setGameMode(GameType mode)
	{
		Minecraft.getMinecraft().thePlayer.setGameType(mode);
	}
	
	public static void denyBreaking()
	{
		Minecraft.getMinecraft().thePlayer.capabilities.allowEdit = false;
	}
}
