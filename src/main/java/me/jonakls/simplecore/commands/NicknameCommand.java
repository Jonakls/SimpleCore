package me.jonakls.simplecore.commands;

import me.jonakls.simplecore.Service;
import me.jonakls.simplecore.handlers.NicknameHandler;
import me.jonakls.simplecore.utils.ColorApply;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NicknameCommand implements CommandExecutor {

    final private Service service;

    public NicknameCommand(Service service){
        this.service = service;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(service.getFiles().getLang().getString("messages.error.no-console"));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.nick"))){
            p.sendMessage(service.getFiles().getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;

        }
        if (!(args.length > 0)){
            p.sendMessage(service.getFiles().getLang().getString("usages.nick-usages")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;
        }

        if (args[0].equalsIgnoreCase("off")){

            p.setDisplayName(p.getName());
            p.sendMessage(service.getFiles().getLang().getString("nick-command.default"));
            return true;
        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null){
                NicknameHandler newNick = new NicknameHandler(service);
                newNick.setNickname(p, args[0]);
                if(!(newNick.getOption())){
                    p.sendMessage(service.getFiles().getLang().getString("nick-command.no-change"));
                    return true;
                }
                p.sendMessage(service.getFiles().getLang().getString("nick-command.change")
                        .replace("%displayName%", ColorApply.apply(args[0])));
                return  true;
        }
        if (!(args.length > 1)){

            p.sendMessage(service.getFiles().getLang().getString("usages.nick-other-usages")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;
        }
        NicknameHandler newNick = new NicknameHandler(service);
        newNick.setNickname(target, args[1]);
        if(!(newNick.getOption())){
            p.sendMessage(service.getFiles().getLang().getString("nick-command.no-change"));
            return true;
        }
        target.sendMessage(service.getFiles().getLang().getString("nick-command.target-change")
                .replace("%displayName%", ColorApply.apply(args[1]))
                .replace("%player%", p.getName()));
        p.sendMessage(service.getFiles().getLang().getString("nick-command.change-other")
                .replace("%displayName%", ColorApply.apply(args[1]))
                .replace("%target%", target.getName()));

        return true;
    }
}