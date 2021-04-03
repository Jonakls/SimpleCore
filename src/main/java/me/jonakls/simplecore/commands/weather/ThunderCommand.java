package me.jonakls.simplecore.commands.weather;

import me.jonakls.simplecore.Service;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ThunderCommand implements CommandExecutor {

    private final Service service;

    public ThunderCommand(Service service){
        this.service = service;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(service.getFiles().getLang().getString("messages.error.no-console")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.weather.thunder"))){
            p.sendMessage(service.getFiles().getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;
        }

        p.getWorld().setThundering(true);
        p.sendMessage(service.getFiles().getLang().getString("weather.weather-set")
                .replace("%world%", p.getWorld().getName())
                .replace("%weather%","" + p.getWorld().getThunderDuration()));

        return true;
    }
}
