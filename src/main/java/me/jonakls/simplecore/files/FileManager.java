package me.jonakls.simplecore.files;

import me.jonakls.simplecore.SimpleCore;
import me.jonakls.simplecore.files.yaml.FileCreator;

public class FileManager {

    private final SimpleCore plugin;

    private FileCreator lang;
    private FileCreator config;

    public FileManager(SimpleCore plugin){
        this.plugin = plugin;
    }

    public void setupFiles(){
        lang = this.fileCreator("lang");
        config = this.fileCreator("config");
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

}
