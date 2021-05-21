package me.jonakls.simplecore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleCore extends JavaPlugin {

    private final Service service = new Service(this);

    @Override
    public void onEnable() {

        if (!(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null)
            || (Bukkit.getPluginManager().getPlugin("Vault") == null)){

            getLogger().info("The required dependencies were detected correctly, starting normally.");

            service.setupFiles();
            service.setupCommands();
            service.setupEvents();
            service.setupDependencies();

            getLogger().info(" Load all files, events and commands!");
            return;
        }
        getLogger().warning(
                "[Error] One of the dependencies was not found, please make sure they are all installed on your server.");
        Bukkit.getPluginManager().disablePlugin(this);

    }

    @Override
    public void onDisable() {
        getLogger().info("Disable all options, good bay ;)");

    }

}
