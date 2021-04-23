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
        simpleCore.getCommand("gamemode").setExecutor(new GeneralGamemodeCommand(this));
        simpleCore.getCommand("gmsp").setExecutor(new SpectatorCommand(this));
        simpleCore.getCommand("gms").setExecutor(new SurvivalCommand(this));
        simpleCore.getCommand("gmc").setExecutor(new CreativeCommand(this));
        simpleCore.getCommand("gma").setExecutor(new AdventureCommand(this));
        simpleCore.getCommand("flymode").setExecutor(new FlyCommand(this));
        simpleCore.getCommand("vanish").setExecutor(new VanishCommand(this));
        simpleCore.getCommand("fake").setExecutor(new FakeMessages(this));
        simpleCore.getCommand("broadcast").setExecutor(new BroadcastCommand(this));
        simpleCore.getCommand("stop").setExecutor(new StopCommand(this));
        simpleCore.getCommand("message").setExecutor(new PrivateMessagesCommand(this));
        simpleCore.getCommand("lag").setExecutor(new LagCommand(this));
        simpleCore.getCommand("nickname").setExecutor(new NicknameCommand(this));
        simpleCore.getCommand("setwarp").setExecutor(new SetWarpCommand(this));
        simpleCore.getCommand("warp").setExecutor(new WarpCommand(this));
        simpleCore.getCommand("delwarp").setExecutor(new DeleteWarpCommand(this));
        simpleCore.getCommand("time").setExecutor(new TimeCommand(this));
        simpleCore.getCommand("sun").setExecutor(new SunCommand(this));
        simpleCore.getCommand("rain").setExecutor(new RainCommand(this));
        simpleCore.getCommand("thunder").setExecutor(new ThunderCommand(this));
        simpleCore.getCommand("day").setExecutor(new DayCommand(this));
        simpleCore.getCommand("night").setExecutor(new NightCommand(this));
        simpleCore.getCommand("midnight").setExecutor(new MidnightCommand(this));
        simpleCore.getCommand("spawn").setExecutor(new SpawnCommand(this));
        simpleCore.getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        simpleCore.getCommand("delspawn").setExecutor(new DelSpawn(this));

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
        pm.registerEvents(new PlayerJoinListener(this), simpleCore);
        pm.registerEvents(new PlayerQuitListener(this), simpleCore);
        pm.registerEvents(new ServerListListener(this), simpleCore);
        pm.registerEvents(new ChatListener(this), simpleCore);
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
