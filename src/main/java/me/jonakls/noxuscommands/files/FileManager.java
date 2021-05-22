package me.jonakls.noxuscommands.files;

import me.jonakls.noxuscommands.NoxusCommands;
import me.jonakls.noxuscommands.files.yaml.FileCreator;

public class FileManager {

    private final NoxusCommands plugin;

    private static FileCreator lang;
    private static FileCreator config;
    private static FileCreator data;
    private static FileCreator warps;
    private static FileCreator spawn;

    public FileManager(NoxusCommands plugin){
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

    public static FileCreator getLang(){
        return lang;
    }

    public static FileCreator getConfig(){
        return config;
    }

    public static FileCreator getData(){
        return data;
    }

    public static FileCreator getWarps(){
        return warps;
    }

    public static FileCreator getSpawn(){
        return spawn;
    }

}
