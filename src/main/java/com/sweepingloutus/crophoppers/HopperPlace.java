package com.sweepingloutus.crophoppers;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HopperPlace implements Listener {

    private main main;
    public HopperPlace(main mainArg){main = mainArg;}

    @EventHandler
    public void onHopperPlace(BlockPlaceEvent e){

        utils utils = main.getUtils();

        Material placedBlock = e.getBlockPlaced().getType();
        if(placedBlock == Material.HOPPER){
            ItemStack itemStack = e.getItemInHand();
            e.getPlayer().sendMessage("placed block called");
            if(utils.checkItemLoreForHopper(itemStack)) {
                int coord_x = e.getBlockPlaced().getX();
                int coord_y = e.getBlockPlaced().getX();
                int coord_z = e.getBlockPlaced().getX();
                int chunk_x = e.getBlockPlaced().getChunk().getX();
                int chunk_z = e.getBlockPlaced().getChunk().getZ();
                String type = utils.hopperType(itemStack);
                e.getPlayer().sendMessage("ints done");
                try{
                    MySQL mySQL = main.getMySQL();
                    PreparedStatement ps = mySQL.openConnection().prepareStatement("INSERT INTO hoppers (coord_x,coord_y,coord_z,chunk_x,chunk_z,type) VALUES (?,?,?,?,?,?)");
                    ps.setInt(1,coord_x);
                    ps.setInt(2,coord_y);
                    ps.setInt(3,coord_z);
                    ps.setInt(4,chunk_x);
                    ps.setInt(5,chunk_z);
                    ps.setString(6,type);
                    ps.execute();
                    e.getPlayer().sendMessage("excute called");
                }catch (SQLException exception){
                    exception.printStackTrace();
                }

            }else{
                main.getLogger().log(Level.WARNING,"return false");
            }
        }
    }
}

