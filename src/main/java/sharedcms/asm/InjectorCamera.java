package sharedcms.asm;

import java.util.HashMap;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import net.minecraft.launchwrapper.IClassTransformer;
import sharedcms.asm.util.ShoulderASMHelper;

public class InjectorCamera implements IClassTransformer
{
	private final HashMap obfStrings;
	private final HashMap mcpStrings;
	public static final int CODE_MODIFICATIONS = 3;
	public static int modifications = 0;

	public InjectorCamera()
	{
		this.obfStrings = new HashMap();
		this.mcpStrings = new HashMap();
		this.registerMapping("EntityRendererClass", "net.minecraft.client.renderer.EntityRenderer", "blt");
		this.registerMapping("EntityRendererJavaClass", "net/minecraft/client/renderer/EntityRenderer", "blt");
		this.registerMapping("EntityLivingJavaClass", "net/minecraft/entity/EntityLivingBase", "sv");
		this.registerMapping("EntityJavaClass", "net/minecraft/entity/Entity", "sa");
		this.registerMapping("orientCameraMethod", "orientCamera", "h");
		this.registerMapping("rotationYawField", "rotationYaw", "y");
		this.registerMapping("rotationPitchField", "rotationPitch", "z");
		this.registerMapping("SHOULDER_ROTATIONField", "SHOULDER_ROTATION", "SHOULDER_ROTATION");
		this.registerMapping("SHOULDER_ZOOM_MODField", "SHOULDER_ZOOM_MOD", "SHOULDER_ZOOM_MOD");
		this.registerMapping("InjectionDelegationJavaClass", "sharedcms/asm/util/InjectionDelegation", "sharedcms/asm/util/InjectionDelegation");
		this.registerMapping("ShoulderRenderBinJavaClass", "sharedcms/renderer/camera/renderer/ShoulderRenderBin", "sharedcms/renderer/camera/renderer/ShoulderRenderBin");
		this.registerMapping("renderWorldMethod", "renderWorld", "a");
		this.registerMapping("clippingHelperImplJavaClass", "net/minecraft/client/renderer/culling/ClippingHelperImpl", "bmw");
		this.registerMapping("clippingHelperJavaClass", "net/minecraft/client/renderer/culling/ClippingHelper", "bmy");
		this.registerMapping("clippingHelperGetInstanceMethod", "getInstance", "a");
	}

	public byte[] transform(String name, String transformedName, byte[] bytes)
	{
		if(name.equals(this.obfStrings.get("EntityRendererClass")))
		{
			System.out.println("Injecting into obfuscated code - EntityRendererClass");
			return this.transformEntityRenderClass(bytes, this.obfStrings);
		}
		
		if(name.equals(this.mcpStrings.get("EntityRendererClass")))
		{
			System.out.println("Injecting into non-obfuscated code - EntityRendererClass");
			return this.transformEntityRenderClass(bytes, this.mcpStrings);
		}
		
		return bytes;
	}

