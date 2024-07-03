package no.vestlandetmc.BanFromClaim.hooks;

import lombok.Getter;
import no.vestlandetmc.BanFromClaim.BfcPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class HookManager {

	@Getter
	private RegionHook activeRegionHook;

	public HookManager() {
		if (isPluginAvailable("GriefPrevention")) {
			activeRegionHook = new GriefPreventionHook();
		} else {
			BfcPlugin.getPlugin().getLogger().warning("No supported protection plugins found!");
			Bukkit.getPluginManager().disablePlugin(BfcPlugin.getPlugin());
		}
	}

	private boolean isPluginAvailable(String pluginName) {
		final Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin(pluginName);
		return plugin != null && plugin.isEnabled();
	}
}
