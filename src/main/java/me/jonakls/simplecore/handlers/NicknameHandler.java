package me.jonakls.simplecore.handlers;

import me.jonakls.simplecore.files.FileManager;
import me.jonakls.simplecore.utils.ColorApply;
import org.bukkit.entity.Player;

public class NicknameHandler {

    private boolean option;

    public void setNickname(Player player, String nickname) {

        if (!(FileManager.getData().contains("data." + player.getName() + ".nick-name"))) {

            player.setDisplayName(ColorApply.apply(nickname));
            FileManager.getData().set("data."+player.getName()+".nick-name", nickname);
            FileManager.getData().save();

            option = true;
            return;
        }
        for (String path: FileManager.getData().getConfigurationSection("data").getKeys(false)){

            if (!(FileManager.getData().getString(
                    ColorApply.withdraw("data."+ path +".nick-name"))
                    .equals(ColorApply.withdraw(nickname)))){

                player.setDisplayName(ColorApply.apply(nickname));
                FileManager.getData().set("data."+player.getName()+".nick-name", nickname);
                FileManager.getData().save();

                option = true;
                return;
            }
            option = false;
        }
        option = false;
    }

    public void unsetNickname(Player player){

        if (!(FileManager.getData().contains("data." + player.getName()))){
            option = false;
            return;
        }
        player.setDisplayName(null);
        FileManager.getData().set("data."+player.getName(), null);
        FileManager.getData().save();
        option = true;

    }

    public boolean getOption(){
        return option;
    }

}
