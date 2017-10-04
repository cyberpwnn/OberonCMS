/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.IClassTransformer
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 */
package sharedcms.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import net.minecraft.launchwrapper.IClassTransformer;

public class InjectorBlurShader implements IClassTransformer
{
	private static final String GUI_SCREEN_CLASS_NAME = "net.minecraft.client.gui.GuiScreen";
	private static final String DRAW_WORLD_BAGKGROUND_METHOD = "drawWorldBackground";
	private static final String DRAW_WORLD_BAGKGROUND_METHOD_OBF = "func_146270_b";
	private static final String BLUR_MAIN_CLASS = "sharedcms/controller/client/BackgroundBlurController";
	private static final String COLOR_HOOK_METHOD_NAME = "getBackgroundColor";
	private static final String COLOR_HOOK_METHOD_DESC = "(Z)I";

	public byte[] transform(String name, String transformedName, byte[] basicClass)
	{
		if(transformedName.equals("net.minecraft.client.gui.GuiScreen"))
		{
			System.out.println(" ===>>> Transforming Class [" + transformedName + "], Method [" + "drawWorldBackground" + "]");
			ClassNode classNode = new ClassNode();
			ClassReader classReader = new ClassReader(basicClass);
			classReader.accept((ClassVisitor) classNode, 0);
			block0: for(MethodNode m : classNode.methods)
			{
				if(!m.name.equals("drawWorldBackground") && !m.name.equals("func_146270_b"))
				{
					continue;
				}

				for(int i = 0; i < m.instructions.size(); ++i)
				{
					AbstractInsnNode next = m.instructions.get(i);
					if(next.getOpcode() != 18)
					{
						continue;
					}

					System.out.println(" ===>>> Modifying GUI background darkness... ");
					MethodInsnNode colorHook = new MethodInsnNode(184, "sharedcms/controller/client/BackgroundBlurController", "getBackgroundColor", "(Z)I", false);
					AbstractInsnNode colorHook2 = colorHook.clone(null);
					m.instructions.set(next, (AbstractInsnNode) colorHook);
					m.instructions.set(colorHook.getNext(), colorHook2);
					m.instructions.insertBefore((AbstractInsnNode) colorHook, (AbstractInsnNode) new InsnNode(4));
					m.instructions.insertBefore(colorHook2, (AbstractInsnNode) new InsnNode(3));

					break block0;
				}
			}

			ClassWriter cw = new ClassWriter(1);
			classNode.accept((ClassVisitor) cw);
			System.out.println(" ===>>> Transforming " + transformedName + " Finished.");
			return cw.toByteArray();
		}

		return basicClass;
	}
}
