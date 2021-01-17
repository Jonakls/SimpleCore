package me.jonakls.simplecore.commands;

import me.jonakls.simplecore.SimpleCore;
import me.jonakls.simplecore.files.MessagesFile;
import me.jonakls.simplecore.objects.ParseColors;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class GeneralCommand implements CommandExecutor {

    private final SimpleCore simpleCore;
    private final ParseColors colors = new ParseColors();

    public GeneralCommand(SimpleCore simpleCore){
        this.simpleCore = simpleCore;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration messages = new MessagesFile(simpleCore).getMessages();

        List<String> helpConsole = messages.getStringList("messages.console.help");
        List<String> helpPlayer = messages.getStringList("messages.player.help");
        String unknownCommand = messages.getString("messages.error.unknown-command");


        if(!(sender instanceof Player)){
            if (!(args.length > 0)){
                sender.sendMessage(colors.setColor(unknownCommand));
                return true;
            }
            switch (args[0]) {
                case "help":
                    for (String help : helpConsole) {
                        sender.sendMessage(help.replace("%version%", simpleCore.versionPlugin));
                    }
                    return true;
                case "reload":
                    new MessagesFile(simpleCore).reloadMessages();
                    simpleCore.reloadConfig();
                    sender.sendMessage("Config has been reload!");
                    return true;
                case "about":
                    sender.sendMessage("You run "+simpleCore.namePlugin+" in a version: "+simpleCore.versionPlugin);
                    sender.sendMessage("made by: "+simpleCore.authorPlugin);
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
        switch (args[0]) {
            case "help":
                for (String help : helpPlayer) {
                    p.sendMessage(colors.setColor(help.replace("%version%", simpleCore.versionPlugin)));
                }
                return true;
            case "reload":
                new MessagesFile(simpleCore).reloadMessages();
                simpleCore.reloadConfig();
                p.sendMessage(colors.setColor("&aConfig has been reload!"));
                Bukkit.getConsoleSender().sendMessage("Config has been reload by: "+p.getName());
                return true;
            case "about":
                p.sendMessage(colors.setColor("&7You run &b"+simpleCore.namePlugin+" &7in a version: &f"+simpleCore.versionPlugin));
                p.sendMessage(colors.setColor("&7Made by: &a"+simpleCore.authorPlugin));
                return true;
            default:
                p.sendMessage(colors.setColor(unknownCommand));
                return true;
        }
    }
}
