package sharedcms.asm;

import static org.objectweb.asm.Opcodes.*;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import net.minecraft.launchwrapper.IClassTransformer;
import sharedcms.asm.util.ASMHelper;
import sharedcms.asm.util.Accept;
import sharedcms.asm.util.Clicker;

@Accept(obf = "ajt", deobf = "net.minecraft.block.BlockCactus")
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
		String methodName = obfuscated ? "a" : "onEntityCollidedWithBlock";
		String methodDesc = obfuscated ? "(Lahb;IIILsa;)V" : "(Lnet/minecraft/world/World;IIILnet/minecraft/entity/Entity;)V";

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

					Clicker.clip();
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
