package me.jonakls.noxuscommands.commands;

import me.jonakls.noxuscommands.files.FileManager;
import me.jonakls.noxuscommands.utils.MessageReplacer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LagCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        long freeMemory = Runtime.getRuntime().freeMemory() / 1000000;
        long totalMemory = Runtime.getRuntime().totalMemory() / 1000000;
        long maxMemory = Runtime.getRuntime().maxMemory() / 1000000;
        long usedMemory = (totalMemory - freeMemory);

        if (!(sender instanceof Player)){
            sender.sendMessage(MessageReplacer.noConsole());
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.lag"))){
            p.sendMessage(MessageReplacer.noPermissions());
            return true;
        }

        if (!(args.length > 0)){

            for (String line : FileManager.getLang().getStringList("lag-command.message")){
                p.sendMessage(line
                        .replace("%freeRam%", "" + freeMemory)
                        .replace("%totalMemory%", "" + totalMemory)
                        .replace("%maxMemory%", "" + maxMemory)
                        .replace("%usedMemory%", "" + usedMemory)
                        .replace("%worldName%", p.getWorld().getName())
                        .replace("%worldType%", p.getWorld().getWorldType().toString())
                        .replace("%ticksAnimals%", ""
                                + Bukkit.getServer().getTicksPerAnimalSpawns())
                        .replace("%ticksMonsters%", ""
                                + Bukkit.getServer().getTicksPerMonsterSpawns())
                        .replace("%worldBiome%", p.getWorld().getBiome(
                                p.getLocation().getBlockX(), p.getLocation().getBlockZ()).toString())
                        .replace("%worldPlayers%", ""+p.getWorld().getPlayers().size()));
            }

            return true;
        }
        return true;
    }
}
