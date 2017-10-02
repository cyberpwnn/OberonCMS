package sharedcms.mutex.client.mouse;

import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;
import sharedcms.gui.util.Point;
import sharedcms.util.GList;

public class MouseManager
{
	private static GList<MouseAdapter> adapters = new GList<MouseAdapter>();
	public static int LX = 0;
	public static int LY = 0;
	public static int X = 0;
	public static int Y = 0;
	public static MouseButton BUTTON = MouseButton.NONE;
	public static MouseState STATE = MouseState.HOVERING;
	public static MouseState LAST_STATE = MouseState.HOVERING;
	public static boolean MOVING = false;
	public static boolean DOWN = false;
	public static boolean CHANGED = false;
	public static String RAW = "?";

	public static void registerMouseAdapter(MouseAdapter a)
	{
		adapters.add(a);
	}

	public static void removeMouseAdapter(MouseAdapter a)
	{
		adapters.remove(a);
	}

	public static void poll()
	{
		X = Mouse.getEventX();
		Y = Mouse.getEventY();
		MOVING = X != LX || Y != LY;
		DOWN = getButtonDown();
		BUTTON = getButton();
		LAST_STATE = STATE;

		if(BUTTON.equals(MouseButton.NONE) && LAST_STATE.equals(MouseState.CLICKING_UP) && MOVING)
		{
			STATE = MouseState.HOVERING;
		}
		
		if(!BUTTON.equals(MouseButton.NONE) && LAST_STATE.equals(MouseState.CLICKING_DOWN) && MOVING)
		{
			STATE = MouseState.DRAGGING;
		}
		
		if(BUTTON.equals(MouseButton.NONE) && (LAST_STATE.equals(MouseState.DRAGGING) || LAST_STATE.equals(MouseState.CLICKING_DOWN)))
		{
			STATE = MouseState.CLICKING_UP;
		}
		
		if(!BUTTON.equals(MouseButton.NONE) && (LAST_STATE.equals(MouseState.HOVERING) || LAST_STATE.equals(MouseState.CLICKING_UP)))
		{
			STATE = MouseState.CLICKING_DOWN;
		}

		CHANGED = !RAW.equals(getState());
		RAW = getState();

		if(CHANGED)
		{
			for(MouseAdapter i : adapters.copy())
			{
				i.onMouseStateChanged();
			}
		}
	}

	private static boolean getButtonDown()
	{
		for(int i = 0; i < Mouse.getButtonCount(); i++)
		{
			if(Mouse.isButtonDown(i))
			{
				return true;
			}
		}

		return false;
	}

	private static MouseButton getButton()
	{
		if(DOWN)
		{
			for(int i = 0; i < Mouse.getButtonCount(); i++)
			{
				if(Mouse.isButtonDown(i))
				{
					try
					{
						return MouseButton.values()[i + 1];
					}

					catch(Exception e)
					{
						return MouseButton.NONE;
					}
				}
			}
		}

		return MouseButton.NONE;
	}

	public static String getState()
	{
		return X + "" + LX + "" + Y + "" + LY + "" + "" + LAST_STATE.ordinal() + "" + STATE.ordinal() + "" + BUTTON.ordinal() + "" + (DOWN ? 1 : 0) + "" + (MOVING ? 1 : 0);
	}

	public static void report()
	{
		if(!CHANGED)
		{
			return;
		}
		
		if(STATE.equals(MouseState.HOVERING))
		{
			System.out.println("The mouse is " + STATE.toString().replaceAll("_", " ").toLowerCase() + " at " + X + ", " + Y);
		}

		else
		{
			System.out.println("The mouse is " + STATE.toString().replaceAll("_", " ").toLowerCase() + " the " + BUTTON.toString().replaceAll("_", " ").toLowerCase() + " mouse button at " + X + ", " + Y);
		}
	}

	public static Point getPointer(int width, int height)
	{
		Minecraft mc = Minecraft.getMinecraft();
		return new Point(Mouse.getEventX() * width / mc.displayWidth, height - Mouse.getEventY() * height / mc.displayHeight - 1);
	}
}
