package me.jonakls.simplecore.commands.gamemodes;

import me.jonakls.simplecore.files.FileManager;
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

            sender.sendMessage(FileManager.getLang().getString("messages.error.no-console").
                    replace("%prefix%", FileManager.getLang().getString("messages.prefix")));

            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.gamemode"))){

            p.sendMessage(FileManager.getLang().getString("messages.error.no-permissions").
                    replace("%prefix%", FileManager.getLang().getString("messages.prefix")));

            return true;
        }
        if (!(args.length > 0)){
            if (!(p.hasPermission("simplecore.command.gamemode.survival"))){

                p.sendMessage(FileManager.getLang().getString("messages.error.no-permissions").
                        replace("%prefix%", FileManager.getLang().getString("messages.prefix")));

                return true;
            }

            p.setGameMode(GameMode.SURVIVAL);
            p.sendMessage(FileManager.getLang().getString("gamemode.change").
                    replace("%type%", FileManager.getLang().getString("gamemode.type.survival")));

            return true;
        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null){

            p.sendMessage(FileManager.getLang().getString("messages.error.no-player").
                    replace("%prefix%", FileManager.getLang().getString("messages.prefix")).
                    replace("%player%", args[0]));

            return true;
        }

        p.sendMessage(FileManager.getLang().getString("gamemode.change-other").
                replace("%type%", FileManager.getLang().getString("gamemode.type.survival")).
                replace("%target%", target.getName()));

        target.sendMessage(FileManager.getLang().getString("gamemode.target-change").
                replace("%type%", FileManager.getLang().getString("gamemode.type.survival")).
                replace("%player%", p.getName()));

        target.setGameMode(GameMode.SURVIVAL);
        return true;
    }
}
