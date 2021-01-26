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

        if (manager.getFiles().getConfig().getBoolean("server-List.enable")){
            serverListPingEvent.setMotd(manager.getFiles().getConfig().getString("server-List.line1")+ "\n"+manager.getFiles().getConfig().getString("server-List.line2"));
        }

        if (!(manager.getFiles().getConfig().getBoolean("server-List.respective-mode"))){
            serverListPingEvent.setMaxPlayers(manager.getFiles().getConfig().getInt("server-List.max-players"));
            return;
        }
        int online = serverListPingEvent.getNumPlayers();
            int maxRespective = 1;
            int totalRespective = online+maxRespective;
            serverListPingEvent.setMaxPlayers(totalRespective);

    }

}
