package jonakls.simplecore.events;

import jonakls.simplecore.SimpleCore;
import jonakls.simplecore.files.MessagesFile;
import jonakls.simplecore.objects.ParseColors;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    private final SimpleCore simpleCore;
    private final ParseColors colors = new ParseColors();

    public JoinEvent(SimpleCore simpleCore){
        this.simpleCore = simpleCore;
    }

    @Deprecated
    @EventHandler
    public void onJoin(PlayerJoinEvent joinEvent){

        FileConfiguration configFile = simpleCore.getConfig();
        FileConfiguration messages = new MessagesFile(simpleCore).getMessages();

        String joinMessage = messages.getString("Events.join-player.message");

        String joinTitle = messages.getString("Events.join-player.title");
        String joinSubTitle = messages.getString("Events.join-player.sub-title");



        Player p = joinEvent.getPlayer();
        if (!(configFile.getBoolean("Config.join-message"))){
            joinEvent.setJoinMessage(null);
            return;
        }
        joinEvent.setJoinMessage(colors.setColor(PlaceholderAPI.setPlaceholders(p, joinMessage.replace("%player%", p.getName()))));

        if (configFile.getBoolean("Titles.join-title.enable")){
            p.sendTitle(colors.setColor(joinTitle), colors.setColor(joinSubTitle));
        }
    }
}
