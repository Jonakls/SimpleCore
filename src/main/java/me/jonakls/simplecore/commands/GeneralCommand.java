package me.jonakls.simplecore.commands;

import me.jonakls.simplecore.SimpleCore;
import me.jonakls.simplecore.files.MessagesFile;
import me.jonakls.simplecore.objects.ParseColors;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class GeneralCommand implements CommandExecutor {

    private SimpleCore simpleCore;
    private ParseColors colors = new ParseColors();

    public GeneralCommand(SimpleCore simpleCore){
        this.simpleCore = simpleCore;
    }


    ConsoleCommandSender console = Bukkit.getConsoleSender();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration messages = new MessagesFile(simpleCore).getMessages();
        colors = new ParseColors();

        List<String> helpConsole = messages.getStringList("Messages.console.help");
        List<String> helpPlayer = messages.getStringList("Messages.player.help");
        String unknownCommand = messages.getString("Messages.error.unknown-command");


        if(!(sender instanceof Player)){
            if (!(args.length > 0)){
                console.sendMessage(colors.setColor(unknownCommand));
                return true;
            }
            switch (args[0]) {
                case "help":
                    for (String help : helpConsole) {
                        console.sendMessage(help.replace("%version%", simpleCore.versionPlugin));
                    }
                    return true;
                case "reload":
                    new MessagesFile(simpleCore).reloadMessages();
                    simpleCore.reloadConfig();
                    console.sendMessage("Config has been reload!");
                    return true;
                case "about":
                    console.sendMessage("You run "+simpleCore.namePlugin+" in a version: "+simpleCore.versionPlugin);
                    console.sendMessage("made by: "+simpleCore.authorPlugin);
                    return true;
                default:
                    console.sendMessage("Unknown command, use /simplecore help");
                    return true;
            }
        }
        Player p = (Player) sender;
        if (!(args.length > 0)){
            p.sendMessage("Unknown command, use /simplecore help");
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
