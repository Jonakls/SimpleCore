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
        Manager manager = new Manager(this);
        manager.setupFiles();
        manager.setupCommands();
        manager.setupEvents();

        console.sendMessage("[SimpleCore] Load all files, events and commands!");
    }

    @Override
    public void onDisable() {
        console.sendMessage("[SimpleCore] Disable all options, good bay ;)");

    }

}
