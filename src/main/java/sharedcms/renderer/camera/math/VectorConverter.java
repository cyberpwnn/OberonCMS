/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.util.Vec3
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.glu.GLU
 *  org.lwjgl.util.vector.Vector2f
 */
package sharedcms.renderer.camera.math;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector2f;

public class VectorConverter {
    public static Vector2f project2D(Vec3 v3) {
        return VectorConverter.project2D((float)v3.xCoord, (float)v3.yCoord, (float)v3.zCoord);
    }

    public static Vector2f project2D(float x, float y, float z) {
        FloatBuffer screen_coords = GLAllocation.createDirectFloatBuffer((int)3);
        IntBuffer viewport = GLAllocation.createDirectIntBuffer((int)16);
        FloatBuffer modelview = GLAllocation.createDirectFloatBuffer((int)16);
        FloatBuffer projection = GLAllocation.createDirectFloatBuffer((int)16);
        screen_coords.clear();
        modelview.clear();
        projection.clear();
        viewport.clear();
        GL11.glGetFloat((int)2982, (FloatBuffer)modelview);
        GL11.glGetFloat((int)2983, (FloatBuffer)projection);
        GL11.glGetInteger((int)2978, (IntBuffer)viewport);
        boolean ret = GLU.gluProject((float)x, (float)y, (float)z, (FloatBuffer)modelview, (FloatBuffer)projection, (IntBuffer)viewport, (FloatBuffer)screen_coords);
        if (ret) {
            return new Vector2f(screen_coords.get(0), screen_coords.get(1));
        }
        return null;
    }
}

