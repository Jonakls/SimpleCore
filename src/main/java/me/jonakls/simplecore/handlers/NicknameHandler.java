package me.jonakls.simplecore.handlers;

import me.jonakls.simplecore.Service;
import me.jonakls.simplecore.files.yaml.FileCreator;
import me.jonakls.simplecore.utils.ColorApply;
import org.bukkit.entity.Player;

public class NicknameHandler {

    final private Service service;
    private boolean option;

    public NicknameHandler(Service service) {
        this.service = service;
    }

    public void setNickname(Player player, String nickname) {

        if (!(service.getFiles().getData().contains("data." + player.getName()))) {

            player.setDisplayName(ColorApply.apply(nickname));
            service.getFiles().getData().set("data."+player.getName()+".nick-name", nickname);
            service.getFiles().getData().save();

            option = true;
            return;
        }
        option = false;
    }

    public void taskNickname(Player player){

        if (service.getFiles().getData().contains("data." + player.getName())) {
            player.setDisplayName(service.getFiles().getData().getString("data."+player.getName()+".nick-name"));
        }
    }

    public boolean getOption(){
        return option;
    }

}