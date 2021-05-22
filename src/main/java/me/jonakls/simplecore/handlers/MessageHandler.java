package me.jonakls.simplecore.handlers;

import me.jonakls.simplecore.files.FileManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class MessageHandler {

    public TextComponent setFormatSender(Player target, String message){

        TextComponent senderMessage = new TextComponent(

                FileManager.getLang().getString("private-messages.sender.format.chat-message").
                        replace("%target%", target.getName()).
                        replace("%message%", message));

        senderMessage.setClickEvent(new ClickEvent(ClickEvent.Action.valueOf(
                FileManager.getLang().getString("private-messages.sender.format.click-event")),
                FileManager.getLang().getString("private-messages.sender.format.action").
                        replace("%target%",target.getName())));

        senderMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                FileManager.getLang().getString("private-messages.sender.format.hover")).create()));

        return senderMessage;

    }

    public TextComponent setFormatTarget(Player sender, String message){

        TextComponent targetMessage = new TextComponent(
                FileManager.getLang().getString("private-messages.target.format.chat-message").
                        replace("%sender%", sender.getName()).
                        replace("%message%", message));

        targetMessage.setClickEvent(new ClickEvent(ClickEvent.Action.valueOf(
                FileManager.getLang().getString("private-messages.target.format.click-event")),
                FileManager.getLang().getString("private-messages.target.format.action").
                        replace("%sender%",sender.getName())));

        targetMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                FileManager.getLang().getString("private-messages.target.format.hover")).create()));

        return targetMessage;

    }
}
