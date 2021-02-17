package com.sweepingloutus.crophoppers;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


public class utils {

    private final main main;
    public utils(main mainArg){main = mainArg;}

    public ItemStack nameItemLore(ItemStack item, String name, String lore) {
        ItemMeta meta = item.getItemMeta();
        List<String> lores = new ArrayList<String>();
        meta.setDisplayName(name);
        lores.add(lore);
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

    public boolean checkItemLoreForHopper(ItemStack item){
        main.getLogger().log(Level.WARNING,"getmeta");
        ItemMeta itemMeta = item.getItemMeta();
        if(itemMeta.hasLore()){
            main.getLogger().log(Level.WARNING,"haslore");
            List<String> lore = itemMeta.getLore();
            String check = lore.get(0);
            main.getLogger().log(Level.WARNING,check);
            return check.contains("Collects");
        }
        main.getLogger().log(Level.WARNING,"ddddddd");
        return false;
    }

    public String hopperType(ItemStack item){
        ItemMeta itemMeta = item.getItemMeta();
        if(itemMeta.hasLore()){
            List<String> lore = itemMeta.getLore();
            String[] split = lore.get(0).split(" ");
            String type = split[3];
            main.getLogger().log(Level.WARNING,type);
            switch (type){
                case "cactus":
                    return "cactus";
            }
        }
    return "cancel";
    }
}
