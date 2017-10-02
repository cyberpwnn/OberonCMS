/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.common.config.Property
 */
package sharedcms.renderer.animation.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventBus;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import sharedcms.renderer.animation.AnimatedEntity;
import sharedcms.renderer.animation.CommonProxy;
import sharedcms.renderer.animation.event.EventHandler_DataUpdate;
import sharedcms.renderer.animation.event.EventHandler_Keyboard;
import sharedcms.renderer.animation.pack.BendsPack;
import sharedcms.renderer.animation.settings.SettingsBoolean;
import sharedcms.renderer.animation.settings.SettingsNode;

public class ClientProxy
extends CommonProxy {
    public static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    public static final ResourceLocation texture_NULL = new ResourceLocation("sharedcms", "textures/white.png");
    public static final ResourceLocation GOBLIN_CAPE = new ResourceLocation("sharedcms", "textures/goblinCape.png");

    @Override
    public void preInit(Configuration config) {
        AnimatedEntity.registerRendering();
        ClientRegistry.registerKeyBinding((KeyBinding)EventHandler_Keyboard.key_Menu);
        MinecraftForge.EVENT_BUS.register((Object)new EventHandler_DataUpdate());
        FMLCommonHandler.instance().bus().register((Object)new EventHandler_DataUpdate());
        FMLCommonHandler.instance().bus().register((Object)new EventHandler_Keyboard());
        for (int i = 0; i < AnimatedEntity.animatedEntities.length; ++i) {
            AnimatedEntity.animatedEntities[i].animate = config.get("Animate", AnimatedEntity.animatedEntities[i].id, true).getBoolean();
        }
        ((SettingsBoolean)SettingsNode.getSetting((String)"swordTrail")).data = config.get("General", "Sword Trail", false).getBoolean();
        BendsPack.preInit(config);
    }
}

