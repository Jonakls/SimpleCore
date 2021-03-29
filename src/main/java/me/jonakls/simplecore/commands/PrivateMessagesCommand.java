package me.jonakls.simplecore.commands;

import me.jonakls.simplecore.Service;
import me.jonakls.simplecore.modules.MessageModule;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PrivateMessagesCommand implements CommandExecutor {

    final private Service service;

    public PrivateMessagesCommand (Service service){
        this.service = service;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        StringBuilder stringBuilder = new StringBuilder();
        float vol = (float) service.getFiles().getConfig().getDouble("sounds.message-sound.vol");
        float pitch = (float) service.getFiles().getConfig().getDouble("sounds.message-sound.pitch");


        if (!(sender instanceof Player)){
            sender.sendMessage(service.getFiles().getLang().getString("messages.error.no-console"
            .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix"))));
            return true;
        }

        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.messages"))){
            p.sendMessage(service.getFiles().getLang().getString("messages.error.no-permissions"
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix"))));
            return true;
        }
        //prefix arg0 arg1
        if (!(args.length > 1)){

            p.sendMessage(service.getFiles().getLang().getString("usages.private-usages"
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix"))));
            return true;

        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null){

            p.sendMessage(service.getFiles().getLang().getString("messages.error.no-player")
                    .replace("%player%", args[0])
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;

        }

        int i = 1;
        while (i < args.length) {
            stringBuilder.append(args[i]).append(' ');
            ++i;
        }


        MessageModule module = new MessageModule(service);

        module.setFormatSender(target, stringBuilder.toString());
        p.spigot().sendMessage(module.getFormatSender());
        p.playSound(p.getLocation(),Sound.valueOf(service.getFiles().getConfig().getString("sounds.message-sound.sound")),vol,pitch);


        module.setFormatTarget(p, stringBuilder.toString());
        target.spigot().sendMessage(module.getFormatTarget());
        target.playSound(target.getLocation(),Sound.valueOf(service.getFiles().getConfig().getString("sounds.message-sound.sound")),vol,pitch);


        return true;
    }
}