	private byte[] transformEntityRenderClass(byte[] bytes, HashMap hm)
	{
		System.out.println("Attempting class transformation against EntityRender");
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(bytes);
		classReader.accept((ClassVisitor) classNode, 0);
		
		for(MethodNode m : classNode.methods)
		{
			InsnList hackCode;
			InsnList searchList;
			int offset;
			
			if(m.name.equals(hm.get("orientCameraMethod")) && m.desc.equals("(F)V"))
			{
				System.out.println("Located method " + m.name + m.desc + ", locating signature");
				searchList = new InsnList();
				searchList.add((AbstractInsnNode) new VarInsnNode(25, 2));
				searchList.add((AbstractInsnNode) new FieldInsnNode(180, (String) hm.get("EntityLivingJavaClass"), (String) hm.get("rotationYawField"), "F"));
				searchList.add((AbstractInsnNode) new VarInsnNode(56, 13));
				searchList.add((AbstractInsnNode) new VarInsnNode(25, 2));
				searchList.add((AbstractInsnNode) new FieldInsnNode(180, (String) hm.get("EntityLivingJavaClass"), (String) hm.get("rotationPitchField"), "F"));
				searchList.add((AbstractInsnNode) new VarInsnNode(56, 12));
				offset = ShoulderASMHelper.locateOffset(m.instructions, searchList);
				
				if(offset == -1)
				{
					System.out.println("Failed to locate first of two offsets in " + m.name + m.desc + "!  Is base file changed?");
					return bytes;
				}
				
				System.out.println("Located offset @ " + offset);
				hackCode = new InsnList();
				hackCode.add((AbstractInsnNode) new VarInsnNode(23, 13));
				hackCode.add((AbstractInsnNode) new MethodInsnNode(184, (String) hm.get("InjectionDelegationJavaClass"), "getShoulderRotation", "()F"));
				hackCode.add((AbstractInsnNode) new InsnNode(98));
				hackCode.add((AbstractInsnNode) new VarInsnNode(56, 13));
				hackCode.add((AbstractInsnNode) new VarInsnNode(24, 10));
				hackCode.add((AbstractInsnNode) new MethodInsnNode(184, (String) hm.get("InjectionDelegationJavaClass"), "getShoulderZoomMod", "()F"));
				hackCode.add((AbstractInsnNode) new InsnNode(141));
				hackCode.add((AbstractInsnNode) new InsnNode(107));
				hackCode.add((AbstractInsnNode) new VarInsnNode(57, 10));
				hackCode.add((AbstractInsnNode) new LabelNode(new Label()));
				m.instructions.insertBefore(m.instructions.get(offset + 1), hackCode);
				System.out.println("Injected code for camera orientation!");
				++modifications;
				searchList = new InsnList();
				searchList.add((AbstractInsnNode) new VarInsnNode(57, 25));
				searchList.add((AbstractInsnNode) new VarInsnNode(24, 25));
				offset = ShoulderASMHelper.locateOffset(m.instructions, searchList);
				if(offset == -1)
				{
					System.out.println("Failed to locate second of two offsets in " + m.name + m.desc + "!  Is base file changed?");
					return bytes;
				}
				System.out.println("Located offset @ " + offset);
				hackCode = new InsnList();
				hackCode.add((AbstractInsnNode) new VarInsnNode(24, 25));
				hackCode.add((AbstractInsnNode) new MethodInsnNode(184, (String) hm.get("InjectionDelegationJavaClass"), "verifyReverseBlockDist", "(D)V"));
				hackCode.add((AbstractInsnNode) new LabelNode(new Label()));
				m.instructions.insertBefore(m.instructions.get(offset), hackCode);
				System.out.println("Injected code for camera distance check!");
				++modifications;
				continue;
			}
			if(!m.name.equals(hm.get("renderWorldMethod")) || !m.desc.equals("(FJ)V"))
				continue;
			System.out.println("Located method " + m.name + m.desc + ", locating signature");
			searchList = new InsnList();
			searchList.add((AbstractInsnNode) new MethodInsnNode(184, (String) hm.get("clippingHelperImplJavaClass"), (String) hm.get("clippingHelperGetInstanceMethod"), "()L" + (String) hm.get("clippingHelperJavaClass") + ";"));
			searchList.add((AbstractInsnNode) new InsnNode(87));
			offset = ShoulderASMHelper.locateOffset(m.instructions, searchList);
			if(offset == -1)
			{
				System.out.println("Failed to locate offset in " + m.name + m.desc + "!  Is base file changed?");
				return bytes;
			}
			System.out.println("Located offset @ " + offset);
			hackCode = new InsnList();
			hackCode.add((AbstractInsnNode) new MethodInsnNode(184, (String) hm.get("InjectionDelegationJavaClass"), "calculateRayTraceProjection", "()V"));
			hackCode.add((AbstractInsnNode) new LabelNode(new Label()));
			m.instructions.insertBefore(m.instructions.get(offset + 1), hackCode);
			System.out.println("Injected code for ray trace projection!");
			++modifications;
		}
		ClassWriter writer = new ClassWriter(1);
		classNode.accept((ClassVisitor) writer);
		return writer.toByteArray();
	}

	private void registerMapping(String key, String normalValue, String obfuscatedValue)
	{
		this.mcpStrings.put(key, normalValue);
		this.obfStrings.put(key, obfuscatedValue);
	}
}
