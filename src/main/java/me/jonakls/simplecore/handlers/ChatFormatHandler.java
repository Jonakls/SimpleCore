package me.jonakls.simplecore.handlers;

import me.clip.placeholderapi.PlaceholderAPI;
import me.jonakls.simplecore.Service;
import me.jonakls.simplecore.utils.ColorApply;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class ChatFormatHandler {

    final private Service service;
    private TextComponent chatFormat;


    public ChatFormatHandler(Service service){
        this.service = service;
    }

    public void setChatFormat(Player player, String message, String group){

        for (String string: service.getFiles().getConfig().getConfigurationSection(
                "config.chat-format.groups").getKeys(false)){

            if (group.equals(string.toLowerCase())){

                HoverHandler hover = new HoverHandler();

                hover.setHoverList(PlaceholderAPI.setPlaceholders(
                        player,service.getFiles().getConfig().getString
                                ("config.chat-format.groups."+group+".format.chat-message")
                                .replace("%displayName%", player.getDisplayName())
                                .replace("%playerName%", player.getName())
                                + ColorApply.apply(message)),

                        service.getFiles().getConfig().getString("config.chat-format.groups."
                                +group+".format.click-event"),

                        service.getFiles().getConfig().getString("config.chat-format.groups."
                                +group+".format.action")
                                .replace("%player%",player.getName()),

                        PlaceholderAPI.setPlaceholders(player,
                                service.getFiles().getConfig().getStringList(
                                        "config.chat-format.groups."+group+".format.hover")));

                chatFormat = hover.getHoverFormat();
                return;
            }

            HoverHandler hover = new HoverHandler();

            hover.setHoverList(PlaceholderAPI.setPlaceholders(
                    player,service.getFiles().getConfig().getString
                            ("config.chat-format.default.format.chat-message")
                            .replace("%displayName%", player.getDisplayName())
                            .replace("%playerName%", player.getName())
                            + ColorApply.apply(message)),

                    service.getFiles().getConfig().getString("config.chat-format.default.format.click-event"),
                    service.getFiles().getConfig().getString("config.chat-format.default.format.action")
                            .replace("%player%",player.getName()),

                    PlaceholderAPI.setPlaceholders(player,
                            service.getFiles().getConfig().getStringList(
                                    "config.chat-format.default.format.hover")));

            chatFormat = hover.getHoverFormat();

        }
    }

    public TextComponent getChatFormat(){
        return chatFormat;
    }

}
