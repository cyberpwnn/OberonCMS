package sharedcms.asm;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.lwjgl.opengl.Display;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import sharedcms.content.world.generator.SimplexOctaveGenerator;
import sharedcms.util.F;
import sharedcms.util.GEN;
import sharedcms.util.SimplexProperties;
import sharedcns.api.biome.Drawer;
import sharedcns.api.biome.ImageDrawer;

@MCVersion("1.7.10")
@IFMLLoadingPlugin.TransformerExclusions({"sharedcms.asm"})
public class ASMHandler implements IFMLLoadingPlugin
{
	public static boolean r = false;
	public static boolean IN_MCP = false;
	private List<String> k;

	public String[] getASMTransformerClass()
	{
		System.out.println("=================== ASM ===================");
		k = new ArrayList<String>();
		System.out.println("Building Injector list");
		buildInjectors();
		// doTests();

		return k.toArray(new String[k.size()]);
	}

	public void doTests()
	{
		if(r)
		{
			return;
		}

		r = true;

		long seed = 50;

		Random r = new Random(50);
		final int[] prog = {0};

		ImageDrawer id = new ImageDrawer(1000, 1000)
		{
			@Override
			public void draw(Graphics g)
			{
				System.out.println("Draw Cities & Villages");

				for(int i = 0; i < 1000; i++)
				{
					for(int j = 0; j < 1000; j++)
					{
						g.setColor(Color.BLACK);
						g.fillRect(i, j, i + 1, j + 1);

						prog[0]++;
					}

					if(i % 100 == 0)
					{
						System.out.println(F.pc(prog[0] / (double) (1000 * 1000)));

						if(prog[0] > (1000 * 1000))
						{
							prog[0] = 0;
						}
					}
				}
			}
		};

		Drawer d = new Drawer(id);
		try
		{
			Thread.sleep(100000);
		}
		catch(InterruptedException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void buildInjectors()
	{
		add("InjectorMinecraftStartGame");
		add("InjectorMinecraftLimitFramerate");
		add("InjectorBlock");
		add("InjectorBlockCactus");
		add("InjectorRenderer");
		add("InjectorCamera");
		add("InjectorGuiScreenBackground");
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
