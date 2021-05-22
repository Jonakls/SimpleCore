package me.jonakls.noxuscommands.commands.warps;

import me.jonakls.noxuscommands.files.FileManager;
import me.jonakls.noxuscommands.handlers.WarpHandler;
import me.jonakls.noxuscommands.utils.MessageReplacer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(MessageReplacer.noConsole());
            return true;
        }
        Player player = (Player) sender;
        if (!(player.hasPermission("simplecore.command.setwarp"))){

            player.sendMessage(MessageReplacer.noPermissions());
            return true;

        }
        if (!(args.length > 0)){
            player.sendMessage(MessageReplacer.prefix(FileManager.getLang().getString("usages.set-warp")));
            return true;
        }

        WarpHandler warp = new WarpHandler();
        warp.setWarp(args[0].toLowerCase(), player.getLocation());

        if (!(warp.getOperation())){
            player.sendMessage(FileManager.getLang().getString("warps.warp-exist")
            .replace("%warp%", args[0].toLowerCase()));

            return true;
        }
        player.sendMessage(FileManager.getLang().getString("warps.set-warp")
                .replace("%warp%", args[0].toLowerCase()));

        return true;
    }
}
