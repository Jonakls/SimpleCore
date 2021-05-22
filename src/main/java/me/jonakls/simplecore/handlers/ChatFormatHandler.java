package me.jonakls.simplecore.handlers;

import me.clip.placeholderapi.PlaceholderAPI;
import me.jonakls.simplecore.files.FileManager;
import me.jonakls.simplecore.utils.ColorApply;
import me.jonakls.simplecore.utils.HoverMethod;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class ChatFormatHandler {

    private TextComponent chatFormat;

    public void setChatFormat(Player player, String message, String group){

        for (String string: FileManager.getConfig().getConfigurationSection(
                "config.chat-format.groups").getKeys(false)){

            if (group.equals(string.toLowerCase())){

                HoverMethod hover = new HoverMethod();

                hover.setHoverList(PlaceholderAPI.setPlaceholders(
                        player,
                        FileManager.getConfig().getString("config.chat-format.groups."+group+".format.chat-message").
                                replace("%displayName%", player.getDisplayName()).
                                replace("%playerName%", player.getName()) +
                                ColorApply.apply(message)),
                        FileManager.getConfig().getString("config.chat-format.groups." +group+".format.click-event"),

                        FileManager.getConfig().getString("config.chat-format.groups." +group+".format.action").
                                replace("%player%",player.getName()),

                        FileManager.getConfig().getStringList("config.chat-format.groups."+group+".format.hover"));

                chatFormat = hover.getHoverFormat();
                return;
            }

            HoverMethod hover = new HoverMethod();

            hover.setHoverList(PlaceholderAPI.setPlaceholders(
                    player,
                    FileManager.getConfig().getString("config.chat-format.default.format.chat-message").
                            replace("%displayName%", player.getDisplayName()).
                            replace("%playerName%", player.getName()) +
                            ColorApply.apply(message)),

                    FileManager.getConfig().getString("config.chat-format.default.format.click-event"),
                    FileManager.getConfig().getString("config.chat-format.default.format.action").
                            replace("%player%",player.getName()),

                    FileManager.getConfig().getStringList("config.chat-format.default.format.hover"));

            chatFormat = hover.getHoverFormat();

        }
    }

    public TextComponent getChatFormat(){
        return chatFormat;
    }

}
