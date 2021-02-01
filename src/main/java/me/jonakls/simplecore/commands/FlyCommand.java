package me.jonakls.simplecore.commands;

import me.jonakls.simplecore.Manager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    private final Manager manager;

    public FlyCommand(Manager manager){
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (!(sender instanceof Player)){
            sender.sendMessage(manager.getFiles().getLang().getString("messages.error.no-console"));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.fly"))){
            p.sendMessage(manager.getFiles().getLang().getString("messages.error.no-permissions"));
            return true;
        }
        if (!(args.length > 0)){

            if (!(p.getGameMode().equals(GameMode.CREATIVE) || p.getGameMode().equals(GameMode.SPECTATOR))){
                if (!(p.getAllowFlight() || p.isFlying())){
                    p.setAllowFlight(true);
                    p.setFlying(true);
                    p.sendMessage(manager.getFiles().getLang().getString("flymode.message")
                            .replace("%type%", manager.getFiles().getLang().getString("type.enable")));
                    return true;
                }
                p.setAllowFlight(false);
                p.setFlying(false);
                p.sendMessage(manager.getFiles().getLang().getString("flymode.message")
                        .replace("%type%", manager.getFiles().getLang().getString("Type.disable")));
                return true;
            }

            p.sendMessage(manager.getFiles().getLang().getString("messages.error.no-flymode-gamemode"));
            return true;

        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null){
            p.sendMessage(manager.getFiles().getLang().getString("messages.error.no-player"));
            return  true;
        }
        if (!(target.getAllowFlight() || target.isFlying())){
            target.setAllowFlight(true);
            target.setFlying(true);
            target.sendMessage(manager.getFiles().getLang().getString("flymode.target-message")
                    .replace("%type%", manager.getFiles().getLang().getString("type.enable"))
                    .replace("%player%", p.getName()));

            p.sendMessage(manager.getFiles().getLang().getString("flymode.other-message")
                    .replace("%type%", manager.getFiles().getLang().getString("type.enable"))
                    .replace("%target%", target.getName()));

            return true;
        }
        target.setAllowFlight(false);
        target.setFlying(false);

        target.sendMessage(manager.getFiles().getLang().getString("flymode.target-message")
                .replace("%type%", manager.getFiles().getLang().getString("type.disable"))
                .replace("%player%", p.getName()));

        p.sendMessage(manager.getFiles().getLang().getString("flymode.other-message")
                .replace("%type%", manager.getFiles().getLang().getString("Type.disable"))
                .replace("%target%", target.getName()));
        return true;
    }
}
