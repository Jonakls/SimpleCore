package me.jonakls.simplecore;

import me.jonakls.simplecore.commands.*;
import me.jonakls.simplecore.commands.gamemodes.AdventureCommand;
import me.jonakls.simplecore.commands.gamemodes.CreativeCommand;
import me.jonakls.simplecore.commands.gamemodes.SpectatorCommand;
import me.jonakls.simplecore.commands.gamemodes.SurvivalCommand;
import me.jonakls.simplecore.listeners.ChatListener;
import me.jonakls.simplecore.listeners.PlayerJoinListener;
import me.jonakls.simplecore.listeners.PlayerQuitListener;
import me.jonakls.simplecore.listeners.ServerListListener;
import me.jonakls.simplecore.files.FileManager;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;

public class Service {

    private final SimpleCore simpleCore;
    private FileManager fileManager;
    private static Chat chat = null;

    public Service(SimpleCore simpleCore){
        this.simpleCore = simpleCore;
    }

    ConsoleCommandSender console = Bukkit.getConsoleSender();

    public void setupCommands(){

        console.sendMessage("[SimpleCore] Installing commands and executors...");

        simpleCore.getCommand("simplecore").setExecutor(new GeneralCommand(this));
        simpleCore.getCommand("gamemode").setExecutor(new GeneralGamemodeCommand(this));
        simpleCore.getCommand("gmsp").setExecutor(new SpectatorCommand(this));
        simpleCore.getCommand("gms").setExecutor(new SurvivalCommand(this));
        simpleCore.getCommand("gmc").setExecutor(new CreativeCommand(this));
        simpleCore.getCommand("gma").setExecutor(new AdventureCommand(this));
        simpleCore.getCommand("flymode").setExecutor(new FlyCommand(this));
        simpleCore.getCommand("vanish").setExecutor(new VanishMode(this));
        simpleCore.getCommand("fake").setExecutor(new FakeMessages(this));
        simpleCore.getCommand("broadcast").setExecutor(new BroadcastCommand(this));
        simpleCore.getCommand("stop").setExecutor(new StopCommand(this));
        simpleCore.getCommand("message").setExecutor(new PrivateMessagesCommand(this));
        simpleCore.getCommand("lag").setExecutor(new LagCommand(this));
        simpleCore.getCommand("nickname").setExecutor(new NicknameCommand(this));

    }

    public void setupFiles(){

        console.sendMessage("[SimpleCore] Installing files of configuration...");

        fileManager = new FileManager(simpleCore);
        fileManager.setupFiles();
    }

    public void setupDependencies(){
        setupVaultChat();
    }

    public void setupEvents(){

        console.sendMessage("[SimpleCore] Installing events and executors...");

        PluginManager pm = simpleCore.getServer().getPluginManager();
        pm.registerEvents(new PlayerJoinListener(this), simpleCore);
        pm.registerEvents(new PlayerQuitListener(this), simpleCore);
        pm.registerEvents(new ServerListListener(this), simpleCore);
        pm.registerEvents(new ChatListener(this), simpleCore);
    }

    private void setupVaultChat() {
        RegisteredServiceProvider<Chat> rsp = simpleCore.getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
    }

    public static Chat getVaultChat(){
        return chat;
    }


    public FileManager getFiles(){
        return fileManager;
    }

    public SimpleCore getSimpleCore(){
        return simpleCore;
    }

}
