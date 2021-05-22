package me.jonakls.noxuscommands.commands.spawn;

import me.jonakls.noxuscommands.Service;
import me.jonakls.noxuscommands.files.FileManager;
import me.jonakls.noxuscommands.utils.CountdownTimer;
import me.jonakls.noxuscommands.utils.MessageReplacer;
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
            sender.sendMessage(MessageReplacer.noConsole());
            return true;
        }
        Player player = (Player) sender;
        if (!(player.hasPermission("simplecore.command.spawn"))) {
            player.sendMessage(MessageReplacer.noPermissions());
            return true;
        }

        if (!(FileManager.getSpawn().contains("spawn.world"))){

            player.sendMessage(FileManager.getLang().getString("spawn.spawn-no-exist"));

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

        if (!(player.hasPermission("simplecore.command.spawn.bypass"))){

            CountdownTimer timer = new CountdownTimer(
                    service.getPlugin(),
                    FileManager.getConfig().getInt("config.teleports.spawn.spawn-time"),
                    () -> FileManager.getLang().getString("spawn.pre-teleport"),
                    () -> {
                        player.teleport(loc);
                        player.sendMessage(FileManager.getLang().getString("spawn.teleport-spawn"));
                    },
                    (t) -> player.sendMessage(FileManager.getLang().getString("spawn.time-teleport")
                            .replace("%seconds%", ""+t.getSecondsLeft()))
            );
            timer.scheduleTimer();

            return true;
        }

        player.teleport(loc);
        player.sendMessage(FileManager.getLang().getString("spawn.teleport-spawn"));

        return true;
    }
}
