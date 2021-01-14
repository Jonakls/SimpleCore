package me.jonakls.simplecore.objects;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class InventoryCreate implements Listener {

    private final ParseColors colors = new ParseColors();

    public void newInventory(Player player){
        Inventory inv = Bukkit.createInventory(null, 54, colors.setColor("&aMenu"));

        ItemStack item = new ItemStack(Material.GRASS, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(colors.setColor("Item random"));
        List<String> lore = new ArrayList<>();
        lore.add(colors.setColor("Prueba yayaya"));
        meta.setLore(lore);
        inv.setItem(22, item);

        ItemStack decoration = new ItemStack(Material.STAINED_GLASS_PANE,1, (short) 9);
        for (int i = 0 ; i < 9; i++){
            inv.setItem(i, decoration);
        }
        player.openInventory(inv);
        return;
    }
    @EventHandler
    public void clickEvent(InventoryClickEvent event){
        String name = colors.setColor("&aMenu");
        String removeName = colors.removeColors(name);


        if (colors.removeColors(event.getInventory().getName()).equals(removeName)){
            if(!(event.getCurrentItem() == null || event.getSlotType() == null || event.getCurrentItem().getType() == Material.AIR)){
                if(!(event.getCurrentItem().hasItemMeta())){
                    event.setCancelled(true);
                    return;
                }
                event.setCancelled(true);
                return;

            }
            event.setCancelled(true);
        }
    }
}
