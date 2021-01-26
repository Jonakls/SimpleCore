package me.jonakls.simplecore.menus.listener;

import me.jonakls.simplecore.Manager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuClickListener implements Listener {

    private final Manager manager;

    public MenuClickListener(Manager manager){
        this.manager = manager;
    }

    @EventHandler
    public void clickEvent(InventoryClickEvent event){

        String name = manager.getFiles().getMenus().getString("general.name");
        String simpleName = ChatColor.stripColor(name);


        if (ChatColor.stripColor(event.getInventory().getName()).equals(simpleName)){
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
