package me.jonakls.simplecore.commands;

import me.jonakls.simplecore.files.FileManager;
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
            sender.sendMessage(FileManager.getLang().getString("messages.error.no-console"));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.fly"))){
            p.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }
        if (!(args.length > 0)){

            if (!(p.getGameMode().equals(GameMode.CREATIVE) || p.getGameMode().equals(GameMode.SPECTATOR))){
                if (!(p.getAllowFlight() || p.isFlying())){
                    p.setAllowFlight(true);
                    p.setFlying(true);
                    p.sendMessage(FileManager.getLang().getString("flymode.message")
                            .replace("%type%", FileManager.getLang().getString("type.enable")));
                    return true;
                }
                p.setAllowFlight(false);
                p.setFlying(false);
                p.sendMessage(FileManager.getLang().getString("flymode.message")
                        .replace("%type%", FileManager.getLang().getString("type.disable")));
                return true;
            }

            p.sendMessage(FileManager.getLang().getString("messages.error.no-flymode-gamemode")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;

        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null){
            p.sendMessage(FileManager.getLang().getString("messages.error.no-player")
                    .replace("%player%", args[0])
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return  true;
        }
        if (!(target.getName().equals(p.getName()))){
            if (!(target.getGameMode().equals(GameMode.CREATIVE) || target.getGameMode().equals(GameMode.SPECTATOR))){
                if (!(target.getAllowFlight() || target.isFlying())){
                    target.setAllowFlight(true);
                    target.setFlying(true);
                    target.sendMessage(FileManager.getLang().getString("flymode.target-message")
                            .replace("%type%", FileManager.getLang().getString("type.enable"))
                            .replace("%player%", p.getName()));

                    p.sendMessage(FileManager.getLang().getString("flymode.other-message")
                            .replace("%type%", FileManager.getLang().getString("type.enable"))
                            .replace("%target%", target.getName()));

                    return true;
                }
                target.setAllowFlight(false);
                target.setFlying(false);

                target.sendMessage(FileManager.getLang().getString("flymode.target-message")
                        .replace("%type%", FileManager.getLang().getString("type.disable"))
                        .replace("%player%", p.getName()));

                p.sendMessage(FileManager.getLang().getString("flymode.other-message")
                        .replace("%type%", FileManager.getLang().getString("type.disable"))
                        .replace("%target%", target.getName()));
                return true;
            }
            p.sendMessage(FileManager.getLang().getString("messages.error.no-flymode-gamemode")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }

        p.sendMessage(FileManager.getLang().getString("messages.error.no-yourself")
                .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
        return true;
    }
}
