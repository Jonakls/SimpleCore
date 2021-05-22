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
    public void onJoin(PlayerJoinEvent joinEvent){

        Player p = joinEvent.getPlayer();
        if (!(FileManager.getConfig().getBoolean("config.join-message"))){
            joinEvent.setJoinMessage(null);
            return;
        }
        joinEvent.setJoinMessage(PlaceholderAPI.setPlaceholders(p, FileManager.getLang().getString("events.join-player.message").replace("%player%", p.getName())));

        if (FileManager.getConfig().getBoolean("sounds.join-sound.enable")){
           p.playSound(p.getLocation(),
                   Sound.valueOf(FileManager.getConfig().getString("sounds.join-sound.sound")),
                   (float) FileManager.getConfig().getDouble("sounds.join-sound.vol"),
                   (float) FileManager.getConfig().getDouble("sounds.join-sound.pitch"));
        }
    }

    @EventHandler
    public void preSetNickname(PlayerJoinEvent joinEvent){
        Player p = joinEvent.getPlayer();
        if (FileManager.getData().contains("data." + p.getName())) {
            p.setDisplayName(FileManager.getData().getString("data."+p.getName()+".nick-name"));
        }
    }

    @EventHandler
    public void goSpawn(PlayerJoinEvent joinEvent){
        Player p = joinEvent.getPlayer();

        if (FileManager.getConfig().getBoolean("config.teleports.spawn.teleport-on-join")){
            if (FileManager.getSpawn().contains("spawn.world")){

                Location loc = new Location(
                        Bukkit.getWorld(FileManager.getSpawn().getString("spawn.world")),
                        FileManager.getSpawn().getDouble("spawn.x"),
                        FileManager.getSpawn().getDouble("spawn.y"),
                        FileManager.getSpawn().getDouble("spawn.z"),
                        (float) FileManager.getSpawn().getDouble("spawn.yaw"),
                        (float) FileManager.getSpawn().getDouble("spawn.pitch")
                );

                p.teleport(loc);
            }
            p.sendMessage(FileManager.getLang().getString("spawn.spawn-no-exist"));
        }
    }
}
