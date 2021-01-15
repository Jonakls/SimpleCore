package me.jonakls.simplecore.menus.generators;


import me.jonakls.simplecore.objects.ParseColors;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemGenerator {

    private final ParseColors colors = new ParseColors();
    private ItemStack item;

    public void newItem(Material material, int amount, String displayName,String lore){

        item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(""+colors.setColor(displayName));
        List<String> loreItem = new ArrayList<>();
        loreItem.add(colors.setColor(""+lore));
        meta.setLore(loreItem);
    }

    public ItemStack getItem(){
        return item;
    }


}
