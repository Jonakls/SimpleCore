package me.jonakls.simplecore.menus.generators;

import me.jonakls.simplecore.SimpleCore;
import me.jonakls.simplecore.files.MenuFile;
import me.jonakls.simplecore.objects.ParseColors;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class MenuGenerator {

    private final ItemGenerator item = new ItemGenerator();
    private final InventoryGenerator inventory = new InventoryGenerator();
    private final SimpleCore simpleCore;
    private final ParseColors colors = new ParseColors();

    public MenuGenerator(SimpleCore simpleCore){
        this.simpleCore = simpleCore;
    }

    public void newMenu(Player player){
        FileConfiguration menuFile = new MenuFile(simpleCore).getMenus();

        item.newItem(Material.getMaterial(menuFile.getString("General.items.centralized-item.material")),
                menuFile.getInt("General.items.centralized-item.amount"),
                menuFile.getString("General.items.centralized-item.displayname"),
                menuFile.getString("General.items.centralized-item.lore"));

        inventory.newInventory( menuFile.getInt("General.size"),
                menuFile.getInt("General.items.centralized-item.slot"),
                item.getItem(),
                colors.setColor(menuFile.getString("General.name")));

        player.openInventory(inventory.getInventory());
    }

}
