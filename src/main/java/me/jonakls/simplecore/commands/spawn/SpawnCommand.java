package me.jonakls.simplecore.commands.spawn;

import me.jonakls.simplecore.Service;
import me.jonakls.simplecore.files.FileManager;
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
            sender.sendMessage(FileManager.getLang().getString("messages.error.no-console")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.spawn"))) {
            p.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }

        if (!(FileManager.getSpawn().contains("spawn.world"))){

            p.sendMessage(FileManager.getLang().getString("spawn.spawn-no-exist"));

            return true;
        }
        Location loc = new Location(
                Bukkit.getWorld(FileManager.getSpawn().getString("spawn.world")),
                FileManager.getSpawn().getDouble("spawn.x"),
                FileManager.getSpawn().getDouble("spawn.y"),
                FileManager.getSpawn().getDouble("spawn.z"),
                (float) FileManager.getSpawn().getDouble("spawn.yaw"),
                (float) FileManager.getSpawn().getDouble("spawn.pitch")
        );

        if (!(p.hasPermission("simplecore.command.spawn.bypass"))){

            CountdownTimer timer = new CountdownTimer(
                    service.getSimpleCore(),
                    FileManager.getConfig().getInt("config.teleports.spawn.spawn-time"),
                    () -> FileManager.getLang().getString("spawn.pre-teleport"),
                    () -> {
                        p.teleport(loc);
                        p.sendMessage(FileManager.getLang().getString("spawn.teleport-spawn"));
                    },
                    (t) -> p.sendMessage(FileManager.getLang().getString("spawn.time-teleport")
                            .replace("%seconds%", ""+t.getSecondsLeft()))
            );
            timer.scheduleTimer();

            return true;
        }

        p.teleport(loc);
        p.sendMessage(FileManager.getLang().getString("spawn.teleport-spawn"));

        return true;
    }
}
