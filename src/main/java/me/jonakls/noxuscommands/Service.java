package me.jonakls.noxuscommands;

import me.jonakls.noxuscommands.commands.*;
import me.jonakls.noxuscommands.commands.gamemodes.AdventureCommand;
import me.jonakls.noxuscommands.commands.gamemodes.CreativeCommand;
import me.jonakls.noxuscommands.commands.gamemodes.SpectatorCommand;
import me.jonakls.noxuscommands.commands.gamemodes.SurvivalCommand;
import me.jonakls.noxuscommands.commands.spawn.DelSpawn;
import me.jonakls.noxuscommands.commands.spawn.SetSpawnCommand;
import me.jonakls.noxuscommands.commands.spawn.SpawnCommand;
import me.jonakls.noxuscommands.commands.time.DayCommand;
import me.jonakls.noxuscommands.commands.time.MidnightCommand;
import me.jonakls.noxuscommands.commands.time.NightCommand;
import me.jonakls.noxuscommands.commands.warps.DeleteWarpCommand;
import me.jonakls.noxuscommands.commands.warps.SetWarpCommand;
import me.jonakls.noxuscommands.commands.warps.WarpCommand;
import me.jonakls.noxuscommands.listeners.ChatListener;
import me.jonakls.noxuscommands.listeners.PlayerJoinListener;
import me.jonakls.noxuscommands.listeners.PlayerQuitListener;
import me.jonakls.noxuscommands.listeners.ServerListListener;
import me.jonakls.noxuscommands.files.FileManager;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;

public class Service {

    private final NoxusCommands plugin;
    private FileManager fileManager;
    private static Chat chat = null;

    public Service(NoxusCommands plugin){
        this.plugin = plugin;
    }

    public void setupCommands(){

        plugin.getLogger().info("Installing commands and executors...");

        plugin.getCommand("simplecore").setExecutor(new GeneralCommand(this));
        plugin.getCommand("gamemode").setExecutor(new GeneralGamemodeCommand());
        plugin.getCommand("gmsp").setExecutor(new SpectatorCommand());
        plugin.getCommand("gms").setExecutor(new SurvivalCommand());
        plugin.getCommand("gmc").setExecutor(new CreativeCommand());
        plugin.getCommand("gma").setExecutor(new AdventureCommand());
        plugin.getCommand("flymode").setExecutor(new FlyCommand());
        plugin.getCommand("vanish").setExecutor(new VanishCommand());
        plugin.getCommand("fake").setExecutor(new FakeMessages());
        plugin.getCommand("broadcast").setExecutor(new BroadcastCommand());
        plugin.getCommand("stop").setExecutor(new StopCommand());
        plugin.getCommand("message").setExecutor(new PrivateMessagesCommand());
        plugin.getCommand("lag").setExecutor(new LagCommand());
        plugin.getCommand("nickname").setExecutor(new NicknameCommand());
        plugin.getCommand("setwarp").setExecutor(new SetWarpCommand());
        plugin.getCommand("warp").setExecutor(new WarpCommand(this));
        plugin.getCommand("delwarp").setExecutor(new DeleteWarpCommand());
        plugin.getCommand("time").setExecutor(new TimeCommand());
        plugin.getCommand("day").setExecutor(new DayCommand());
        plugin.getCommand("night").setExecutor(new NightCommand());
        plugin.getCommand("midnight").setExecutor(new MidnightCommand());
        plugin.getCommand("spawn").setExecutor(new SpawnCommand(this));
        plugin.getCommand("setspawn").setExecutor(new SetSpawnCommand());
        plugin.getCommand("delspawn").setExecutor(new DelSpawn());

    }

    public void setupFiles(){

        plugin.getLogger().info("Installing files of configuration...");

        fileManager = new FileManager(plugin);
        fileManager.setupFiles();

    }

    public void setupDependencies(){
        setupVaultChat();
    }

    public void setupEvents(){

        plugin.getLogger().info("Installing events and executors...");

        PluginManager pm = plugin.getServer().getPluginManager();
        pm.registerEvents(new PlayerJoinListener(), plugin);
        pm.registerEvents(new PlayerQuitListener(), plugin);
        pm.registerEvents(new ServerListListener(), plugin);
        pm.registerEvents(new ChatListener(), plugin);
    }

    private void setupVaultChat() {
        RegisteredServiceProvider<Chat> rsp = plugin.getServer().getServicesManager().getRegistration(Chat.class);
        if (rsp == null){
            plugin.getLogger().warning("You need a permission manager or permission plugin.");
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

    public NoxusCommands getPlugin(){
        return plugin;
    }

}
