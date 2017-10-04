package sharedcms.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import net.minecraft.launchwrapper.IClassTransformer;

public class InjectorRenderer implements IClassTransformer
{
	public byte[] transform(String name, String transformedName, byte[] bytes)
	{
		if(bytes == null)
		{
			return null;
		}
		
		boolean obfuscated = true;
		
		if(!"blm".equals(name))
		{
			if(!"net.minecraft.client.renderer.RenderBlocks".equals(name))
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
				if(!"b".equals(methodNode.name) || !"(Laji;III)Z".equals(methodNode.desc))
				{
					continue;
				}
				
				targetMethod = methodNode;
				
				break;
			}
			
			if(!"renderBlockByRenderType".equals(methodNode.name))
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
		injectedMethod.visitVarInsn(25, 1);
		injectedMethod.visitMethodInsn(184, "sharedcms/voxel/SoftBlockRenderer", "shouldHookRenderer", obfuscated ? "(Laji;)Z" : "(Lnet/minecraft/block/Block;)Z");
		Label label2 = new Label();
		injectedMethod.visitJumpInsn(153, label2);
		Label label3 = new Label();
		injectedMethod.visitLabel(label3);
		injectedMethod.visitFieldInsn(178, "sharedcms/controller/client/BoxelController", "softBlockRenderer", "Lsharedcms/voxel/SoftBlockRenderer;");
		injectedMethod.visitVarInsn(25, 1);
		injectedMethod.visitVarInsn(21, 2);
		injectedMethod.visitVarInsn(21, 3);
		injectedMethod.visitVarInsn(21, 4);
		injectedMethod.visitVarInsn(25, 0);
		injectedMethod.visitMethodInsn(182, "sharedcms/voxel/SoftBlockRenderer", "directRenderHook", obfuscated ? "(Laji;IIILblm;)Z" : "(Lnet/minecraft/block/Block;IIILnet/minecraft/client/renderer/RenderBlocks;)Z");
		injectedMethod.visitInsn(172);
		injectedMethod.visitLabel(label2);
		targetMethod.instructions.insert(injectedMethod.instructions);
		ClassWriter writer = new ClassWriter(1);
		classNode.accept((ClassVisitor) writer);
		
		return writer.toByteArray();
	}
}
