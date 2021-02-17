package com.sweepingloutus.crophoppers;


import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Level;
import java.util.logging.Logger;

public class convertCommand implements CommandExecutor {

    private main main;
    public convertCommand(main mainArg){main = mainArg;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Logger logger = main.getLogger();
        utils util = main.getUtils();

        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length == 0){
                return false;
            }else{
                String type = args[0];
                switch(type){
                    case "cactus":
                        ItemStack currentItem = player.getInventory().getItemInMainHand();
                        if(currentItem.getType() == Material.HOPPER){
                            int amount = currentItem.getAmount();
                            player.getInventory().remove(currentItem);
                            ItemStack cactusCropHopper = new ItemStack(Material.HOPPER,amount);
                            ItemStack LoreHopper = util.nameItemLore(cactusCropHopper,"&2&lCactus Crop Hopper","Collects all the cactus in a chunk!");
                            player.getInventory().setItem(player.getInventory().getHeldItemSlot(),LoreHopper);
                            player.sendMessage("Converted "+ amount + " of hoppers into Crop Hoppers!");
                        }
                }
            }
        }else {
            logger.log(Level.INFO,"You must be a player to run this command!");
        }
    return false;}
}
