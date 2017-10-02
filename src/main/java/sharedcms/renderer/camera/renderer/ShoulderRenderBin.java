/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Vec3
 *  org.lwjgl.util.vector.Vector2f
 */
package sharedcms.renderer.camera.renderer;

import net.minecraft.util.Vec3;
import org.lwjgl.util.vector.Vector2f;

public final class ShoulderRenderBin {
    public static Vec3 rayTraceHit = null;
    public static boolean rayTraceInReach = false;
    public static Vector2f projectedVector = null;
    public static boolean skipPlayerRender = false;

    private ShoulderRenderBin() {
    }
}

