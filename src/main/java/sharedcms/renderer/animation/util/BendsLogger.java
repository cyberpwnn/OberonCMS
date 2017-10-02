/*
 * Decompiled with CFR 0_122.
 */
package sharedcms.renderer.animation.util;

import java.io.PrintStream;

public enum BendsLogger {
    INFO,
    DEBUG,
    ERROR;
    

    private BendsLogger() {
    }

    public static void log(String argText, BendsLogger argType) {
        if (argType != DEBUG) {
            System.out.println("(MO'BENDS - " + argType.name() + " ) " + argText);
        }
    }
}

