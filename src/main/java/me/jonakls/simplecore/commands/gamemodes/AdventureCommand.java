package me.jonakls.simplecore.commands.gamemodes;

import me.jonakls.simplecore.files.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdventureCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){

            sender.sendMessage(FileManager.getLang().getString("messages.error.no-console").
                    replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;

        }

        Player player = (Player) sender;

        if (!(player.hasPermission("simplecore.command.gamemode"))){
            player.sendMessage(FileManager.getLang().getString("messages.error.no-permissions").
                    replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;

        }
        if (!(args.length > 0)){

            if (!(player.hasPermission("simplecore.command.gamemode.adventure"))){
                player.sendMessage(FileManager.getLang().getString("messages.error.no-permissions").
                        replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
                return true;

            }

            player.setGameMode(GameMode.ADVENTURE);
            player.sendMessage(FileManager.getLang().getString("gamemode.change").
                    replace("%type%", FileManager.getLang().getString("gamemode.type.adventure")));
            return true;

        }

        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null){

            player.sendMessage(FileManager.getLang().getString("messages.error.no-player").
                    replace("%prefix%", FileManager.getLang().getString("messages.prefix")).
                    replace("%player%", args[0]));
            return true;

        }

        player.sendMessage(FileManager.getLang().getString("gamemode.change-other").
                replace("%type%", FileManager.getLang().getString("gamemode.type.adventure")).
                replace("%target%", target.getName()));

        target.sendMessage(FileManager.getLang().getString("gamemode.target-change").
                replace("%type%", FileManager.getLang().getString("gamemode.type.adventure")).
                replace("%player%", player.getName()));

        target.setGameMode(GameMode.ADVENTURE);

        return true;

    }
}
