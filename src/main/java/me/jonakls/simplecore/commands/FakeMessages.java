package me.jonakls.simplecore.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import me.jonakls.simplecore.files.FileManager;
import me.jonakls.simplecore.utils.ColorApply;
import me.jonakls.simplecore.utils.MessageReplacer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FakeMessages implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        StringBuilder stringBuilder = new StringBuilder();

        if (!(sender instanceof Player)){
            sender.sendMessage(MessageReplacer.noConsole());
            return true;
        }

        Player player = (Player) sender;

        if(!(player.hasPermission("simplecore.command.fake"))){
            player.sendMessage(MessageReplacer.noPermissions());
            return true;
        }
        if (!(args.length > 0)){
            player.sendMessage(FileManager.getLang().getString("usages.fake-messages")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }
        switch (args[0]){
            case "message":

                if (!(args.length > 1)){
                    player.sendMessage(FileManager.getLang().getString("usages.fake-messages")
                            .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
                    return true;
                }
                for (int i = 1; i < args.length; ++i) {
                    stringBuilder.append(args[i]).append(' ');
                }
                String message = stringBuilder.toString();
                Bukkit.broadcastMessage(ColorApply.apply(message));
                return true;

            case "join":

                if (!(args.length > 1)){
                    player.sendMessage(FileManager.getLang().getString("usages.fake-messages")
                            .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
                    return true;
                }
                Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(
                        player,
                        FileManager.getLang().getString("events.join-player.message").
                                replace("%player%", args[1])));
                return true;

            case "leave" :

                if (!(args.length > 1)){
                    player.sendMessage(FileManager.getLang().getString("usages.fake-messages")
                            .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
                    return true;
                }
                Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(
                        player,
                        FileManager.getLang().getString("events.quit-player.message").
                                replace("%player%", args[1])));
                return true;

            default:
                player.sendMessage(FileManager.getLang().getString("usages.fake-messages"
                        .replace("%prefix%", FileManager.getLang().getString("messages.prefix"))));
                return true;
        }
    }
}
