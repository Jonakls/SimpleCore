package jonakls.simplecore;

import jonakls.simplecore.commands.GeneralCommand;
import jonakls.simplecore.commands.GeneralGamemodeCommand;
import jonakls.simplecore.commands.gamemodes.AdventureCommand;
import jonakls.simplecore.commands.gamemodes.CreativeCommand;
import jonakls.simplecore.commands.gamemodes.SpectatorCommand;
import jonakls.simplecore.commands.gamemodes.SurvivalCommand;
import jonakls.simplecore.events.JoinEvent;
import jonakls.simplecore.events.QuitEvent;
import jonakls.simplecore.events.ServerListEvent;
import jonakls.simplecore.files.MessagesFile;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public final class SimpleCore extends JavaPlugin {

    private MessagesFile messagesFile;

    public String configFile;

    PluginDescriptionFile pluginFile = getDescription();

    public String namePlugin = pluginFile.getName();
    public List<String> authorPlugin = pluginFile.getAuthors();
    public String versionPlugin = pluginFile.getVersion();

    ConsoleCommandSender console = Bukkit.getConsoleSender();

    @Override
    public void onEnable() {
        console.sendMessage("[SimpleCore] Installing commands and executors...");
        setupCommands();
        console.sendMessage("[SimpleCore] Installing events and executors...");
        setupEvents();
        console.sendMessage("[SimpleCore] Installing files of configuration...");
        setupConfig();
        messagesFile = new MessagesFile(this).setupMessages();
        console.sendMessage("[SimpleCore] Load all files, events and commands!");
    }

    @Override
    public void onDisable() {
        console.sendMessage("[SimpleCore] Disable all options, good bay ;)");

    }


    public void setupCommands(){
        getCommand("simplecore").setExecutor(new GeneralCommand(this));
        getCommand("gamemode").setExecutor(new GeneralGamemodeCommand(this));
        getCommand("gmsp").setExecutor(new SpectatorCommand(this));
        getCommand("gms").setExecutor(new SurvivalCommand(this));
        getCommand("gmc").setExecutor(new CreativeCommand(this));
        getCommand("gma").setExecutor(new AdventureCommand(this));
    }

    public void setupEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JoinEvent(this), this);
        pm.registerEvents(new QuitEvent(this), this);
        pm.registerEvents(new ServerListEvent(this), this);
    }

    public void setupConfig(){
        File config = new File(this.getDataFolder(), "config.yml");
        configFile = config.getPath();

        if(!config.exists()){
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }
}
