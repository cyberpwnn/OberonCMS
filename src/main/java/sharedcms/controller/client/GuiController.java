package sharedcms.controller.client;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.MouseInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import sharedcms.controllable.Controller;
import sharedcms.gui.UIOptions;
import sharedcms.gui.UIWork;
import sharedcms.util.MouseManager;

public class GuiController extends Controller
{
	public static List<Runnable> queue = new ArrayList<Runnable>();

	public GuiController()
	{

	}

	@Override
	public void onPreInitialization()
	{

	}

	@Override
	public void onInitialization()
	{

	}

	@Override
	public void onPostInitialization()
	{

	}

	@SideOnly(Side.CLIENT)
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

	@SideOnly(Side.CLIENT)
	public static void doWork(String title, String message, final Runnable work)
	{
		openGui(new UIWork(title, message)
		{
			@Override
			public void doWork()
			{
				work.run();
			}
		});
	}

	@SideOnly(Side.CLIENT)
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
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void on(ClientTickEvent e)
	{
		MouseManager.poll();
		Keyboard.poll();

		if(Minecraft.getMinecraft().currentScreen instanceof GuiIngameMenu)
		{
			openGui(new UIOptions());
		}

		for(Runnable i : queue)
		{
			i.run();
		}

		queue.clear();
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
	public void on(MouseInputEvent e)
	{
		MouseManager.poll();
	}
}
