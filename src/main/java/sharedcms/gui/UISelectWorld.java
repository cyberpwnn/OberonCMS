package sharedcms.gui;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.StartupQuery;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiErrorScreen;
import net.minecraft.client.gui.GuiRenameWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveFormatComparator;
import net.minecraft.world.storage.WorldInfo;

@SideOnly(Side.CLIENT)
public class UISelectWorld extends GuiScreen implements GuiYesNoCallback
{
	private static final Logger logger = LogManager.getLogger();
	private final DateFormat field_146633_h = new SimpleDateFormat();
	protected GuiScreen field_146632_a;
	protected String field_146628_f = "Select world";
	private boolean field_146634_i;
	private int field_146640_r;
	private java.util.List field_146639_s;
	private UISelectWorld.List field_146638_t;
	private String field_146637_u;
	private String field_146636_v;
	private String[] field_146635_w = new String[3];
	private boolean field_146643_x;
	private GuiButton field_146642_y;
	private GuiButton field_146641_z;
	private GuiButton field_146630_A;
	private GuiButton field_146631_B;
	private static final String __OBFID = "CL_00000711";

	public UISelectWorld(GuiScreen p_i1054_1_)
	{
		this.field_146632_a = p_i1054_1_;
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	public void initGui()
	{
		this.field_146628_f = I18n.format("selectWorld.title", new Object[0]);

		try
		{
			this.func_146627_h();
		}
		catch(AnvilConverterException anvilconverterexception)
		{
			logger.error("Couldn\'t load level list", anvilconverterexception);
			this.mc.displayGuiScreen(new GuiErrorScreen("Unable to load worlds", anvilconverterexception.getMessage()));
			return;
		}

		this.field_146637_u = I18n.format("selectWorld.world", new Object[0]);
		this.field_146636_v = I18n.format("selectWorld.conversion", new Object[0]);
		this.field_146635_w[WorldSettings.GameType.SURVIVAL.getID()] = I18n.format("gameMode.survival", new Object[0]);
		this.field_146635_w[WorldSettings.GameType.CREATIVE.getID()] = I18n.format("gameMode.creative", new Object[0]);
		this.field_146635_w[WorldSettings.GameType.ADVENTURE.getID()] = I18n.format("gameMode.adventure", new Object[0]);
		this.field_146638_t = new UISelectWorld.List();
		this.field_146638_t.registerScrollButtons(4, 5);
		this.func_146618_g();
	}

	private void func_146627_h() throws AnvilConverterException
	{
		ISaveFormat isaveformat = this.mc.getSaveLoader();
		this.field_146639_s = isaveformat.getSaveList();
		Collections.sort(this.field_146639_s);
		this.field_146640_r = -1;
	}

	protected String func_146621_a(int p_146621_1_)
	{
		return ((SaveFormatComparator) this.field_146639_s.get(p_146621_1_)).getFileName();
	}

	protected String func_146614_d(int p_146614_1_)
	{
		String s = ((SaveFormatComparator) this.field_146639_s.get(p_146614_1_)).getDisplayName();

		if(s == null || MathHelper.stringNullOrLengthZero(s))
		{
			s = I18n.format("selectWorld.world", new Object[0]) + " " + (p_146614_1_ + 1);
		}

		return s;
	}

	public void func_146618_g()
	{
		this.buttonList.add(this.field_146641_z = new GuiButton(1, this.width / 2 - 154, this.height - 52, 150, 20, I18n.format("selectWorld.select", new Object[0])));
		this.buttonList.add(new GuiButton(3, this.width / 2 + 4, this.height - 52, 150, 20, I18n.format("selectWorld.create", new Object[0])));
		this.buttonList.add(this.field_146630_A = new GuiButton(6, this.width / 2 - 154, this.height - 28, 72, 20, I18n.format("selectWorld.rename", new Object[0])));
		this.buttonList.add(this.field_146642_y = new GuiButton(2, this.width / 2 - 76, this.height - 28, 72, 20, I18n.format("selectWorld.delete", new Object[0])));
		this.buttonList.add(this.field_146631_B = new GuiButton(7, this.width / 2 + 4, this.height - 28, 72, 20, I18n.format("selectWorld.recreate", new Object[0])));
		this.buttonList.add(new GuiButton(0, this.width / 2 + 82, this.height - 28, 72, 20, I18n.format("gui.cancel", new Object[0])));
		this.field_146641_z.enabled = false;
		this.field_146642_y.enabled = false;
		this.field_146630_A.enabled = false;
		this.field_146631_B.enabled = false;
	}

	protected void actionPerformed(GuiButton p_146284_1_)
	{
		if(p_146284_1_.enabled)
		{
			if(p_146284_1_.id == 2)
			{
				String s = this.func_146614_d(this.field_146640_r);

				if(s != null)
				{
					this.field_146643_x = true;
					GuiYesNo guiyesno = func_152129_a(this, s, this.field_146640_r);
					this.mc.displayGuiScreen(guiyesno);
				}
			}
			else if(p_146284_1_.id == 1)
			{
				this.func_146615_e(this.field_146640_r);
			}
			else if(p_146284_1_.id == 3)
			{
				this.mc.displayGuiScreen(new UIGuiCreateWorld(this));
			}
			else if(p_146284_1_.id == 6)
			{
				this.mc.displayGuiScreen(new GuiRenameWorld(this, this.func_146621_a(this.field_146640_r)));
			}
			else if(p_146284_1_.id == 0)
			{
				this.mc.displayGuiScreen(this.field_146632_a);
			}
			else if(p_146284_1_.id == 7)
			{
				GuiCreateWorld guicreateworld = new GuiCreateWorld(this);
				ISaveHandler isavehandler = this.mc.getSaveLoader().getSaveLoader(this.func_146621_a(this.field_146640_r), false);
				WorldInfo worldinfo = isavehandler.loadWorldInfo();
				isavehandler.flush();
				guicreateworld.func_146318_a(worldinfo);
				this.mc.displayGuiScreen(guicreateworld);
			}
			else
			{
				this.field_146638_t.actionPerformed(p_146284_1_);
			}
		}
	}

	public void func_146615_e(int p_146615_1_)
	{
		this.mc.displayGuiScreen((GuiScreen) null);

		if(!this.field_146634_i)
		{
			this.field_146634_i = true;
			String s = this.func_146621_a(p_146615_1_);

			if(s == null)
			{
				s = "World" + p_146615_1_;
			}

			String s1 = this.func_146614_d(p_146615_1_);

			if(s1 == null)
			{
				s1 = "World" + p_146615_1_;
			}

			if(this.mc.getSaveLoader().canLoadWorld(s))
			{
				tryLoadExistingWorld(s, s1);
			}
		}
	}

	public void tryLoadExistingWorld(String dirName, String saveName)
	{
		File dir = new File(new File(mc.mcDataDir, "saves"), dirName);
		NBTTagCompound leveldat;
		try
		{
			leveldat = CompressedStreamTools.readCompressed(new FileInputStream(new File(dir, "level.dat")));
		}
		catch(Exception e)
		{
			try
			{
				leveldat = CompressedStreamTools.readCompressed(new FileInputStream(new File(dir, "level.dat_old")));
			}
			catch(Exception e1)
			{
				FMLLog.warning("There appears to be a problem loading the save %s, both level files are unreadable.", dirName);
				return;
			}
		}
		NBTTagCompound fmlData = leveldat.getCompoundTag("FML");
		try
		{
			mc.launchIntegratedServer(dirName, saveName, (WorldSettings) null);
		}

		catch(StartupQuery.AbortedException e)
		{
			// ignore
		}
	}

	public void confirmClicked(boolean p_73878_1_, int p_73878_2_)
	{
		if(this.field_146643_x)
		{
			this.field_146643_x = false;

			if(p_73878_1_)
			{
				ISaveFormat isaveformat = this.mc.getSaveLoader();
				isaveformat.flushCache();
				isaveformat.deleteWorldDirectory(this.func_146621_a(p_73878_2_));

				try
				{
					this.func_146627_h();
				}
				catch(AnvilConverterException anvilconverterexception)
				{
					logger.error("Couldn\'t load level list", anvilconverterexception);
				}
			}

			this.mc.displayGuiScreen(this);
		}
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
	{
		this.field_146638_t.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
		this.drawCenteredString(this.fontRendererObj, this.field_146628_f, this.width / 2, 20, 16777215);
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}

	public static GuiYesNo func_152129_a(GuiYesNoCallback p_152129_0_, String p_152129_1_, int p_152129_2_)
	{
		String s1 = I18n.format("selectWorld.deleteQuestion", new Object[0]);
		String s2 = "\'" + p_152129_1_ + "\' " + I18n.format("selectWorld.deleteWarning", new Object[0]);
		String s3 = I18n.format("selectWorld.deleteButton", new Object[0]);
		String s4 = I18n.format("gui.cancel", new Object[0]);
		GuiYesNo guiyesno = new GuiYesNo(p_152129_0_, s1, s2, s3, s4, p_152129_2_);
		return guiyesno;
	}

	@SideOnly(Side.CLIENT)
	class List extends GuiSlot
	{
		private static final String __OBFID = "CL_00000712";

		public List()
		{
			super(UISelectWorld.this.mc, UISelectWorld.this.width, UISelectWorld.this.height, 32, UISelectWorld.this.height - 64, 36);
		}

		protected int getSize()
		{
			return UISelectWorld.this.field_146639_s.size();
		}

		/**
		 * The element in the slot that was clicked, boolean for whether it was double
		 * clicked or not
		 */
		protected void elementClicked(int p_148144_1_, boolean p_148144_2_, int p_148144_3_, int p_148144_4_)
		{
			UISelectWorld.this.field_146640_r = p_148144_1_;
			boolean flag1 = UISelectWorld.this.field_146640_r >= 0 && UISelectWorld.this.field_146640_r < this.getSize();
			UISelectWorld.this.field_146641_z.enabled = flag1;
			UISelectWorld.this.field_146642_y.enabled = flag1;
			UISelectWorld.this.field_146630_A.enabled = flag1;
			UISelectWorld.this.field_146631_B.enabled = flag1;

			if(p_148144_2_ && flag1)
			{
				UISelectWorld.this.func_146615_e(p_148144_1_);
			}
		}

		/**
		 * Returns true if the element passed in is currently selected
		 */
		protected boolean isSelected(int p_148131_1_)
		{
			return p_148131_1_ == UISelectWorld.this.field_146640_r;
		}

		/**
		 * Return the height of the content being scrolled
		 */
		protected int getContentHeight()
		{
			return UISelectWorld.this.field_146639_s.size() * 36;
		}

		protected void drawBackground()
		{
			UISelectWorld.this.drawDefaultBackground();
		}

		protected void drawSlot(int p_148126_1_, int p_148126_2_, int p_148126_3_, int p_148126_4_, Tessellator p_148126_5_, int p_148126_6_, int p_148126_7_)
		{
			SaveFormatComparator saveformatcomparator = (SaveFormatComparator) UISelectWorld.this.field_146639_s.get(p_148126_1_);
			String s = saveformatcomparator.getDisplayName();

			if(s == null || MathHelper.stringNullOrLengthZero(s))
			{
				s = UISelectWorld.this.field_146637_u + " " + (p_148126_1_ + 1);
			}

			String s1 = saveformatcomparator.getFileName();
			s1 = s1 + " (" + UISelectWorld.this.field_146633_h.format(new Date(saveformatcomparator.getLastTimePlayed()));
			s1 = s1 + ")";
			String s2 = "";

			if(saveformatcomparator.requiresConversion())
			{
				s2 = UISelectWorld.this.field_146636_v + " " + s2;
			}
			else
			{
				s2 = UISelectWorld.this.field_146635_w[saveformatcomparator.getEnumGameType().getID()];

				if(saveformatcomparator.isHardcoreModeEnabled())
				{
					s2 = EnumChatFormatting.DARK_RED + I18n.format("gameMode.hardcore", new Object[0]) + EnumChatFormatting.RESET;
				}

				if(saveformatcomparator.getCheatsEnabled())
				{
					s2 = s2 + ", " + I18n.format("selectWorld.cheats", new Object[0]);
				}
			}

			UISelectWorld.this.drawString(UISelectWorld.this.fontRendererObj, s, p_148126_2_ + 2, p_148126_3_ + 1, 16777215);
			UISelectWorld.this.drawString(UISelectWorld.this.fontRendererObj, s1, p_148126_2_ + 2, p_148126_3_ + 12, 8421504);
			UISelectWorld.this.drawString(UISelectWorld.this.fontRendererObj, s2, p_148126_2_ + 2, p_148126_3_ + 12 + 10, 8421504);
		}
	}
}