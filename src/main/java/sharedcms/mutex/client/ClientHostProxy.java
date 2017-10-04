package sharedcms.mutex.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.MouseInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import sharedcms.gui.UIOptions;
import sharedcms.gui.UIWork;
import sharedcms.mutex.client.mouse.MouseManager;
import sharedcms.proxy.IProxy;
import sharedcms.renderer.blur.Blur;

public class ClientHostProxy implements IProxy
{
	private CharacterManager cm;
	private Blur b;
	public static List<Runnable> queue = new ArrayList<Runnable>();
	public static Map<UUID, NBTTagCompound> trackedEntities = new HashMap<UUID, NBTTagCompound>();

	@Override
	public void onPreInit(FMLPreInitializationEvent e)
	{
		b = new Blur();
		cm = new CharacterManager();
		cm.onPreInit(e);
		b.preInit(e);
		MinecraftForge.EVENT_BUS.register(cm);
		FMLCommonHandler.instance().bus().register(cm);
	}

	public static void openGui(final GuiScreen s)
	{
		queue.add(new Runnable()
		{
			@Override
			public void run()
			{
				Minecraft.getMinecraft().displayGuiScreen(s);
			}
		});
	}

	public static void doWork(String title, String message, final Runnable work)
	{
		openGui(new UIWork(title, message)
		{
			@Override
			public void doWork()
			{
				try
				{
					Thread.sleep(500);
				}

				catch(InterruptedException e)
				{

				}

				work.run();

				try
				{
					Thread.sleep(500);
				}

				catch(InterruptedException e)
				{

				}
			}
		});
	}

	public static void closeGui()
	{
		queue.add(new Runnable()
		{
			@Override
			public void run()
			{
				Minecraft.getMinecraft().thePlayer.closeScreenNoPacket();
			}
		});
	}

	@Override
	public void onInit(FMLInitializationEvent e)
	{
		cm.onInit(e);
	}

	@Override
	public void onPostInit(FMLPostInitializationEvent e)
	{
		cm.onPostInit(e);
		cm.onPostInit(e);
		System.out.println("CLIENT HOST ONLINE");
	}

	@SubscribeEvent
	public void on(ClientTickEvent e)
	{
		MouseManager.poll();
		Keyboard.poll();
		retrackEntities();
		
		if(Minecraft.getMinecraft().currentScreen instanceof GuiIngameMenu)
		{
			ClientHostProxy.openGui(new UIOptions());
		}

		for(Runnable i : queue)
		{
			i.run();
		}

		queue.clear();
	}

	private void retrackEntities()
	{
		EntityPlayer e = Minecraft.getMinecraft().thePlayer;
		
		if(e != null)
		{
			
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
	public void on(MouseInputEvent e)
	{
		MouseManager.poll();
	}

	
}
