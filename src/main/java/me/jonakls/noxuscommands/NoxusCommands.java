package me.jonakls.noxuscommands;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoxusCommands extends JavaPlugin {

    private final Service service = new Service(this);

    @Override
    public void onEnable() {

        getLogger().info(" ");
        getLogger().info("|\\ | \\/ (`  | NoxusCommands");
        getLogger().info("| \\| /\\ _)  | Version: " + getDescription().getVersion());
        getLogger().info(" ");
        getLogger().info("Plugin by: Jonakls");

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null || Bukkit.getPluginManager().getPlugin("Vault") == null){

            getLogger().warning("[Error] One of the dependencies was not found, please make sure they are all installed on your server.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        getLogger().info("The required dependencies were detected correctly, starting normally.");

        service.setupFiles();
        service.setupCommands();
        service.setupEvents();
        service.setupDependencies();

        getLogger().info("Load all files, events and commands!");


    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling, thanks...");

    }

}
