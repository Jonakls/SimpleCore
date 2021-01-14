package me.jonakls.simplecore.menus;

import me.jonakls.simplecore.SimpleCore;
import me.jonakls.simplecore.files.MenuFile;
import me.jonakls.simplecore.menus.inventory.InventoryGenerator;
import me.jonakls.simplecore.menus.item.ItemGenerator;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class MenuGenerator {

    private ItemGenerator item;
    private InventoryGenerator inventory;
    private SimpleCore simpleCore;

    public  MenuGenerator (SimpleCore simpleCore){
        this.simpleCore = simpleCore;
    }

    FileConfiguration menusFile = new MenuFile(simpleCore).getMenus();
    public void newMenu(Player player){
        item.newItem(player, "Name", Material.getMaterial("asd"), null, 5, "&dude", "loreee");
        inventory.newInventory(20);
        inventory.setItem(5, item.getItem());

        player.openInventory(inventory.getInventory());
    }
}
