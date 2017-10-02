/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.FieldInsnNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.IntInsnNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package sharedcms.asm.util;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class ShoulderASMHelper {
    public static void removeLastNInstructions(InsnList instructions, int startAt, int numberToRemove) {
        for (int i = 0; i < numberToRemove; ++i) {
            instructions.remove(instructions.get(startAt - i));
        }
    }

    public static int locateOffset(InsnList instructions, InsnList search) {
        return ShoulderASMHelper.locateOffset(instructions, search, true, true);
    }

    public static int locateOffset(InsnList instructions, InsnList search, boolean ignoreLabel, boolean ignoreLineNumber) {
        return ShoulderASMHelper.locateOffset(instructions, search, 0, 0, instructions.size(), ignoreLabel, ignoreLineNumber);
    }

    private static int locateOffset(InsnList instructions, InsnList search, int searchNdx, int startAt, int limit, boolean ignoreLabel, boolean ignoreLineNumber) {
        int attempts = 0;
        for (int i = startAt; i < instructions.size() && attempts < limit; ++i) {
            if (ignoreLabel && instructions.get(i).getType() == 8 || ignoreLineNumber && instructions.get(i).getType() == 15) continue;
            boolean match = false;
            if (instructions.get(i).getType() == search.get(searchNdx).getType()) {
                if (instructions.get(i).getType() == 4) {
                    if (((FieldInsnNode)instructions.get((int)i)).desc.equals(((FieldInsnNode)search.get((int)searchNdx)).desc) && ((FieldInsnNode)instructions.get((int)i)).name.equals(((FieldInsnNode)search.get((int)searchNdx)).name) && ((FieldInsnNode)instructions.get((int)i)).owner.equals(((FieldInsnNode)search.get((int)searchNdx)).owner)) {
                        match = true;
                    }
                } else if (instructions.get(i).getType() == 2) {
                    if (((VarInsnNode)instructions.get((int)i)).var == ((VarInsnNode)search.get((int)searchNdx)).var && instructions.get(i).getOpcode() == search.get(searchNdx).getOpcode()) {
                        match = true;
                    }
                } else if (instructions.get(i).getType() == 0) {
                    if (((InsnNode)instructions.get(i)).getOpcode() == ((InsnNode)search.get(searchNdx)).getOpcode()) {
                        match = true;
                    }
                } else if (instructions.get(i).getType() == 5) {
                    if (((MethodInsnNode)instructions.get((int)i)).desc.equals(((MethodInsnNode)search.get((int)searchNdx)).desc) && ((MethodInsnNode)instructions.get((int)i)).name.equals(((MethodInsnNode)search.get((int)searchNdx)).name) && ((MethodInsnNode)instructions.get((int)i)).owner.equals(((MethodInsnNode)search.get((int)searchNdx)).owner)) {
                        match = true;
                    }
                } else if (instructions.get(i).getType() == 1 && ((IntInsnNode)instructions.get((int)i)).operand == ((IntInsnNode)search.get((int)searchNdx)).operand && instructions.get(i).getOpcode() == search.get(searchNdx).getOpcode()) {
                    match = true;
                }
                if (match) {
                    if (searchNdx < search.size() - 1) {
                        int offset = ShoulderASMHelper.locateOffset(instructions, search, searchNdx + 1, i + 1, 1, ignoreLabel, ignoreLineNumber);
                        if (offset != -1) {
                            return offset;
                        }
                    } else {
                        return i;
                    }
                }
            }
            if (match) continue;
            ++attempts;
        }
        return -1;
    }
}

