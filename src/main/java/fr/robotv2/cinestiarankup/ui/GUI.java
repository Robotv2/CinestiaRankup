package fr.robotv2.cinestiarankup.ui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public interface GUI {
    String getName(Player player);
    int getSize();
    void contents(Player player, Inventory inv);
    void onClick(Player player, Inventory inv, ItemStack current, int slot, ClickType type);
    InventoryHolder getHolder();
}
