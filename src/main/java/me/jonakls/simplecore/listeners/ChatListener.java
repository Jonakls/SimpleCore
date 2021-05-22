package me.jonakls.simplecore.listeners;

import me.jonakls.simplecore.Service;
import me.jonakls.simplecore.files.FileManager;
import me.jonakls.simplecore.handlers.ChatFormatHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChatListener(AsyncPlayerChatEvent chatEvent){

        if (!(FileManager.getConfig().getBoolean("config.chat-format.enable"))){
            return;
        }

        String message = chatEvent.getMessage();
        chatEvent.setCancelled(true);

        ChatFormatHandler chatFormat = new ChatFormatHandler();


        chatFormat.setChatFormat(chatEvent.getPlayer(), message,
                Service.getVaultChat().getPlayerGroups(chatEvent.getPlayer())
                        [Service.getVaultChat().getPlayerGroups(chatEvent.getPlayer()).length - 1].toLowerCase());



        chatEvent.getRecipients().forEach(allPlayers -> allPlayers.spigot().sendMessage(chatFormat.getChatFormat()));

    }
}
