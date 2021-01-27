package me.jonakls.simplecore.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import me.jonakls.simplecore.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FakeMessages implements CommandExecutor {

    private final Manager manager;

    public FakeMessages(Manager manager){
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        StringBuilder stringBuilder = new StringBuilder();

        if (!(sender instanceof Player)){
            sender.sendMessage(manager.getFiles().getLang().getString("messages.error.no-console"));
            return true;
        }

        Player p = (Player) sender;

        if(!(p.hasPermission("simplecore.command.fake"))){
            p.sendMessage(manager.getFiles().getLang().getString("messages.error.no-permissions"));
            return true;
        }
        if (!(args.length > 0)){
            p.sendMessage(manager.getFiles().getLang().getString("usages.fake-messages"));
            return true;
        }
        switch (args[0]){
            case "message":

                if (!(args.length > 1)){
                    p.sendMessage(manager.getFiles().getLang().getString("usages.fake-messages"));
                    return true;
                }
                for (int i = 1; i < args.length; ++i) {
                    stringBuilder.append(args[i]).append(' ');
                }
                String message = stringBuilder.toString();
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
                return true;

            case "join":

                if (!(args.length > 1)){
                    p.sendMessage(manager.getFiles().getLang().getString("usages.fake-messages"));
                    return true;
                }
                Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(p, manager.getFiles().getLang().getString("events.join-player.message").replace("%player%", args[1])));
                return true;

            case "leave" :

                if (!(args.length > 1)){
                    p.sendMessage(manager.getFiles().getLang().getString("usages.fake-messages"));
                    return true;
                }
                Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(p, manager.getFiles().getLang().getString("events.quit-player.message").replace("%player%", args[1])));
                return true;
            default:
                p.sendMessage(manager.getFiles().getLang().getString("usages.fake-messages"));
                return true;
        }
    }
}
