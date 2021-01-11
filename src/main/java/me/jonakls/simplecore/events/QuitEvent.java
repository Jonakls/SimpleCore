package me.jonakls.simplecore.events;

import me.jonakls.simplecore.SimpleCore;
import me.jonakls.simplecore.files.MessagesFile;
import me.jonakls.simplecore.objects.ParseColors;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    private SimpleCore simpleCore;
    private ParseColors colors = new ParseColors();

    public QuitEvent(SimpleCore simpleCore){
        this.simpleCore = simpleCore;
    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent quitEvent){

        FileConfiguration configFile = simpleCore.getConfig();
        FileConfiguration messages = new MessagesFile(simpleCore).getMessages();

        String joinMessage = messages.getString("Events.quit-player.message");
        String quitOption = "Config.quit-message";


        Player p = quitEvent.getPlayer();
        if (!(configFile.getBoolean(quitOption))){
            quitEvent.setQuitMessage(null);
            return;
        }
        quitEvent.setQuitMessage(colors.setColor(PlaceholderAPI.setPlaceholders(p, joinMessage.replace("%player%", p.getName()))));
    }
}
