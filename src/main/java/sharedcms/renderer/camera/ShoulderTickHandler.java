/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$RenderTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Type
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.util.Vec3
 */
package sharedcms.renderer.camera;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.Vec3;
import sharedcms.renderer.camera.ShoulderLoader;
import sharedcms.renderer.camera.math.RayTracer;
import sharedcms.renderer.camera.renderer.ShoulderRenderBin;

public class ShoulderTickHandler {
    @SubscribeEvent
    public void renderTickStart(TickEvent.RenderTickEvent event) {
        if (event.side == Side.CLIENT && event.phase == TickEvent.Phase.START && event.type == TickEvent.Type.RENDER) {
            ShoulderRenderBin.skipPlayerRender = false;
            RayTracer.traceFromEyes(1.0f);
            if (ShoulderRenderBin.rayTraceHit != null && ShoulderLoader.mc.thePlayer != null) {
                ShoulderRenderBin.rayTraceHit.xCoord -= ShoulderLoader.mc.thePlayer.posX;
                ShoulderRenderBin.rayTraceHit.yCoord -= ShoulderLoader.mc.thePlayer.posY;
                ShoulderRenderBin.rayTraceHit.zCoord -= ShoulderLoader.mc.thePlayer.posZ;
            }
        }
    }
}

