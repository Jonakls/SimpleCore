package me.jonakls.simplecore.commands;

import me.jonakls.simplecore.files.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(FileManager.getLang().getString("messages.error.no-console")
            .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.vanish"))){
            p.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }
        if (!(args.length > 0)){
            p.sendMessage(FileManager.getLang().getString("usages.vanish")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }
        if (args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("on")){
            if (!(args.length > 1)){
                p.sendMessage(FileManager.getLang().getString("vanish.message")
                        .replace("%type%", FileManager.getLang().getString("type.enable")));

                for (Player online : Bukkit.getOnlinePlayers()) online.hidePlayer(p);
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){
                p.sendMessage(FileManager.getLang().getString("messages.error.no-player").replace("%player%", args[1]));
                return true;
            }
            p.sendMessage(FileManager.getLang().getString("vanish.other-message")
                    .replace("%type%", FileManager.getLang().getString("type.enable"))
                    .replace("%target%", target.getName()));
            target.sendMessage(FileManager.getLang().getString("vanish.target-message")
                    .replace("%type%", FileManager.getLang().getString("type.enable"))
                    .replace("%player%", p.getName()));
            Bukkit.getOnlinePlayers().forEach(online -> online.hidePlayer(target));
            return true;
        }
        if (args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("off")){
            if (!(args.length > 1)){
                p.sendMessage(FileManager.getLang().getString("vanish.message")
                    .replace("%type%", FileManager.getLang().getString("type.disable")));

                Bukkit.getOnlinePlayers().forEach(online -> online.showPlayer(p));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){
                p.sendMessage(FileManager.getLang().getString("messages.error.no-player")
                        .replace("%player%", args[1])
                        .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
                return true;
            }
            p.sendMessage(FileManager.getLang().getString("vanish.other-message")
                    .replace("%type%", FileManager.getLang().getString("type.disable"))
                    .replace("%target%", target.getName()));
            target.sendMessage(FileManager.getLang().getString("vanish.target-message")
                    .replace("%type%", FileManager.getLang().getString("type.disable"))
                    .replace("%player%", p.getName()));
            Bukkit.getOnlinePlayers().forEach(online -> online.showPlayer(target));
        }
        p.sendMessage(FileManager.getLang().getString("usages.vanish")
                .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
        return true;
    }
}
