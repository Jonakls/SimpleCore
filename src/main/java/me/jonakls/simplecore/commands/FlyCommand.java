package me.jonakls.simplecore.commands;

import me.jonakls.simplecore.SimpleCore;
import me.jonakls.simplecore.files.MessagesFile;
import me.jonakls.simplecore.objects.ParseColors;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    private final SimpleCore simpleCore;
    private final ParseColors colors = new ParseColors();

    public FlyCommand(SimpleCore simpleCore){
        this.simpleCore = simpleCore;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration messagesFile = new MessagesFile(simpleCore).getMessages();

        if (!(sender instanceof Player)){
            sender.sendMessage(colors.setColor(messagesFile.getString("messages.error.no-console")));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.fly"))){
            p.sendMessage(colors.setColor(messagesFile.getString("messages.error.no-permissions")));
            return true;
        }
        if (!(args.length > 0)){
            if (!(p.getAllowFlight() || p.isFlying())){
                p.setAllowFlight(true);
                p.setFlying(true);
                p.sendMessage(colors.setColor(messagesFile.getString("flymode.message")
                        .replace("%type%", messagesFile.getString("type.enable"))));
                return true;
            }
            p.setAllowFlight(false);
            p.setFlying(false);
            p.sendMessage(colors.setColor(messagesFile.getString("flymode.message")
                    .replace("%type%", messagesFile.getString("Type.disable"))));
            return true;
        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null){
            p.sendMessage(colors.setColor(messagesFile.getString("messages.error.no-player")));
            return  true;
        }
        if (!(target.getAllowFlight() || target.isFlying())){
            target.setAllowFlight(true);
            target.setFlying(true);
            target.sendMessage(colors.setColor(messagesFile.getString("flymode.target-message")
                    .replace("%type%", messagesFile.getString("type.enable"))
                    .replace("%player%", p.getName())));

            p.sendMessage(colors.setColor(messagesFile.getString("flymode.other-message")
                    .replace("%type%", messagesFile.getString("type.enable"))
                    .replace("%target%", target.getName())));

            return true;
        }
        target.setAllowFlight(false);
        target.setFlying(false);

        target.sendMessage(colors.setColor(messagesFile.getString("flymode.target-message")
                .replace("%type%", messagesFile.getString("type.disable"))
                .replace("%player%", p.getName())));

        p.sendMessage(colors.setColor(messagesFile.getString("flymode.other-message")
                .replace("%type%", messagesFile.getString("Type.disable"))
                .replace("%target%", target.getName())));
        return true;
    }
}
