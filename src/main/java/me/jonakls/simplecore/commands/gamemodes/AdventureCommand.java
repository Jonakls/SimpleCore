package me.jonakls.simplecore.commands.gamemodes;

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

public class AdventureCommand implements CommandExecutor {

    private final SimpleCore simpleCore;
    private final ParseColors colors = new ParseColors();

    public AdventureCommand(SimpleCore simpleCore){
        this.simpleCore = simpleCore;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration messages = new MessagesFile(simpleCore).getMessages();

        String noPermissions = messages.getString("Messages.error.no-permissions");
        String noPlayer = messages.getString("Messages.error.no-player");

        String changeGamemode = messages.getString("Gamemode.change");
        String otherGamemode = messages.getString("Gamemode.change-other");
        String targetGamemode = messages.getString("Gamemode.target-change");
        String typeGamemode = messages.getString("Gamemode.type.adventure");

        if (!(sender instanceof Player)){
            sender.sendMessage(messages.getString("Messages.error.no-console"));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.gamemode"))){
            p.sendMessage(colors.setColor(noPermissions));
            return true;
        }
        if (!(args.length > 0)){
            if (!(p.hasPermission("simplecore.command.gamemode.adventure"))){
                p.sendMessage(colors.setColor(noPermissions));
                return true;
            }
            p.setGameMode(GameMode.ADVENTURE);
            p.sendMessage(colors.setColor(changeGamemode.replace("%type%", typeGamemode)));
            return true;
        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null){
            p.sendMessage(colors.setColor(noPlayer.replace("%player%", args[0])));
            return true;
        }
        p.sendMessage(colors.setColor(otherGamemode.replace("%type%", typeGamemode).replace("%target%", target.getName())));

        target.sendMessage(colors.setColor(targetGamemode.replace("%type%", typeGamemode).replace("%player%", p.getName())));
        target.setGameMode(GameMode.ADVENTURE);
        return true;
    }
}
