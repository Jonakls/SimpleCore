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

public class IncognitMode implements CommandExecutor {

    private final SimpleCore simpleCore;
    private final ParseColors colors = new ParseColors();

    public IncognitMode(SimpleCore simpleCore){
        this.simpleCore = simpleCore;
    }


    ConsoleCommandSender console = Bukkit.getConsoleSender();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration messagesFile = new MessagesFile(simpleCore).getMessages();

        if (!(sender instanceof Player)){
            console.sendMessage(colors.setColor(messagesFile.getString("Messages.error.no-console")));
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
                p.sendMessage(colors.setColor(messagesFile.getString("Vanish.message"
                        .replace("%value%", messagesFile.getString("Vanish.type.enable")))));

                Bukkit.getOnlinePlayers().forEach(online -> {
                        online.hidePlayer(p);
                });
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){
                p.sendMessage(colors.setColor(messagesFile.getString("Messages.error.no-player".replace("%player%", args[1]))));
                return true;
            }
            Bukkit.getOnlinePlayers().forEach(online -> {
                    online.hidePlayer(target);
                    p.sendMessage(colors.setColor(messagesFile.getString("Vanish.other-message"
                            .replace("%value%", messagesFile.getString("Vanish.type.enable")
                            .replace("%target%", args[1])))));
                    target.sendMessage(colors.setColor(messagesFile.getString("Vanish.other-message"
                            .replace("%value%", messagesFile.getString("Vanish.type.enable")
                            .replace("%player%", p.getName())))));
            });
            return true;
        }
        if (args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("off")){
            if (!(args.length > 1)){
                p.sendMessage(colors.setColor(messagesFile.getString("Vanish.message"
                        .replace("%value%", messagesFile.getString("Vanish.type.disable")))));

                Bukkit.getOnlinePlayers().forEach(online -> {
                        online.showPlayer(p);
                });
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){
                p.sendMessage(colors.setColor(messagesFile.getString("Messages.error.no-player")));
                return true;
            }
            Bukkit.getOnlinePlayers().forEach(online -> {
                p.sendMessage(colors.setColor(messagesFile.getString("Vanish.other-message"
                        .replace("%value%", messagesFile.getString("Vanish.type.disable")
                        .replace("%target%", args[1])))));
                target.sendMessage(colors.setColor(messagesFile.getString("Vanish.other-message"
                        .replace("%value%", messagesFile.getString("Vanish.type.disable")
                        .replace("%player%", p.getName())))));
                online.showPlayer(target);
            });
        }
        return true;
    }
}
