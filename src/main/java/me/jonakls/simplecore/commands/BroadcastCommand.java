package me.jonakls.simplecore.commands;

import me.jonakls.simplecore.files.FileManager;
import me.jonakls.simplecore.utils.ColorApply;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        StringBuilder stringBuilder = new StringBuilder();
        float vol = (float) FileManager.getConfig().getDouble("sounds.broadcast-sound.vol");
        float pitch = (float) FileManager.getConfig().getDouble("sounds.broadcast-sound.pitch");

        if (!(sender instanceof Player)){
            sender.sendMessage(FileManager.getLang().getString("messages.error.no-console"));
            return true;
        }

        Player p = (Player) sender;

        if(!(p.hasPermission("simplecore.command.broadcast"))){
            p.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }
        if (!(args.length > 0)){
            p.sendMessage(FileManager.getLang().getString("usages.broadcast-message")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }
        int i = 0;
        while (i < args.length) {
            stringBuilder.append(' ').append(args[i]);
            ++i;
        }
        Bukkit.broadcastMessage(ColorApply.apply(FileManager.getLang().getString("broadcast.prefix")+ stringBuilder));

        Bukkit.getOnlinePlayers().forEach(online -> online.playSound(online.getLocation(),
                Sound.valueOf(FileManager.getConfig().getString("sounds.broadcast-sound.sound")),
                vol,
                pitch));

        return true;
    }
}
