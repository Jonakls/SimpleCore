package me.jonakls.simplecore.commands.weather;

import me.jonakls.simplecore.files.FileManager;
import me.jonakls.simplecore.utils.MessageReplacer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ThunderCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(FileManager.getLang().getString("messages.error.no-console")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.weather.thunder"))){
            p.sendMessage(MessageReplacer.noPermissions());
            return true;
        }

        p.getWorld().setThundering(true);
        p.sendMessage(FileManager.getLang().getString("weather.weather-set")
                .replace("%world%", p.getWorld().getName())
                .replace("%weather%","" + p.getWorld().getThunderDuration()));

        return true;
    }
}
