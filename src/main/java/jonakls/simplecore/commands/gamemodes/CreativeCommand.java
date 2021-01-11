package jonakls.simplecore.commands.gamemodes;

import jonakls.simplecore.SimpleCore;
import jonakls.simplecore.files.MessagesFile;
import jonakls.simplecore.objects.ParseColors;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CreativeCommand implements CommandExecutor {

    private SimpleCore simpleCore;
    private ParseColors colors = new ParseColors();

    public CreativeCommand(SimpleCore simpleCore){
        this.simpleCore = simpleCore;
    }


    ConsoleCommandSender console = Bukkit.getConsoleSender();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration messages = new MessagesFile(simpleCore).getMessages();

        String noPermissions = messages.getString("Messages.error.no-permissions");
        String noPlayer = messages.getString("Messages.error.no-player");

        String changeGamemode = messages.getString("Gamemode.change");
        String otherGamemode = messages.getString("Gamemode.change-other");
        String targetGamemode = messages.getString("Gamemode.target-change");
        String typeGamemode = messages.getString("Gamemode.type.creative");

        if (!(sender instanceof Player)){
            console.sendMessage(messages.getString("Messages.error.no-console"));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.gamemode"))){
            p.sendMessage(colors.setColor(noPermissions));
            return true;
        }
        if (!(args.length > 0)){
            if (!(p.hasPermission("simplecore.command.gamemode.creative"))){
                p.sendMessage(colors.setColor(noPermissions));
                return true;
            }
            p.setGameMode(GameMode.CREATIVE);
            p.sendMessage(colors.setColor(changeGamemode.replace("%type%", typeGamemode)));
            return true;
        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null){
            p.sendMessage(colors.setColor(noPlayer.replace("%player%", args[0])));
            return true;
        }
        p.sendMessage(colors.setColor(otherGamemode
                .replace("%type%", typeGamemode).replace("%target%", target.getName())));

        target.sendMessage(colors.setColor(targetGamemode
                .replace("%type%", typeGamemode).replace("%player%", p.getName())));
        target.setGameMode(GameMode.CREATIVE);
        return true;
    }
}
