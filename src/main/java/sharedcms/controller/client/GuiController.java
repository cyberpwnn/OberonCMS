package sharedcms.controller.client;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.MouseInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.Tessellator;
import sharedcms.controllable.Controller;
import sharedcms.gui.UIMainMenu;
import sharedcms.gui.UIOptions;
import sharedcms.gui.UIWork;
import sharedcms.util.MouseManager;

public class GuiController extends Controller
{
	private static double shx = 0;
	private static double shy = 0;
	private static double ashx = 0;
	private static double ashy = 0;
	private boolean first = true;
	public static List<Runnable> queue = new ArrayList<Runnable>();

	public GuiController()
	{
		Display.setTitle("Project Oberon");
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
	public static void renderBackgroundNoWorld()
	{
		int w = Minecraft.getMinecraft().displayWidth;
		int h = Minecraft.getMinecraft().displayHeight;
		GL11.glScalef(1f / 4f, 1f / 4f, 1f / 4f);
		GL11.glTranslated(-ashx, -ashy, 0);
	}

	@SideOnly(Side.CLIENT)
	public static void renderBackgroundNoWorldPre()
	{
		int w = Minecraft.getMinecraft().displayWidth;
		int h = Minecraft.getMinecraft().displayHeight;

		shx = ((-MouseManager.X + (w)) / 6f) - (w);
		shy = ((MouseManager.Y - (h)) / 6f) - (h / 4);

		if(ashy == 0 && ashx == 0)
		{
			ashx = shx;
			ashy = shy;
		}

		if(ashx > shx)
		{
			ashx -= (ashx - shx) / 40.0;
		}

		if(ashx < shx)
		{
			ashx += (shx - ashx) / 40.0;
		}

		if(ashy > shy)
		{
			ashy -= (ashy - shy) / 40.0;
		}

		if(ashy < shy)
		{
			ashy += (shy - ashy) / 40.0;
		}

		GL11.glTranslated(ashx, ashy, 0);
		GL11.glScalef(4f, 4f, 4f);
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
	public static void showMainMenu()
	{
		Minecraft mc = Minecraft.getMinecraft();
		mc.displayGuiScreen(new UIMainMenu());
	}

	@SideOnly(Side.CLIENT)
	public static void exitToMainMenu()
	{
		Minecraft mc = Minecraft.getMinecraft();

		if(mc.theWorld == null)
		{
			showMainMenu();
			return;
		}

		mc.theWorld.sendQuittingDisconnectingPacket();
		mc.loadWorld((WorldClient) null);
		showMainMenu();
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void on(ClientTickEvent e)
	{
		MouseManager.poll();
		Keyboard.poll();

		if(first)
		{
			showMainMenu();
			first = false;
		}

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
