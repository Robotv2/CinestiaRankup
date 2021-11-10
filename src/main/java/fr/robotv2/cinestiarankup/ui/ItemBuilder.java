package fr.robotv2.cinestiarankup.ui;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static fr.robotv2.cinestiarankup.Utility.colorize;

public class ItemBuilder {

    private static ItemStack empty;
    private static ItemStack close;
    private static ItemStack air;

    public static ItemStack getEmptySlots() {
        if(empty == null) {
            ItemStack deco = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            ItemMeta decometa = deco.getItemMeta();
            decometa.setDisplayName("§8");
            deco.setItemMeta(decometa);
            empty = deco;
        }
        return empty;
    }

    public static ItemStack getClose() {
        if(close == null) {
            ItemStack deco = new ItemStack(Material.BARRIER);
            ItemMeta decometa = deco.getItemMeta();
            decometa.setDisplayName(colorize("&cCliquez ici pour revenir en arrière."));
            deco.setItemMeta(decometa);
            close = deco;
        }
        return close;
    }

    public static ItemStack getAir() {
        if(air == null) {
            air = new ItemStack(Material.AIR);
        }
        return air;
    }

    public static void setupEmptySlots(Inventory inv, int row) {
        for(int i=0; i<=row-1; i++) {
            inv.setItem(i, getEmptySlots());
        }
    }
}
