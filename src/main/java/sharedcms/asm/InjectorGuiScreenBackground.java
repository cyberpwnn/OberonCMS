package sharedcms.asm;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.GETSTATIC;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import net.minecraft.launchwrapper.IClassTransformer;
import sharedcms.asm.util.ASMHelper;
import sharedcms.asm.util.Accept;

@Accept(obf = ASMKey.CLASS_GUISCREEN_O, deobf = ASMKey.CLASS_GUISCREEN_D)
public class InjectorGuiScreenBackground extends ASMHelper implements IClassTransformer
{
	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes)
	{
		return super.transform(name, transformedName, bytes);
	}

	@Override
	public ClassNode transform(ClassNode node)
	{
		String main = obfuscated ? ASMKey.CLASS_GUISCREEN_O : ASMKey.CLASS_GUISCREEN_DM;
		String methodName = obfuscated ? ASMKey.METHOD_GUISCREEN_DRAWBACKGROUND_O : ASMKey.METHOD_GUISCREEN_DRAWBACKGROUND_D;
		String methodName2 = obfuscated ? ASMKey.METHOD_GUISCREEN_DRAWBG_O : ASMKey.METHOD_GUISCREEN_DRAWBG_D;
		String methodDesc = obfuscated ? ASMKey.SIGNATURE_GUICONTROLLER_RENDERBG : ASMKey.SIGNATURE_GUICONTROLLER_RENDERBG;

		for(MethodNode i : node.methods)
		{
			if(i.name.equals(methodName) && i.desc.equals(methodDesc))
			{
				MethodNode method = i;
				AbstractInsnNode mNode = null;

				for(AbstractInsnNode j : method.instructions.toArray())
				{
					if(j.getOpcode() == Opcodes.INVOKEVIRTUAL)
					{
						MethodInsnNode m = (MethodInsnNode) j;
						
						if(m.owner.equals(main) && m.name.equals(methodName2))
						{
							mNode = m;
							break;
						}
					}
				}
				
				if(mNode != null)
				{
					MethodInsnNode m = (MethodInsnNode) mNode;
					MethodInsnNode n = new MethodInsnNode(Opcodes.INVOKESTATIC, ASMKey.CLASS_GUICONTROLLER_M, ASMKey.METHOD_GUICONTROLLER_RENDERBACKGROUND, ASMKey.SIGNATURE_GUICONTROLLER_RENDERBACKGROUND, false);
					MethodInsnNode npre = new MethodInsnNode(Opcodes.INVOKESTATIC, ASMKey.CLASS_GUICONTROLLER_M, ASMKey.METHOD_GUICONTROLLER_RENDERBACKGROUND_PRE, ASMKey.SIGNATURE_GUICONTROLLER_RENDERBACKGROUND, false);
					method.instructions.insertBefore(m, npre);
					method.instructions.insertBefore(m.getNext(), n);

					System.out.println(" ===>>> RPLC " + m.getOpcode() + ": " + m.owner + "." + m.name + m.desc);
					System.out.println(" ===>>> TPLC " + n.getOpcode() + ": " + n.owner + "." + n.name + n.desc);
					System.out.println(" ===>>> Replaced drawBackground for non-world guis");
				}
			}
		}

		return node;
	}

	public void thisIsTestByteCode()
	{
		System.out.println("PLS REMOVE ME");
	}
}
