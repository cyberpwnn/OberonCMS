package sharedcms.asm.util;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import net.minecraft.launchwrapper.IClassTransformer;

public abstract class ASMHelper implements IClassTransformer
{
	protected boolean obfuscated;

	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes)
	{
		Accept a = getClass().getDeclaredAnnotation(Accept.class);
		boolean k = false;

		if(a.obf().equals(name))
		{
			k = true;
			obfuscated = true;
		}

		if(a.deobf().equals(name))
		{
			k = true;
			obfuscated = false;
		}

		if(k)
		{
			ClassNode node = new ClassNode();
			ClassReader reader = new ClassReader(bytes);
			ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
			reader.accept(node, 0);
			transform(node).accept(writer);
			System.out.println(" ===>>> TRANSFORM " + name);
			return writer.toByteArray();
		}

		return bytes;
	}

	public abstract ClassNode transform(ClassNode node);
}
