package me.jonakls.simplecore.utils;

import org.bukkit.ChatColor;

public class ColorApply {

    public static String apply(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String withdraw(String string){
        return ChatColor.stripColor(string);
    }

}
