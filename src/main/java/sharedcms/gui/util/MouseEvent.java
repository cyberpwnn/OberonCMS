package sharedcms.gui.util;

import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;
import sharedcms.mutex.client.mouse.MouseButton;

public class MouseEvent extends Point
{
	public MouseButton button;

	public MouseEvent(int width, int height)
	{
		this(0, 0, 0);
		Mouse.next();
		
		try
		{
			this.button = MouseButton.values()[Mouse.getEventButton() + 1];
		}

		catch(IndexOutOfBoundsException e)
		{
			this.button = MouseButton.NONE;
		}
		
		if(!Mouse.getEventButtonState())
		{
			this.button = MouseButton.NONE;
		}

		Minecraft mc = Minecraft.getMinecraft();
		setX(Mouse.getEventX() * width / mc.displayWidth);
		setY(height - Mouse.getEventY() * height / mc.displayHeight - 1);
	}

	public MouseEvent(int x, int y, int button)
	{
		super(x, y);

		try
		{
			this.button = MouseButton.values()[button + 1];
		}

		catch(IndexOutOfBoundsException e)
		{
			this.button = MouseButton.NONE;
		}
	}

	public MouseButton getButton()
	{
		return button;
	}
}
