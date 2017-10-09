package sharedcms.asm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;

@MCVersion("1.7.10")
@IFMLLoadingPlugin.TransformerExclusions({"sharedcms.asm"})
public class ASMHandler implements IFMLLoadingPlugin
{
	public static boolean IN_MCP = false;
	private List<String> k;

	public String[] getASMTransformerClass()
	{
		System.out.println("=================== ASM ===================");
		k = new ArrayList<String>();
		System.out.println("Building Injector list");
		buildInjectors();

		return k.toArray(new String[k.size()]);
	}

	public void buildInjectors()
	{
		add("InjectorBlock");
		add("InjectorBlockCactus");
		add("InjectorRenderer");
		add("InjectorCamera");
		add("InjectorBlurShader");
		add("InjectorWorldRenderer");
	}

	private void add(String name)
	{
		k.add("sharedcms.asm." + name);
		System.out.println(" ===>>> PREPARE " + name);
	}

	public String getModContainerClass()
	{
		return null;
	}

	public String getSetupClass()
	{
		return null;
	}

	public void injectData(Map<String, Object> data)
	{
		IN_MCP = (Boolean) data.get("runtimeDeobfuscationEnabled") == false;
	}

	public String getAccessTransformerClass()
	{
		return null;
	}
}
