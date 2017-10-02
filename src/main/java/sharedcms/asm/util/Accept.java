package sharedcms.asm.util;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import cpw.mods.fml.common.Optional.Method;

@Retention(RUNTIME)
@Target(TYPE)
public @interface Accept
{
	String deobf();
	String obf();
}
