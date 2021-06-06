package me.jonakls.noxuscommands.handlers;

import me.jonakls.noxuscommands.files.FileManager;
import me.jonakls.noxuscommands.utils.HoverMethod;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class MessageHandler {

    private final HoverMethod hoverMethod = new HoverMethod();

    public TextComponent setFormatSender(Player target, String message){


        hoverMethod.setHover(
                FileManager.getConfig().getString("private-messages.sender.format.chat-message").
                        replace("%target%", target.getName()).
                        replace("%message%", message),
                FileManager.getConfig().getString("private-messages.sender.format.click-event"),
                FileManager.getConfig().getString("private-messages.sender.format.action").
                        replace("%target%",target.getName()),
                FileManager.getConfig().getString("private-messages.sender.format.hover"));

        return hoverMethod.getHoverFormat();

    }

    public TextComponent setFormatTarget(Player sender, String message){

        hoverMethod.setHover(
                FileManager.getConfig().getString("private-messages.target.format.chat-message").
                        replace("%sender%", sender.getName()).
                        replace("%message%", message),
                FileManager.getConfig().getString("private-messages.target.format.click-event"),
                FileManager.getConfig().getString("private-messages.target.format.action").
                        replace("%sender%",sender.getName()),
                FileManager.getConfig().getString("private-messages.target.format.hover"));


        return hoverMethod.getHoverFormat();

    }
}
