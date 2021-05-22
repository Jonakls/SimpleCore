package me.jonakls.noxuscommands.commands;

import me.jonakls.noxuscommands.files.FileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimeCommand implements CommandExecutor {

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
        if (!(args.length > 0)){
            p.sendMessage(FileManager.getLang().getString("usages.time-usage")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }

        if (args[0].equalsIgnoreCase("day")){

            if (!(p.hasPermission("simplecore.command.time.day"))){
                p.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                        .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
                return true;
            }

            p.getWorld().setTime(600);
            p.sendMessage(FileManager.getLang().getString("time.time-set")
                    .replace("%world%", p.getWorld().getName())
                    .replace("%time%","" + p.getWorld().getTime()));
            return true;
        }

        if (args[0].equalsIgnoreCase("night")){

            if (!(p.hasPermission("simplecore.command.time.night"))){
                p.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                        .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
                return true;
            }

            p.getWorld().setTime(19000);
            p.sendMessage(FileManager.getLang().getString("time.time-set")
                    .replace("%world%", p.getWorld().getName())
                    .replace("%time%","" + p.getWorld().getTime()));
            return true;
        }

        if (args[0].equalsIgnoreCase("midnight")){

            if (!(p.hasPermission("simplecore.command.time.midnight"))){
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

        p.sendMessage(FileManager.getLang().getString("usages.time-usage")
                .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
        return true;
    }
}
