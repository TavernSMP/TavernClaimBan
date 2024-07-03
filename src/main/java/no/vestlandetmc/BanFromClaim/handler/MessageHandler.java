package no.vestlandetmc.BanFromClaim.handler;

import no.vestlandetmc.BanFromClaim.BfcPlugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class MessageHandler {

	public static ArrayList<String> spamMessageClaim = new ArrayList<>();

	public static void sendTitle(Player player, String title, String subtitle) {
		player.sendTitle(colorize(title), colorize(subtitle), 20, 3 * 20, 10);
	}

	public static void sendMessage(Player player, String... messages) {
		for (final String message : messages) {
			player.sendMessage(colorize(message));
		}
	}

	public static void sendConsole(String... messages) {
		for (final String message : messages) {
			BfcPlugin.getPlugin().getServer().getConsoleSender().sendMessage(colorize(message));
		}
	}

	public static String colorize(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}