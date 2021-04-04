package me.jonakls.simplecore.files;

import me.jonakls.simplecore.SimpleCore;
import me.jonakls.simplecore.files.yaml.FileCreator;

public class FileManager {

    private final SimpleCore plugin;

    private FileCreator lang;
    private FileCreator config;
    private FileCreator data;
    private FileCreator warps;
    private FileCreator spawn;

    public FileManager(SimpleCore plugin){
        this.plugin = plugin;
    }

    public void setupFiles(){
        lang = this.fileCreator("lang");
        config = this.fileCreator("config");
        data = this.fileCreator("data");
        warps = this.fileCreator("warps");
        spawn = this.fileCreator("spawn");

    }

    public FileCreator fileCreator(String string){
        return new FileCreator(plugin, string);
    }

    public FileCreator getLang(){
        return lang;
    }

    public FileCreator getConfig(){
        return config;
    }

    public FileCreator getData(){
        return data;
    }

    public FileCreator getWarps(){
        return warps;
    }

    public FileCreator getSpawn(){
        return spawn;
    }

}
