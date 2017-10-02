/*
 * Decompiled with CFR 0_122.
 */
package sharedcms.renderer.animation.settings;

import sharedcms.renderer.animation.settings.SettingsBoolean;

public abstract class SettingsNode {
    public static SettingsNode[] settings = new SettingsNode[]{new SettingsBoolean("swordTrail", "Sword Trail").setupDefault(true), new SettingsBoolean("dummy", "Dummy")};
    public String id;
    public String displayName;

    public SettingsNode() {
        this.id = "NULL";
        this.displayName = "NULL";
    }

    public SettingsNode(String argID, String argDisplayName) {
        this.id = argID;
        this.displayName = argDisplayName;
    }

    public static SettingsNode getSetting(String argID) {
        for (int i = 0; i < settings.length; ++i) {
            if (!SettingsNode.settings[i].id.equalsIgnoreCase(argID)) continue;
            return settings[i];
        }
        return null;
    }
}

