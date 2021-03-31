package me.jonakls.simplecore.handlers;

import me.jonakls.simplecore.Service;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class MessageHandler {

    final private Service service;
    private TextComponent senderMessage;
    private TextComponent targetMessage;

    public MessageHandler(Service service){
        this.service = service;
    }

    public void setFormatSender(Player target, String message){
        senderMessage = new TextComponent(
                service.getFiles().getLang().getString("private-messages.sender.format.chat-message")
                .replace("%target%",target.getName())
                .replace("%message%", message));

        senderMessage.setClickEvent(new ClickEvent(ClickEvent.Action
                .valueOf(service.getFiles().getLang().getString("private-messages.sender.format.click-event"))
                ,service.getFiles().getLang().getString("private-messages.sender.format.action")
                .replace("%target%",target.getName())));

        senderMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                service.getFiles().getLang().getString("private-messages.sender.format.hover")).create()));

    }

    public void setFormatTarget(Player sender, String message){
        targetMessage = new TextComponent(
                service.getFiles().getLang().getString("private-messages.target.format.chat-message")
                .replace("%sender%",sender.getName())
                .replace("%message%", message));

        targetMessage.setClickEvent(new ClickEvent(ClickEvent.Action
                .valueOf(service.getFiles().getLang().getString("private-messages.target.format.click-event"))
                , service.getFiles().getLang().getString("private-messages.target.format.action")
                .replace("%sender%",sender.getName())));

        targetMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                service.getFiles().getLang().getString("private-messages.target.format.hover")).create()));

    }

    public TextComponent getFormatSender(){
        return senderMessage;
    }

    public TextComponent getFormatTarget(){
        return targetMessage;
    }
}
