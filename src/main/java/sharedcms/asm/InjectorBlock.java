package sharedcms.asm;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.launchwrapper.IClassTransformer;
import sharedcms.asm.util.Clicker;

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

		if(!"aji".equals(name))
		{
			if(!"net.minecraft.block.Block".equals(name))
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
				if(!"a".equals(methodNode.name) || !"(Lahb;IIILazt;Ljava/util/List;Lsa;)V".equals(methodNode.desc))
				{
					continue;
				}

				targetMethod = methodNode;

				break;
			}

			if(!"addCollisionBoxesToList".equals(methodNode.name))
			{
				continue;
			}

			targetMethod = methodNode;

			break;
		}

		if(targetMethod == null)
		{
			System.out.println("  TARGET NULL!");
			return bytes;
		}
		
		Clicker.clip();
		MethodNode injectedMethod = new MethodNode();
		Label label0 = new Label();
		injectedMethod.visitLabel(label0);
		injectedMethod.visitVarInsn(25, 0);
		System.out.println("  ===>>> Inject Softblock Collision check");
		injectedMethod.visitMethodInsn(184, "sharedcms/proxy/ProxyVoxel", "isBlockSoftForCollision", obfuscated ? "(Laji;)Z" : "(Lnet/minecraft/block/Block;)Z");
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
		injectedMethod.visitMethodInsn(184, "sharedcms/voxel/SoftBlockRenderer", "inject", obfuscated ? "(Laji;Lahb;IIILazt;Ljava/util/List;Lsa;)V" : "(Lnet/minecraft/block/Block;Lnet/minecraft/world/World;IIILnet/minecraft/util/AxisAlignedBB;Ljava/util/List;Lnet/minecraft/entity/Entity;)V");
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
