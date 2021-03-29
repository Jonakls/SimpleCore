package me.jonakls.simplecore.commands;

import me.jonakls.simplecore.Service;
import me.jonakls.simplecore.utils.ColorApply;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {

    private final Service service;

    public BroadcastCommand(Service service){
        this.service = service;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        StringBuilder stringBuilder = new StringBuilder();
        float vol = (float) service.getFiles().getConfig().getDouble("sounds.broadcast-sound.vol");
        float pitch = (float) service.getFiles().getConfig().getDouble("sounds.broadcast-sound.pitch");

        if (!(sender instanceof Player)){
            sender.sendMessage(service.getFiles().getLang().getString("messages.error.no-console"));
            return true;
        }

        Player p = (Player) sender;

        if(!(p.hasPermission("simplecore.command.broadcast"))){
            p.sendMessage(service.getFiles().getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;
        }
        if (!(args.length > 0)){
            p.sendMessage(service.getFiles().getLang().getString("usages.broadcast-message")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;
        }

        int i = 0;
        while (i < args.length) {
            stringBuilder.append(' ').append(args[i]);
            ++i;
        }
        String message = stringBuilder.toString();
        Bukkit.broadcastMessage(ColorApply.apply("broadcast.prefix"+message));

        Bukkit.getOnlinePlayers().forEach(online -> online.playSound(online.getLocation(),
                Sound.valueOf(service.getFiles().getConfig().getString("sounds.broadcast-sound.sound")),
                vol,
                pitch));

        return true;
    }
}
