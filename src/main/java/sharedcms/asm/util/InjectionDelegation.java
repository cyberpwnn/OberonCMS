/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.util.Vec3
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GL14
 *  org.lwjgl.util.vector.Vector2f
 */
package sharedcms.asm.util;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import sharedcms.renderer.camera.ShoulderCamera;
import sharedcms.renderer.camera.ShoulderLoader;
import sharedcms.renderer.camera.ShoulderSettings;
import sharedcms.renderer.camera.math.VectorConverter;
import sharedcms.renderer.camera.renderer.ShoulderRenderBin;

public final class InjectionDelegation {
    @Deprecated
    private static float lastX = 0.0f;
    @Deprecated
    private static float lastY = 0.0f;

    private InjectionDelegation() {
    }

    public static float getShoulderRotation() {
        if (ShoulderLoader.mc.gameSettings.thirdPersonView == 1) {
            return ShoulderCamera.SHOULDER_ROTATION;
        }
        return 0.0f;
    }

    public static float getShoulderZoomMod() {
        if (ShoulderLoader.mc.gameSettings.thirdPersonView == 1) {
            return ShoulderCamera.SHOULDER_ZOOM_MOD;
        }
        return 1.0f;
    }

    public static void calculateRayTraceProjection() {
        if (ShoulderRenderBin.rayTraceHit != null) {
            ShoulderRenderBin.projectedVector = VectorConverter.project2D((float)ShoulderRenderBin.rayTraceHit.xCoord, (float)ShoulderRenderBin.rayTraceHit.yCoord, (float)ShoulderRenderBin.rayTraceHit.zCoord);
            ShoulderRenderBin.rayTraceHit = null;
        }
    }

    @Deprecated
    public static void drawCrosshairs(Gui g, float tick) {
        ScaledResolution sr = new ScaledResolution(ShoulderLoader.mc, ShoulderLoader.mc.displayWidth, ShoulderLoader.mc.displayHeight);
        if (ShoulderLoader.mc.gameSettings.thirdPersonView == 0) {
            lastX = sr.getScaledWidth() * sr.getScaleFactor() / 2;
            lastY = sr.getScaledHeight() * sr.getScaleFactor() / 2;
            g.drawTexturedModalRect(sr.getScaledWidth() / 2 - 7, sr.getScaledHeight() / 2 - 7, 0, 0, 16, 16);
        } else if (ShoulderLoader.mc.gameSettings.thirdPersonView == 1) {
            if (ShoulderRenderBin.projectedVector != null) {
                GL11.glEnable((int)3042);
                if (ShoulderRenderBin.rayTraceInReach) {
                    GL14.glBlendColor((float)0.2f, (float)0.2f, (float)1.0f, (float)1.0f);
                    GL11.glBlendFunc((int)775, (int)32769);
                } else {
                    GL14.glBlendColor((float)1.0f, (float)0.2f, (float)0.2f, (float)1.0f);
                    GL11.glBlendFunc((int)775, (int)32769);
                }
                float diffX = (ShoulderRenderBin.projectedVector.x - lastX) * tick;
                float diffY = (ShoulderRenderBin.projectedVector.y - lastY) * tick;
                g.drawTexturedModalRect((int)((lastX + diffX) / (float)sr.getScaleFactor() - 7.0f), (int)((lastY + diffY) / (float)sr.getScaleFactor() - 7.0f), 0, 0, 16, 16);
                lastX += diffX;
                lastY += diffY;
                GL11.glDisable((int)3042);
            } else if (ShoulderSettings.TRACE_TO_HORIZON_LAST_RESORT) {
                GL11.glEnable((int)3042);
                GL14.glBlendColor((float)1.0f, (float)0.2f, (float)0.2f, (float)1.0f);
                GL11.glBlendFunc((int)775, (int)32769);
                float diffX = ((float)(sr.getScaledWidth() * sr.getScaleFactor() / 2) - lastX) * tick;
                float diffY = ((float)(sr.getScaledHeight() * sr.getScaleFactor() / 2) - lastY) * tick;
                g.drawTexturedModalRect((int)((lastX + diffX) / (float)sr.getScaleFactor() - 7.0f), (int)((lastY + diffY) / (float)sr.getScaleFactor() - 7.0f), 0, 0, 16, 16);
                lastX += diffX;
                lastY += diffY;
                GL11.glDisable((int)3042);
            }
        }
    }

    public static void verifyReverseBlockDist(double distance) {
        if (distance < 0.8 && ShoulderSettings.HIDE_PLAYER_IF_TOO_CLOSE_TO_CAMERA) {
            ShoulderRenderBin.skipPlayerRender = true;
        }
    }
}

