package fr.robotv2.cinestiarankup.ui.stock;

import fr.robotv2.cinestiarankup.Main;
import fr.robotv2.cinestiarankup.Utility;
import fr.robotv2.cinestiarankup.object.RankupLevel;
import fr.robotv2.cinestiarankup.ui.GUI;
import fr.robotv2.cinestiarankup.ui.UImanager;
import fr.robotv2.cinestiarankup.ui.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import static fr.robotv2.cinestiarankup.Utility.*;

public class MenuGUI implements GUI {

    @Override
    public String getName(Player player) {
        return color("&7&l➤ &fRankup");
    }

    @Override
    public int getSize() {
        return 27;
    }

    @Override
    public void contents(Player player, Inventory inv) {
        int level = getLevel(player);
        ItemBuilder.setupEmptySlots(inv, getSize());
        inv.setItem(13, getRankupLevel(level).getNextLevel().getItem());
    }

    @Override
    public void onClick(Player player, Inventory inv, ItemStack current, int slot, ClickType type) {
        if(slot == 13) {
            switch (type) {
                case LEFT:
                    if(canRankUp(player)) {
                        player.closeInventory();
                        rankUp(player);
                    }
                    break;

                case RIGHT:
                    int level = getLevel(player);
                    RankupLevel currentLevel = getRankupLevel(level);
                    RankupLevel nextLevel = currentLevel.getNextLevel();

                    if(nextLevel.getLevel() == -1) {
                        return;
                    }

                    UImanager.selectedMenus.put(player, nextLevel.getLevel());
                    Main.getManager().open(player, BlockGUI.class);
                    break;
            }

        }
    }

    @Override
    public InventoryHolder getHolder() {
        return new UImanager.holdersMENU();
    }
}
