package me.jonakls.simplecore.commands;

import me.jonakls.simplecore.files.FileManager;
import me.jonakls.simplecore.handlers.MessageHandler;
import me.jonakls.simplecore.utils.ColorApply;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PrivateMessagesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        StringBuilder stringBuilder = new StringBuilder();
        float vol = (float) FileManager.getConfig().getDouble("sounds.message-sound.vol");
        float pitch = (float) FileManager.getConfig().getDouble("sounds.message-sound.pitch");


        if (!(sender instanceof Player)){
            sender.sendMessage(FileManager.getLang().getString("messages.error.no-console")
            .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }

        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.messages"))){
            p.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }
        //prefix arg0 arg1
        if (!(args.length > 1)){

            p.sendMessage(FileManager.getLang().getString("usages.private-usages")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;

        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null){

            p.sendMessage(FileManager.getLang().getString("messages.error.no-player")
                    .replace("%player%", args[0])
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;

        }

        int i = 1;
        while (i < args.length) {
            stringBuilder.append(args[i]).append(' ');
            ++i;
        }


        MessageHandler module = new MessageHandler();

        module.setFormatSender(target, ColorApply.apply(stringBuilder.toString()));
        p.spigot().sendMessage(module.getFormatSender());
        p.playSound(p.getLocation(),Sound.valueOf(FileManager.getConfig().getString("sounds.message-sound.sound")),vol,pitch);


        module.setFormatTarget(p, ColorApply.apply(stringBuilder.toString()));
        target.spigot().sendMessage(module.getFormatTarget());
        target.playSound(target.getLocation(),Sound.valueOf(FileManager.getConfig().getString("sounds.message-sound.sound")),vol,pitch);


        return true;
    }
}
