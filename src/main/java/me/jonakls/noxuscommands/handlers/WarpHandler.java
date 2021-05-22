package me.jonakls.noxuscommands.handlers;

import me.jonakls.noxuscommands.files.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;

public class WarpHandler {

    private boolean operation;
    private Location warpLocation;
    private final ArrayList<String> listWarps = new ArrayList<>();

    public void setWarp(String warp, Location loc){


        if(!(FileManager.getWarps().contains("warps." + warp))){

            FileManager.getWarps().set("warps."+ warp +".world", loc.getWorld().getName());
            FileManager.getWarps().set("warps."+ warp +".x", loc.getX());
            FileManager.getWarps().set("warps."+ warp +".y", loc.getY());
            FileManager.getWarps().set("warps."+ warp +".z", loc.getZ());
            FileManager.getWarps().set("warps."+ warp +".yaw", loc.getYaw());
            FileManager.getWarps().set("warps."+ warp +".pitch", loc.getPitch());
            FileManager.getWarps().save();
            operation = true;
            return;
        }
        operation  = false;

    }

    public void getWarp(String warp){

        if(!(FileManager.getWarps().contains("warps." + warp))){
            operation = false;
            return;
        }


        World world = Bukkit.getWorld(FileManager.getWarps().getString("warps."+ warp +".world"));

        double x = FileManager.getWarps().getDouble("warps."+ warp +".x");
        double y = FileManager.getWarps().getDouble("warps."+ warp +".y");
        double z = FileManager.getWarps().getDouble("warps."+ warp +".z");
        float yaw = (float) FileManager.getWarps().getDouble("warps."+ warp +".yaw");
        float pitch = (float) FileManager.getWarps().getDouble("warps."+ warp +".pitch");

        warpLocation = new Location(world, x, y , z, yaw , pitch);

        operation = true;
    }

    public void deleteWarp(String warp){

        if(!(FileManager.getWarps().contains("warps." + warp))){
            operation = false;
            return;
        }

        FileManager.getWarps().set("warps."+warp, null);
        FileManager.getWarps().save();

        operation = true;

    }

    public void listWarps(){


        ConfigurationSection configSection = FileManager.getWarps().getConfigurationSection("warps");

        for (String key : configSection.getKeys(false)) {
            listWarps.add(key);
        }


    }

    public ArrayList<String> getListWarps(){
        return listWarps;
    }


    public boolean getOperation(){
        return operation;
    }

    public Location getWarpLocation(){
        return warpLocation;
    }


}
