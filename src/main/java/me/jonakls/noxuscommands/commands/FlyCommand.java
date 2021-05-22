package me.jonakls.noxuscommands.commands;

import me.jonakls.noxuscommands.files.FileManager;
import me.jonakls.noxuscommands.utils.MessageReplacer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (!(sender instanceof Player)){
            sender.sendMessage(MessageReplacer.noConsole());
            return true;
        }
        Player player = (Player) sender;
        if (!(player.hasPermission("simplecore.command.fly"))){
            player.sendMessage(MessageReplacer.noPermissions());
            return true;
        }
        if (!(args.length > 0)){

            if (!(player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR))){
                if (!(player.getAllowFlight() || player.isFlying())){
                    player.setAllowFlight(true);
                    player.setFlying(true);
                    player.sendMessage(FileManager.getLang().getString("flymode.message")
                            .replace("%type%", FileManager.getLang().getString("type.enable")));
                    return true;
                }
                player.setAllowFlight(false);
                player.setFlying(false);
                player.sendMessage(FileManager.getLang().getString("flymode.message")
                        .replace("%type%", FileManager.getLang().getString("type.disable")));
                return true;
            }

            player.sendMessage(MessageReplacer.prefix(FileManager.getLang().getString("messages.error.no-flymode-gamemode")));
            return true;

        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null){
            player.sendMessage(MessageReplacer.noPlayer(args[0]));
            return  true;
        }
        if (!(target.getName().equals(player.getName()))){
            if (!(target.getGameMode().equals(GameMode.CREATIVE) || target.getGameMode().equals(GameMode.SPECTATOR))){
                if (!(target.getAllowFlight() || target.isFlying())){
                    target.setAllowFlight(true);
                    target.setFlying(true);
                    target.sendMessage(FileManager.getLang().getString("flymode.target-message")
                            .replace("%type%", FileManager.getLang().getString("type.enable"))
                            .replace("%player%", player.getName()));

                    player.sendMessage(FileManager.getLang().getString("flymode.other-message")
                            .replace("%type%", FileManager.getLang().getString("type.enable"))
                            .replace("%target%", target.getName()));

                    return true;
                }
                target.setAllowFlight(false);
                target.setFlying(false);

                target.sendMessage(FileManager.getLang().getString("flymode.target-message")
                        .replace("%type%", FileManager.getLang().getString("type.disable"))
                        .replace("%player%", player.getName()));

                player.sendMessage(FileManager.getLang().getString("flymode.other-message")
                        .replace("%type%", FileManager.getLang().getString("type.disable"))
                        .replace("%target%", target.getName()));
                return true;
            }
            player.sendMessage(MessageReplacer.prefix(FileManager.getLang().getString("messages.error.no-flymode-gamemode")));
            return true;
        }

        player.sendMessage(MessageReplacer.prefix(FileManager.getLang().getString("messages.error.no-yourself")));
        return true;
    }
}
