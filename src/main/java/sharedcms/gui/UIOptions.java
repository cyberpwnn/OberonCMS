package sharedcms.gui;

import java.awt.Color;

import com.google.common.net.HostSpecifier;

import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.block.Block.SoundType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.ChatComponentText;
import sharedcms.Info;
import sharedcms.audio.DSound;
import sharedcms.audio.SFX;
import sharedcms.config.GConfig;
import sharedcms.config.GG;
import sharedcms.config.graphics.GraphicsLevel;
import sharedcms.config.graphics.GraphicsManager;
import sharedcms.content.Content;
import sharedcms.controller.client.ClientController;
import sharedcms.controller.client.GuiController;
import sharedcms.gui.base.LUI;
import sharedcms.gui.component.IComponent;
import sharedcms.gui.components.HButton;
import sharedcms.gui.components.HCheckBox;
import sharedcms.gui.components.HLabel;
import sharedcms.gui.components.HPanel;
import sharedcms.gui.components.HSlider;
import sharedcms.gui.layouts.LinearLayout;
import sharedcms.gui.layouts.LinearLayout.LinearDirection;
import sharedcms.gui.util.Callback;
import sharedcms.gui.util.Point;
import sharedcms.util.C;
import sharedcms.util.M;

public class UIOptions extends LUI
{
	private HPanel sidebar;
	private String last = "none";
	private HSlider sliderSimplex;
	private HSlider sliderVertex;
	private HPanel panelGraphics;
	private HPanel panelGameplay;
	private HPanel panelSound;
	private long lastSave = M.ms();
	private boolean changedSettingRender = false;
	private boolean changedSetting = false;

	@Override
	public void reconstruct(int w, int h)
	{
		canvas.getRecycler().getComponents().clear();
		buildSidebar();
		buildPanels();
		canvas.setLayout(new LinearLayout(LinearDirection.HORIZONTAL));
		canvas.getLayout().getMargin().setMarginLeft(20);
		canvas.getLayout().getPadding().setPaddingLeft(20);
		visibility(last);
	}

	private void buildPanels()
	{
		buildOptionsPanel();
		buildGraphicsPanel();
		buildSoundPanel();
	}

	private void visibility(String panel)
	{
		panelGameplay.setVisible(false);
		panelGraphics.setVisible(false);
		panelSound.setVisible(false);

		if(panel.equals("gameplay"))
		{
			panelGameplay.setVisible(true);
		}

		if(panel.equals("graphics"))
		{
			panelGraphics.setVisible(true);
		}

		if(panel.equals("sound"))
		{
			panelSound.setVisible(true);
		}
	}

	@Override
	public void drawWorldBackground(int p_146270_1_)
	{
		if(this.mc.theWorld != null)
		{
			super.drawWorldBackground(p_146270_1_);
		}
		
		else
		{
			GuiController.renderBackgroundNoWorldPre();
			super.drawBackground(p_146270_1_);
			GuiController.renderBackgroundNoWorld();
		}
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

	private void buildSoundPanel()
	{
		panelSound = new HPanel(canvas.getScaledX(0.70f), canvas.getScaledY(0.9f));
		panelSound.getLayout().getMargin().setMarginUp(20);
		panelSound.getLayout().getMargin().setMarginLeft(5);
		panelSound.getLayout().getPadding().setPaddingUp(15);
		canvas.centerHeight(panelSound);
		canvas.add(panelSound);

		HLabel title = addLabel(panelSound, "Sound Options", 18);

		HPanel panelSep = new HPanel(panelSound.getSize().getX() - (panelSound.getLayout().getMargin().getMarginLeft() * 2), panelSound.getSize().getY() - (panelSound.getSize().getY() / 6));
		panelSep.setLayout(new LinearLayout(LinearDirection.HORIZONTAL));
		panelSep.getLayout().getPadding().setPaddingLeft(5);
		panelSep.setOpacity(0);
		panelSound.add(panelSep);

		HPanel panel1 = new HPanel((panelSep.getSize().getX() / 3) - 5, panelSep.getSize().getY());
		panel1.getLayout().getPadding().setPaddingUp(6);
		panel1.getLayout().getMargin().setMarginUp(5);
		panel1.getLayout().getMargin().setMarginLeft(5);
		panel1.setOpacity(0);
		panelSep.add(panel1);

		HLabel labelMouse = addLabel(panel1, "Master", 12);

		HSlider sliderMaster = new HSlider((int) (panel1.getSize().getX() / 1.5), 0, 100)
		{
			@Override
			public void slid(Point p)
			{
				super.slid(p);
				Minecraft.getMinecraft().gameSettings.setSoundLevel(SoundCategory.MASTER, (float) getValue() / 100f);
			}
		};

		sliderMaster.setValue((int) (Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.MASTER) * 100f));
		sliderMaster.setFontSize(12);
		panel1.add(sliderMaster);

		HLabel labelMusic = addLabel(panel1, "Music", 12);

		HSlider sliderMusic = new HSlider((int) (panel1.getSize().getX() / 1.5), 0, 100)
		{
			@Override
			public void slid(Point p)
			{
				super.slid(p);
				Minecraft.getMinecraft().gameSettings.setSoundLevel(SoundCategory.MUSIC, (float) getValue() / 100f);
				Minecraft.getMinecraft().gameSettings.setSoundLevel(SoundCategory.RECORDS, (float) getValue() / 100f);
			}
		};

		sliderMusic.setValue((int) (Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.MUSIC) * 100f));
		sliderMusic.setFontSize(12);
		panel1.add(sliderMusic);

