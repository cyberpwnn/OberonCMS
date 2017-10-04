package sharedcms.command;

import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import sharedcms.controller.client.GuiController;
import sharedcms.gui.UIComponentTest;
import sharedcms.gui.UIOptions;
import sharedcms.gui.UIRenderTest;
import sharedcms.util.GList;

public class CommandCMS implements ICommand
{
	private GList<ISub> subs;
	private GList<String> fs;

	public CommandCMS()
	{
		subs = new GList<ISub>();
		fs = new GList<String>();

		subs.add(new ISub()
		{
			@Override
			public List<String> getAliases(List<String> empt)
			{
				empt.add("help");
				empt.add("h");

				return empt;
			}

			@Override
			public void onCalled(ICommandSender sender)
			{
				for(String i : fs)
				{
					sender.addChatMessage(new ChatComponentText("/cms " + i));
				}
			}
		});

		subs.add(new ISub()
		{
			@Override
			public List<String> getAliases(List<String> empt)
			{
				empt.add("status");
				empt.add("stat");
				empt.add("s");

				return empt;
			}

			@Override
			public void onCalled(ICommandSender sender)
			{
				sender.addChatMessage(new ChatComponentText("SIDE: " + FMLCommonHandler.instance().getSide().toString()));
			}
		});
		
		if(FMLCommonHandler.instance().getSide().equals(Side.CLIENT))
		{
			subs.add(new ISub()
			{
				@Override
				public List<String> getAliases(List<String> empt)
				{
					empt.add("ui-renderer");

					return empt;
				}

				@Override
				public void onCalled(ICommandSender sender)
				{
					GuiController.openGui(new UIRenderTest());
				}
			});
			
			subs.add(new ISub()
			{
				@Override
				public List<String> getAliases(List<String> empt)
				{
					empt.add("ui-component");

					return empt;
				}

				@Override
				public void onCalled(ICommandSender sender)
				{
					GuiController.openGui(new UIComponentTest());
				}
			});
			
			subs.add(new ISub()
			{
				@Override
				public List<String> getAliases(List<String> empt)
				{
					empt.add("ui");

					return empt;
				}

				@Override
				public void onCalled(ICommandSender sender)
				{
					GuiController.openGui(new UIOptions());
				}
			});
		}

		for(ISub i : subs)
		{
			fs.add(i.getAliases(new GList<String>()).get(0));
		}
	}

	@Override
	public int compareTo(Object arg0)
	{
		return 0;
	}

	@Override
	public String getCommandName()
	{
		return "cms";
	}

	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return "Use /cms help for help";
	}

	@Override
	public List getCommandAliases()
	{
		return new GList<String>().qadd("sharedcms");
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args)
	{
		if(args.length == 0)
		{
			for(String i : fs)
			{
				sender.addChatMessage(new ChatComponentText("/cms " + i));
			}
		}

		else
		{
			for(ISub i : subs)
			{
				if(i.getAliases(new GList<String>()).contains(args[0].toLowerCase()))
				{
					i.onCalled(sender);
					return;
				}
			}
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender)
	{
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_)
	{
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] arg, int i)
	{
		return false;
	}
}
