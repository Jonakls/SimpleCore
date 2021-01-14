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

public class VanishMode implements CommandExecutor {

    private final SimpleCore simpleCore;
    private final ParseColors colors = new ParseColors();

    public VanishMode(SimpleCore simpleCore){
        this.simpleCore = simpleCore;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration messagesFile = new MessagesFile(simpleCore).getMessages();

        if (!(sender instanceof Player)){
            sender.sendMessage(colors.setColor(messagesFile.getString("Messages.error.no-console")));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.vanish"))){
            p.sendMessage(colors.setColor(messagesFile.getString("Messages.error.no-permissions")));
            return true;
        }
        if (!(args.length > 0)){
            p.sendMessage(colors.setColor(messagesFile.getString("Usages.vanish")));
            return true;
        }
        if (args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("on")){
            if (!(args.length > 1)){
                p.sendMessage(colors.setColor(messagesFile.getString("Vanish.message")
                        .replace("%type%", messagesFile.getString("Type.enable"))));

                for (Player online : Bukkit.getOnlinePlayers()) online.hidePlayer(p);
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){
                p.sendMessage(colors.setColor(messagesFile.getString("Messages.error.no-player").replace("%player%", args[1])));
                return true;
            }
            p.sendMessage(colors.setColor(messagesFile.getString("Vanish.other-message")
                    .replace("%type%", messagesFile.getString("Type.enable"))
                    .replace("%target%", target.getName())));
            target.sendMessage(colors.setColor(messagesFile.getString("Vanish.target-message")
                    .replace("%type%", messagesFile.getString("Type.enable"))
                    .replace("%player%", p.getName())));
            Bukkit.getOnlinePlayers().forEach(online -> online.hidePlayer(target));
            return true;
        }
        if (args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("off")){
            if (!(args.length > 1)){
                p.sendMessage(colors.setColor(messagesFile.getString("Vanish.message")
                    .replace("%type%", messagesFile.getString("Type.disable"))));

                Bukkit.getOnlinePlayers().forEach(online -> online.showPlayer(p));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){
                p.sendMessage(colors.setColor(messagesFile.getString("Messages.error.no-player")));
                return true;
            }
            p.sendMessage(colors.setColor(messagesFile.getString("Vanish.other-message")
                    .replace("%type%", messagesFile.getString("Type.disable"))
                    .replace("%target%", target.getName())));
            target.sendMessage(colors.setColor(messagesFile.getString("Vanish.target-message")
                    .replace("%type%", messagesFile.getString("Type.disable"))
                    .replace("%player%", p.getName())));
            Bukkit.getOnlinePlayers().forEach(online -> online.showPlayer(target));
        }
        p.sendMessage(colors.setColor(messagesFile.getString("Usages.vanish")));
        return true;
    }
}
