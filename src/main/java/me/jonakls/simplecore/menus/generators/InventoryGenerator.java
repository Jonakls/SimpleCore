package me.jonakls.simplecore.menus.generators;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryGenerator {

    private Inventory inventory;

    public void newInventory(int size, int slot, ItemStack item, String nameInventory){

         inventory = Bukkit.createInventory(null, size, ""+nameInventory);

         inventory.setItem(slot, item);

    }

    public Inventory getInventory(){
        return inventory;
    }
}
