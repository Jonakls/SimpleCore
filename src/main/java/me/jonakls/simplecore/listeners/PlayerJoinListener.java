package me.jonakls.simplecore.listeners;

import com.connorlinfoot.titleapi.TitleAPI;
import me.jonakls.simplecore.Manager;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final Manager manager;

    public PlayerJoinListener(Manager manager){
        this.manager = manager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent joinEvent){

        Player p = joinEvent.getPlayer();
        if (!(manager.getFiles().getConfig().getBoolean("config.join-message"))){
            joinEvent.setJoinMessage(null);
            return;
        }
        joinEvent.setJoinMessage(PlaceholderAPI.setPlaceholders(p, manager.getFiles().getLang().getString("events.join-player.message").replace("%player%", p.getName())));

        if (manager.getFiles().getConfig().getBoolean("titles.join-title.enable")){
            TitleAPI.sendTitle(p,
                    manager.getFiles().getConfig().getInt("titles.join-title.fade-in"),
                    manager.getFiles().getConfig().getInt("titles.join-title.stay"),
                    manager.getFiles().getConfig().getInt("titles.join-title.fade-out"),
                    PlaceholderAPI.setPlaceholders(p, manager.getFiles().getLang().getString("events.join-player.title")),
                    PlaceholderAPI.setPlaceholders(p, manager.getFiles().getLang().getString("events.join-player.sub-title")));
        }
    }
}
