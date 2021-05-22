package me.jonakls.noxuscommands.commands;

import me.jonakls.noxuscommands.files.FileManager;
import me.jonakls.noxuscommands.utils.MessageReplacer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(MessageReplacer.noConsole());
            return true;
        }
        Player player = (Player) sender;
        if (!(player.hasPermission("simplecore.command.time"))){
            player.sendMessage(MessageReplacer.noPermissions());
            return true;
        }
        if (!(args.length > 0)){
            player.sendMessage(MessageReplacer.prefix(FileManager.getLang().getString("usages.time-usage")));
            return true;
        }

        if (args[0].equalsIgnoreCase("day")){

            if (!(player.hasPermission("simplecore.command.time.day"))){
                player.sendMessage(MessageReplacer.noPermissions());
                return true;
            }

            player.getWorld().setTime(600);
            player.sendMessage(FileManager.getLang().getString("time.time-set").
                    replace("%world%", player.getWorld().getName()).
                    replace("%time%","" + player.getWorld().getTime()));
            return true;
        }

        if (args[0].equalsIgnoreCase("night")){

            if (!(player.hasPermission("simplecore.command.time.night"))){
                player.sendMessage(MessageReplacer.noPermissions());
                return true;
            }

            player.getWorld().setTime(19000);
            player.sendMessage(FileManager.getLang().getString("time.time-set").
                    replace("%world%", player.getWorld().getName()).
                    replace("%time%","" + player.getWorld().getTime()));
            return true;
        }

        if (args[0].equalsIgnoreCase("midnight")){

            if (!(player.hasPermission("simplecore.command.time.midnight"))){
                player.sendMessage(MessageReplacer.noPermissions());
                return true;
            }

            player.getWorld().setTime(0);
            player.sendMessage(FileManager.getLang().getString("time.time-set").
                    replace("%world%", player.getWorld().getName()).
                    replace("%time%","" + player.getWorld().getTime()));
            return true;
        }

        player.sendMessage(MessageReplacer.prefix(FileManager.getLang().getString("usages.time-usage")));
        return true;
    }
}
