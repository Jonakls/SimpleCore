package me.jonakls.simplecore;

import me.jonakls.simplecore.commands.*;
import me.jonakls.simplecore.commands.gamemodes.AdventureCommand;
import me.jonakls.simplecore.commands.gamemodes.CreativeCommand;
import me.jonakls.simplecore.commands.gamemodes.SpectatorCommand;
import me.jonakls.simplecore.commands.gamemodes.SurvivalCommand;
import me.jonakls.simplecore.listeners.JoinEvent;
import me.jonakls.simplecore.listeners.QuitEvent;
import me.jonakls.simplecore.listeners.ServerListEvent;
import me.jonakls.simplecore.files.FileManager;
import me.jonakls.simplecore.menus.listener.MenuClickEvent;
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
        pm.registerEvents(new JoinEvent(this), simpleCore);
        pm.registerEvents(new QuitEvent(this), simpleCore);
        pm.registerEvents(new ServerListEvent(this), simpleCore);
        pm.registerEvents(new MenuClickEvent(this), simpleCore);
    }


    public FileManager getFiles(){
        return fileManager;
    }

    public SimpleCore getSimpleCore(){
        return simpleCore;
    }

}
