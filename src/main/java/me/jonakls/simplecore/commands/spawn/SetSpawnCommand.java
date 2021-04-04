package me.jonakls.simplecore.commands.spawn;

import me.jonakls.simplecore.Service;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    private final Service service;

    public SetSpawnCommand(Service service){
        this.service = service;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(service.getFiles().getLang().getString("messages.error.no-console")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.setspawn"))) {
            p.sendMessage(service.getFiles().getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;
        }

        if (!(service.getFiles().getSpawn().contains("spawn.world"))){

            service.getFiles().getSpawn().set("spawn.world", p.getWorld().getName());
            service.getFiles().getSpawn().set("spawn.x", p.getLocation().getX());
            service.getFiles().getSpawn().set("spawn.y", p.getLocation().getY());
            service.getFiles().getSpawn().set("spawn.z", p.getLocation().getZ());
            service.getFiles().getSpawn().set("spawn.yaw", p.getLocation().getYaw());
            service.getFiles().getSpawn().set("spawn.pitch", p.getLocation().getPitch());
            service.getFiles().getSpawn().save();

            p.sendMessage(service.getFiles().getLang().getString("spawn.spawn-set")
                    .replace("%world%", p.getWorld().getName())
                    .replace("%x%", ""+p.getLocation().getX())
                    .replace("%y%", ""+p.getLocation().getY())
                    .replace("%z%", ""+p.getLocation().getZ())
                    .replace("%yaw%", ""+p.getLocation().getYaw())
                    .replace("%pitch%", ""+p.getLocation().getPitch()));

            return true;
        }

        p.sendMessage(service.getFiles().getLang().getString("spawn.spawn-exist"));

        return true;
    }
}
