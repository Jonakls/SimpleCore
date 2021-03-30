package me.jonakls.simplecore.listeners;

import me.jonakls.simplecore.Service;
import me.jonakls.simplecore.modules.ChatFormatModule;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    final private Service service;
    public ChatListener(Service service){
        this.service = service;
    }

    @EventHandler
    public void onChatListener(AsyncPlayerChatEvent chatEvent){

        if (!(service.getFiles().getConfig().getBoolean("config.chat-format.enable"))){
            return;
        }

        String message = chatEvent.getMessage();
        chatEvent.setCancelled(true);

        ChatFormatModule chatFormat = new ChatFormatModule(service);
        chatFormat.setChatFormat(chatEvent.getPlayer(), message);

        chatEvent.getRecipients().forEach(allPlayers -> allPlayers.spigot().sendMessage(chatFormat.getChatFormat()));

    }
}
