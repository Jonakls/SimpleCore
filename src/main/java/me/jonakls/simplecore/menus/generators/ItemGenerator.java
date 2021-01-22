package me.jonakls.simplecore.menus.generators;


import me.jonakls.simplecore.objects.ParseColors;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;

public class ItemGenerator {

    private final ParseColors colors = new ParseColors();
    private ItemStack item;

    public void newItem(Material material, int amount, String displayName,List<String> lore){

        item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(colors.setColor(displayName));
        meta.setLore(lore);
        item.setItemMeta(meta);
    }

    public ItemStack getItem(){
        return item;
    }


}
