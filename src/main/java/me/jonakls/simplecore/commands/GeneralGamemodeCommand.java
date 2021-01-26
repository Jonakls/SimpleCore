package me.jonakls.simplecore.commands;

import me.jonakls.simplecore.Manager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GeneralGamemodeCommand implements CommandExecutor {

    private final Manager manager;

    public GeneralGamemodeCommand(Manager manager){
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        String noPermissions = manager.getFiles().getLang().getString("messages.error.no-permissions");
        String correctUsage = manager.getFiles().getLang().getString("usages.general-gamemode");
        String noPlayer = manager.getFiles().getLang().getString("messages.error.no-player");

        String changeGamemode = manager.getFiles().getLang().getString("gamemode.change");
        String otherGamemode = manager.getFiles().getLang().getString("gamemode.change-other");
        String targetGamemode = manager.getFiles().getLang().getString("gamemode.target-change");
        String typeCreative = manager.getFiles().getLang().getString("gamemode.type.creative");
        String typeSurvival = manager.getFiles().getLang().getString("gamemode.type.survival");
        String typeSpectator = manager.getFiles().getLang().getString("gamemode.type.spectator");
        String typeAdventure = manager.getFiles().getLang().getString("gamemode.type.adventure");

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
            p.sendMessage(correctUsage);
            return true;
        }
        if (!(p.hasPermission("simplecore.command.gamemode.creative"))){
            p.sendMessage(noPermissions);
            return true;
        }
        if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c")){
            if(!(args.length > 1)){
                p.setGameMode(GameMode.CREATIVE);
                p.sendMessage(changeGamemode.replace("%type%", typeCreative));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){
                p.sendMessage(noPlayer.replace("%player%", args[1]));
                return true;
            }
            p.sendMessage(otherGamemode
                    .replace("%type%", typeCreative).replace("%target%", target.getName()));

            target.sendMessage(targetGamemode
                    .replace("%type%", typeCreative).replace("%player%", p.getName()));
            target.setGameMode(GameMode.CREATIVE);
            return true;
        }
        if (!(p.hasPermission("simplecore.command.gamemode.survival"))){
            p.sendMessage(noPermissions);
            return true;
        }
        if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s")){
            if(!(args.length > 1)){
                p.setGameMode(GameMode.SURVIVAL);
                p.sendMessage(changeGamemode.replace("%type%", typeSurvival));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){
                p.sendMessage(noPlayer.replace("%player%", args[1]));
                return true;
            }
            p.sendMessage(otherGamemode
                    .replace("%type%", typeSurvival).replace("%target%", target.getName()));

            target.sendMessage(targetGamemode
                    .replace("%type%", typeSurvival).replace("%player%", p.getName()));
            target.setGameMode(GameMode.SURVIVAL);
            return true;
        }
        if (!(p.hasPermission("simplecore.command.gamemode.adventure"))){
            p.sendMessage(noPermissions);
            return true;
        }
        if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a")){
            if(!(args.length > 1)){
                p.setGameMode(GameMode.ADVENTURE);
                p.sendMessage(changeGamemode.replace("%type%", typeAdventure));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){
                p.sendMessage(noPlayer.replace("%player%", args[1]));
                return true;
            }
            p.sendMessage(otherGamemode
                    .replace("%type%", typeAdventure).replace("%target%", target.getName()));

            target.sendMessage(targetGamemode
                    .replace("%type%", typeAdventure).replace("%player%", p.getName()));
            target.setGameMode(GameMode.ADVENTURE);
            return true;
        }
        if (!(p.hasPermission("simplecore.command.gamemode.spectator"))){
            p.sendMessage(noPermissions);
            return true;
        }
        if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("sp")){
            if(!(args.length > 1)){
                p.setGameMode(GameMode.SPECTATOR);
                p.sendMessage(changeGamemode.replace("%type%", typeSpectator));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){
                p.sendMessage(noPlayer.replace("%player%", args[1]));
                return true;
            }
            p.sendMessage(otherGamemode
                    .replace("%type%", typeSpectator).replace("%target%", target.getName()));

            target.sendMessage(targetGamemode
                    .replace("%type%", typeSpectator).replace("%player%", p.getName()));
            target.setGameMode(GameMode.SPECTATOR);
            return true;
        }
        p.sendMessage(correctUsage);
        return true;
    }
}
