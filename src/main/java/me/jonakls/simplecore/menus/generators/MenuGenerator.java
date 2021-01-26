package me.jonakls.simplecore.menus.generators;

import me.jonakls.simplecore.Manager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class MenuGenerator{

    private final ItemGenerator item = new ItemGenerator();
    private final InventoryGenerator inventory = new InventoryGenerator();
    private final Manager manager;

    public MenuGenerator(Manager manager){
        this.manager = manager;
    }

    public void generalMenu(Player player){

        List<String> lore = manager.getFiles().getMenus().getStringList("general.items.centralized-item.lore");

        item.newItem(Material.getMaterial(manager.getFiles().getMenus().getString("general.items.centralized-item.material")),
                manager.getFiles().getMenus().getInt("general.items.centralized-item.amount"),
                manager.getFiles().getMenus().getString("general.items.centralized-item.displayname"),
                lore);

        inventory.newInventory( manager.getFiles().getMenus().getInt("general.size"),
                manager.getFiles().getMenus().getString("general.name"));
        inventory.newItem(manager.getFiles().getMenus().getInt("general.items.centralized-item.slot"),
                item.getItem());


        // Other item
        lore = manager.getFiles().getMenus().getStringList("general.items.close-item.lore");

        item.newItem(Material.getMaterial(manager.getFiles().getMenus().getString("general.items.close-item.material")),
                manager.getFiles().getMenus().getInt("general.items.close-item.amount"),
                manager.getFiles().getMenus().getString("general.items.close-item.displayname"),
                lore);

        inventory.newItem(manager.getFiles().getMenus().getInt("general.items.close-item.slot"),
                item.getItem());


        player.openInventory(inventory.getInventory());
    }

}
