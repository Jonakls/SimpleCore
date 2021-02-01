package me.jonakls.simplecore.commands;

import me.jonakls.simplecore.Manager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StopCommand implements CommandExecutor {

    private Manager manager;

    public StopCommand(Manager manager){
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder1 = new StringBuilder();

        if (!(sender instanceof Player)){
            sender.sendMessage(manager.getFiles().getLang().getString("messages.error.no-console"));
            return true;
        }

        Player p = (Player) sender;

        if (!(p.hasPermission("simplecore.command.stop"))){
            p.sendMessage(manager.getFiles().getLang().getString("messages.error.no-permissions"));
            return true;
        }
        if (!(args.length > 0)){
            p.sendMessage(manager.getFiles().getLang().getString("usages.stop-message"));
            return true;
        }

        int i = 0;
        while (i < args.length) {
            stringBuilder.append(args[i]).append(' ');
            ++i;
        }
        String reason = stringBuilder.toString();

        for (String line: manager.getFiles().getLang().getStringList("stop.message")){
            stringBuilder1.append(line).append('\n');
        }
        String message = stringBuilder1.toString();

        Bukkit.getOnlinePlayers().forEach(online -> online.kickPlayer(message
                .replace("%player%", p.getName())
                .replace("%reason%", reason)
                .replace("%serverName%", manager.getFiles().getLang().getString("stop.prefix"))));
        Bukkit.shutdown();
        return true;
    }
}
