package sharedcms.gui;

import java.awt.Color;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import sharedcms.gui.base.LUI;
import sharedcms.gui.component.IButton;
import sharedcms.gui.component.IColorable;
import sharedcms.gui.component.IComponent;
import sharedcms.gui.components.HButton;
import sharedcms.gui.components.HCheckBox;
import sharedcms.gui.components.HLabel;
import sharedcms.gui.components.HPanel;
import sharedcms.gui.components.HSlider;
import sharedcms.gui.util.MouseEvent;
import sharedcms.gui.util.RenderMetadata;
import sharedcms.util.C;
import sharedcms.util.GList;
import sharedcms.util.MouseButton;

public class UIComponentTest extends LUI
{
	private HPanel panel;

	@Override
	public void reconstruct(int w, int h)
	{
		panel = new HPanel(canvas.getScaledX(0.75f), canvas.getScaledY(0.75f));
		canvas.center(panel);
		canvas.add(panel);

		HLabel label4 = new HLabel("Some Form?");
		label4.setFontSize(18);
		label4.setShadowVisible(true);
		panel.add(label4);

		HCheckBox button = new HCheckBox("Check 1");

		button.setColor(Color.WHITE);
		button.setOpacity(0.25);
		button.setFontSize(14);
		button.setButtonPadding(2);
		button.setShadowVisible(true);

		HCheckBox button2 = new HCheckBox("Check 2");

		button2.setColor(Color.WHITE);
		button2.setOpacity(0.25);
		button2.setFontSize(14);
		button2.setButtonPadding(2);
		button2.setShadowVisible(true);

		HSlider slider4 = new HSlider(100, 0, 50);
		slider4.setFontSize(14);
		
		HButton button5 = new HButton("Submit")
		{
			@Override
			public void clicked()
			{
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("Congrats, you fucking clicked a god damn button."));
			}
		};

		button5.setColor(Color.WHITE);
		button5.setOpacity(0.25);
		button5.setFontSize(14);
		button5.setButtonPadding(2);
		button5.setShadowVisible(true);

		HLabel label3 = new HLabel("Please, for the love of god don't submit this.");
		label3.setFontSize(5);
		label3.setShadowVisible(true);

		panel.add(slider4);
		panel.add(button);
		panel.add(button5);
		panel.add(label3);

		panel.getLayout().getMargin().setMarginUp(10);
		panel.getLayout().getMargin().setMarginLeft(10);
		panel.getLayout().getPadding().setPaddingUp(20);
	}

	@Override
	public void draw()
	{
		panel.setColor(C.getRGBShift(0.001));
		panel.setOpacity(0.35);
	}

	@Override
	public void onGuiClose()
	{
		
	}
}
