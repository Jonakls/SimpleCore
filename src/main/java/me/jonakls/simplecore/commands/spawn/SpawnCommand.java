package me.jonakls.simplecore.commands.spawn;

import me.jonakls.simplecore.Service;
import me.jonakls.simplecore.utils.CountdownTimer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private final Service service;

    public SpawnCommand(Service service){
        this.service = service;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(service.getFiles().getLang().getString("messages.error.no-console")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.spawn"))) {
            p.sendMessage(service.getFiles().getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;
        }

        if (!(service.getFiles().getSpawn().contains("spawn.world"))){

            p.sendMessage(service.getFiles().getLang().getString("spawn.spawn-no-exist"));

            return true;
        }
        Location loc = new Location(
                Bukkit.getWorld(service.getFiles().getSpawn().getString("spawn.world")),
                service.getFiles().getSpawn().getDouble("spawn.x"),
                service.getFiles().getSpawn().getDouble("spawn.y"),
                service.getFiles().getSpawn().getDouble("spawn.z"),
                (float) service.getFiles().getSpawn().getDouble("spawn.yaw"),
                (float) service.getFiles().getSpawn().getDouble("spawn.pitch")
        );

        if (!(p.hasPermission("simplecore.command.spawn.bypass"))){

            CountdownTimer timer = new CountdownTimer(
                    service.getSimpleCore(),
                    service.getFiles().getConfig().getInt("config.teleports.spawn.spawn-time"),
                    () -> service.getFiles().getLang().getString("spawn.pre-teleport"),
                    () -> {
                        p.teleport(loc);
                        p.sendMessage(service.getFiles().getLang().getString("spawn.teleport-spawn"));
                    },
                    (t) -> p.sendMessage(service.getFiles().getLang().getString("spawn.time-teleport")
                            .replace("%seconds%", ""+t.getSecondsLeft()))
            );
            timer.scheduleTimer();

            return true;
        }

        p.teleport(loc);
        p.sendMessage(service.getFiles().getLang().getString("spawn.teleport-spawn"));

        return true;
    }
}
