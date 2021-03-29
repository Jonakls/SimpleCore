package me.jonakls.simplecore.commands;

import me.jonakls.simplecore.Service;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GeneralGamemodeCommand implements CommandExecutor {

    private final Service service;

    public GeneralGamemodeCommand(Service service){
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
            p.sendMessage(service.getFiles().getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;
        }
        if (!(args.length > 0)){
            p.sendMessage(service.getFiles().getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;
        }
        if (!(p.hasPermission("simplecore.command.gamemode.creative"))){
            p.sendMessage(service.getFiles().getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;
        }
        if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c")){
            if(!(args.length > 1)){
                p.setGameMode(GameMode.CREATIVE);
                p.sendMessage(service.getFiles().getLang().getString("gamemode.change").replace("%type%",
                        service.getFiles().getLang().getString("gamemode.type.creative")));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){
                p.sendMessage(service.getFiles().getLang().getString("messages.error.no-player")
                        .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix"))
                        .replace("%player%", args[1])
                        .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
                return true;
            }
            p.sendMessage(service.getFiles().getLang().getString("gamemode.change-other")
                    .replace("%type%", service.getFiles().getLang().getString("gamemode.type.creative"))
                    .replace("%target%", target.getName()));

            target.sendMessage(service.getFiles().getLang().getString("gamemode.target-change")
                    .replace("%type%", service.getFiles().getLang().getString("gamemode.type.creative")).replace("%player%", p.getName()));
            target.setGameMode(GameMode.CREATIVE);
            return true;
        }
        if (!(p.hasPermission("simplecore.command.gamemode.survival"))){
            p.sendMessage(service.getFiles().getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;
        }
        if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s")){
            if(!(args.length > 1)){
                p.setGameMode(GameMode.SURVIVAL);
                p.sendMessage(service.getFiles().getLang().getString("gamemode.change")
                        .replace("%type%", service.getFiles().getLang().getString("gamemode.type.survival")));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){
                p.sendMessage(service.getFiles().getLang().getString("messages.error.no-player")
                        .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix"))
                        .replace("%player%", args[1]));
                return true;
            }
            p.sendMessage(service.getFiles().getLang().getString("gamemode.change-other")
                    .replace("%type%", service.getFiles().getLang().getString("gamemode.type.survival")).replace("%target%", target.getName()));

            target.sendMessage(service.getFiles().getLang().getString("gamemode.target-change")
                    .replace("%type%", service.getFiles().getLang().getString("gamemode.type.survival")).replace("%player%", p.getName()));
            target.setGameMode(GameMode.SURVIVAL);
            return true;
        }
        if (!(p.hasPermission("simplecore.command.gamemode.adventure"))){
            p.sendMessage(service.getFiles().getLang().getString("messages.error.no-permissions"
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix"))));
            return true;
        }
        if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a")){
            if(!(args.length > 1)){
                p.setGameMode(GameMode.ADVENTURE);
                p.sendMessage(service.getFiles().getLang().getString("gamemode.change")
                        .replace("%type%", service.getFiles().getLang().getString("gamemode.type.adventure")));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){
                p.sendMessage(service.getFiles().getLang().getString("messages.error.no-player")
                        .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")).replace("%player%", args[1]));
                return true;
            }
            p.sendMessage(service.getFiles().getLang().getString("gamemode.change-other")
                    .replace("%type%", service.getFiles().getLang().getString("gamemode.type.adventure")).replace("%target%", target.getName()));

            target.sendMessage(service.getFiles().getLang().getString("gamemode.target-change")
                    .replace("%type%", service.getFiles().getLang().getString("gamemode.type.adventure")).replace("%player%", p.getName()));
            target.setGameMode(GameMode.ADVENTURE);
            return true;
        }
        if (!(p.hasPermission("simplecore.command.gamemode.spectator"))){
            p.sendMessage(service.getFiles().getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;
        }
        if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("sp")){
            if(!(args.length > 1)){
                p.setGameMode(GameMode.SPECTATOR);
                p.sendMessage(service.getFiles().getLang().getString("gamemode.change")
                        .replace("%type%", service.getFiles().getLang().getString("gamemode.type.spectator")));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){
                p.sendMessage(service.getFiles().getLang().getString("messages.error.no-player")
                        .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")).replace("%player%", args[1]));
                return true;
            }
            p.sendMessage(service.getFiles().getLang().getString("gamemode.change-other")
                    .replace("%type%", service.getFiles().getLang().getString("gamemode.type.spectator")).replace("%target%", target.getName()));

            target.sendMessage(service.getFiles().getLang().getString("gamemode.target-change")
                    .replace("%type%", service.getFiles().getLang().getString("gamemode.type.spectator")).replace("%player%", p.getName()));
            target.setGameMode(GameMode.SPECTATOR);
            return true;
        }
        p.sendMessage(service.getFiles().getLang().getString("usages.general-gamemode")
                .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
        return true;
    }
}
