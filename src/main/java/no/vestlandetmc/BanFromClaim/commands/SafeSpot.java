package no.vestlandetmc.BanFromClaim.commands;

import no.vestlandetmc.BanFromClaim.config.Config;
import no.vestlandetmc.BanFromClaim.handler.MessageHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SafeSpot implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player player)) {
			MessageHandler.sendConsole("&cYou cannot use this command from the console.");
			return true;
		}

		Config.setSafespot(player.getLocation());

		MessageHandler.sendMessage(player, "&eCurrent location has been stored as a safespot.");

		return true;
	}

}
