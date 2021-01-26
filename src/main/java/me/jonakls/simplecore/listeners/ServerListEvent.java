package me.jonakls.simplecore.listeners;

import me.jonakls.simplecore.Manager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListEvent implements Listener {

    private final Manager manager;

    public ServerListEvent(Manager manager){
        this.manager = manager;
    }

    @EventHandler
    public void customServerList(ServerListPingEvent serverListPingEvent){

        String lineOne = manager.getFiles().getConfig().getString("server-List.line1");
        String lineTwo = manager.getFiles().getConfig().getString("server-List.line2");
        int maxPlayers = manager.getFiles().getConfig().getInt("server-List.max-players");

        if (manager.getFiles().getConfig().getBoolean("server-List.enable")){
            serverListPingEvent.setMotd(lineOne+"\n"+lineTwo);
        }

        if (!(manager.getFiles().getConfig().getBoolean("server-List.respective-mode"))){
            serverListPingEvent.setMaxPlayers(maxPlayers);
            return;
        }
        int online = serverListPingEvent.getNumPlayers();
            int maxRespective = 1;
            int totalRespective = online+maxRespective;
            serverListPingEvent.setMaxPlayers(totalRespective);

    }

}
