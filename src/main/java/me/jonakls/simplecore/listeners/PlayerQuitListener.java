package me.jonakls.simplecore.listeners;

import me.jonakls.simplecore.Service;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final Service service;

    public PlayerQuitListener(Service service){
        this.service = service;
    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent quitEvent){

        Player p = quitEvent.getPlayer();
        if (!(service.getFiles().getConfig().getBoolean("config.quit-message"))){
            quitEvent.setQuitMessage(null);
            return;
        }
        quitEvent.setQuitMessage(PlaceholderAPI.setPlaceholders(p, service.getFiles().getLang().getString("events.quit-player.message").replace("%player%", p.getName())));
    }
}
