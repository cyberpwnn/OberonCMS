package sharedcms.gui;

import java.awt.Color;

import sharedcms.controller.client.GuiController;
import sharedcms.gui.base.LUI;
import sharedcms.gui.component.IComponent;
import sharedcms.gui.components.HLabel;
import sharedcms.gui.components.HPanel;

public abstract class UIWork extends LUI
{
	private String title;
	private String message;
	private boolean startedWork;
	private int tick = 0;
	
	public UIWork(String title, String message)
	{
		super();
		
		this.title = title;
		this.message = message;
		startedWork = false;
	}
	
	@Override
	public void reconstruct(int w, int h)
	{
		
		HPanel panel = new HPanel(canvas.getScaledX(0.5f), canvas.getScaledY(0.25f));
		panel.getLayout().getMargin().setMarginUp(10);
		panel.getLayout().getMargin().setMarginLeft(10);
		panel.getLayout().getPadding().setPaddingUp(10);
		panel.setColor(Color.black);
		panel.setOpacity(0.45f);
		canvas.center(panel);
		canvas.add(panel);
		addLabel(panel, title, 18);
		addLabel(panel, message, 12);
	}
	
	private HLabel addLabel(IComponent parent, String name, int font)
	{
		HLabel h = new HLabel(name);
		h.setFontSize(font);
		h.setTextColor(Color.orange);
		h.setShadowColor(Color.black);
		
		parent.add(h);

		return h;
	}

	@Override
	public void draw()
	{
		tick++;
		
		canvas.drawComponents();
		
		if(!startedWork && tick > 10)
		{
			startedWork = true;
			doWork();
			GuiController.closeGui();
		}
	}
	
	public abstract void doWork();

	@Override
	public void onGuiClose()
	{
		
	}
}
