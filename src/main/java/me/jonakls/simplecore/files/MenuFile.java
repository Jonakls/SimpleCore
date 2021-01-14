package me.jonakls.simplecore.files;

import me.jonakls.simplecore.SimpleCore;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class MenuFile {

    private FileConfiguration menu = null;
    private File menuFile = null;

    private final SimpleCore simpleCore;

    public MenuFile(SimpleCore simpleCore){
        this.simpleCore =  simpleCore;
    }

    public FileConfiguration getMenus(){
        if(menu == null){
            reloadMenus();
        }
        return menu;
    }

    public void reloadMenus(){
        if(menu == null){
            menuFile = new File(Bukkit.getServer().getPluginManager().getPlugin("SimpleCore").getDataFolder(),"menus.yml");
        }
        menu = YamlConfiguration.loadConfiguration(menuFile);
        Reader defConfigStream;
        defConfigStream = new InputStreamReader(this.simpleCore.getResource("menus.yml"), StandardCharsets.UTF_8);
        if(defConfigStream != null){
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            menu.setDefaults(defConfig);
        }
    }

    public void saveMenus(){
        try{
            menu.save(menuFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public MenuFile setupMenus(){
        menuFile = new File(Bukkit.getServer().getPluginManager().getPlugin("SimpleCore").getDataFolder(),"menus.yml");
        if(!menuFile.exists()){
            this.getMenus().options().copyDefaults(true);
            saveMenus();
        }
        return null;
    }

}
