package fr.robotv2.cinestiarankup.ui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public interface GUI {
    public String getName(Player player);
    public int getSize();
    public void contents(Player player, Inventory inv);
    public void onClick(Player player, Inventory inv, ItemStack current, int slot);
    public InventoryHolder getHolder();
}
