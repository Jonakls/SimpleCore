package me.jonakls.noxuscommands.commands.spawn;

import me.jonakls.noxuscommands.files.FileManager;
import me.jonakls.noxuscommands.utils.MessageReplacer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageReplacer.noConsole());
            return true;
        }
        Player player = (Player) sender;
        if (!(player.hasPermission("simplecore.command.setspawn"))) {
            player.sendMessage(MessageReplacer.noPermissions());
            return true;
        }

        if (!(FileManager.getSpawn().contains("spawn.world"))){

            FileManager.getSpawn().set("spawn.world", player.getWorld().getName());
            FileManager.getSpawn().set("spawn.x", player.getLocation().getX());
            FileManager.getSpawn().set("spawn.y", player.getLocation().getY());
            FileManager.getSpawn().set("spawn.z", player.getLocation().getZ());
            FileManager.getSpawn().set("spawn.yaw", player.getLocation().getYaw());
            FileManager.getSpawn().set("spawn.pitch", player.getLocation().getPitch());
            FileManager.getSpawn().save();

            player.sendMessage(MessageReplacer.prefix(FileManager.getLang().getString("spawn.spawn-set")
                    .replace("%world%", player.getWorld().getName())
                    .replace("%x%", ""+player.getLocation().getX())
                    .replace("%y%", ""+player.getLocation().getY())
                    .replace("%z%", ""+player.getLocation().getZ())
                    .replace("%yaw%", ""+player.getLocation().getYaw())
                    .replace("%pitch%", ""+player.getLocation().getPitch())));

            return true;
        }

        player.sendMessage(MessageReplacer.prefix(FileManager.getLang().getString("spawn.spawn-exist")));

        return true;
    }
}
