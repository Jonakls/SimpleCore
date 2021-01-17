package me.jonakls.simplecore.commands;

import me.jonakls.simplecore.SimpleCore;
import me.jonakls.simplecore.files.MessagesFile;
import me.jonakls.simplecore.objects.ParseColors;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class GeneralGamemodeCommand implements CommandExecutor {

    private final SimpleCore simpleCore;
    private final ParseColors colors = new ParseColors();

    public GeneralGamemodeCommand(SimpleCore simpleCore){
        this.simpleCore = simpleCore;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration messages = new MessagesFile(simpleCore).getMessages();


        String noPermissions = messages.getString("messages.error.no-permissions");
        String correctUsage = messages.getString("usages.general-gamemode");
        String noPlayer = messages.getString("messages.error.no-player");

        String changeGamemode = messages.getString("gamemode.change");
        String otherGamemode = messages.getString("gamemode.change-other");
        String targetGamemode = messages.getString("gamemode.target-change");
        String typeCreative = messages.getString("gamemode.type.creative");
        String typeSurvival = messages.getString("gamemode.type.survival");
        String typeSpectator = messages.getString("gamemode.type.spectator");
        String typeAdventure = messages.getString("gamemode.type.adventure");

        if (!(sender instanceof Player)){
            sender.sendMessage(messages.getString("messages.error.no-console"));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.gamemode"))){
            p.sendMessage(colors.setColor(noPermissions));
            return true;
        }
        if (!(args.length > 0)){
            p.sendMessage(colors.setColor(correctUsage));
            return true;
        }
        if (!(p.hasPermission("simplecore.command.gamemode.creative"))){
            p.sendMessage(colors.setColor(noPermissions));
            return true;
        }
        if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c")){
            if(!(args.length > 1)){
                p.setGameMode(GameMode.CREATIVE);
                p.sendMessage(colors.setColor(changeGamemode.replace("%type%", typeCreative)));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){
                p.sendMessage(colors.setColor(noPlayer.replace("%player%", args[1])));
                return true;
            }
            p.sendMessage(colors.setColor(otherGamemode
                    .replace("%type%", typeCreative).replace("%target%", target.getName())));

            target.sendMessage(colors.setColor(targetGamemode
                    .replace("%type%", typeCreative).replace("%player%", p.getName())));
            target.setGameMode(GameMode.CREATIVE);
            return true;
        }
        if (!(p.hasPermission("simplecore.command.gamemode.survival"))){
            p.sendMessage(colors.setColor(noPermissions));
            return true;
        }
        if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s")){
            if(!(args.length > 1)){
                p.setGameMode(GameMode.SURVIVAL);
                p.sendMessage(colors.setColor(changeGamemode.replace("%type%", typeSurvival)));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){
                p.sendMessage(colors.setColor(noPlayer.replace("%player%", args[1])));
                return true;
            }
            p.sendMessage(colors.setColor(otherGamemode
                    .replace("%type%", typeSurvival).replace("%target%", target.getName())));

            target.sendMessage(colors.setColor(targetGamemode
                    .replace("%type%", typeSurvival).replace("%player%", p.getName())));
            target.setGameMode(GameMode.SURVIVAL);
            return true;
        }
        if (!(p.hasPermission("simplecore.command.gamemode.adventure"))){
            p.sendMessage(colors.setColor(noPermissions));
            return true;
        }
        if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a")){
            if(!(args.length > 1)){
                p.setGameMode(GameMode.ADVENTURE);
                p.sendMessage(colors.setColor(changeGamemode.replace("%type%", typeAdventure)));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){
                p.sendMessage(colors.setColor(noPlayer.replace("%player%", args[1])));
                return true;
            }
            p.sendMessage(colors.setColor(otherGamemode
                    .replace("%type%", typeAdventure).replace("%target%", target.getName())));

            target.sendMessage(colors.setColor(targetGamemode
                    .replace("%type%", typeAdventure).replace("%player%", p.getName())));
            target.setGameMode(GameMode.ADVENTURE);
            return true;
        }
        if (!(p.hasPermission("simplecore.command.gamemode.spectator"))){
            p.sendMessage(colors.setColor(noPermissions));
            return true;
        }
        if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("sp")){
            if(!(args.length > 1)){
                p.setGameMode(GameMode.SPECTATOR);
                p.sendMessage(colors.setColor(changeGamemode.replace("%type%", typeSpectator)));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){
                p.sendMessage(colors.setColor(noPlayer.replace("%player%", args[1])));
                return true;
            }
            p.sendMessage(colors.setColor(otherGamemode
                    .replace("%type%", typeSpectator).replace("%target%", target.getName())));

            target.sendMessage(colors.setColor(targetGamemode
                    .replace("%type%", typeSpectator).replace("%player%", p.getName())));
            target.setGameMode(GameMode.SPECTATOR);
            return true;
        }
        p.sendMessage(colors.setColor(correctUsage));
        return true;
    }
}
