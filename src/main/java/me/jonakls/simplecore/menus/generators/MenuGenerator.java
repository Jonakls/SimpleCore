package me.jonakls.simplecore.menus.generators;

import me.jonakls.simplecore.SimpleCore;
import me.jonakls.simplecore.files.MenuFile;
import me.jonakls.simplecore.objects.ParseColors;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import java.util.List;

public class MenuGenerator{

    private final ItemGenerator item = new ItemGenerator();
    private final InventoryGenerator inventory = new InventoryGenerator();
    private final SimpleCore simpleCore;
    private final ParseColors colors = new ParseColors();

    public MenuGenerator(SimpleCore simpleCore){
        this.simpleCore = simpleCore;
    }

    public void generalMenu(Player player){

        FileConfiguration menuFile = new MenuFile(simpleCore).getMenus();

        List<String> lore = menuFile.getStringList("general.items.centralized-item.lore");

        lore.replaceAll(colors::setColor);

        item.newItem(Material.getMaterial(menuFile.getString("general.items.centralized-item.material")),
                menuFile.getInt("general.items.centralized-item.amount"),
                menuFile.getString("general.items.centralized-item.displayname"),
                lore);

        inventory.newInventory( menuFile.getInt("general.size"),
                colors.setColor(menuFile.getString("general.name")));
        inventory.newItem(menuFile.getInt("general.items.centralized-item.slot"),
                item.getItem());


        // Other item
        lore = menuFile.getStringList("general.items.close-item.lore");

        lore.replaceAll(colors::setColor);

        item.newItem(Material.getMaterial(menuFile.getString("general.items.close-item.material")),
                menuFile.getInt("general.items.close-item.amount"),
                menuFile.getString("general.items.close-item.displayname"),
                lore);

        inventory.newItem(menuFile.getInt("general.items.close-item.slot"),
                item.getItem());


        player.openInventory(inventory.getInventory());
    }

}
