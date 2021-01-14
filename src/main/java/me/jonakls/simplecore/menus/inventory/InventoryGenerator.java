package me.jonakls.simplecore.menus.inventory;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryGenerator {

    private Inventory inventory;

    public void newInventory(int size){
         Bukkit.createInventory(null, size);
    }
    public void setItem(int slot, ItemStack item){
        inventory.setItem(slot, item);
    }

    public Inventory getInventory(){
        return inventory;
    }
}
