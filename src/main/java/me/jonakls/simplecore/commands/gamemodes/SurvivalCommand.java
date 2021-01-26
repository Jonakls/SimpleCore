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

        if (!(sender instanceof Player)){
            sender.sendMessage(manager.getFiles().getLang().getString("messages.error.no-console"));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.gamemode"))){
            p.sendMessage(manager.getFiles().getLang().getString("messages.error.no-permissions"));
            return true;
        }
        if (!(args.length > 0)){
            if (!(p.hasPermission("simplecore.command.gamemode.survival"))){
                p.sendMessage(manager.getFiles().getLang().getString("messages.error.no-permissions"));
                return true;
            }
            p.setGameMode(GameMode.SURVIVAL);
            p.sendMessage(manager.getFiles().getLang().getString("gamemode.change").replace("%type%", manager.getFiles().getLang().getString("gamemode.type.survival")));
            return true;
        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null){
            p.sendMessage(manager.getFiles().getLang().getString("messages.error.no-player").replace("%player%", args[0]));
            return true;
        }
        p.sendMessage(manager.getFiles().getLang().getString("gamemode.change-other").replace("%type%", manager.getFiles().getLang().getString("gamemode.type.survival")).replace("%target%", target.getName()));

        target.sendMessage(manager.getFiles().getLang().getString("gamemode.target-change").replace("%type%", manager.getFiles().getLang().getString("gamemode.type.survival")).replace("%player%", p.getName()));
        target.setGameMode(GameMode.SURVIVAL);
        return true;
    }
}
