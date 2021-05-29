package me.jonakls.noxuscommands.commands.warps;

import me.jonakls.noxuscommands.Service;
import me.jonakls.noxuscommands.files.FileManager;
import me.jonakls.noxuscommands.handlers.WarpHandler;
import me.jonakls.noxuscommands.utils.CountdownTimer;
import me.jonakls.noxuscommands.utils.MessageReplacer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {

    private final Service service;
    private final WarpHandler warp = new WarpHandler();

    public WarpCommand(Service service){
        this.service = service;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(MessageReplacer.noConsole());
            return true;
        }
        Player player = (Player) sender;
        if (!(player.hasPermission("simplecore.command.warp"))){
            player.sendMessage(MessageReplacer.noPermissions());
            return true;

        }
        if (!(args.length > 0)){
            player.sendMessage(MessageReplacer.prefix(FileManager.getLang().getString("usages.set-warp")));
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
            player.sendMessage(FileManager.getLang().getString("warps.warp-list")+ stringBuilder);
            return true;
        }


        if (!(player.hasPermission("simplecore.command.warp."+args[0].toLowerCase()))){
            player.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;

        }

        warp.getWarp(args[0].toLowerCase());

        if (!(warp.getOperation())){
            player.sendMessage(FileManager.getLang().getString("warps.no-exist")
                    .replace("%warp%", args[0].toLowerCase()));
            return true;
        }

        if (!(player.hasPermission("simplecore.command.warp.bypass-time"))){
            CountdownTimer timer = new CountdownTimer(
                    service.getPlugin(),
                    FileManager.getConfig().getInt("config.teleports.warp-time"),
                    () -> FileManager.getLang().getString("warps.pre-teleport"),
                    () -> {
                        player.teleport(warp.getWarpLocation());
                        player.sendMessage(FileManager.getLang().getString("warps.teleport-warp")
                                .replace("%warp%", args[0].toLowerCase()));
                    },
                    (t) -> player.sendMessage(FileManager.getLang().getString("warps.time-teleport")
                            .replace("%warp%", args[0].toLowerCase())
                            .replace("%seconds%", ""+t.getSecondsLeft()))
            );
            timer.scheduleTimer();
            return true;
        }

        player.teleport(warp.getWarpLocation());
        player.sendMessage(FileManager.getLang().getString("warps.teleport-warp")
                .replace("%warp%", args[0].toLowerCase()));
        return true;
    }
}
