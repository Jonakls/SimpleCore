package me.jonakls.simplecore.commands;

import me.jonakls.simplecore.Service;
import me.jonakls.simplecore.files.FileManager;
import me.jonakls.simplecore.utils.ColorApply;
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
                sender.sendMessage(FileManager.getLang().getString("messages.error.unknown-command")
                        .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
                return true;
            }
            switch (args[0]) {
                case "help":
                    for (String help : FileManager.getLang().getStringList("messages.console.help")) {
                        sender.sendMessage(help.replace("%version%", service.getSimpleCore().getDescription().getVersion()));
                    }
                    return true;
                case "reload":
                    FileManager.getLang().reload();
                    FileManager.getConfig().reload();
                    FileManager.getData().reload();
                    FileManager.getWarps().reload();
                    return true;
                case "about":
                    sender.sendMessage("You run "+ service.getSimpleCore().getDescription().getName()+
                            " in a version: "+ service.getSimpleCore().getDescription().getVersion());
                    sender.sendMessage("made by: "+ service.getSimpleCore().getDescription().getAuthors());
                    return true;
                default:
                    sender.sendMessage(FileManager.getLang().getString("messages.error.unknown-command")
                            .replace("%prefix%"
                                    , FileManager.getLang().getString("messages.prefix")));
                    return true;
            }
        }
        Player p = (Player) sender;
        if (!(args.length > 0)){
            p.sendMessage(FileManager.getLang().getString("messages.error.unknown-command")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }
        if (!(p.hasPermission("simplecore.command.admin"))){
            p.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }
        switch (args[0]) {
            case "help":
                for (String help : FileManager.getLang().getStringList("messages.player.help")) {
                    p.sendMessage(help.replace("%version%", service.getSimpleCore().getDescription().getVersion()));
                }
                return true;
            case "commands":
                for (String commands : FileManager.getLang().getStringList("messages.commands")) {
                    p.sendMessage(commands.replace("%version%", service.getSimpleCore().getDescription().getVersion()));
                }
                return true;
            case "reload":
                FileManager.getLang().reload();
                FileManager.getConfig().reload();
                FileManager.getData().reload();
                FileManager.getWarps().reload();
                p.sendMessage(ColorApply.apply("&8[&bSimpleCore&8] &aConfig has been reloaded!"));

                Bukkit.getConsoleSender().sendMessage(ColorApply.apply(
                        "&8[&bSimpleCore&8] Config has been reloaded by: "+p.getName()));
                return true;
            case "about":
                p.sendMessage(ColorApply.apply("&aYou run &b"+ service.getSimpleCore().getDescription().getName()+
                        " &ain a version: &f"+ service.getSimpleCore().getDescription().getVersion()));
                p.sendMessage(ColorApply.apply("&aMade by: &b"+ service.getSimpleCore().getDescription().getAuthors()));
                return true;
            default:
                p.sendMessage(FileManager.getLang().getString("messages.error.unknown-command")
                        .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
                return true;
        }
    }
}
