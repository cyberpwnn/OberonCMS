package sharedcms.asm;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

import net.minecraft.launchwrapper.IClassTransformer;
import sharedcms.Info;
import sharedcms.asm.util.ASMHelper;
import sharedcms.asm.util.Accept;

@Accept(obf = ASMKey.CLASS_MINECRAFT_O, deobf = ASMKey.CLASS_MINECRAFT_D)
public class InjectorMinecraftStartGame extends ASMHelper implements IClassTransformer
{
	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes)
	{
		return super.transform(name, transformedName, bytes);
	}

	@Override
	public ClassNode transform(ClassNode node)
	{
		String methodName = obfuscated ? ASMKey.METHOD_MINECRAFT_STARTGAME_O : ASMKey.METHOD_MINECRAFT_STARTGAME_D;
		String methodDesc = ASMKey.SIGNATURE_MINECRAFT_STARTGAME;
		String guiScreenClass = obfuscated ? ASMKey.CLASS_GUIMAINMENU_O : ASMKey.CLASS_GUIMAINMENU_D;

		for(MethodNode i : node.methods)
		{
			if(i.name.equals(methodName) && i.desc.equals(methodDesc))
			{
				MethodNode method = i;
				AbstractInsnNode mNode = null;

				for(AbstractInsnNode j : method.instructions.toArray())
				{
					if(j.getOpcode() == Opcodes.INVOKESTATIC)
					{
						if(((MethodInsnNode) j).getPrevious().getOpcode() == Opcodes.LDC)
						{
							MethodInsnNode m = (MethodInsnNode) j;
							LdcInsnNode ldc = (LdcInsnNode) m.getPrevious();

							if(m.name.equals("setTitle") && m.desc.equals("(Ljava/lang/String;)V"))
							{
								System.out.println(" ===>>> Replace Game Title with " + Info.WINDOW_TITLE);
								ldc.cst = Info.WINDOW_TITLE;
								mNode = m;
							}
						}
					}

					if(j.getOpcode() == Opcodes.NEW)
					{
						TypeInsnNode n = (TypeInsnNode) j;

						if(n.desc.equals(guiScreenClass))
						{
							if(n.getNext().getOpcode() == Opcodes.DUP)
							{
								if(n.getNext().getNext().getOpcode() == Opcodes.INVOKESPECIAL)
								{
									MethodInsnNode m = (MethodInsnNode) j.getNext().getNext();

									n.desc = ASMKey.CLASS_UIMAINMENU;
									System.out.println(" ===>>> Replaced Main Menu <NEW> with " + n.desc);
									m.owner = ASMKey.CLASS_UIMAINMENU;
									System.out.println(" ===>>> Replaced Main Menu <INSNSPECIAL> with " + n.desc);
								}
							}
						}
					}
				}

				if(mNode != null)
				{

				}
			}
		}

		return node;
	}
}
