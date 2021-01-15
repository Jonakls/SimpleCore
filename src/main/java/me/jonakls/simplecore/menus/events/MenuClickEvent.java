package me.jonakls.simplecore.menus.events;

import me.jonakls.simplecore.SimpleCore;
import me.jonakls.simplecore.files.MenuFile;
import me.jonakls.simplecore.objects.ParseColors;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuClickEvent implements Listener {

    private final ParseColors colors = new ParseColors();
    private final SimpleCore simpleCore;

    public MenuClickEvent(SimpleCore simpleCore){
        this.simpleCore = simpleCore;
    }

    @EventHandler
    public void clickEvent(InventoryClickEvent event){

        FileConfiguration menuFile = new MenuFile(simpleCore).getMenus();

        String menuName = menuFile.getString("General.name");
        String name = colors.setColor(menuName);
        String simpleName = colors.removeColors(name);


        if (colors.removeColors(event.getInventory().getName()).equals(simpleName)){
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
