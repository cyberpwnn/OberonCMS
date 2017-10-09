package sharedcms.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import net.minecraft.launchwrapper.IClassTransformer;

public class InjectorWorldRenderer implements IClassTransformer
{
	private static final String asmHandler = "sharedcms/renderer/world/AsmHandler";

	public byte[] transform(String name, String transformedName, byte[] basicClass)
	{
		if(transformedName.startsWith("net.minecraft.client.renderer.RenderList"))
		{
			System.out.println(" ===>>> World Renderer RenderList " + transformedName);

			return this.patchRenderList(basicClass);
		}

		if(transformedName.startsWith("net.minecraft.client.renderer.WorldRenderer"))
		{
			System.out.println(" ===>>> World Renderer " + transformedName);

			return this.patchWorldRenderer(basicClass);
		}

		return basicClass;
	}

	private byte[] patchWorldRenderer(byte[] basicClass)
	{
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(basicClass);
		classReader.accept((ClassVisitor) classNode, 0);
		System.out.println("Found WorldRenderer Class: " + classNode.name);

		System.out.flush();

		MethodNode setPosition = null;

		for(MethodNode mn : classNode.methods)
		{
			if(!mn.name.equals("func_78913_a") && !mn.name.equals("setPosition") && !mn.name.equals("a"))
			{
				continue;
			}

			setPosition = mn;
			break;
		}

		if(setPosition != null)
		{
			System.out.println(" ===>>> Found setPosition");

			for(int i = 0; i < setPosition.instructions.size(); ++i)
			{
				AbstractInsnNode ain = setPosition.instructions.get(i);

				if(ain instanceof MethodInsnNode)
				{
					MethodInsnNode min = (MethodInsnNode) ain;
					
					System.out.println(min.name + " %%%E$$");
					
					if(!min.name.equals("glEndList"))
					{
						continue;
					}

					InsnList toInsert = new InsnList();
					toInsert.add((AbstractInsnNode) new VarInsnNode(25, 0));
					toInsert.add((AbstractInsnNode) new MethodInsnNode(184, asmHandler, "setPosition", "(Lnet/minecraft/client/renderer/WorldRenderer;)V", false));
					setPosition.instructions.insertBefore(ain, toInsert);
					System.out.println(" ===>>> Patched Vanilla setPosition");
					System.out.println(" ===>>> INJECTION COMPLETE FOR WREND");
					break;
				}

				if(!(ain instanceof FieldInsnNode))
				{
					continue;
				}

				FieldInsnNode fin = (FieldInsnNode) ain;

				if(!fin.name.equals("needsBoxUpdate"))
				{
					continue;
				}

				InsnList toInsert = new InsnList();
				toInsert.add((AbstractInsnNode) new VarInsnNode(25, 0));
				toInsert.add((AbstractInsnNode) new MethodInsnNode(184, asmHandler, "setPosition", "(Lnet/minecraft/client/renderer/WorldRenderer;)V", false));
				setPosition.instructions.insertBefore(ain, toInsert);
				System.out.println(" ===>>> Patched Optifine setPosition");
				System.out.println(" ===>>> INJECTION COMPLETE FOR WREND");
				break;
			}
		}

		ClassWriter writer = new ClassWriter(1);
		classNode.accept((ClassVisitor) writer);
		return writer.toByteArray();
	}

	private byte[] patchRenderList(byte[] basicClass)
	{
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(basicClass);
		classReader.accept((ClassVisitor) classNode, 0);
		System.out.println(" ===>>> Found RenderList Class: " + classNode.name);
		MethodNode callLists = null;

		for(MethodNode mn : classNode.methods)
		{
			if(!mn.name.equals("func_78419_a") && !mn.name.equals("callLists") && !mn.name.equals("a"))
			{
				continue;
			}

			callLists = mn;
		}

		if(callLists != null)
		{
			System.out.println(" ===>>> Found callLists");

			for(int i = 0; i < callLists.instructions.size(); ++i)
			{
				AbstractInsnNode ain = callLists.instructions.get(i);

				if(!(ain instanceof MethodInsnNode))
				{
					continue;
				}

				MethodInsnNode min = (MethodInsnNode) ain;

				if(!min.name.equals("glCallLists"))
				{
					continue;
				}

				System.out.println(" ===>>> Patched callLists");
				InsnList toInsert = new InsnList();
				toInsert.add((AbstractInsnNode) new MethodInsnNode(184, asmHandler, "callLists", "(Ljava/nio/IntBuffer;)V", false));
				callLists.instructions.insert((AbstractInsnNode) min, toInsert);
				callLists.instructions.remove((AbstractInsnNode) min);
				System.out.println(" ===>>> INJECTION COMPLETE FOR RLIST");
				break;
			}
		}

		ClassWriter writer = new ClassWriter(1);
		classNode.accept((ClassVisitor) writer);
		return writer.toByteArray();
	}

	private byte[] patchDummyClass(byte[] basicClass)
	{
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(basicClass);
		classReader.accept((ClassVisitor) classNode, 0);
		System.out.println(" ===>>> Found Dummy Class: " + classNode.name);
		ClassWriter writer = new ClassWriter(1);
		classNode.accept((ClassVisitor) writer);
		return writer.toByteArray();
	}
}
