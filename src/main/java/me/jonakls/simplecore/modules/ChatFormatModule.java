package me.jonakls.simplecore.modules;

import me.clip.placeholderapi.PlaceholderAPI;
import me.jonakls.simplecore.Service;
import me.jonakls.simplecore.utils.ColorApply;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class ChatFormatModule {

    final private Service service;
    private TextComponent chatFormat;


    public ChatFormatModule(Service service){
        this.service = service;
    }

    public void setChatFormat(Player player, String message){

        if (!(service.getFiles().getConfig().getBoolean("config.chat-format.displayname-with-prefix"))){
            chatFormat = new TextComponent(PlaceholderAPI.setPlaceholders(
                    player,service.getFiles().getConfig().getString("config.chat-format.format.chat-message")
                            .replace("%displayName%", player.getDisplayName())
                            .replace("%playerName%", player.getName())
                            + ColorApply.apply(message)));

            chatFormat.setClickEvent(new ClickEvent(ClickEvent.Action
                    .valueOf(service.getFiles().getConfig().getString("config.chat-format.format.click-event"))
                    ,service.getFiles().getConfig().getString("config.chat-format.format.action")
                    .replace("%player%",player.getName())));

            chatFormat.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                    PlaceholderAPI.setPlaceholders(player,
                    service.getFiles().getConfig().getString("config.chat-format.format.hover"))).create()));
            return;
        }

        chatFormat = new TextComponent(PlaceholderAPI.setPlaceholders(
                player, service.getFiles().getConfig().getString("config.chat-format.format.chat-message")
                        .replace("%displayName%", ColorApply.apply(
                                Service.getVaultChat().getPlayerPrefix(player)+player.getDisplayName()))
                        .replace("%playerName%", ColorApply.apply(
                                Service.getVaultChat().getPlayerPrefix(player)+player.getName()))
                        + ColorApply.apply(message)));

        chatFormat.setClickEvent(new ClickEvent(ClickEvent.Action
                .valueOf(service.getFiles().getConfig().getString("config.chat-format.format.click-event"))
                ,service.getFiles().getConfig().getString("config.chat-format.format.action")
                .replace("%player%",player.getName())));

        chatFormat.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                PlaceholderAPI.setPlaceholders(player,service.getFiles().getConfig().getString(
                        "config.chat-format.format.hover"))).create()));

    }

    public TextComponent getChatFormat(){
        return chatFormat;
    }

}
