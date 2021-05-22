package me.jonakls.noxuscommands.commands;

import me.jonakls.noxuscommands.files.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder1 = new StringBuilder();

        if (!(sender instanceof Player)){
            if (!(args.length > 0)){

                for (String line: FileManager.getLang().getStringList("stop.message")){
                    stringBuilder1.append(line).append('\n');
                }
                String message = stringBuilder1.toString();

                Bukkit.getOnlinePlayers().forEach(online -> online.kickPlayer(message
                        .replace("%player%", FileManager.getLang().getString("stop.console-name"))
                        .replace("%reason%", FileManager.getLang().getString("stop.no-reason"))
                        .replace("%serverName%", FileManager.getLang().getString("stop.prefix"))));
                Bukkit.shutdown();
            }
            int i = 0;
            while (i < args.length) {
                stringBuilder.append(args[i]).append(' ');
                ++i;
            }
            String reason = stringBuilder.toString();

            for (String line: FileManager.getLang().getStringList("stop.message")){
                stringBuilder1.append(line).append('\n');
            }
            String message = stringBuilder1.toString();

            Bukkit.getOnlinePlayers().forEach(online -> online.kickPlayer(message
                    .replace("%player%", FileManager.getLang().getString("stop.console-name"))
                    .replace("%reason%", reason)
                    .replace("%serverName%", FileManager.getLang().getString("stop.prefix"))));
            Bukkit.shutdown();

            return true;
        }

        Player p = (Player) sender;

        if (!(p.hasPermission("simplecore.command.stop"))){
            p.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }
        if (!(args.length > 0)){

            for (String line: FileManager.getLang().getStringList("stop.message")){
                stringBuilder1.append(line).append('\n');
            }
            String message = stringBuilder1.toString();

            Bukkit.getOnlinePlayers().forEach(online -> online.kickPlayer(message
                    .replace("%player%", p.getName())
                    .replace("%reason%", FileManager.getLang().getString("stop.no-reason"))
                    .replace("%serverName%", FileManager.getLang().getString("stop.prefix"))));
            Bukkit.shutdown();

            return true;
        }

        int i = 0;
        while (i < args.length) {
            stringBuilder.append(args[i]).append(' ');
            ++i;
        }
        String reason = stringBuilder.toString();

        for (String line: FileManager.getLang().getStringList("stop.message")){
            stringBuilder1.append(line).append('\n');
        }
        String message = stringBuilder1.toString();

        Bukkit.getOnlinePlayers().forEach(online -> online.kickPlayer(message
                .replace("%player%", p.getName())
                .replace("%reason%", reason)
                .replace("%serverName%", FileManager.getLang().getString("stop.prefix"))));
        Bukkit.shutdown();
        return true;
    }
}
