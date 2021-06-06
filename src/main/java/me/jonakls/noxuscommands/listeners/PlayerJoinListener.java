package me.jonakls.noxuscommands.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import me.jonakls.noxuscommands.files.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoinPlayer(PlayerJoinEvent joinEvent){

        Player player = joinEvent.getPlayer();

        if (!(FileManager.getConfig().getBoolean("config.join-message"))){
            joinEvent.setJoinMessage(null);
            return;
        }

        joinEvent.setJoinMessage(PlaceholderAPI.setPlaceholders(
                player,
                FileManager.getLang().getString("events.join-player.message").
                        replace("%player%", player.getName())));

        if (FileManager.getConfig().getBoolean("sounds.join-sound.enable")){

           player.playSound(
                   player.getLocation(),
                   Sound.valueOf(FileManager.getConfig().getString("sounds.join-sound.sound")),
                   (float) FileManager.getConfig().getDouble("sounds.join-sound.vol"),
                   (float) FileManager.getConfig().getDouble("sounds.join-sound.pitch"));
        }
    }

    @EventHandler
    public void preSetNickname(PlayerJoinEvent joinEvent){

        Player player = joinEvent.getPlayer();

        if (FileManager.getData().contains("data." + player.getName())) {

            player.setDisplayName(FileManager.getData().getString("data."+player.getName()+".nick-name"));

        }
    }

    @EventHandler
    public void goSpawn(PlayerJoinEvent joinEvent){

        Player player = joinEvent.getPlayer();

        if (FileManager.getConfig().getBoolean("config.teleports.spawn.teleport-on-join")){

            if (FileManager.getSpawn().contains("spawn.world")){

                Location location = new Location(
                        Bukkit.getWorld(FileManager.getSpawn().getString("spawn.world")),
                        FileManager.getSpawn().getDouble("spawn.x"),
                        FileManager.getSpawn().getDouble("spawn.y"),
                        FileManager.getSpawn().getDouble("spawn.z"),
                        (float) FileManager.getSpawn().getDouble("spawn.yaw"),
                        (float) FileManager.getSpawn().getDouble("spawn.pitch")
                );

                player.teleport(location);
                return;
            }
            player.sendMessage(FileManager.getLang().getString("spawn.spawn-no-exist"));
        }
    }
}
