package me.jonakls.simplecore;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class SimpleCore extends JavaPlugin {


    PluginDescriptionFile pluginFile = getDescription();

    public String namePlugin = pluginFile.getName();
    public List<String> authorPlugin = pluginFile.getAuthors();
    public String versionPlugin = pluginFile.getVersion();

    ConsoleCommandSender console = Bukkit.getConsoleSender();

    @Override
    public void onEnable() {

        if (!(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null)){
            Bukkit.getLogger().info("[SimpleCore] The required dependencies were detected correctly, starting normally.");

            Service service = new Service(this);
            service.setupFiles();
            service.setupCommands();
            service.setupEvents();

            console.sendMessage("[SimpleCore] Load all files, events and commands!");
            return;
        }
        Bukkit.getLogger().warning("[Error] One of the dependencies was not found, please make sure they are all installed on your server.");
        Bukkit.getPluginManager().disablePlugin(this);

    }

    @Override
    public void onDisable() {
        console.sendMessage("[SimpleCore] Disable all options, good bay ;)");

    }

}
