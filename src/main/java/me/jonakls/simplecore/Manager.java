package me.jonakls.simplecore;

import me.jonakls.simplecore.commands.*;
import me.jonakls.simplecore.commands.gamemodes.AdventureCommand;
import me.jonakls.simplecore.commands.gamemodes.CreativeCommand;
import me.jonakls.simplecore.commands.gamemodes.SpectatorCommand;
import me.jonakls.simplecore.commands.gamemodes.SurvivalCommand;
import me.jonakls.simplecore.listeners.PlayerJoinListener;
import me.jonakls.simplecore.listeners.PlayerQuitListener;
import me.jonakls.simplecore.listeners.ServerListListener;
import me.jonakls.simplecore.files.FileManager;
import me.jonakls.simplecore.menus.listener.MenuClickListener;
import org.bukkit.plugin.PluginManager;

public class Manager {

    private final SimpleCore simpleCore;
    private FileManager fileManager;

    public Manager(SimpleCore simpleCore){
        this.simpleCore = simpleCore;
    }

    public void setupCommands(){
        simpleCore.getCommand("simplecore").setExecutor(new GeneralCommand(this));
        simpleCore.getCommand("gamemode").setExecutor(new GeneralGamemodeCommand(this));
        simpleCore.getCommand("gmsp").setExecutor(new SpectatorCommand(this));
        simpleCore.getCommand("gms").setExecutor(new SurvivalCommand(this));
        simpleCore.getCommand("gmc").setExecutor(new CreativeCommand(this));
        simpleCore.getCommand("gma").setExecutor(new AdventureCommand(this));
        simpleCore.getCommand("flymode").setExecutor(new FlyCommand(this));
        simpleCore.getCommand("vanish").setExecutor(new VanishMode(this));
        simpleCore.getCommand("menu").setExecutor(new MenuCommand(this));

    }

    public void setupFiles(){
        fileManager = new FileManager(simpleCore);
        fileManager.setupFiles();
    }

    public void setupEvents(){
        PluginManager pm = simpleCore.getServer().getPluginManager();
        pm.registerEvents(new PlayerJoinListener(this), simpleCore);
        pm.registerEvents(new PlayerQuitListener(this), simpleCore);
        pm.registerEvents(new ServerListListener(this), simpleCore);
        pm.registerEvents(new MenuClickListener(this), simpleCore);
    }


    public FileManager getFiles(){
        return fileManager;
    }

    public SimpleCore getSimpleCore(){
        return simpleCore;
    }

}
