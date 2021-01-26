package me.jonakls.simplecore.listeners;

import me.jonakls.simplecore.Manager;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    private final Manager manager;

    public QuitEvent( Manager manager){
        this.manager = manager;
    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent quitEvent){

        String joinMessage = manager.getFiles().getLang().getString("events.quit-player.message");
        String quitOption = "config.quit-message";


        Player p = quitEvent.getPlayer();
        if (!(manager.getFiles().getConfig().getBoolean(quitOption))){
            quitEvent.setQuitMessage(null);
            return;
        }
        quitEvent.setQuitMessage(PlaceholderAPI.setPlaceholders(p, joinMessage.replace("%player%", p.getName())));
    }
}
