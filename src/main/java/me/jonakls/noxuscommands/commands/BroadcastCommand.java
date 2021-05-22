package me.jonakls.noxuscommands.commands;

import me.jonakls.noxuscommands.files.FileManager;
import me.jonakls.noxuscommands.utils.ColorApply;
import me.jonakls.noxuscommands.utils.MessageReplacer;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(MessageReplacer.noConsole());
            return true;
        }

        Player player = (Player) sender;

        if(!(player.hasPermission("simplecore.command.broadcast"))){
            player.sendMessage(MessageReplacer.noPermissions());
            return true;
        }
        if (!(args.length > 0)){
            player.sendMessage(MessageReplacer.prefix(FileManager.getLang().getString("usages.broadcast-message")));
            return true;
        }

        StringBuilder stringBuilder = new StringBuilder();

        int i = 0;
        while (i < args.length) {
            stringBuilder.append(' ').append(args[i]);
            ++i;
        }
        Bukkit.broadcastMessage(ColorApply.apply(FileManager.getLang().getString("broadcast.prefix")+ stringBuilder));

        Bukkit.getOnlinePlayers().forEach(online -> online.playSound(
                online.getLocation(),
                Sound.valueOf(FileManager.getConfig().getString("sounds.broadcast-sound.sound")),
                (float) FileManager.getConfig().getDouble("sounds.broadcast-sound.vol"),
                (float) FileManager.getConfig().getDouble("sounds.broadcast-sound.pitch")));

        return true;
    }
}
