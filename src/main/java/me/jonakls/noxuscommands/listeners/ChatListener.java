package me.jonakls.noxuscommands.listeners;

import me.jonakls.noxuscommands.Service;
import me.jonakls.noxuscommands.files.FileManager;
import me.jonakls.noxuscommands.handlers.ChatFormatHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final ChatFormatHandler chatFormat = new ChatFormatHandler();

    @EventHandler
    public void onChatListener(AsyncPlayerChatEvent chatEvent){

        if (!(FileManager.getConfig().getBoolean("config.chat-format.enable"))){
            return;
        }

        String message = chatEvent.getMessage();
        chatEvent.setCancelled(true);

        chatFormat.setChatFormat(
                chatEvent.getPlayer(),
                message,
                Service.getVaultChat().getPlayerGroups(chatEvent.getPlayer())[

                        Service.getVaultChat().getPlayerGroups(chatEvent.getPlayer()).length - 1

                        ].toLowerCase());

        chatEvent.getRecipients().forEach(allPlayers -> allPlayers.spigot().sendMessage(chatFormat.getChatFormat()));

    }
}
