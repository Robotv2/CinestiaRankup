package fr.robotv2.cinestiarankup.ui.stock;

import fr.robotv2.cinestiarankup.ui.GUI;
import fr.robotv2.cinestiarankup.ui.UImanager;
import fr.robotv2.cinestiarankup.ui.itemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import static fr.robotv2.cinestiarankup.utility.*;

public class menuGUI implements GUI {

    @Override
    public String getName(Player player) {
        int level = getLevel(player);
        return color("&7&l➤ &fRankup");
    }

    @Override
    public int getSize() {
        return 27;
    }

    @Override
    public void contents(Player player, Inventory inv) {
        int level = getLevel(player);
        itemBuilder.setupEmptySlots(inv, getSize());
        inv.setItem(13, getLevel(level).getNextLevel().getItem());
    }

    @Override
    public void onClick(Player player, Inventory inv, ItemStack current, int slot) {
        if(slot == 13 && canRankUp(player)) {
            player.closeInventory();
            rankUp(player);
        }
    }

    @Override
    public InventoryHolder getHolder() {
        return new UImanager.holdersMENU();
    }
}
