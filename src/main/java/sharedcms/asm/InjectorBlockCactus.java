package sharedcms.asm;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.GETSTATIC;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import net.minecraft.launchwrapper.IClassTransformer;
import sharedcms.asm.util.ASMHelper;
import sharedcms.asm.util.Accept;

@Accept(obf = ASMKey.CLASS_BLOCKCACTUS_O, deobf = ASMKey.CLASS_BLOCKCACTUS_D)
public class InjectorBlockCactus extends ASMHelper implements IClassTransformer
{
	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes)
	{
		return super.transform(name, transformedName, bytes);
	}

	@Override
	public ClassNode transform(ClassNode node)
	{
		String methodName = obfuscated ? ASMKey.METHOD_BLOCKCACTUS_COLLISION_O : ASMKey.METHOD_BLOCKCACTUS_COLLISION_D;
		String methodDesc = obfuscated ? ASMKey.SIGNATURE_BLOCKCACTUS_O : ASMKey.SIGNATURE_BLOCKCACTUS_D;

		for(MethodNode i : node.methods)
		{
			if(i.name.equals(methodName) && i.desc.equals(methodDesc))
			{
				MethodNode method = i;
				AbstractInsnNode mNode = null;

				for(AbstractInsnNode j : method.instructions.toArray())
				{
					if(j.getOpcode() == ALOAD)
					{
						if(((VarInsnNode) j).var == 5 && j.getNext().getOpcode() == GETSTATIC)
						{
							mNode = j;
							break;
						}
					}
				}

				if(mNode != null)
				{
					for(int j = 0; j < 5; j++)
					{
						mNode = mNode.getNext();
						method.instructions.remove(mNode.getPrevious());
					}

					System.out.println(" ===>>> Removed Cactus Damage Code");
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
