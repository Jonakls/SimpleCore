package me.jonakls.simplecore.events;

import me.jonakls.simplecore.SimpleCore;
import me.jonakls.simplecore.objects.ParseColors;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListEvent implements Listener {

    private final SimpleCore simpleCore;
    private final ParseColors colors = new ParseColors();

    public ServerListEvent(SimpleCore simpleCore){
        this.simpleCore = simpleCore;
    }

    @EventHandler
    public void customServerList(ServerListPingEvent serverListPingEvent){

        FileConfiguration configFile = simpleCore.getConfig();

        String lineOne = configFile.getString("server-List.line1");
        String lineTwo = configFile.getString("server-List.line2");
        int maxPlayers = configFile.getInt("server-List.max-players");

        if (configFile.getBoolean("server-List.enable")){
            serverListPingEvent.setMotd(colors.setColor(lineOne+"\n"+lineTwo));
        }

        if (!(configFile.getBoolean("server-List.respective-mode"))){
            serverListPingEvent.setMaxPlayers(maxPlayers);
            return;
        }
        int online = serverListPingEvent.getNumPlayers();
            int maxRespective = 1;
            int totalRespective = online+maxRespective;
            serverListPingEvent.setMaxPlayers(totalRespective);

    }

}
