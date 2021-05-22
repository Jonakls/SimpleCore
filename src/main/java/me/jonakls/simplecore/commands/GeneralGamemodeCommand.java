package me.jonakls.simplecore.commands;

import me.jonakls.simplecore.files.FileManager;
import me.jonakls.simplecore.utils.MessageReplacer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GeneralGamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(FileManager.getLang().getString("messages.error.no-console"));
            return true;
        }
        Player player = (Player) sender;

        if (!(player.hasPermission("simplecore.command.gamemode"))){
            player.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }
        if (!(args.length > 0)){
            player.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }
        if (!(player.hasPermission("simplecore.command.gamemode.creative"))){
            player.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }

        if (args[0].equalsIgnoreCase("creative")
                || args[0].equalsIgnoreCase("1")
                || args[0].equalsIgnoreCase("c")){

            if(!(args.length > 1)){
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage(MessageReplacer.gamemode(
                        FileManager.getLang().getString("gamemode.change"),
                        FileManager.getLang().getString("gamemode.type.creative"),
                        player));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);

            if (target == null){
                player.sendMessage(MessageReplacer.noPlayer(args[1]));
                return true;
            }
            player.sendMessage(MessageReplacer.gamemode(
                    FileManager.getLang().getString("gamemode.change-other"),
                    FileManager.getLang().getString("gamemode.type.creative"),
                    target));

            target.sendMessage(MessageReplacer.gamemode(
                    FileManager.getLang().getString("gamemode.target-change"),
                    FileManager.getLang().getString("gamemode.type.creative"),
                    target));

            target.setGameMode(GameMode.CREATIVE);
            return true;
        }

        if (!(player.hasPermission("simplecore.command.gamemode.survival"))){

            player.sendMessage(MessageReplacer.noPermissions());
            return true;
        }

        if (args[0].equalsIgnoreCase("survival")
                || args[0].equalsIgnoreCase("0")
                || args[0].equalsIgnoreCase("s")){

            if(!(args.length > 1)){
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(MessageReplacer.gamemode(
                        FileManager.getLang().getString("gamemode.change"),
                        FileManager.getLang().getString("gamemode.type.survival"),
                        player));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){
                player.sendMessage(MessageReplacer.noPlayer(args[1]));
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
        if (!(player.hasPermission("simplecore.command.gamemode.adventure"))){
            player.sendMessage(FileManager.getLang().getString("messages.error.no-permissions"
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix"))));
            return true;
        }
        if (args[0].equalsIgnoreCase("adventure")
                || args[0].equalsIgnoreCase("2")
                || args[0].equalsIgnoreCase("a")){

            if(!(args.length > 1)){
                player.setGameMode(GameMode.ADVENTURE);
                player.sendMessage(MessageReplacer.gamemode(
                        FileManager.getLang().getString("gamemode.change"),
                        FileManager.getLang().getString("gamemode.type.adventure"),
                        player));

                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){
                player.sendMessage(MessageReplacer.noPlayer(args[1]));
                return true;
            }
            player.sendMessage(MessageReplacer.gamemode(
                    FileManager.getLang().getString("gamemode.change-other"),
                    FileManager.getLang().getString("gamemode.type.adventure"),
                    target));

            target.sendMessage(MessageReplacer.gamemode(
                    FileManager.getLang().getString("gamemode.target-change"),
                    FileManager.getLang().getString("gamemode.type.adventure"),
                    player));

            target.setGameMode(GameMode.ADVENTURE);
            return true;
        }
        if (!(player.hasPermission("simplecore.command.gamemode.spectator"))){
            player.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }
        if (args[0].equalsIgnoreCase("spectator")
                || args[0].equalsIgnoreCase("3")
                || args[0].equalsIgnoreCase("sp")){

            if(!(args.length > 1)){
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(MessageReplacer.gamemode(
                        FileManager.getLang().getString("gamemode.change"),
                        FileManager.getLang().getString("gamemode.type.spectator"),
                        player));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){
                player.sendMessage(MessageReplacer.noPlayer(args[1]));
                return true;
            }
            player.sendMessage(MessageReplacer.gamemode(
                    FileManager.getLang().getString("gamemode.change-other"),
                    FileManager.getLang().getString("gamemode.type.spectator"),
                    target));

            target.sendMessage(MessageReplacer.gamemode(
                    FileManager.getLang().getString("gamemode.target-change"),
                    FileManager.getLang().getString("gamemode.type.spectator"),
                    player));
            target.setGameMode(GameMode.SPECTATOR);
            return true;
        }
        player.sendMessage(FileManager.getLang().getString("usages.general-gamemode")
                .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
        return true;
    }
}
