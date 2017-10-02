package sharedcms.command;

import java.util.List;

import net.minecraft.command.ICommandSender;

public interface ISub
{
	public List<String> getAliases(List<String> empt);

	public void onCalled(ICommandSender sender);
}
