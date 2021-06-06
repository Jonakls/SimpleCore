package me.jonakls.noxuscommands.commands.spawn;

import me.jonakls.noxuscommands.files.FileManager;
import me.jonakls.noxuscommands.utils.MessageReplacer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageReplacer.noConsole());
            return true;
        }
        Player player = (Player) sender;
        if (!(player.hasPermission("simplecore.command.delspawn"))) {
            player.sendMessage(MessageReplacer.noPermissions());
            return true;
        }

        if (FileManager.getSpawn().contains("spawn.world")){

            FileManager.getSpawn().set("spawn", null);
            FileManager.getSpawn().save();

            player.sendMessage(FileManager.getLang().getString("spawn.spawn-delete"));

            return true;
        }

        player.sendMessage(FileManager.getLang().getString("spawn.spawn-no-exist"));

        return true;
    }
}
