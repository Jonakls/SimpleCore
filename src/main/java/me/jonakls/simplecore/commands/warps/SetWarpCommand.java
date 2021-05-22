package me.jonakls.simplecore.commands.warps;

import me.jonakls.simplecore.files.FileManager;
import me.jonakls.simplecore.handlers.WarpHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(FileManager.getLang().getString("messages.error.no-console"));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.setwarp"))){
            p.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;

        }
        if (!(args.length > 0)){
            p.sendMessage(FileManager.getLang().getString("usages.set-warp")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }

        WarpHandler warp = new WarpHandler();
        warp.setWarp(args[0].toLowerCase(), p.getLocation());

        if (!(warp.getOperation())){
            p.sendMessage(FileManager.getLang().getString("warps.warp-exist")
            .replace("%warp%", args[0].toLowerCase()));

            return true;
        }
        p.sendMessage(FileManager.getLang().getString("warps.set-warp")
                .replace("%warp%", args[0].toLowerCase()));

        return true;
    }
}
