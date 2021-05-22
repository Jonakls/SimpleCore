package me.jonakls.simplecore.commands.spawn;

import me.jonakls.simplecore.files.FileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(FileManager.getLang().getString("messages.error.no-console")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.delspawn"))) {
            p.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }

        if (!(FileManager.getSpawn().contains("spawn.world"))){

            FileManager.getSpawn().set("spawn.", null);
            FileManager.getSpawn().save();

            p.sendMessage(FileManager.getLang().getString("spawn.spawn-delete"));

            return true;
        }

        p.sendMessage(FileManager.getLang().getString("spawn.spawn-no-exist"));

        return true;
    }
}
