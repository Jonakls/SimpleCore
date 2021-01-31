package me.jonakls.simplecore.commands;

import me.jonakls.simplecore.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {

    private final Manager manager;

    public BroadcastCommand(Manager manager){
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        StringBuilder stringBuilder = new StringBuilder();
        float vol = (float) manager.getFiles().getConfig().getDouble("sounds.broadcast-sound.vol");
        float pitch = (float) manager.getFiles().getConfig().getDouble("sounds.broadcast-sound.pitch");

        if (!(sender instanceof Player)){
            sender.sendMessage(manager.getFiles().getLang().getString("messages.error.no-console"));
            return true;
        }

        Player p = (Player) sender;

        if(!(p.hasPermission("simplecore.command.broadcast"))){
            p.sendMessage(manager.getFiles().getLang().getString("messages.error.no-permissions"));
            return true;
        }
        if (!(args.length > 0)){
            p.sendMessage(manager.getFiles().getLang().getString("usages.broadcast-message"));
            return true;
        }

        int i = 0;
        while (i < args.length) {
            stringBuilder.append(' ').append(args[i]);
            ++i;
        }
        String message = stringBuilder.toString();
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                manager.getFiles().getLang().getString("broadcast.prefix")+message));

        Bukkit.getOnlinePlayers().forEach(online -> online.playSound(online.getLocation(),
                Sound.valueOf(manager.getFiles().getConfig().getString("sounds.broadcast-sound.sound")),
                vol,
                pitch));

        return true;
    }
}
