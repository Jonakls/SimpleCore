package me.jonakls.simplecore.commands;

import me.jonakls.simplecore.Service;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LagCommand implements CommandExecutor {

    final private Service service;

    public LagCommand(Service service){
        this.service = service;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        long freeMemory = Runtime.getRuntime().freeMemory() / 1000000;
        long totalMemory = Runtime.getRuntime().totalMemory() / 1000000;
        long maxMemory = Runtime.getRuntime().maxMemory() / 1000000;
        long usedMemory = (totalMemory - freeMemory);

        if (!(sender instanceof Player)){
            sender.sendMessage(service.getFiles().getLang().getString("messages.error.no-console"));
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("simplecore.command.lag"))){
            p.sendMessage(service.getFiles().getLang().getString("messages.error.no-permissions")
                    .replace("%prefix%", service.getFiles().getLang().getString("messages.prefix")));
            return true;
        }

        if (!(args.length > 0)){

            for (String line : service.getFiles().getLang().getStringList("lag-command.message")){
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
