package sharedcms.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import net.minecraft.launchwrapper.IClassTransformer;

public class InjectorBlurShader implements IClassTransformer
{
	public byte[] transform(String name, String transformedName, byte[] basicClass)
	{
		if(transformedName.equals(ASMKey.CLASS_GUISCREEN_D))
		{
			System.out.println(" ===>>> Transforming Class [" + transformedName + "], Method [" + ASMKey.METHOD_GUISCREEN_DRAWBACKGROUND_D + "]");
			ClassNode classNode = new ClassNode();
			ClassReader classReader = new ClassReader(basicClass);
			classReader.accept((ClassVisitor) classNode, 0);
			block0: for(MethodNode m : classNode.methods)
			{
				if(!m.name.equals(ASMKey.METHOD_GUISCREEN_DRAWBACKGROUND_D) && !m.name.equals(ASMKey.METHOD_GUISCREEN_DRAWBACKGROUND_O))
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

					System.out.println(" ===>>> Modifying GUI background darkness");
					MethodInsnNode colorHook = new MethodInsnNode(184, ASMKey.CLASS_BACKGROUNDBLURCONTROLLER, ASMKey.METHOD_BGC_BACKGROUND_D, ASMKey.SIGNATURE_GUISCREEN_BACKGROUND, false);
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
