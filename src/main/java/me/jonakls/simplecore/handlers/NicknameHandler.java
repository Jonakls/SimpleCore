package me.jonakls.simplecore.handlers;

import me.jonakls.simplecore.Service;
import me.jonakls.simplecore.utils.ColorApply;
import org.bukkit.entity.Player;

public class NicknameHandler {

    final private Service service;
    private boolean option;

    public NicknameHandler(Service service) {
        this.service = service;
    }

    public void setNickname(Player player, String nickname) {

        if (!(service.getFiles().getData().contains("data." + player.getName() + ".nick-name"))) {

            player.setDisplayName(ColorApply.apply(nickname));
            service.getFiles().getData().set("data."+player.getName()+".nick-name", nickname);
            service.getFiles().getData().save();

            option = true;
            return;
        }
        for (String path: service.getFiles().getData().getConfigurationSection("data").getKeys(false)){

            if (!(service.getFiles().getData().getString(
                    ColorApply.withdraw("data."+ path +".nick-name"))
                    .equals(ColorApply.withdraw(nickname)))){

                player.setDisplayName(ColorApply.apply(nickname));
                service.getFiles().getData().set("data."+player.getName()+".nick-name", nickname);
                service.getFiles().getData().save();

                option = true;
                return;
            }
            option = false;
        }
        option = false;
    }

    public void unsetNickname(Player player){

        if (!(service.getFiles().getData().contains("data." + player.getName()))){
            option = false;
            return;
        }
        player.setDisplayName(null);
        service.getFiles().getData().set("data."+player.getName(), null);
        service.getFiles().getData().save();
        option = true;

    }

    public boolean getOption(){
        return option;
    }

}