		HLabel labelSounds = addLabel(panel1, "Sounds", 12);

		HSlider sliderSounds = new HSlider((int) (panel1.getSize().getX() / 1.5), 0, 100)
		{
			@Override
			public void slid(Point p)
			{
				super.slid(p);

				for(SoundCategory i : SoundCategory.values())
				{
					if(i.equals(SoundCategory.MASTER) || i.equals(SoundCategory.MUSIC) || i.equals(SoundCategory.RECORDS))
					{
						continue;
					}

					Minecraft.getMinecraft().gameSettings.setSoundLevel(i, (float) getValue() / 100f);
				}
			}
		};

		sliderSounds.setValue((int) (Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.AMBIENT) * 100f));
		sliderSounds.setFontSize(12);
		panel1.add(sliderSounds);

		HPanel panel2 = new HPanel((panelSep.getSize().getX() / 3) - 5, panelSep.getSize().getY());
		panel2.getLayout().getPadding().setPaddingUp(6);
		panel2.getLayout().getMargin().setMarginUp(5);
		panel2.getLayout().getMargin().setMarginLeft(5);
		panel2.setOpacity(0);
		panelSep.add(panel2);

		HLabel labelReverb = addLabel(panel2, "Reverb Decay", 12);

		HSlider sliderDisplacement = new HSlider((int) (panel1.getSize().getX() / 1.5), 0, 100)
		{
			@Override
			public void slid(Point p)
			{
				super.slid(p);

				Info.REVERB_DECAY = getValue() / 10f;
			}
		};

		sliderDisplacement.setValue((int) (Info.REVERB_DECAY * 10));
		sliderDisplacement.setFontSize(12);
		panel2.add(sliderDisplacement);

		HLabel labelReverbGain = addLabel(panel2, "Reverb Gain", 12);

		HSlider sliderGain = new HSlider((int) (panel1.getSize().getX() / 1.5), 0, 100)
		{
			@Override
			public void slid(Point p)
			{
				super.slid(p);

				Info.REVERB_GAIN = getValue() / 100f;
			}
		};

		sliderGain.setValue((int) (Info.REVERB_GAIN * 100));
		sliderGain.setFontSize(12);
		panel2.add(sliderGain);

		HLabel labelReverbDiff = addLabel(panel2, "Reverb Diffusion", 12);

		HSlider sliderDiff = new HSlider((int) (panel1.getSize().getX() / 1.5), 0, 100)
		{
			@Override
			public void slid(Point p)
			{
				super.slid(p);

				Info.REVERB_DIFFUSION = getValue() / 100f;
			}
		};

		sliderDiff.setValue((int) (Info.REVERB_DIFFUSION * 100));
		sliderDiff.setFontSize(12);
		panel2.add(sliderDiff);

		HPanel panel3 = new HPanel((panelSep.getSize().getX() / 3), panelSep.getSize().getY());
		panel3.getLayout().getPadding().setPaddingUp(6);
		panel3.getLayout().getMargin().setMarginUp(5);
		panel3.getLayout().getMargin().setMarginLeft(5);
		panel3.setOpacity(0);
		panelSep.add(panel3);

		HLabel labelNetwork = addLabel(panel3, "Other", 12);
	}

	private void buildGraphicsPanel()
	{
		panelGraphics = new HPanel(canvas.getScaledX(0.70f), canvas.getScaledY(0.9f));
		panelGraphics.getLayout().getMargin().setMarginUp(20);
		panelGraphics.getLayout().getMargin().setMarginLeft(5);
		panelGraphics.getLayout().getPadding().setPaddingUp(15);
		canvas.centerHeight(panelGraphics);
		canvas.add(panelGraphics);

		HLabel title = addLabel(panelGraphics, "Graphics Options", 18);

		HPanel panelSep = new HPanel(panelGraphics.getSize().getX() - (panelGraphics.getLayout().getMargin().getMarginLeft() * 2), panelGraphics.getSize().getY() - (panelGraphics.getSize().getY() / 6));
		panelSep.setLayout(new LinearLayout(LinearDirection.HORIZONTAL));
		panelSep.getLayout().getPadding().setPaddingLeft(5);
		panelSep.setOpacity(0);
		panelGraphics.add(panelSep);

		HPanel panel1 = new HPanel((panelSep.getSize().getX() / 3) - 5, panelSep.getSize().getY());
		panel1.getLayout().getPadding().setPaddingUp(6);
		panel1.getLayout().getMargin().setMarginUp(5);
		panel1.getLayout().getMargin().setMarginLeft(5);
		panel1.setOpacity(0);
		panelSep.add(panel1);

		HLabel labelDisplay = addLabel(panel1, "Display", 12);

		HSlider sliderRenderSet = new HSlider((int) (panel1.getSize().getX() / 1.2), 0, 4)
		{
			@Override
			public void slid(Point p)
			{
				super.slid(p);
				GraphicsManager.set(getValue());
				changedSettingRender = true;
				setText(GraphicsManager.getLevel().toString());
				changedSetting = true;
			}

			@Override
			public void uslid()
			{
				super.uslid();
				last = "graphics";
				changedSetting = true;
			}
		};

		sliderRenderSet.manualText = true;
		sliderRenderSet.setText(GraphicsManager.getLevel().toString());
		sliderRenderSet.setFontSize(12);
		sliderRenderSet.setValue((Integer) GraphicsManager.getLevel().ordinal());
		panel1.add(sliderRenderSet);

		HCheckBox checkVSync = new HCheckBox("Vertical Sync")
		{
			@Override
			public void clicked()
			{
				super.clicked();
				GG.ENABLE_VSYNC.set(getToggleState());
				changedSetting = true;
			}
		};

		checkVSync.setToggleState((Boolean) GG.ENABLE_VSYNC.get());
		checkVSync.setFontSize(12);
		panel1.add(checkVSync);

		HCheckBox checkViewBob = new HCheckBox("View Bobbing")
		{
			@Override
			public void clicked()
			{
				super.clicked();
				GG.VIEW_BOBBING.set(getToggleState());
				changedSetting = true;
			}
		};

		checkViewBob.setToggleState((Boolean) GG.VIEW_BOBBING.get());
		checkViewBob.setFontSize(12);
		panel1.add(checkViewBob);

		HCheckBox checkFullScreen = new HCheckBox("Full Screen")
		{
			@Override
			public void clicked()
			{
				super.clicked();
				GG.FULL_SCREEN.set(getToggleState());
				changedSetting = true;
			}
		};

		checkFullScreen.setToggleState((Boolean) GG.FULL_SCREEN.get());
		checkFullScreen.setFontSize(12);
		panel1.add(checkFullScreen);

		HCheckBox checkTouch = new HCheckBox("Tablet Mode")
		{
			@Override
			public void clicked()
			{
				super.clicked();
				GG.TOUCHSCREEN.set(getToggleState());
				changedSetting = true;
			}
		};

		checkTouch.setToggleState((Boolean) GG.TOUCHSCREEN.get());
		checkTouch.setFontSize(12);
		panel1.add(checkTouch);

		HLabel labelViewDistance = addLabel(panel1, "View Distance", 9);

		HSlider sliderViewDistance = new HSlider((int) (panel1.getSize().getX() / 1.5), 0, 10)
		{
			@Override
			public void slid(Point p)
			{
				super.slid(p);
				GG.RENDER_DISTANCE_CHUNKS.set((int) getValue());
				changedSetting = true;
			}
		};

		sliderViewDistance.setFontSize(12);
		sliderViewDistance.setValue((Integer) GG.RENDER_DISTANCE_CHUNKS.get());
		panel1.add(sliderViewDistance);

		HLabel labelFOV = addLabel(panel1, "Field of View", 9);

		HSlider sliderFOV = new HSlider((int) (panel1.getSize().getX() / 1.5), 30, 110)
		{
			@Override
			public void slid(Point p)
			{
				super.slid(p);
				GG.FOV_SETTING.set((float) getValue());
				changedSetting = true;
			}
		};

		sliderFOV.setFontSize(12);
		sliderFOV.setValue(((Float) GG.FOV_SETTING.get()).intValue());
		panel1.add(sliderFOV);

		HLabel labelGamma = addLabel(panel1, "Gamma Correction", 9);

		HSlider sliderGamma = new HSlider((int) (panel1.getSize().getX() / 1.5), 0, 100)
		{
			@Override
			public void slid(Point p)
			{
				super.slid(p);
				GG.GAMMA_SETTING.set(((float) getValue()) / 50);
				changedSetting = true;
			}
		};

		sliderGamma.setFontSize(12);
		sliderGamma.setValue((int) (((Float) GG.GAMMA_SETTING.get()).floatValue() * 50f));
		panel1.add(sliderGamma);

		HPanel panel2 = new HPanel((panelSep.getSize().getX() / 3) - 5, panelSep.getSize().getY());
		panel2.getLayout().getPadding().setPaddingUp(6);
		panel2.getLayout().getMargin().setMarginUp(5);
		panel2.getLayout().getMargin().setMarginLeft(5);
		panel2.setOpacity(0);
		panelSep.add(panel2);

		HLabel labelBoxel = addLabel(panel2, "Boxel", 12);

		HCheckBox checkVertexTessellation = new HCheckBox("Vertex")
		{
			@Override
			public void clicked()
			{
				super.clicked();
				Info.TESSELLATION_VERTEX = getToggleState();
				sliderVertex.setVisible(getToggleState());
				changedSetting = true;
			}
		};

		checkVertexTessellation.setToggleState(Info.TESSELLATION_VERTEX);
		checkVertexTessellation.setFontSize(12);
		panel2.add(checkVertexTessellation);

		sliderVertex = new HSlider((int) (panel1.getSize().getX() / 1.5), 0, 250)
		{
			@Override
			public void slid(Point p)
			{
				super.slid(p);
				Info.TESSELLATION_VERTEX_MODIFIER = (float) getValue() / 100f;
				changedSetting = true;
			}
		};

		sliderVertex.setVisible(checkVertexTessellation.getToggleState());
		sliderVertex.setFontSize(12);
		sliderVertex.setValue((int) (Info.TESSELLATION_VERTEX_MODIFIER * 100));
		panel2.add(sliderVertex);

		HCheckBox checkSimplexDist = new HCheckBox("Simplex")
		{
			@Override
			public void clicked()
			{
				super.clicked();
				Info.TESSELLATION_SIMPLEX = getToggleState();
				sliderSimplex.setVisible(getToggleState());
				changedSetting = true;
			}
		};

		checkSimplexDist.setToggleState(Info.TESSELLATION_SIMPLEX);
		checkSimplexDist.setFontSize(12);
		panel2.add(checkSimplexDist);

		sliderSimplex = new HSlider((int) (panel1.getSize().getX() / 1.5), 0, 200)
		{
			@Override
			public void slid(Point p)
			{
				super.slid(p);
				Info.TESSELLATION_SIMPLEX_MODIFIER = (float) getValue() / 100f;
				changedSetting = true;
			}
		};

		sliderSimplex.setVisible(checkSimplexDist.getToggleState());
		sliderSimplex.setFontSize(12);
		sliderSimplex.setValue((int) (Info.TESSELLATION_SIMPLEX_MODIFIER * 100));
		panel2.add(sliderSimplex);

		HPanel panel3 = new HPanel((panelSep.getSize().getX() / 3), panelSep.getSize().getY());
		panel3.getLayout().getPadding().setPaddingUp(6);
		panel3.getLayout().getMargin().setMarginUp(5);
		panel3.getLayout().getMargin().setMarginLeft(5);
		panel3.setOpacity(0);
		panelSep.add(panel3);

		HLabel labelNetwork = addLabel(panel3, "Rendering", 12);
	}

	private void buildOptionsPanel()
	{
		panelGameplay = new HPanel(canvas.getScaledX(0.70f), canvas.getScaledY(0.9f));
		panelGameplay.getLayout().getMargin().setMarginUp(20);
		panelGameplay.getLayout().getMargin().setMarginLeft(5);
		panelGameplay.getLayout().getPadding().setPaddingUp(15);
		canvas.centerHeight(panelGameplay);
		canvas.add(panelGameplay);

		HLabel title = addLabel(panelGameplay, "Gameplay Options", 18);

		HPanel panelSep = new HPanel(panelGameplay.getSize().getX() - (panelGameplay.getLayout().getMargin().getMarginLeft() * 2), panelGameplay.getSize().getY() - (panelGameplay.getSize().getY() / 6));
		panelSep.setLayout(new LinearLayout(LinearDirection.HORIZONTAL));
		panelSep.getLayout().getPadding().setPaddingLeft(5);
		panelSep.setOpacity(0);
		panelGameplay.add(panelSep);

		HPanel panel1 = new HPanel((panelSep.getSize().getX() / 3) - 5, panelSep.getSize().getY());
		panel1.getLayout().getPadding().setPaddingUp(6);
		panel1.getLayout().getMargin().setMarginUp(5);
		panel1.getLayout().getMargin().setMarginLeft(5);
		panel1.setOpacity(0);
		panelSep.add(panel1);

		HLabel labelMouse = addLabel(panel1, "Mouse", 12);

		HCheckBox checkInvertPitch = new HCheckBox("Invert Y Axis")
		{
			@Override
			public void clicked()
			{
				super.clicked();
				GG.INVERT_MOUSE.set(getToggleState());
				changedSetting = true;
			}
		};

		checkInvertPitch.setToggleState((Boolean) GG.INVERT_MOUSE.get());
		checkInvertPitch.setFontSize(12);
		panel1.add(checkInvertPitch);

		HCheckBox checkSmoothLook = new HCheckBox("Smooth Look")
		{
			@Override
			public void clicked()
			{
				super.clicked();
				GG.SMOOTH_CAMERA.set(getToggleState());
				changedSetting = true;
			}
		};

		checkSmoothLook.setToggleState((Boolean) GG.SMOOTH_CAMERA.get());
		checkSmoothLook.setFontSize(12);
		panel1.add(checkSmoothLook);

		HLabel labelSensitivity = addLabel(panel1, "Sensitivity", 9);

		HSlider sliderMouse = new HSlider((int) (panel1.getSize().getX() / 1.5), 0, 100)
		{
			@Override
			public void slid(Point p)
			{
				super.slid(p);
				GG.MOUSE_SENSITIVITY.set(((float) getValue()) / 100);
				changedSetting = true;
			}
		};

		sliderMouse.setFontSize(12);
		sliderMouse.setValue((int) (((Float) GG.MOUSE_SENSITIVITY.get()).floatValue() * 100f));
		panel1.add(sliderMouse);

		HPanel panel2 = new HPanel((panelSep.getSize().getX() / 3) - 5, panelSep.getSize().getY());
		panel2.getLayout().getPadding().setPaddingUp(6);
		panel2.getLayout().getMargin().setMarginUp(5);
		panel2.getLayout().getMargin().setMarginLeft(5);
		panel2.setOpacity(0);
		panelSep.add(panel2);

		HLabel labelMore = addLabel(panel2, "Misc", 12);

		HPanel panel3 = new HPanel((panelSep.getSize().getX() / 3), panelSep.getSize().getY());
		panel3.getLayout().getPadding().setPaddingUp(6);
		panel3.getLayout().getMargin().setMarginUp(5);
		panel3.getLayout().getMargin().setMarginLeft(5);
		panel3.setOpacity(0);
		panelSep.add(panel3);

		HLabel labelNetwork = addLabel(panel3, "Networking", 12);

		HCheckBox checkPromptOnLinks = new HCheckBox("Prompt on Links")
		{
			@Override
			public void clicked()
			{
				super.clicked();
				GG.CHAT_LINKS_PROMPT.set(getToggleState());
				changedSetting = true;
			}
		};

		checkPromptOnLinks.setToggleState((Boolean) GG.CHAT_LINKS_PROMPT.get());
		checkPromptOnLinks.setFontSize(12);
		panel3.add(checkPromptOnLinks);

		HCheckBox checkSnoop = new HCheckBox("Mojang Snooper")
		{
			@Override
			public void clicked()
			{
				super.clicked();
				GG.SNOOPER_ENABLED.set(getToggleState());
				changedSetting = true;
			}
		};

		checkSnoop.setToggleState((Boolean) GG.SNOOPER_ENABLED.get());
		checkSnoop.setFontSize(12);
		panel3.add(checkSnoop);

		HCheckBox checkLimitUp = new HCheckBox("Limit Up Rate")
		{
			@Override
			public void clicked()
			{
				super.clicked();
				// TODO save this shit
			}
		};

		checkLimitUp.setToggleState(false);
		checkLimitUp.setFontSize(12);
		panel3.add(checkLimitUp);

		HCheckBox checkLimitDown = new HCheckBox("Limit Down Rate")
		{
			@Override
			public void clicked()
			{
				super.clicked();
				// TODO save this shit
			}
		};

		checkLimitDown.setToggleState(false);
		checkLimitDown.setFontSize(12);
		panel3.add(checkLimitDown);

		HLabel labelAdditional = addLabel(panel3, "System", 12);

		HCheckBox checkChunkCaching = new HCheckBox("Use CCache")
		{
			@Override
			public void clicked()
			{
				super.clicked();
				// TODO save this shit
			}
		};

		checkChunkCaching.setToggleState(true);
		checkChunkCaching.setFontSize(12);
		panel3.add(checkChunkCaching);

		HCheckBox checkUseMalloc = new HCheckBox("Use Malloc")
		{
			@Override
			public void clicked()
			{
				super.clicked();
				// TODO save this shit
			}
		};

		checkUseMalloc.setToggleState(false);
		checkUseMalloc.setFontSize(12);
		panel3.add(checkUseMalloc);

		HCheckBox checkP2P = new HCheckBox("Use P2P Data")
		{
			@Override
			public void clicked()
			{
				super.clicked();
				// TODO save this shit
			}
		};

		checkP2P.setToggleState(false);
		checkP2P.setFontSize(12);
		panel3.add(checkP2P);
	}

	private void buildSidebar()
	{
		sidebar = new HPanel(canvas.getScaledX(0.15f), canvas.getScaledY(0.9f));
		sidebar.getLayout().getMargin().setMarginUp(20);
		sidebar.getLayout().getMargin().setMarginLeft(5);
		sidebar.getLayout().getPadding().setPaddingUp(15);
		canvas.centerHeight(sidebar);
		canvas.add(sidebar);

		HLabel optionsTitle = addLabel(sidebar, "Options", 18);

		HButton buttonOptions = new HButton("Gameplay")
		{
			@Override
			public void clicked()
			{
				visibility("gameplay");
			}
		};

		buttonOptions.setFontSize(14);
		sidebar.add(buttonOptions);

		HButton buttonGraphics = new HButton("Graphics")
		{
			@Override
			public void clicked()
			{
				visibility("graphics");
			}
		};

		buttonGraphics.setFontSize(14);
		sidebar.add(buttonGraphics);

		HButton buttonSound = new HButton("Sound")
		{
			@Override
			public void clicked()
			{
				visibility("sound");
			}
		};

		buttonSound.setFontSize(14);
		sidebar.add(buttonSound);

		String k = "Quit Game";

		if(mc.theWorld == null)
		{
			k = "Back";
		}

		HButton buttonQuit = new HButton(k)
		{
			@Override
			public void clicked()
			{
				GuiController.exitToMainMenu();
			}
		};

		buttonQuit.setFontSize(14);
		sidebar.add(buttonQuit);
	}

	@Override
	public void draw()
	{
		sidebar.setColor(Color.black);
		panelGameplay.setColor(Color.black);
		panelGraphics.setColor(Color.black);
		panelSound.setColor(Color.black);

		sidebar.setOpacity(0.45);
		panelGameplay.setOpacity(0.45);
		panelGraphics.setOpacity(0.45);
		panelSound.setOpacity(0.45);
	}

	@Override
	public void onGuiClose()
	{
		Info.save();

		if(changedSetting)
		{
			GConfig.forceSaveALL();
			GG.forceRefreshChunks();
		}

		if(changedSettingRender)
		{
			Info.save();
			GraphicsManager.apply();
		}
	}
}
