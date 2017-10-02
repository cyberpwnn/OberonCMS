/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.PlayerControllerMP
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  org.lwjgl.util.vector.Vector2f
 */
package sharedcms.renderer.camera.math;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import sharedcms.renderer.camera.ShoulderLoader;
import sharedcms.renderer.camera.ShoulderSettings;
import sharedcms.renderer.camera.renderer.ShoulderRenderBin;

import org.lwjgl.util.vector.Vector2f;

public final class RayTracer {
    private RayTracer() {
    }

    public static void traceFromEyes(float tick) {
        ShoulderRenderBin.projectedVector = null;
        if (ShoulderLoader.mc.renderViewEntity != null && ShoulderLoader.mc.theWorld != null && ShoulderLoader.mc.gameSettings.thirdPersonView == 1) {
            double playerReach = 1.0;
            playerReach = ShoulderSettings.USE_CUSTOM_RAYTRACE_DISTANCE ? (double)ShoulderSettings.RAYTRACE_DISTANCE : (double)ShoulderLoader.mc.playerController.getBlockReachDistance();
            MovingObjectPosition omo = ShoulderLoader.mc.renderViewEntity.rayTrace(playerReach, tick);
            double blockDist = 0.0;
            if (omo != null) {
                ShoulderRenderBin.rayTraceHit = omo.hitVec;
                blockDist = omo.hitVec.distanceTo(Vec3.createVectorHelper((double)ShoulderLoader.mc.renderViewEntity.posX, (double)ShoulderLoader.mc.renderViewEntity.posY, (double)ShoulderLoader.mc.renderViewEntity.posZ));
                ShoulderRenderBin.rayTraceInReach = blockDist <= (double)ShoulderLoader.mc.playerController.getBlockReachDistance();
            } else {
                ShoulderRenderBin.rayTraceHit = null;
            }
            Vec3 renderViewPos = ShoulderLoader.mc.renderViewEntity.getPosition(tick);
            Vec3 sightVector = ShoulderLoader.mc.renderViewEntity.getLook(tick);
            Vec3 sightRay = renderViewPos.addVector(sightVector.xCoord * playerReach, sightVector.yCoord * playerReach, sightVector.zCoord * playerReach);
            List entityList = ShoulderLoader.mc.theWorld.getEntitiesWithinAABBExcludingEntity((Entity)ShoulderLoader.mc.renderViewEntity, ShoulderLoader.mc.renderViewEntity.boundingBox.addCoord(sightVector.xCoord * playerReach, sightVector.yCoord * playerReach, sightVector.zCoord * playerReach).expand(1.0, 1.0, 1.0));
            for (int i = 0; i < entityList.size(); ++i) {
                AxisAlignedBB aabb;
                double entityDist;
                MovingObjectPosition potentialIntercept;
                float collisionSize;
                Entity ent = (Entity)entityList.get(i);
                if (!ent.canBeCollidedWith() || (potentialIntercept = (aabb = ent.boundingBox.expand((double)(collisionSize = ent.getCollisionBorderSize()), (double)collisionSize, (double)collisionSize)).calculateIntercept(renderViewPos, sightRay)) == null || (entityDist = potentialIntercept.hitVec.distanceTo(Vec3.createVectorHelper((double)ShoulderLoader.mc.renderViewEntity.posX, (double)ShoulderLoader.mc.renderViewEntity.posY, (double)ShoulderLoader.mc.renderViewEntity.posZ))) >= blockDist) continue;
                ShoulderRenderBin.rayTraceHit = potentialIntercept.hitVec;
                ShoulderRenderBin.rayTraceInReach = entityDist <= (double)ShoulderLoader.mc.playerController.getBlockReachDistance();
            }
        }
    }
}

