package sharedcms;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import sharedcms.command.CommandCMS;
import sharedcms.config.GG;
import sharedcms.controllable.ControllerManager;
import sharedcms.controller.client.AnimationController;
import sharedcms.controller.client.AudioController;
import sharedcms.controller.client.BackgroundBlurController;
import sharedcms.controller.client.CameraController;
import sharedcms.controller.client.ChunkAnimationController;
import sharedcms.controller.client.ClientController;
import sharedcms.controller.client.FXController;
import sharedcms.controller.client.GuiController;
import sharedcms.controller.client.HudController;
import sharedcms.controller.client.SFXController;
import sharedcms.controller.client.VFXController;
import sharedcms.controller.server.PlayerMovementController;
import sharedcms.controller.shared.BoxelController;
import sharedcms.controller.shared.ContentController;
import sharedcms.controller.shared.NetworkController;
import sharedcms.controller.shared.WorldHostController;
import sharedcms.json.JSONObject;
import sharedcms.proxy.IProxy;
import sharedcms.proxy.ProxyCommon;
import sharedcms.registry.IRegistrant;

@Mod(modid = Info.ID, version = Info.VERSION, name = Info.NAME)
public class Ares implements IProxy, IRegistrant
{
	@Instance(Info.ID)
	public static Ares instance;

	@SidedProxy(clientSide = Info.PROXY_CLIENT, serverSide = Info.PROXY_SERVER)
	public static ProxyCommon proxy;

	public ControllerManager manager;
	public static Side side;

	@Override
	@EventHandler
	public void onPreInit(FMLPreInitializationEvent e)
	{
		side = e.getSide();
		L.k = e.getModLog();
		proxy.onPreInit(e);
		controlMinecraft(e.getSide());
		manager.onPreInit(e);
	}

	private void controlMinecraft(Side side)
	{
		manager = new ControllerManager(side)
		{
			@Override
			public void buildControlledServer()
			{
				register(new PlayerMovementController());
			}

			@Override
			public void buildControlledLink()
			{
				register(new BoxelController());
				register(new ContentController());
				register(new NetworkController());
				register(new WorldHostController());
			}

			@Override
			public void buildControlledClient()
			{
				register(new AudioController());
				register(new CameraController());
				register(new AnimationController());
				register(new HudController());
				register(new BackgroundBlurController());
				register(new GuiController());
				register(new ClientController());
				register(new FXController());
				register(new VFXController());
				register(new SFXController());
				register(new ChunkAnimationController());
			}
		};
	}

	public InputStream rcl(String r) throws IOException
	{
		return Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("sharedcms", r)).getInputStream();
	}

	public InputStream rse(String r)
	{
		return MinecraftServer.class.getResourceAsStream("/assets/sharedcms/" + r);
	}

	private JSONObject readJSONFromJar(String resourceName, Side side)
	{
		try
		{
			InputStream stream = null;

			if(side.equals(Side.CLIENT))
			{
				stream = rcl(resourceName);
			}

			else
			{
				stream = rse(resourceName);
			}

			ByteArrayOutputStream resStreamOut = null;
			String jarFolder;

			if(stream == null)
			{
				throw new Exception("Cannot get resource \"" + resourceName + "\" from Jar file.");
			}

			int readBytes;
			byte[] buffer = new byte[4096];
			resStreamOut = new ByteArrayOutputStream();

			while((readBytes = stream.read(buffer)) > 0)
			{
				resStreamOut.write(buffer, 0, readBytes);
			}

			stream.close();

			return new JSONObject(new String(resStreamOut.toByteArray(), StandardCharsets.UTF_8));
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	private JSONObject readFileToJSON(File file)
	{
		try
		{
			String sx = "";

			FileInputStream fin = new FileInputStream(file);
			InputStreamReader is = new InputStreamReader(fin);
			BufferedReader bu = new BufferedReader(is);

			String line;

			while((line = bu.readLine()) != null)
			{
				sx += line;
			}

			bu.close();

			return new JSONObject(sx);
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	@EventHandler
	public void on(FMLServerStartingEvent e)
	{
		e.registerServerCommand(new CommandCMS());
	}

	@Override
	@EventHandler
	public void onInit(FMLInitializationEvent e)
	{
		proxy.onInit(e);
		manager.onInit(e);
	}

	@Override
	@EventHandler
	public void onPostInit(FMLPostInitializationEvent e)
	{
		proxy.onPostInit(e);
		manager.onPostInit(e);

		if(e.getSide().equals(Side.CLIENT))
		{
			System.out.println("================ VALID ================");

			for(GG gg : GG.gg())
			{
				gg.print();
			}

			System.out.println("================ UNKNOWN ================");

			for(GG ii : GG.ii())
			{
				ii.print();
			}
		}
	}

	@Override
	public void onPreRegister(ContentController cms, Side side)
	{

	}
}
