package me.jonakls.simplecore.commands.warps;

import me.jonakls.simplecore.Service;
import me.jonakls.simplecore.handlers.WarpHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteWarpCommand implements CommandExecutor{

    private final Service service;
    public DeleteWarpCommand(Service service){
        this.service = service;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(service.getFiles().getLang().getString("messages.error.no-console"));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.setwarp"))){
            p.sendMessage(service.getFiles().getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;

        }
        if (!(args.length > 0)){
            p.sendMessage(service.getFiles().getLang().getString("usages.set-warp")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;
        }

        WarpHandler warp = new WarpHandler(service);
        warp.deleteWarp(args[0].toLowerCase());

        if (!(warp.getOperation())){
            p.sendMessage(service.getFiles().getLang().getString("warps.no-exist")
                    .replace("%warp%", args[0].toLowerCase()));

            return true;
        }
        p.sendMessage(service.getFiles().getLang().getString("warps.delete-warp")
                .replace("%warp%", args[0].toLowerCase()));

        return true;
    }
}
