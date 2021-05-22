package me.jonakls.simplecore.commands.spawn;

import me.jonakls.simplecore.files.FileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(FileManager.getLang().getString("messages.error.no-console")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.setspawn"))) {
            p.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }

        if (!(FileManager.getSpawn().contains("spawn.world"))){

            FileManager.getSpawn().set("spawn.world", p.getWorld().getName());
            FileManager.getSpawn().set("spawn.x", p.getLocation().getX());
            FileManager.getSpawn().set("spawn.y", p.getLocation().getY());
            FileManager.getSpawn().set("spawn.z", p.getLocation().getZ());
            FileManager.getSpawn().set("spawn.yaw", p.getLocation().getYaw());
            FileManager.getSpawn().set("spawn.pitch", p.getLocation().getPitch());
            FileManager.getSpawn().save();

            p.sendMessage(FileManager.getLang().getString("spawn.spawn-set")
                    .replace("%world%", p.getWorld().getName())
                    .replace("%x%", ""+p.getLocation().getX())
                    .replace("%y%", ""+p.getLocation().getY())
                    .replace("%z%", ""+p.getLocation().getZ())
                    .replace("%yaw%", ""+p.getLocation().getYaw())
                    .replace("%pitch%", ""+p.getLocation().getPitch()));

            return true;
        }

        p.sendMessage(FileManager.getLang().getString("spawn.spawn-exist"));

        return true;
    }
}
