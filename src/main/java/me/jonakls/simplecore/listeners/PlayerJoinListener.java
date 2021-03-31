package me.jonakls.simplecore.listeners;

import me.jonakls.simplecore.Service;
import me.clip.placeholderapi.PlaceholderAPI;
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

        float vol = (float) service.getFiles().getConfig().getDouble("sounds.join-sound.vol");
        float pitch = (float) service.getFiles().getConfig().getDouble("sounds.join-sound.pitch");

        Player p = joinEvent.getPlayer();
        if (service.getFiles().getData().contains("data." + p.getName())) {
            p.setDisplayName(service.getFiles().getData().getString("data."+p.getName()+".nick-name"));
            return;
        }

        if (!(service.getFiles().getConfig().getBoolean("config.join-message"))){
            joinEvent.setJoinMessage(null);
            return;
        }
        joinEvent.setJoinMessage(PlaceholderAPI.setPlaceholders(p, service.getFiles().getLang().getString("events.join-player.message").replace("%player%", p.getName())));

        if (service.getFiles().getConfig().getBoolean("sounds.join-sound.enable")){
           p.playSound(p.getLocation(),
                   Sound.valueOf(service.getFiles().getConfig().getString("sounds.join-sound.sound")),
                   vol,
                   pitch);
        }
    }

    @EventHandler
    public void preSetNickname(PlayerJoinEvent joinEvent){
        Player p = joinEvent.getPlayer();
        if (service.getFiles().getData().contains("data." + p.getName())) {
            p.setDisplayName(service.getFiles().getData().getString("data."+p.getName()+".nick-name"));
        }
    }
}
