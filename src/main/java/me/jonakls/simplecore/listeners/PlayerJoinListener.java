package me.jonakls.simplecore.listeners;

import me.jonakls.simplecore.Service;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final Service service;

    public PlayerJoinListener(Service service){
        this.service = service;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent joinEvent){

        Player p = joinEvent.getPlayer();
        if (!(service.getFiles().getConfig().getBoolean("config.join-message"))){
            joinEvent.setJoinMessage(null);
            return;
        }
        joinEvent.setJoinMessage(PlaceholderAPI.setPlaceholders(p, service.getFiles().getLang().getString("events.join-player.message").replace("%player%", p.getName())));

        if (service.getFiles().getConfig().getBoolean("sounds.join-sound.enable")){
           p.playSound(p.getLocation(),
                   Sound.valueOf(service.getFiles().getConfig().getString("sounds.join-sound.sound")),
                   (float) service.getFiles().getConfig().getDouble("sounds.join-sound.vol"),
                   (float) service.getFiles().getConfig().getDouble("sounds.join-sound.pitch"));
        }
    }

    @EventHandler
    public void preSetNickname(PlayerJoinEvent joinEvent){
        Player p = joinEvent.getPlayer();
        if (service.getFiles().getData().contains("data." + p.getName())) {
            p.setDisplayName(service.getFiles().getData().getString("data."+p.getName()+".nick-name"));
        }
    }

    @EventHandler
    public void goSpawn(PlayerJoinEvent joinEvent){
        Player p = joinEvent.getPlayer();
        if (service.getFiles().getConfig().getBoolean("config.teleports.spawn.teleport-on-join")){
            if (service.getFiles().getSpawn().contains("spawn.world")){
                Location loc = new Location(
                        Bukkit.getWorld(service.getFiles().getSpawn().getString("spawn.world")),
                        service.getFiles().getSpawn().getDouble("spawn.x"),
                        service.getFiles().getSpawn().getDouble("spawn.y"),
                        service.getFiles().getSpawn().getDouble("spawn.z"),
                        (float) service.getFiles().getSpawn().getDouble("spawn.yaw"),
                        (float) service.getFiles().getSpawn().getDouble("spawn.pitch")
                );
                p.teleport(loc);
            }
            p.sendMessage(service.getFiles().getLang().getString("spawn.spawn-no-exist"));
        }
    }
}
