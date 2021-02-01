package me.jonakls.simplecore.listeners;

import me.jonakls.simplecore.Service;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListListener implements Listener {

    private final Service service;

    public ServerListListener(Service service){
        this.service = service;
    }

    @EventHandler
    public void customServerList(ServerListPingEvent serverListPingEvent){

        if (service.getFiles().getConfig().getBoolean("server-list.enable")){
            serverListPingEvent.setMotd(service.getFiles().getConfig().getString("server-list.line1")
                    +"\n"+
                    service.getFiles().getConfig().getString("server-list.line2"));
        }

        if (!(service.getFiles().getConfig().getBoolean("server-list.respective-mode"))){
            serverListPingEvent.setMaxPlayers(service.getFiles().getConfig().getInt("server-list.max-players"));
            return;
        }
        int online = serverListPingEvent.getNumPlayers();
            int maxRespective = 1;
            int totalRespective = online+maxRespective;
            serverListPingEvent.setMaxPlayers(totalRespective);

    }

}
