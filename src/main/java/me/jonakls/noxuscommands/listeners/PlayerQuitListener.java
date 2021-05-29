package me.jonakls.noxuscommands.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import me.jonakls.noxuscommands.files.FileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent quitEvent){

        Player player = quitEvent.getPlayer();

        if (!(FileManager.getConfig().getBoolean("config.quit-message"))){
            quitEvent.setQuitMessage(null);
            return;
        }
        quitEvent.setQuitMessage(PlaceholderAPI.setPlaceholders(
                player,
                FileManager.getLang().getString("events.quit-player.message").
                        replace("%player%", player.getName())));
    }
}
