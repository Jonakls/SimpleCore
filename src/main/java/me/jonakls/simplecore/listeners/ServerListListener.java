package me.jonakls.simplecore.listeners;

import me.jonakls.simplecore.Manager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListListener implements Listener {

    private final Manager manager;

    public ServerListListener(Manager manager){
        this.manager = manager;
    }

    @EventHandler
    public void customServerList(ServerListPingEvent serverListPingEvent){

        if (manager.getFiles().getConfig().getBoolean("server-list.enable")){
            serverListPingEvent.setMotd(manager.getFiles().getConfig().getString("server-list.line1")
                    +"\n"+
                    manager.getFiles().getConfig().getString("server-list.line2"));
        }

        if (!(manager.getFiles().getConfig().getBoolean("server-list.respective-mode"))){
            serverListPingEvent.setMaxPlayers(manager.getFiles().getConfig().getInt("server-list.max-players"));
            return;
        }
        int online = serverListPingEvent.getNumPlayers();
            int maxRespective = 1;
            int totalRespective = online+maxRespective;
            serverListPingEvent.setMaxPlayers(totalRespective);

    }

}
