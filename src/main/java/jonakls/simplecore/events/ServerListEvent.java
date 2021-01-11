package jonakls.simplecore.events;

import jonakls.simplecore.SimpleCore;
import jonakls.simplecore.objects.ParseColors;
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

        String lineOne = configFile.getString("Server-List.line1");
        String lineTwo = configFile.getString("Server-List.line2");
        int maxPlayers = configFile.getInt("Server-List.max-players");

        if (configFile.getBoolean("Server-List.enable")){
            serverListPingEvent.setMotd(colors.setColor(lineOne+"\n"+lineTwo));
        }

        if (!(configFile.getBoolean("Server-List.respective-mode"))){
            serverListPingEvent.setMaxPlayers(maxPlayers);
            return;
        }
        int online = serverListPingEvent.getNumPlayers();
            int maxRespective = 1;
            int totalRespective = online+maxRespective;
            serverListPingEvent.setMaxPlayers(totalRespective);

    }

}
