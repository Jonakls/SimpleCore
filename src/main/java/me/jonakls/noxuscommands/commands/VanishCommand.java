package me.jonakls.noxuscommands.commands;

import me.jonakls.noxuscommands.files.FileManager;
import me.jonakls.noxuscommands.utils.MessageReplacer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(MessageReplacer.noConsole());
            return true;
        }
        Player player = (Player) sender;
        if (!(player.hasPermission("simplecore.command.vanish"))){
            player.sendMessage(MessageReplacer.noPermissions());
            return true;
        }
        if (!(args.length > 0)){
            player.sendMessage(MessageReplacer.prefix(FileManager.getLang().getString("usages.vanish")));
            return true;
        }
        if (args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("on")){
            if (!(args.length > 1)){
                player.sendMessage(FileManager.getLang().getString("vanish.message")
                        .replace("%type%", FileManager.getLang().getString("type.enable")));

                for (Player online : Bukkit.getOnlinePlayers()) online.hidePlayer(player);
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){
                player.sendMessage(MessageReplacer.noPlayer(args[1]));
                return true;
            }
            player.sendMessage(FileManager.getLang().getString("vanish.other-message")
                    .replace("%type%", FileManager.getLang().getString("type.enable"))
                    .replace("%target%", target.getName()));
            target.sendMessage(FileManager.getLang().getString("vanish.target-message")
                    .replace("%type%", FileManager.getLang().getString("type.enable"))
                    .replace("%player%", player.getName()));
            Bukkit.getOnlinePlayers().forEach(online -> online.hidePlayer(target));
            return true;
        }
        if (args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("off")){
            if (!(args.length > 1)){
                player.sendMessage(FileManager.getLang().getString("vanish.message")
                    .replace("%type%", FileManager.getLang().getString("type.disable")));

                Bukkit.getOnlinePlayers().forEach(online -> online.showPlayer(player));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){

                player.sendMessage(MessageReplacer.noPlayer(args[1]));
                return true;
            }
            player.sendMessage(FileManager.getLang().getString("vanish.other-message")
                    .replace("%type%", FileManager.getLang().getString("type.disable"))
                    .replace("%target%", target.getName()));
            target.sendMessage(FileManager.getLang().getString("vanish.target-message")
                    .replace("%type%", FileManager.getLang().getString("type.disable"))
                    .replace("%player%", player.getName()));
            Bukkit.getOnlinePlayers().forEach(online -> online.showPlayer(target));
        }
        player.sendMessage(MessageReplacer.prefix(FileManager.getLang().getString("usages.vanish")));
        return true;
    }
}
