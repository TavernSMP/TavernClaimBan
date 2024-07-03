package no.vestlandetmc.BanFromClaim;

import lombok.Getter;
import no.vestlandetmc.BanFromClaim.commands.*;
import no.vestlandetmc.BanFromClaim.config.ClaimData;
import no.vestlandetmc.BanFromClaim.config.Config;
import no.vestlandetmc.BanFromClaim.config.Messages;
import no.vestlandetmc.BanFromClaim.hooks.HookManager;
import no.vestlandetmc.BanFromClaim.listener.RegionListener;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;

public class BfcPlugin extends JavaPlugin {

    @Getter
    private static BfcPlugin plugin;
    @Getter
    private static HookManager hookManager;

    private FileConfiguration data;

    @Override
    public void onEnable() {
        plugin = this;

        Config.initialize();
        hookManager = new HookManager();

        this.getServer().getPluginManager().registerEvents(new RegionListener(), this);
        this.getCommand("banfromclaim").setExecutor(new BfcCommand());
        this.getCommand("unbanfromclaim").setExecutor(new UnbfcCommand());
        this.getCommand("banfromclaimlist").setExecutor(new BfclistCommand());
        this.getCommand("banfromclaimall").setExecutor(new BfcAllCommand());

        if (Config.KICKMODE) {
            this.getCommand("kickfromclaim").setExecutor(new KfcCommand());
        }

        this.getCommand("bfcsafespot").setExecutor(new SafeSpot());

        createDatafile();
        Messages.initialize();
        ClaimData.createSection();

        new BukkitRunnable() {
            @Override
            public void run() {
                ClaimData.cleanDatafile();
            }

        }.runTaskTimer(this, 30 * 20L, 3600 * 20L);
    }

    public FileConfiguration getDataFile() {
        return this.data;
    }

    public void createDatafile() {
        final File dataFile = new File(this.getDataFolder(), "data.dat");
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            try {
                dataFile.createNewFile();
            } catch (final IOException e) {
                getLogger().severe(e.getMessage());
            }
        }
        data = new YamlConfiguration();
        try {
            data.load(dataFile);
        } catch (IOException | InvalidConfigurationException e) {
            getLogger().severe(e.getMessage());
        }
    }
}
