package sharedcms.asm;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.MethodNode;

import net.minecraft.launchwrapper.IClassTransformer;
import sharedcms.asm.util.ASMHelper;
import sharedcms.asm.util.Accept;

@Accept(obf = ASMKey.CLASS_MINECRAFT_O, deobf = ASMKey.CLASS_MINECRAFT_D)
public class InjectorMinecraftLimitFramerate extends ASMHelper implements IClassTransformer
{
	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes)
	{
		return super.transform(name, transformedName, bytes);
	}

	@Override
	public ClassNode transform(ClassNode node)
	{
		String methodName = obfuscated ? ASMKey.METHOD_MINECRAFT_GETLIMITFRAMERATE_O : ASMKey.METHOD_MINECRAFT_GETLIMITFRAMERATE_D;
		String methodDesc = ASMKey.SIGNATURE_MINECRAFT_GETLIMITFRAMERATE;
		
		block0: for(MethodNode i : node.methods)
		{
			if(i.name.equals(methodName) && i.desc.equals(methodDesc))
			{
				MethodNode method = i;

				for(AbstractInsnNode j : method.instructions.toArray())
				{
					if(j.getOpcode() == Opcodes.BIPUSH && j instanceof IntInsnNode)
					{
						IntInsnNode iinsn = (IntInsnNode) j;
						
						if(iinsn.operand == 30)
						{
							iinsn.operand = 69;
							System.out.println(" ===>>> Set locked framerate to " + 69);
							break block0;
						}
					}
				}
			}
		}

		return node;
	}
}
