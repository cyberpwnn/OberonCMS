/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.lwjgl.openal.AL10
 *  org.lwjgl.openal.AL11
 */
package sharedcms.audio.filter;

import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;

public abstract class BaseFilter {
    protected boolean isLoaded = false;
    protected boolean isEnabled = false;
    protected int id = -1;
    protected int slot = -1;

    public abstract void loadFilter();

    public abstract void checkParameters();

    public abstract void loadParameters();

    private static int safeID(BaseFilter filter) {
        if (filter != null && filter.isEnabled && filter.id != -1) {
            return filter.id;
        }
        return 0;
    }

    private static int safeSlot(BaseFilter filter) {
        if (filter != null && filter.isEnabled && filter.slot != -1) {
            return filter.slot;
        }
        return 0;
    }

    public static void load3SourceFilters(int sourceChannel, int type, BaseFilter filter1, BaseFilter filter2, BaseFilter filter3) {
        AL11.alSource3i((int)sourceChannel, (int)type, (int)BaseFilter.safeSlot(filter1), (int)BaseFilter.safeSlot(filter2), (int)BaseFilter.safeSlot(filter3));
    }

    public static void loadSourceFilter(int sourceChannel, int type, BaseFilter filter) {
        AL10.alSourcei((int)sourceChannel, (int)type, (int)BaseFilter.safeSlot(filter));
    }

    public boolean isLoaded() {
        return this.isLoaded;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void enable() {
        this.isEnabled = true;
    }

    public void disable() {
        this.isEnabled = false;
    }
}

