package me.jonakls.noxuscommands.commands;

import me.jonakls.noxuscommands.Service;
import me.jonakls.noxuscommands.files.FileManager;
import me.jonakls.noxuscommands.utils.ColorApply;
import me.jonakls.noxuscommands.utils.MessageReplacer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GeneralCommand implements CommandExecutor {

    private final Service service;

    public GeneralCommand(Service service){
        this.service = service;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            if (!(args.length > 0)){
                sender.sendMessage(MessageReplacer.prefix(FileManager.getLang().getString("messages.error.unknown-command")));
                return true;
            }
            switch (args[0].toLowerCase()) {
                case "help":
                    for (String help : FileManager.getLang().getStringList("messages.console.help")) {
                        sender.sendMessage(help.replace("%version%", service.getPlugin().getDescription().getVersion()));
                    }
                    return true;
                case "reload":
                    FileManager.getLang().reload();
                    FileManager.getConfig().reload();
                    FileManager.getData().reload();
                    FileManager.getWarps().reload();
                    sender.sendMessage(MessageReplacer.prefix("%prefix% &aConfig has been reloaded!"));
                    return true;
                case "about":
                    sender.sendMessage("You run "+ service.getPlugin().getDescription().getName()+
                            " in a version: "+ service.getPlugin().getDescription().getVersion());
                    sender.sendMessage("made by: "+ service.getPlugin().getDescription().getAuthors());
                    return true;
                default:
                    sender.sendMessage(FileManager.getLang().getString("messages.error.unknown-command")
                            .replace("%prefix%"
                                    , FileManager.getLang().getString("messages.prefix")));
                    return true;
            }
        }
        Player player = (Player) sender;
        if (!(args.length > 0)){
            player.sendMessage(MessageReplacer.prefix(FileManager.getLang().getString("messages.error.unknown-command")));
            return true;
        }
        if (!(player.hasPermission("simplecore.command.admin"))){
            player.sendMessage(MessageReplacer.noPermissions());
            return true;
        }
        switch (args[0].toLowerCase()) {
            case "help":
                for (String help : FileManager.getLang().getStringList("messages.player.help")) {
                    player.sendMessage(help.replace("%version%", service.getPlugin().getDescription().getVersion()));
                }
                return true;
            case "commands":
                for (String commands : FileManager.getLang().getStringList("messages.commands")) {
                    player.sendMessage(commands.replace("%version%", service.getPlugin().getDescription().getVersion()));
                }
                return true;
            case "reload":
                FileManager.getLang().reload();
                FileManager.getConfig().reload();
                FileManager.getData().reload();
                FileManager.getWarps().reload();
                player.sendMessage(MessageReplacer.prefix("%prefix% &aConfig has been reloaded!"));

                Bukkit.getConsoleSender().sendMessage(MessageReplacer.prefix("%prefix% Config has been reloaded by: "+player.getName()));
                return true;
            case "about":
                player.sendMessage(ColorApply.apply("&aYou run &b"+ service.getPlugin().getDescription().getName()+
                        " &ain a version: &f"+ service.getPlugin().getDescription().getVersion()));
                player.sendMessage(ColorApply.apply("&aMade by: &b"+ service.getPlugin().getDescription().getAuthors()));
                return true;
            default:
                player.sendMessage(MessageReplacer.prefix(FileManager.getLang().getString("messages.error.unknown-command")));
                return true;
        }
    }
}
