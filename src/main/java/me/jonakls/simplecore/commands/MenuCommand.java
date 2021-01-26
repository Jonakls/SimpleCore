package me.jonakls.simplecore.commands;

import me.jonakls.simplecore.Manager;
import me.jonakls.simplecore.menus.generators.MenuGenerator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MenuCommand implements CommandExecutor {

    private final Manager manager;

    public MenuCommand(Manager manager){
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(manager.getFiles().getLang().getString("messages.error.no-console"));
            return true;
        }
        Player p = (Player) sender;

        MenuGenerator menu = new MenuGenerator(manager);
        menu.generalMenu(p);

        return true;
    }
}
