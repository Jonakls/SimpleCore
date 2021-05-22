package me.jonakls.simplecore;

import me.jonakls.simplecore.commands.*;
import me.jonakls.simplecore.commands.gamemodes.AdventureCommand;
import me.jonakls.simplecore.commands.gamemodes.CreativeCommand;
import me.jonakls.simplecore.commands.gamemodes.SpectatorCommand;
import me.jonakls.simplecore.commands.gamemodes.SurvivalCommand;
import me.jonakls.simplecore.commands.spawn.DelSpawn;
import me.jonakls.simplecore.commands.spawn.SetSpawnCommand;
import me.jonakls.simplecore.commands.spawn.SpawnCommand;
import me.jonakls.simplecore.commands.time.DayCommand;
import me.jonakls.simplecore.commands.time.MidnightCommand;
import me.jonakls.simplecore.commands.time.NightCommand;
import me.jonakls.simplecore.commands.warps.DeleteWarpCommand;
import me.jonakls.simplecore.commands.warps.SetWarpCommand;
import me.jonakls.simplecore.commands.warps.WarpCommand;
import me.jonakls.simplecore.commands.weather.RainCommand;
import me.jonakls.simplecore.commands.weather.SunCommand;
import me.jonakls.simplecore.commands.weather.ThunderCommand;
import me.jonakls.simplecore.listeners.ChatListener;
import me.jonakls.simplecore.listeners.PlayerJoinListener;
import me.jonakls.simplecore.listeners.PlayerQuitListener;
import me.jonakls.simplecore.listeners.ServerListListener;
import me.jonakls.simplecore.files.FileManager;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;

public class Service {

    private final SimpleCore simpleCore;
    private FileManager fileManager;
    private static Chat chat = null;

    public Service(SimpleCore simpleCore){
        this.simpleCore = simpleCore;
    }

    public void setupCommands(){

        simpleCore.getLogger().info("Installing commands and executors...");

        simpleCore.getCommand("simplecore").setExecutor(new GeneralCommand(this));
        simpleCore.getCommand("gamemode").setExecutor(new GeneralGamemodeCommand());
        simpleCore.getCommand("gmsp").setExecutor(new SpectatorCommand());
        simpleCore.getCommand("gms").setExecutor(new SurvivalCommand());
        simpleCore.getCommand("gmc").setExecutor(new CreativeCommand());
        simpleCore.getCommand("gma").setExecutor(new AdventureCommand());
        simpleCore.getCommand("flymode").setExecutor(new FlyCommand());
        simpleCore.getCommand("vanish").setExecutor(new VanishCommand());
        simpleCore.getCommand("fake").setExecutor(new FakeMessages());
        simpleCore.getCommand("broadcast").setExecutor(new BroadcastCommand());
        simpleCore.getCommand("stop").setExecutor(new StopCommand());
        simpleCore.getCommand("message").setExecutor(new PrivateMessagesCommand());
        simpleCore.getCommand("lag").setExecutor(new LagCommand());
        simpleCore.getCommand("nickname").setExecutor(new NicknameCommand());
        simpleCore.getCommand("setwarp").setExecutor(new SetWarpCommand());
        simpleCore.getCommand("warp").setExecutor(new WarpCommand(this));
        simpleCore.getCommand("delwarp").setExecutor(new DeleteWarpCommand());
        simpleCore.getCommand("time").setExecutor(new TimeCommand());
        simpleCore.getCommand("sun").setExecutor(new SunCommand());
        simpleCore.getCommand("rain").setExecutor(new RainCommand());
        simpleCore.getCommand("thunder").setExecutor(new ThunderCommand());
        simpleCore.getCommand("day").setExecutor(new DayCommand());
        simpleCore.getCommand("night").setExecutor(new NightCommand());
        simpleCore.getCommand("midnight").setExecutor(new MidnightCommand());
        simpleCore.getCommand("spawn").setExecutor(new SpawnCommand(this));
        simpleCore.getCommand("setspawn").setExecutor(new SetSpawnCommand());
        simpleCore.getCommand("delspawn").setExecutor(new DelSpawn());

    }

    public void setupFiles(){

        simpleCore.getLogger().info("Installing files of configuration...");

        fileManager = new FileManager(simpleCore);
        fileManager.setupFiles();

    }

    public void setupDependencies(){
        setupVaultChat();
    }

    public void setupEvents(){

        simpleCore.getLogger().info("Installing events and executors...");

        PluginManager pm = simpleCore.getServer().getPluginManager();
        pm.registerEvents(new PlayerJoinListener(), simpleCore);
        pm.registerEvents(new PlayerQuitListener(), simpleCore);
        pm.registerEvents(new ServerListListener(), simpleCore);
        pm.registerEvents(new ChatListener(), simpleCore);
    }

    private void setupVaultChat() {
        RegisteredServiceProvider<Chat> rsp = simpleCore.getServer().getServicesManager().getRegistration(Chat.class);
        if (rsp == null){
            simpleCore.getLogger().warning("You need a permission manager or permission plugin.");
            return;
        }
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
