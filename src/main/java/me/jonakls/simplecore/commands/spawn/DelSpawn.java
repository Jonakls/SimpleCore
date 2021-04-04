package me.jonakls.simplecore.commands.spawn;

import me.jonakls.simplecore.Service;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelSpawn implements CommandExecutor {

    private final Service service;

    public DelSpawn(Service service){
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
        if (!(p.hasPermission("simplecore.command.delspawn"))) {
            p.sendMessage(service.getFiles().getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;
        }

        if (!(service.getFiles().getSpawn().contains("spawn.world"))){

            service.getFiles().getSpawn().set("spawn", null);
            service.getFiles().getSpawn().save();

            p.sendMessage(service.getFiles().getLang().getString("spawn.spawn-delete"));

            return true;
        }

        p.sendMessage(service.getFiles().getLang().getString("spawn.spawn-no-exist"));

        return true;
    }
}
