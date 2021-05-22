package me.jonakls.noxuscommands.commands;

import me.jonakls.noxuscommands.files.FileManager;
import me.jonakls.noxuscommands.utils.MessageReplacer;
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

                Bukkit.getOnlinePlayers().forEach(online -> online.kickPlayer(stringBuilder1.toString().
                        replace("%player%", FileManager.getLang().getString("stop.console-name")).
                        replace("%reason%", FileManager.getLang().getString("stop.no-reason")).
                        replace("%serverName%", FileManager.getLang().getString("stop.prefix"))));
                Bukkit.shutdown();
            }
            int i = 0;
            while (i < args.length) {
                stringBuilder.append(args[i]).append(' ');
                ++i;
            }

            for (String line: FileManager.getLang().getStringList("stop.message")){
                stringBuilder1.append(line).append('\n');
            }

            Bukkit.getOnlinePlayers().forEach(online -> online.kickPlayer(stringBuilder1.toString().
                    replace("%player%", FileManager.getLang().getString("stop.console-name")).
                    replace("%reason%", stringBuilder.toString()).
                    replace("%serverName%", FileManager.getLang().getString("stop.prefix"))));
            Bukkit.shutdown();

            return true;
        }

        Player player = (Player) sender;

        if (!(player.hasPermission("simplecore.command.stop"))){
            player.sendMessage(MessageReplacer.noPermissions());
            return true;
        }
        if (!(args.length > 0)){

            for (String line: FileManager.getLang().getStringList("stop.message")){
                stringBuilder1.append(line).append('\n');
            }

            Bukkit.getOnlinePlayers().forEach(online -> online.kickPlayer(stringBuilder1.toString().
                    replace("%player%", player.getName()).
                    replace("%reason%", FileManager.getLang().getString("stop.no-reason")).
                    replace("%serverName%", FileManager.getLang().getString("stop.prefix"))));
            Bukkit.shutdown();

            return true;
        }

        int i = 0;
        while (i < args.length) {
            stringBuilder.append(args[i]).append(' ');
            ++i;
        }

        for (String line: FileManager.getLang().getStringList("stop.message")){
            stringBuilder1.append(line).append('\n');
        }

        Bukkit.getOnlinePlayers().forEach(online -> online.kickPlayer(stringBuilder1.toString().
                replace("%player%", player.getName()).
                replace("%reason%", stringBuilder.toString()).
                replace("%serverName%", FileManager.getLang().getString("stop.prefix"))));
        Bukkit.shutdown();
        return true;
    }
}
