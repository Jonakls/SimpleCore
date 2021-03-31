package me.jonakls.simplecore.handlers;

import me.jonakls.simplecore.Service;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class WarpHandler {

    private final Service service;
    private boolean operation;
    private Location warpLocation;

    public WarpHandler(Service service){
        this.service = service;
    }

    public void setWarp(String warp, Location loc){


        if(!(service.getFiles().getWarps().contains("warps." + warp))){

            service.getFiles().getWarps().set("warps."+ warp +".world", loc.getWorld().getName());
            service.getFiles().getWarps().set("warps."+ warp +".x", loc.getX());
            service.getFiles().getWarps().set("warps."+ warp +".y", loc.getY());
            service.getFiles().getWarps().set("warps."+ warp +".z", loc.getZ());
            service.getFiles().getWarps().set("warps."+ warp +".yaw", loc.getYaw());
            service.getFiles().getWarps().set("warps."+ warp +".pitch", loc.getPitch());
            service.getFiles().getWarps().save();
            operation = true;
            return;
        }
        operation  = false;

    }

    public void getWarp(String warp){

        if(!(service.getFiles().getWarps().contains("warps." + warp))){
            operation = false;
            return;
        }


        World world = Bukkit.getWorld(service.getFiles().getWarps().getString("warps."+ warp +".world"));

        double x = service.getFiles().getWarps().getDouble("warps."+ warp +".x");
        double y = service.getFiles().getWarps().getDouble("warps."+ warp +".y");
        double z = service.getFiles().getWarps().getDouble("warps."+ warp +".z");
        float yaw = (float) service.getFiles().getWarps().getDouble("warps."+ warp +".yaw");
        float pitch = (float) service.getFiles().getWarps().getDouble("warps."+ warp +".pitch");

        warpLocation = new Location(world, x, y , z, yaw , pitch);

        operation = true;
    }

    public void deleteWarp(String warp){

        if(!(service.getFiles().getWarps().contains("warps." + warp))){
            operation = false;
            return;
        }

        service.getFiles().getWarps().set("warps."+warp, null);
        service.getFiles().getWarps().save();

        operation = true;

    }


    public boolean getOperation(){
        return operation;
    }

    public Location getWarpLocation(){
        return warpLocation;
    }


}
