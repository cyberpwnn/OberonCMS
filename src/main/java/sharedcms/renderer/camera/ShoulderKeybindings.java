/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  net.minecraft.client.settings.KeyBinding
 */
package sharedcms.renderer.camera;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import sharedcms.renderer.camera.ShoulderKeyHandler;

public class ShoulderKeybindings {
    public static final KeyBinding KEYBIND_ROTATE_CAMERA_LEFT = new KeyBinding("ShoulderSurfing.key.cameraMoveLeft", 36, "ShoulderSurfing.keyCategory");
    public static final KeyBinding KEYBIND_ROTATE_CAMERA_RIGHT = new KeyBinding("ShoulderSurfing.key.cameraMoveRight", 38, "ShoulderSurfing.keyCategory");
    public static final KeyBinding KEYBIND_ZOOM_CAMERA_OUT = new KeyBinding("ShoulderSurfing.key.cameraMoveCloser", 23, "ShoulderSurfing.keyCategory");
    public static final KeyBinding KEYBIND_ZOOM_CAMERA_IN = new KeyBinding("ShoulderSurfing.key.cameraMoveFarther", 37, "ShoulderSurfing.keyCategory");
    private static KeyBinding[] keyBindings = new KeyBinding[]{KEYBIND_ROTATE_CAMERA_LEFT, KEYBIND_ROTATE_CAMERA_RIGHT, KEYBIND_ZOOM_CAMERA_OUT, KEYBIND_ZOOM_CAMERA_IN};

    public static ShoulderKeyHandler registerKeybindings() {
        ShoulderKeyHandler skh = new ShoulderKeyHandler();
        for (KeyBinding keybinding : keyBindings) {
            ClientRegistry.registerKeyBinding((KeyBinding)keybinding);
        }
        return skh;
    }
}

