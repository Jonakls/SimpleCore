package me.jonakls.simplecore.menus.events;

import me.jonakls.simplecore.objects.ParseColors;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryCreate implements Listener {

    private final ParseColors colors = new ParseColors();

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
