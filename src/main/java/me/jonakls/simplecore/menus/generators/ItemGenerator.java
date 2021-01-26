package me.jonakls.simplecore.menus.generators;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;

public class ItemGenerator {

    private ItemStack item;

    public void newItem(Material material, int amount, String displayName,List<String> lore){

        item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        item.setItemMeta(meta);
    }

    public ItemStack getItem(){
        return item;
    }


}
