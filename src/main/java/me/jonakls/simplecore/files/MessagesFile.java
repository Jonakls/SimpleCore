package me.jonakls.simplecore.files;

import me.jonakls.simplecore.SimpleCore;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MessagesFile{

    private FileConfiguration messages = null;
    private File messagesFile = null;

    private final SimpleCore simpleCore;

    public MessagesFile(SimpleCore simpleCore){
        this.simpleCore =  simpleCore;
    }

    public FileConfiguration getMessages(){
        if(messages == null){
            reloadMessages();
        }
        return messages;
    }

    public void reloadMessages(){
        if(messages == null){
            messagesFile = new File(Bukkit.getServer().getPluginManager().getPlugin("SimpleCore").getDataFolder(),"messages.yml");
        }
        messages = YamlConfiguration.loadConfiguration(messagesFile);
        Reader defConfigStream;
        defConfigStream = new InputStreamReader(this.simpleCore.getResource("messages.yml"), StandardCharsets.UTF_8);
        if(defConfigStream != null){
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            messages.setDefaults(defConfig);
        }
    }

    public void saveMessages(){
        try{
            messages.save(messagesFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public MessagesFile setupMessages(){
        messagesFile = new File(Bukkit.getServer().getPluginManager().getPlugin("SimpleCore").getDataFolder(),"messages.yml");
        if(!messagesFile.exists()){
            this.getMessages().options().copyDefaults(true);
            saveMessages();
        }
        return null;
    }

}
