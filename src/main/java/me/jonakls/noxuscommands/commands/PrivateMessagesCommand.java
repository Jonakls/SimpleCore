package me.jonakls.noxuscommands.commands;

import me.jonakls.noxuscommands.files.FileManager;
import me.jonakls.noxuscommands.handlers.MessageHandler;
import me.jonakls.noxuscommands.utils.ColorApply;
import me.jonakls.noxuscommands.utils.MessageReplacer;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PrivateMessagesCommand implements CommandExecutor {

    private final MessageHandler module = new MessageHandler();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(MessageReplacer.noConsole());
            return true;
        }

        Player player = (Player) sender;

        if (!(player.hasPermission("simplecore.command.messages"))){

            player.sendMessage(MessageReplacer.noPermissions());
            return true;
        }

        if (!(args.length > 1)){

            player.sendMessage(MessageReplacer.prefix(FileManager.getLang().getString("usages.private-usages")));
            return true;

        }

        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null){

            player.sendMessage(MessageReplacer.noPlayer(args[0]));
            return true;

        }

        StringBuilder stringBuilder = new StringBuilder();

        int i = 1;
        while (i < args.length) {
            stringBuilder.append(args[i]).append(' ');
            ++i;
        }

        player.spigot().sendMessage(module.setFormatSender(target, ColorApply.apply(stringBuilder.toString())));

        player.playSound(player.getLocation(),Sound.valueOf(
                FileManager.getConfig().getString("sounds.message-sound.sound")),
                (float) FileManager.getConfig().getDouble("sounds.message-sound.vol"),
                (float) FileManager.getConfig().getDouble("sounds.message-sound.pitch"));
        
        target.spigot().sendMessage(module.setFormatTarget(player, ColorApply.apply(stringBuilder.toString())));

        target.playSound(player.getLocation(),Sound.valueOf(
                FileManager.getConfig().getString("sounds.message-sound.sound")),
                (float) FileManager.getConfig().getDouble("sounds.message-sound.vol"),
                (float) FileManager.getConfig().getDouble("sounds.message-sound.pitch"));


        return true;
    }
}
