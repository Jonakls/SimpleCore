package me.jonakls.noxuscommands.commands;

import me.jonakls.noxuscommands.files.FileManager;
import me.jonakls.noxuscommands.handlers.NicknameHandler;
import me.jonakls.noxuscommands.utils.ColorApply;
import me.jonakls.noxuscommands.utils.MessageReplacer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NicknameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(MessageReplacer.noConsole());
            return true;
        }
        Player player = (Player) sender;
        if (!(player.hasPermission("simplecore.command.nick"))){
            player.sendMessage(MessageReplacer.noPermissions());
            return true;

        }
        if (!(args.length > 0)){
            player.sendMessage(MessageReplacer.prefix(FileManager.getLang().getString("usages.nick-usages")));
            return true;
        }
        if (args[0].equalsIgnoreCase("off")){

            NicknameHandler newNick = new NicknameHandler();
            newNick.unsetNickname(player);
            if(!(newNick.getOption())){
                player.sendMessage(FileManager.getLang().getString("nick-command.no-change"));
                return true;
            }
            player.sendMessage(FileManager.getLang().getString("nick-command.default"));
            return true;
        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null){
                NicknameHandler newNick = new NicknameHandler();
                newNick.setNickname(player, args[0]);
                if(!(newNick.getOption())){
                    player.sendMessage(FileManager.getLang().getString("nick-command.no-change"));
                    return true;
                }
                player.sendMessage(FileManager.getLang().getString("nick-command.change")
                        .replace("%displayName%", ColorApply.apply(args[0])));
                return  true;
        }
        if (!(args.length > 1)){

            player.sendMessage(MessageReplacer.prefix(FileManager.getLang().getString("usages.nick-other-usages")));
            return true;
        }
        NicknameHandler newNick = new NicknameHandler();
        newNick.setNickname(target, args[1]);
        if(!(newNick.getOption())){
            player.sendMessage(FileManager.getLang().getString("nick-command.no-change"));
            return true;
        }
        target.sendMessage(FileManager.getLang().getString("nick-command.target-change")
                .replace("%displayName%", ColorApply.apply(args[1]))
                .replace("%player%", player.getName()));
        player.sendMessage(FileManager.getLang().getString("nick-command.change-other")
                .replace("%displayName%", ColorApply.apply(args[1]))
                .replace("%target%", target.getName()));

        return true;
    }
}
