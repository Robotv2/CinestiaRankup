package fr.robotv2.cinestiarankup.ui.stock;

import fr.robotv2.cinestiaapi.ItemAPI;
import fr.robotv2.cinestiarankup.Main;
import fr.robotv2.cinestiarankup.Utility;
import fr.robotv2.cinestiarankup.object.RankupLevel;
import fr.robotv2.cinestiarankup.ui.GUI;
import fr.robotv2.cinestiarankup.ui.UImanager;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;
import java.util.Map;

import static fr.robotv2.cinestiarankup.Utility.color;

public class BlockGUI implements GUI {

    public ItemStack BLUE_GLASS;
    public ItemStack WHITE_GLASS;
    public ItemStack RETURN_BUTTON;
    public DecimalFormat format = new DecimalFormat("###,###.###");

    @Override
    public String getName(Player player) {
        return color("&7&l➤ &fLes prérequis");
    }

    @Override
    public int getSize() {
        return 6 * 9;
    }

    @Override
    public void contents(Player player, Inventory inv) {
        inv.setItem(0, getBlueGlass());
        inv.setItem(1, getBlueGlass());
        inv.setItem(2, getWhiteGlass());
        inv.setItem(3, getWhiteGlass());
        inv.setItem(4, getWhiteGlass());
        inv.setItem(5, getWhiteGlass());
        inv.setItem(6, getWhiteGlass());
        inv.setItem(7, getBlueGlass());
        inv.setItem(8, getBlueGlass());
        inv.setItem(9, getBlueGlass());
        inv.setItem(10, getMoneyRequirement(player));
        inv.setItem(17, getBlueGlass());
        inv.setItem(18, getWhiteGlass());
        inv.setItem(26, getWhiteGlass());
        inv.setItem(27, getWhiteGlass());
        inv.setItem(35, getWhiteGlass());
        inv.setItem(36, getBlueGlass());
        inv.setItem(44, getBlueGlass());
        inv.setItem(45, getReturnButton());
        inv.setItem(46, getBlueGlass());
        inv.setItem(47, getWhiteGlass());
        inv.setItem(48, getWhiteGlass());
        inv.setItem(49, getWhiteGlass());
        inv.setItem(50, getWhiteGlass());
        inv.setItem(51, getWhiteGlass());
        inv.setItem(52, getBlueGlass());
        inv.setItem(53, getBlueGlass());

        int current = UImanager.selectedMenus.get(player);
        RankupLevel rankupLevel = Utility.getRankupLevel(current);

        int count = 11;
        for(Map.Entry<ItemStack, Integer> itemEntry : rankupLevel.getBlockRequirements().entrySet()) {
            ItemStack currentItem = itemEntry.getKey();
            currentItem.setAmount(itemEntry.getValue());
            inv.setItem(count, currentItem);

            count++;
            switch (count) {
                case 17:
                case 26:
                case 35:
                    count += 2;
                case 44:
                    break;
            }
        }
    }

    @Override
    public void onClick(Player player, Inventory inv, ItemStack current, int slot, ClickType type) {
        if(slot == 45) {
            Main.getManager().open(player, MenuGUI.class);
        }
    }

    @Override
    public InventoryHolder getHolder() {
        return null;
    }

    public ItemStack getBlueGlass() {
        if(BLUE_GLASS == null) {
            BLUE_GLASS = new ItemAPI.itemBuilder()
                    .setType(Material.LIGHT_BLUE_STAINED_GLASS_PANE)
                    .setName("&8")
                    .addFlags(ItemFlag.HIDE_ATTRIBUTES)
                    .build();
        }
        return BLUE_GLASS;
    }

    public ItemStack getWhiteGlass() {
        if(WHITE_GLASS == null) {
            WHITE_GLASS = new ItemAPI.itemBuilder()
                    .setType(Material.WHITE_STAINED_GLASS_PANE)
                    .setName("&8")
                    .addFlags(ItemFlag.HIDE_ATTRIBUTES)
                    .build();
        }
        return WHITE_GLASS;
    }

    public ItemStack getReturnButton() {
        if(RETURN_BUTTON == null) {
            RETURN_BUTTON = new ItemAPI.itemBuilder()
                    .setType(Material.BARRIER)
                    .setName("&7< &cRetour")
                    .addFlags(ItemFlag.HIDE_ATTRIBUTES)
                    .build();
        }
        return RETURN_BUTTON;
    }

    public ItemStack getMoneyRequirement(Player player) {
        RankupLevel current = Utility.getRankupLevel(Utility.getLevel(player));
        RankupLevel next = current.getNextLevel();

        double money = next.getCost();

        return new ItemAPI.itemBuilder()
                .setName("&aArgent requis: " + format.format(money) + "$")
                .setType(Material.EMERALD)
                .addEnchant(Enchantment.ARROW_FIRE, 1, true)
                .addFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS)
                .build();
    }
}
