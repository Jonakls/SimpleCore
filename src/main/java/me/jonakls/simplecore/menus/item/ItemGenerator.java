package me.jonakls.simplecore.menus.item;


import me.jonakls.simplecore.objects.ParseColors;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemGenerator {

    private final ParseColors colors = new ParseColors();
    private ItemStack item;

    public void newItem(Player player, String name, Material material, Short metaData, int amount, String displayname,String lore){
        item = new ItemStack(material, amount, metaData);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(colors.setColor(displayname));
        List<String> loreItem = new ArrayList<>();
        loreItem.add(colors.setColor(lore));
        meta.setLore(loreItem);
    }

    public ItemStack getItem(){
        return item;
    }


}
