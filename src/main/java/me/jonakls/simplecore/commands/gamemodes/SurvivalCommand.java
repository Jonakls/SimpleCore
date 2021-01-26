package me.jonakls.simplecore.commands.gamemodes;

import me.jonakls.simplecore.Manager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class SurvivalCommand implements CommandExecutor {

    private final Manager manager;

    public SurvivalCommand(Manager manager){
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        String noPermissions = manager.getFiles().getLang().getString("messages.error.no-permissions");
        String noPlayer = manager.getFiles().getLang().getString("messages.error.no-player");

        String changeGamemode = manager.getFiles().getLang().getString("gamemode.change");
        String otherGamemode = manager.getFiles().getLang().getString("gamemode.change-other");
        String targetGamemode = manager.getFiles().getLang().getString("gamemode.target-change");
        String typeGamemode = manager.getFiles().getLang().getString("gamemode.type.survival");

        if (!(sender instanceof Player)){
            sender.sendMessage(manager.getFiles().getLang().getString("messages.error.no-console"));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.gamemode"))){
            p.sendMessage(noPermissions);
            return true;
        }
        if (!(args.length > 0)){
            if (!(p.hasPermission("simplecore.command.gamemode.survival"))){
                p.sendMessage(noPermissions);
                return true;
            }
            p.setGameMode(GameMode.SURVIVAL);
            p.sendMessage(changeGamemode.replace("%type%", typeGamemode));
            return true;
        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null){
            p.sendMessage(noPlayer.replace("%player%", args[0]));
            return true;
        }
        p.sendMessage(otherGamemode.replace("%type%", typeGamemode).replace("%target%", target.getName()));

        target.sendMessage(targetGamemode.replace("%type%", typeGamemode).replace("%player%", p.getName()));
        target.setGameMode(GameMode.SURVIVAL);
        return true;
    }
}
