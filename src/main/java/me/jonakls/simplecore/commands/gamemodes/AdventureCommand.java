package me.jonakls.simplecore.commands.gamemodes;

import me.jonakls.simplecore.Service;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdventureCommand implements CommandExecutor {

    private final Service service;

    public AdventureCommand(Service service){
        this.service = service;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(service.getFiles().getLang().getString("messages.error.no-console")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.gamemode"))){
            p.sendMessage(service.getFiles().getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;
        }
        if (!(args.length > 0)){
            if (!(p.hasPermission("simplecore.command.gamemode.adventure"))){
                p.sendMessage(service.getFiles().getLang().getString("messages.error.no-permissions")
                        .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
                return true;
            }
            p.setGameMode(GameMode.ADVENTURE);
            p.sendMessage(service.getFiles().getLang().getString("gamemode.change").replace("%type%", service.getFiles().getLang().getString("gamemode.type.adventure")));
            return true;
        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null){
            p.sendMessage(service.getFiles().getLang().getString("messages.error.no-player").replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")).replace("%player%", args[0]));
            return true;
        }
        p.sendMessage(service.getFiles().getLang().getString("gamemode.change-other").replace("%type%", service.getFiles().getLang().getString("gamemode.type.adventure")).replace("%target%", target.getName()));

        target.sendMessage(service.getFiles().getLang().getString("gamemode.target-change").replace("%type%", service.getFiles().getLang().getString("gamemode.type.adventure")).replace("%player%", p.getName()));
        target.setGameMode(GameMode.ADVENTURE);
        return true;
    }
}
