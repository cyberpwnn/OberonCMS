/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.InputEvent
 *  cpw.mods.fml.common.gameevent.InputEvent$KeyInputEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.settings.KeyBinding
 */
package sharedcms.renderer.animation.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.KeyBinding;
import sharedcms.renderer.animation.client.gui.GuiMBMenu;
import sharedcms.renderer.animation.pack.BendsPack;

public class EventHandler_Keyboard {
    public static final KeyBinding key_Menu = new KeyBinding("Mo'Bends Menu", 34, "GobBob's Mods");

    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event) throws IOException {
        if (key_Menu.getIsKeyPressed()) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiMBMenu());
            ++sharedcms.renderer.animation.MoBends.refreshModel;
            BendsPack.initPacks();
        }
    }
}

