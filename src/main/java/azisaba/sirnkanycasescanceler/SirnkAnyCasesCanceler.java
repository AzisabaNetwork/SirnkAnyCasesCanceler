package azisaba.sirnkanycasescanceler;

import azisaba.sirnkanycasescanceler.Cancelers.CantPlaceHasLore;
import azisaba.sirnkanycasescanceler.Cancelers.CantUseHasSection;
import azisaba.sirnkanycasescanceler.Cancelers.IllegalAuctionCanceler;
import azisaba.sirnkanycasescanceler.Commands.RootCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SirnkAnyCasesCanceler extends JavaPlugin {
    private static SirnkAnyCasesCanceler instance;

    public SirnkAnyCasesCanceler() {
        instance = this;
    }

    public static SirnkAnyCasesCanceler getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.saveConfig();
        Objects.requireNonNull(getCommand("sacc")).setExecutor(new RootCommand());
        getServer().getPluginManager().registerEvents(new CantPlaceHasLore(), this);
        getServer().getPluginManager().registerEvents(new IllegalAuctionCanceler(), this);
        getServer().getPluginManager().registerEvents(new CantUseHasSection(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void save() {
        this.saveDefaultConfig();
        this.saveConfig();
    }
    public void reload() {
        this.saveDefaultConfig();
        this.reloadConfig();
    }
}
