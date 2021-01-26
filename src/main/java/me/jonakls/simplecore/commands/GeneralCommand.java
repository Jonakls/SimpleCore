package me.jonakls.simplecore.commands;

import me.jonakls.simplecore.Manager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class GeneralCommand implements CommandExecutor {

    private final Manager manager;

    public GeneralCommand(Manager manager){
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        List<String> helpConsole = manager.getFiles().getLang().getStringList("messages.console.help");
        List<String> helpPlayer = manager.getFiles().getLang().getStringList("messages.player.help");
        String unknownCommand = manager.getFiles().getLang().getString("messages.error.unknown-command");


        if(!(sender instanceof Player)){
            if (!(args.length > 0)){
                sender.sendMessage(unknownCommand);
                return true;
            }
            switch (args[0]) {
                case "help":
                    for (String help : helpConsole) {
                        sender.sendMessage(help.replace("%version%", manager.getSimpleCore().versionPlugin));
                    }
                    return true;
                case "reload":
                    manager.getFiles().getLang().reload();
                    manager.getFiles().getConfig().reload();
                    manager.getFiles().getMenus().reload();
                    return true;
                case "about":
                    sender.sendMessage("You run "+manager.getSimpleCore().namePlugin+" in a version: "+manager.getSimpleCore().versionPlugin);
                    sender.sendMessage("made by: "+manager.getSimpleCore().authorPlugin);
                    return true;
                default:
                    sender.sendMessage("Unknown command, use /simplecore help");
                    return true;
            }
        }
        Player p = (Player) sender;
        if (!(args.length > 0)){
            p.sendMessage(unknownCommand);
            return true;
        }
        if (!(p.hasPermission("simplecore.command.admin"))){
            p.sendMessage(manager.getFiles().getLang().getString("messages.error.no-permissions"));
            return true;
        }
        switch (args[0]) {
            case "help":
                for (String help : helpPlayer) {
                    p.sendMessage(help.replace("%version%", manager.getSimpleCore().versionPlugin));
                }
                return true;
            case "reload":
                manager.getFiles().getLang().reload();
                manager.getFiles().getConfig().reload();
                manager.getFiles().getMenus().reload();
                p.sendMessage("&aConfig has been reload!");
                Bukkit.getConsoleSender().sendMessage("Config has been reload by: "+p.getName());
                return true;
            case "about":
                p.sendMessage("&7You run &b"+manager.getSimpleCore().namePlugin+" &7in a version: &f"+manager.getSimpleCore().versionPlugin);
                p.sendMessage("&7Made by: &a"+manager.getSimpleCore().authorPlugin);
                return true;
            default:
                p.sendMessage(unknownCommand);
                return true;
        }
    }
}
