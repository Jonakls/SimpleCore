package me.jonakls.simplecore.menus.generators;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class MenuGenerator {

    private final ItemGenerator item = new ItemGenerator();
    private final InventoryGenerator inventory = new InventoryGenerator();

    public void newMenu(Player player){
        item.newItem(player, "Name", Material.getMaterial("BOW"), 5, "&dude", "loreee");
        inventory.newInventory(54, 22, item.getItem());

        player.openInventory(inventory.getInventory());
    }

}
