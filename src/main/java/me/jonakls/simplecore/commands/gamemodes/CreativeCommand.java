package me.jonakls.simplecore.commands.gamemodes;

import me.jonakls.simplecore.Service;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreativeCommand implements CommandExecutor {

    private final Service service;

    public CreativeCommand(Service service){
        this.service = service;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(service.getFiles().getLang().getString("messages.error.no-console"));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.gamemode"))){
            p.sendMessage(service.getFiles().getLang().getString("messages.error.no-permissions"));
            return true;
        }
        if (!(args.length > 0)){
            if (!(p.hasPermission("simplecore.command.gamemode.creative"))){
                p.sendMessage(service.getFiles().getLang().getString("messages.error.no-permissions"));
                return true;
            }
            p.setGameMode(GameMode.CREATIVE);
            p.sendMessage(service.getFiles().getLang().getString("gamemode.change").replace("%type%", service.getFiles().getLang().getString("gamemode.type.creative")));
            return true;
        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null){
            p.sendMessage(service.getFiles().getLang().getString("messages.error.no-player").replace("%player%", args[0]));
            return true;
        }
        p.sendMessage(service.getFiles().getLang().getString("gamemode.change-other")
                .replace("%type%", service.getFiles().getLang().getString("gamemode.type.creative")).replace("%target%", target.getName()));

        target.sendMessage(service.getFiles().getLang().getString("gamemode.target-change")
                .replace("%type%", service.getFiles().getLang().getString("gamemode.type.creative")).replace("%player%", p.getName()));
        target.setGameMode(GameMode.CREATIVE);
        return true;
    }
}
