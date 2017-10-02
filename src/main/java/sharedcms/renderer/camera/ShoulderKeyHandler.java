/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.client.settings.KeyBinding
 */
package sharedcms.renderer.camera;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import sharedcms.renderer.camera.ShoulderCamera;
import sharedcms.renderer.camera.ShoulderKeybindings;
import sharedcms.renderer.camera.ShoulderLoader;
import sharedcms.renderer.camera.ShoulderSurfing;

public class ShoulderKeyHandler {
    ShoulderKeyHandler() {
    }

    @SubscribeEvent
    public void tickStart(TickEvent.ClientTickEvent event) {
        if (event.side == Side.CLIENT && event.phase == TickEvent.Phase.START && ShoulderLoader.mc != null && ShoulderLoader.mc.currentScreen == null && ShoulderLoader.mc.gameSettings.thirdPersonView == 1) {
            if (ShoulderKeybindings.KEYBIND_ROTATE_CAMERA_LEFT.getIsKeyPressed()) {
                ShoulderCamera.adjustCameraLeft();
            } else if (ShoulderKeybindings.KEYBIND_ROTATE_CAMERA_RIGHT.getIsKeyPressed()) {
                ShoulderCamera.adjustCameraRight();
            } else if (ShoulderKeybindings.KEYBIND_ZOOM_CAMERA_IN.getIsKeyPressed()) {
                ShoulderCamera.adjustCameraIn();
            } else if (ShoulderKeybindings.KEYBIND_ZOOM_CAMERA_OUT.getIsKeyPressed()) {
                ShoulderCamera.adjustCameraOut();
            }
            ShoulderSurfing.writeConfig();
        }
    }
}

