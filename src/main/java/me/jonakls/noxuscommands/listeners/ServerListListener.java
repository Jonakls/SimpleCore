package me.jonakls.noxuscommands.listeners;

import me.jonakls.noxuscommands.files.FileManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListListener implements Listener {

    @EventHandler
    public void customServerList(ServerListPingEvent serverListPingEvent){

        if (FileManager.getConfig().getBoolean("server-list.enable")){

            serverListPingEvent.setMotd(FileManager.getConfig().getString("server-list.line1")
                    +"\n"+
                    FileManager.getConfig().getString("server-list.line2"));
        }

        if (!(FileManager.getConfig().getBoolean("server-list.respective-mode"))){
            serverListPingEvent.setMaxPlayers(FileManager.getConfig().getInt("server-list.max-players"));
            return;
        }
        int online = serverListPingEvent.getNumPlayers();
            int maxRespective = 1;
            int totalRespective = online+maxRespective;
            serverListPingEvent.setMaxPlayers(totalRespective);

    }

}
