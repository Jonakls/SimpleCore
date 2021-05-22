package me.jonakls.simplecore.commands.warps;

import me.jonakls.simplecore.Service;
import me.jonakls.simplecore.files.FileManager;
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
            sender.sendMessage(FileManager.getLang().getString("messages.error.no-console"));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.warp"))){
            p.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;

        }
        if (!(args.length > 0)){
            p.sendMessage(FileManager.getLang().getString("usages.set-warp")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }

        if (args[0].equalsIgnoreCase("list")){


            WarpHandler listWarps = new WarpHandler();

            listWarps.listWarps();

            StringBuilder stringBuilder = new StringBuilder();
            int i = 0;
            while (i < listWarps.getListWarps().size()) {
                stringBuilder.append(listWarps.getListWarps().get(i)).append(' ');
                i++;
            }
            p.sendMessage(FileManager.getLang().getString("warps.warp-list")+ stringBuilder);
            return true;
        }


        if (!(p.hasPermission("simplecore.command.warp."+args[0].toLowerCase()))){
            p.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;

        }

        WarpHandler warp = new WarpHandler();

        warp.getWarp(args[0].toLowerCase());

        if (!(warp.getOperation())){
            p.sendMessage(FileManager.getLang().getString("warps.no-exist")
                    .replace("%warp%", args[0].toLowerCase()));
            return true;
        }

        if (!(p.hasPermission("simplecore.command.warp.bypass-time"))){
            CountdownTimer timer = new CountdownTimer(
                    service.getSimpleCore(),
                    FileManager.getConfig().getInt("config.teleports.warp-time"),
                    () -> FileManager.getLang().getString("warps.pre-teleport"),
                    () -> {
                        p.teleport(warp.getWarpLocation());
                        p.sendMessage(FileManager.getLang().getString("warps.teleport-warp")
                                .replace("%warp%", args[0].toLowerCase()));
                    },
                    (t) -> p.sendMessage(FileManager.getLang().getString("warps.time-teleport")
                            .replace("%warp%", args[0].toLowerCase())
                            .replace("%seconds%", ""+t.getSecondsLeft()))
            );
            timer.scheduleTimer();
            return true;
        }

        p.teleport(warp.getWarpLocation());
        p.sendMessage(FileManager.getLang().getString("warps.teleport-warp")
                .replace("%warp%", args[0].toLowerCase()));
        return true;
    }
}
