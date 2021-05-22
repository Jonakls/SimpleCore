package me.jonakls.noxuscommands.commands.time;

import me.jonakls.noxuscommands.files.FileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NightCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(FileManager.getLang().getString("messages.error.no-console")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }
        Player player = (Player) sender;
        if (!(player.hasPermission("simplecore.command.time"))){
            player.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }

        if (!(player.hasPermission("simplecore.command.time.night"))){
            player.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }

        player.getWorld().setTime(13000);
        player.sendMessage(FileManager.getLang().getString("time.time-set")
                .replace("%world%", player.getWorld().getName())
                .replace("%time%","" + player.getWorld().getTime()));

        return true;
    }
}
