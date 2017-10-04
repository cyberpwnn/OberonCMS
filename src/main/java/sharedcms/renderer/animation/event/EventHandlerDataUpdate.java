/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$RenderTickEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  org.lwjgl.util.vector.ReadableVector3f
 *  org.lwjgl.util.vector.Vector3f
 */
package sharedcms.renderer.animation.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import sharedcms.renderer.animation.client.renderer.entity.RenderBendsPlayer;
import sharedcms.renderer.animation.data.Data_Player;
import sharedcms.renderer.animation.data.Data_Spider;
import sharedcms.renderer.animation.data.Data_Zombie;
import sharedcms.renderer.animation.util.BendsLogger;

import org.lwjgl.util.vector.ReadableVector3f;
import org.lwjgl.util.vector.Vector3f;

public class EventHandlerDataUpdate
{
	@SubscribeEvent
	public void updateAnimations(TickEvent.RenderTickEvent event)
	{
		int i;
		if(Minecraft.getMinecraft().theWorld == null)
		{
			return;
		}
		for(i = 0; i < Data_Player.dataList.size(); ++i)
		{
			Data_Player.dataList.get(i).update(event.renderTickTime);
		}
		for(i = 0; i < Data_Zombie.dataList.size(); ++i)
		{
			Data_Zombie.dataList.get(i).update(event.renderTickTime);
		}
		for(i = 0; i < Data_Spider.dataList.size(); ++i)
		{
			Data_Spider.dataList.get(i).update(event.renderTickTime);
		}
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event)
	{
		int i;
		Entity entity;
		if(Minecraft.getMinecraft().theWorld == null)
		{
			return;
		}
		if(!(RenderManager.instance.entityRenderMap.get(EntityPlayer.class) instanceof RenderBendsPlayer))
		{
			RenderBendsPlayer render = new RenderBendsPlayer();
			RenderManager.instance.entityRenderMap.put(EntityPlayer.class, render);
			render.setRenderManager(RenderManager.instance);
		}
		for(i = 0; i < Data_Player.dataList.size(); ++i)
		{
			Data_Player data = Data_Player.dataList.get(i);
			entity = Minecraft.getMinecraft().theWorld.getEntityByID(data.entityID);
			if(entity != null)
			{
				if(!data.entityType.equalsIgnoreCase(entity.getCommandSenderName()))
				{
					Data_Player.dataList.remove(data);
					Data_Player.add(new Data_Player(entity.getEntityId()));
					BendsLogger.log("Reset entity", BendsLogger.DEBUG);
					continue;
				}
				data.motion_prev.set((ReadableVector3f) data.motion);
				data.motion.x = (float) entity.posX - data.position.x;
				data.motion.y = (float) entity.posY - data.position.y;
				data.motion.z = (float) entity.posZ - data.position.z;
				data.position = new Vector3f((float) entity.posX, (float) entity.posY, (float) entity.posZ);
				continue;
			}
			Data_Player.dataList.remove(data);
			BendsLogger.log("No entity", BendsLogger.DEBUG);
		}
		for(i = 0; i < Data_Zombie.dataList.size(); ++i)
		{
			Data_Zombie data = Data_Zombie.dataList.get(i);
			entity = Minecraft.getMinecraft().theWorld.getEntityByID(data.entityID);
			if(entity != null)
			{
				if(!data.entityType.equalsIgnoreCase(entity.getCommandSenderName()))
				{
					Data_Zombie.dataList.remove(data);
					Data_Zombie.add(new Data_Zombie(entity.getEntityId()));
					BendsLogger.log("Reset entity", BendsLogger.DEBUG);
					continue;
				}
				data.motion_prev.set((ReadableVector3f) data.motion);
				data.motion.x = (float) entity.posX - data.position.x;
				data.motion.y = (float) entity.posY - data.position.y;
				data.motion.z = (float) entity.posZ - data.position.z;
				data.position = new Vector3f((float) entity.posX, (float) entity.posY, (float) entity.posZ);
				continue;
			}
			Data_Zombie.dataList.remove(data);
			BendsLogger.log("No entity", BendsLogger.DEBUG);
		}
		for(i = 0; i < Data_Spider.dataList.size(); ++i)
		{
			Data_Spider data = Data_Spider.dataList.get(i);
			entity = Minecraft.getMinecraft().theWorld.getEntityByID(data.entityID);
			if(entity != null)
			{
				if(!data.entityType.equalsIgnoreCase(entity.getCommandSenderName()))
				{
					Data_Spider.dataList.remove(data);
					Data_Spider.add(new Data_Spider(entity.getEntityId()));
					BendsLogger.log("Reset entity", BendsLogger.DEBUG);
					continue;
				}
				data.motion_prev.set((ReadableVector3f) data.motion);
				data.motion.x = (float) entity.posX - data.position.x;
				data.motion.y = (float) entity.posY - data.position.y;
				data.motion.z = (float) entity.posZ - data.position.z;
				data.position = new Vector3f((float) entity.posX, (float) entity.posY, (float) entity.posZ);
				continue;
			}
			Data_Spider.dataList.remove(data);
			BendsLogger.log("No entity", BendsLogger.DEBUG);
		}
	}
}
