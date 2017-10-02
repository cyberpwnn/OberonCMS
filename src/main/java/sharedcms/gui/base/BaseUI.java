package sharedcms.gui.base;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;

public abstract class BaseUI extends GuiScreen
{
	public static int w = 0;
	public static int h = 0;
	private boolean pausesGame;

	public BaseUI()
	{
		pausesGame = false;
	}

	public abstract void redraw(int w, int h);

	@Override
	public void drawScreen(int x, int y, float t)
	{
		BaseUI.w = x;
		BaseUI.h = y;
		drawDefaultBackground();
		redraw(width, height);
		super.drawScreen(x, y, t);
	}

	@Override
	public abstract void onGuiClosed();

	@Override
	public boolean doesGuiPauseGame()
	{
		return pausesGame;
	}
}
