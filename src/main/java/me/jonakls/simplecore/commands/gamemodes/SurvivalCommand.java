package me.jonakls.simplecore.commands.gamemodes;

import me.jonakls.simplecore.files.FileManager;
import me.jonakls.simplecore.utils.MessageReplacer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SurvivalCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){

            sender.sendMessage(MessageReplacer.noConsole());

            return true;
        }

        Player player = (Player) sender;

        if (!(player.hasPermission("simplecore.command.gamemode"))){

            player.sendMessage(MessageReplacer.noPermissions());

            return true;
        }
        if (!(args.length > 0)){
            if (!(player.hasPermission("simplecore.command.gamemode.survival"))){

                player.sendMessage(MessageReplacer.noPermissions());

                return true;
            }

            player.setGameMode(GameMode.SURVIVAL);

            player.sendMessage(MessageReplacer.gamemode(
                    FileManager.getLang().getString("gamemode.change"),
                    FileManager.getLang().getString("gamemode.type.survival"),
                    player));

            return true;
        }
        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null){

            player.sendMessage(MessageReplacer.noPlayer(args[0]));

            return true;
        }

        player.sendMessage(MessageReplacer.gamemode(
                FileManager.getLang().getString("gamemode.change-other"),
                FileManager.getLang().getString("gamemode.type.survival"),
                target));

        target.sendMessage(MessageReplacer.gamemode(
                FileManager.getLang().getString("gamemode.target-change"),
                FileManager.getLang().getString("gamemode.type.survival"),
                player));

        target.setGameMode(GameMode.SURVIVAL);
        return true;
    }
}