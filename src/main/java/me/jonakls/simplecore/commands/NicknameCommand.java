package me.jonakls.simplecore.commands;

import me.jonakls.simplecore.files.FileManager;
import me.jonakls.simplecore.handlers.NicknameHandler;
import me.jonakls.simplecore.utils.ColorApply;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NicknameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(FileManager.getLang().getString("messages.error.no-console"));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.nick"))){
            p.sendMessage(FileManager.getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;

        }
        if (!(args.length > 0)){
            p.sendMessage(FileManager.getLang().getString("usages.nick-usages")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }
        if (args[0].equalsIgnoreCase("off")){

            NicknameHandler newNick = new NicknameHandler();
            newNick.unsetNickname(p);
            if(!(newNick.getOption())){
                p.sendMessage(FileManager.getLang().getString("nick-command.no-change"));
                return true;
            }
            p.sendMessage(FileManager.getLang().getString("nick-command.default"));
            return true;
        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null){
                NicknameHandler newNick = new NicknameHandler();
                newNick.setNickname(p, args[0]);
                if(!(newNick.getOption())){
                    p.sendMessage(FileManager.getLang().getString("nick-command.no-change"));
                    return true;
                }
                p.sendMessage(FileManager.getLang().getString("nick-command.change")
                        .replace("%displayName%", ColorApply.apply(args[0])));
                return  true;
        }
        if (!(args.length > 1)){

            p.sendMessage(FileManager.getLang().getString("usages.nick-other-usages")
                    .replace("%prefix%", FileManager.getLang().getString("messages.prefix")));
            return true;
        }
        NicknameHandler newNick = new NicknameHandler();
        newNick.setNickname(target, args[1]);
        if(!(newNick.getOption())){
            p.sendMessage(FileManager.getLang().getString("nick-command.no-change"));
            return true;
        }
        target.sendMessage(FileManager.getLang().getString("nick-command.target-change")
                .replace("%displayName%", ColorApply.apply(args[1]))
                .replace("%player%", p.getName()));
        p.sendMessage(FileManager.getLang().getString("nick-command.change-other")
                .replace("%displayName%", ColorApply.apply(args[1]))
                .replace("%target%", target.getName()));

        return true;
    }
}
