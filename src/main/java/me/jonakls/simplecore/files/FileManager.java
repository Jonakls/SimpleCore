package me.jonakls.simplecore.files;

import me.jonakls.simplecore.SimpleCore;
import me.jonakls.simplecore.files.yaml.FileCreator;

public class FileManager {

    private final SimpleCore plugin;

    private FileCreator lang;
    private FileCreator config;
    private FileCreator menus;

    public FileManager(SimpleCore plugin){
        this.plugin = plugin;
    }

    public void setupFiles(){
        lang = this.setFile("lang");
        config = this.setFile("config");
        menus = this.setFile("menus");
    }

    public FileCreator setFile(String string){
        return new FileCreator(plugin, string);
    }

    public FileCreator getLang(){
        return lang;
    }

    public FileCreator getConfig(){
        return config;
    }

    public FileCreator getMenus(){
        return menus;
    }

}
