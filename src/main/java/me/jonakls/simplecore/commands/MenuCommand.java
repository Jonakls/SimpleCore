package me.jonakls.simplecore.commands;

import me.jonakls.simplecore.SimpleCore;
import me.jonakls.simplecore.files.MessagesFile;
import me.jonakls.simplecore.menus.generators.MenuGenerator;
import me.jonakls.simplecore.objects.ParseColors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class MenuCommand implements CommandExecutor {

    private final SimpleCore simpleCore;
    private final ParseColors colors = new ParseColors();

    public MenuCommand(SimpleCore simpleCore){
        this.simpleCore = simpleCore;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration messagesFile = new MessagesFile(simpleCore).getMessages();

        if(!(sender instanceof Player)){
            sender.sendMessage(colors.setColor(messagesFile.getString("messages.error.no-console")));
            return true;
        }
        Player p = (Player) sender;

        MenuGenerator menu = new MenuGenerator(simpleCore);
        menu.generalMenu(p);

        return true;
    }
}
