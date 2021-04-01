package me.jonakls.simplecore.commands.warps;

import me.jonakls.simplecore.Service;
import me.jonakls.simplecore.handlers.WarpHandler;
import me.jonakls.simplecore.utils.CountdownTimer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {

    private final Service service;

    public WarpCommand(Service service){
        this.service = service;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(service.getFiles().getLang().getString("messages.error.no-console"));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.warp"))){
            p.sendMessage(service.getFiles().getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;

        }
        if (!(args.length > 0)){
            p.sendMessage(service.getFiles().getLang().getString("usages.set-warp")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;
        }

        if (args[0].equalsIgnoreCase("list")){


            WarpHandler listWarps = new WarpHandler(service);

            listWarps.listWarps();

            StringBuilder stringBuilder = new StringBuilder();
            int i = 0;
            while (i < listWarps.getListWarps().size()) {
                stringBuilder.append(listWarps.getListWarps().get(i)).append(' ');
                i++;
            }
            p.sendMessage(service.getFiles().getLang().getString("warps.warp-list")+stringBuilder.toString());
            return true;
        }


        if (!(p.hasPermission("simplecore.command.warp."+args[0].toLowerCase()))){
            p.sendMessage(service.getFiles().getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;

        }

        WarpHandler warp = new WarpHandler(service);

        warp.getWarp(args[0].toLowerCase());
        if (!(warp.getOperation())){
            p.sendMessage(service.getFiles().getLang().getString("warps.no-exist")
                    .replace("%warp%", args[0].toLowerCase()));
            return true;
        }

        CountdownTimer timer = new CountdownTimer(
                service.getSimpleCore(),
                service.getFiles().getConfig().getInt("config.teleports.warp-time"),
                () -> service.getFiles().getLang().getString("warps.pre-teleport"),
                () -> {
                    p.teleport(warp.getWarpLocation());
                    p.sendMessage(service.getFiles().getLang().getString("warps.teleport-warp")
                            .replace("%warp%", args[0].toLowerCase()));
                    },
                (t) -> p.sendMessage(service.getFiles().getLang().getString("warps.time-teleport")
                        .replace("%warp%", args[0].toLowerCase())
                        .replace("%seconds%", ""+t.getSecondsLeft()))
                );
        timer.scheduleTimer();

        return true;
    }
}
