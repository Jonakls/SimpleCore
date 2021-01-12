package me.jonakls.simplecore.objects;

import org.bukkit.ChatColor;

public class ParseColors {
    public String setColor(String string){
         return ChatColor.translateAlternateColorCodes('&', string);
    }
}
