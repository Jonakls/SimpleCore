package me.jonakls.simplecore.utils;

import me.jonakls.simplecore.files.FileManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageReplacer {

    public static String noConsole(){

        return ChatColor.translateAlternateColorCodes('&',
                FileManager.getLang().getString("messages.error.no-console")).
                replace("%prefix%", FileManager.getLang().getString("messages.prefix"));
    }

    public static String gamemode(String message, String gamemode, Player player){

        return ChatColor.translateAlternateColorCodes('&', message).
                replace("%prefix%", FileManager.getLang().getString("messages.prefix")).
                replace("%type%", gamemode).
                replace("%player%", player.getName()).
                replace("%target%", player.getName());
    }

    public static String noPlayer(String error){

        return ChatColor.translateAlternateColorCodes('&',
                FileManager.getLang().getString("messages.error.no-player")).
                replace("%prefix%", FileManager.getLang().getString("messages.prefix")).
                replace("%player%", error);
    }

    public static String noPermissions(){

        return ChatColor.translateAlternateColorCodes('&',
                FileManager.getLang().getString("messages.error.no-permissions").
                replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
    }
}
