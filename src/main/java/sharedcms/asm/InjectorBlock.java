package sharedcms.asm;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

public class InjectorBlock implements IClassTransformer
{
	public byte[] transform(String name, String transformedName, byte[] bytes)
	{
		if(bytes == null)
		{
			return null;
		}

		boolean obfuscated = true;

		if(!name.equals(ASMKey.CLASS_BLOCK_O))
		{
			if(!name.equals(ASMKey.CLASS_BLOCK_D))
			{
				return bytes;
			}

			obfuscated = false;
		}

		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(bytes);
		classReader.accept((ClassVisitor) classNode, 8);
		MethodNode targetMethod = null;

		for(MethodNode methodNode : classNode.methods)
		{
			if(obfuscated)
			{
				if(!methodNode.name.equals(ASMKey.METHOD_BLOCK_COLLISION_O) || !methodNode.desc.equals(ASMKey.SIGNATURE_BLOCK_COLLISION))
				{
					continue;
				}

				targetMethod = methodNode;

				break;
			}

			if(!methodNode.name.equals(ASMKey.METHOD_BLOCK_COLLISION_D))
			{
				continue;
			}

			targetMethod = methodNode;

			break;
		}

		if(targetMethod == null)
		{
			return bytes;
		}
		
		MethodNode injectedMethod = new MethodNode();
		Label label0 = new Label();
		injectedMethod.visitLabel(label0);
		injectedMethod.visitVarInsn(25, 0);
		System.out.println("  ===>>> Inject Softblock Collision check");
		injectedMethod.visitMethodInsn(184, ASMKey.CLASS_BOXEL, ASMKey.METHOD_BOXEL_CHECK, obfuscated ? ASMKey.SIGNATURE_BOXEL_CHECK_O : ASMKey.SIGNATURE_BOXEL_CHECK_D);
		Label label2 = new Label();
		injectedMethod.visitJumpInsn(153, label2);
		Label label3 = new Label();
		injectedMethod.visitLabel(label3);
		injectedMethod.visitVarInsn(25, 0);
		injectedMethod.visitVarInsn(25, 1);
		injectedMethod.visitVarInsn(21, 2);
		injectedMethod.visitVarInsn(21, 3);
		injectedMethod.visitVarInsn(21, 4);
		injectedMethod.visitVarInsn(25, 5);
		injectedMethod.visitVarInsn(25, 6);
		injectedMethod.visitVarInsn(25, 7);
		System.out.println("  ===>>> Inject Softblock Collision bounds");
		injectedMethod.visitMethodInsn(184, ASMKey.CLASS_BLOCKRENDERER, ASMKey.METHOD_BLOCKRENDERER_INJECT, obfuscated ? ASMKey.SIGNATURE_BLOCKRENDERER_INJECT_O : ASMKey.SIGNATURE_BLOCKRENDERER_INJECT_D);
		Label label4 = new Label();
		injectedMethod.visitLabel(label4);
		injectedMethod.visitInsn(177);
		injectedMethod.visitLabel(label2);
		targetMethod.instructions.insert(injectedMethod.instructions);
		ClassWriter writer = new ClassWriter(3);
		classNode.accept((ClassVisitor) writer);

		return writer.toByteArray();
	}
}
