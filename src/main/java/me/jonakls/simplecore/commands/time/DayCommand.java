package me.jonakls.simplecore.commands.time;

import me.jonakls.simplecore.files.FileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(FileManager.getLang().getString("messages.error.no-console")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.time"))){
            p.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }

        if (!(p.hasPermission("simplecore.command.time.day"))){
            p.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }

        p.getWorld().setTime(0);
        p.sendMessage(FileManager.getLang().getString("time.time-set")
                .replace("%world%", p.getWorld().getName())
                .replace("%time%","" + p.getWorld().getTime()));

        return true;
    }
}
