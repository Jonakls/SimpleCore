package me.jonakls.simplecore.listeners;

import com.connorlinfoot.titleapi.TitleAPI;
import me.jonakls.simplecore.Manager;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    private final Manager manager;

    public JoinEvent(Manager manager){
        this.manager = manager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent joinEvent){

        String joinMessage = manager.getFiles().getLang().getString("events.join-player.message");

        String joinTitle = manager.getFiles().getLang().getString("events.join-player.title");
        String joinSubTitle = manager.getFiles().getLang().getString("events.join-player.sub-title");
        int fadeIn = manager.getFiles().getConfig().getInt("titles.join-title.fade-in");
        int stay = manager.getFiles().getConfig().getInt("titles.join-title.stay");
        int fadeOut = manager.getFiles().getConfig().getInt("titles.join-title.fade-out");



        Player p = joinEvent.getPlayer();
        if (!(manager.getFiles().getConfig().getBoolean("config.join-message"))){
            joinEvent.setJoinMessage(null);
            return;
        }
        joinEvent.setJoinMessage(PlaceholderAPI.setPlaceholders(p, joinMessage.replace("%player%", p.getName())));

        if (manager.getFiles().getConfig().getBoolean("titles.join-title.enable")){
            TitleAPI.sendTitle(p,
                    fadeIn,
                    stay,
                    fadeOut,
                    PlaceholderAPI.setPlaceholders(p, joinTitle),
                    PlaceholderAPI.setPlaceholders(p, joinSubTitle));
        }
    }
}
