/*
 * Decompiled with CFR 0_122.
 */
package sharedcms.renderer.animation.settings;

import sharedcms.renderer.animation.settings.SettingsNode;

public class SettingsBoolean
extends SettingsNode {
    public boolean data;
    public boolean defaultData;

    public SettingsBoolean() {
    }

    public SettingsBoolean(String argID, String argDisplayName) {
        super(argID, argDisplayName);
    }

    public void setData(boolean argData) {
        this.data = argData;
    }

    public SettingsBoolean setupDefault(boolean argData) {
        this.data = argData;
        this.defaultData = argData;
        return this;
    }
}

