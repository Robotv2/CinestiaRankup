package fr.robotv2.cinestiarankup.object;

import fr.robotv2.cinestiarankup.config.rankupDB;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static fr.robotv2.cinestiarankup.utility.color;

public class level {

    private String levelStr;

    private final String display;
    private final Double cost;
    private final Double expCost;
    private final ItemStack item;

    private final int level;
    private final level nextLevel;

    public level(int level) {
        this.levelStr = "level-" + level;
        this.level = setupLevel(level);
        this.nextLevel = setupNextLevel();
        this.display = color(rankupDB.getDB().getString("rankup." + levelStr + ".display"));
        this.cost = rankupDB.getDB().getDouble("rankup." + levelStr + ".cost");
        this.expCost = rankupDB.getDB().getDouble("rankup." + levelStr + ".exp-cost");

        ItemStack item = new ItemStack(Material.valueOf(rankupDB.getDB().getString("rankup." + levelStr + ".item.material")));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(color(rankupDB.getDB().getString("rankup." + levelStr + ".item.name")));
        meta.setLore(translateLore(rankupDB.getDB().getStringList("rankup." + levelStr + ".item.lore")));
        item.setItemMeta(meta);
        this.item = item;
    }

    public int setupLevel(int level) {
        if(rankupDB.getDB().get("rankup." + levelStr + ".display") == null) {
            this.levelStr = "level--1";
            return -1;
        }
        return level;
    }

    private level setupNextLevel() {
        if(level == -1) return null;
        if(rankupDB.getDB().get("rankup." + levelStr + ".display") == null) return new level(-1);
        return new level(level + 1);
    }

    public int getLevel() {
        return level;
    }

    public level getNextLevel() {
        return nextLevel;
    }

    private List<String> translateLore(List<String> list) {
        List<String> result = new ArrayList<>();
        for (String s : list) {
            result.add(color(s));
        }
        return result;
    }

    public ItemStack getItem() {
        return item;
    }

    public Double getExpCost() {
        return expCost;
    }

    public Double getCost() {
        return cost;
    }

    public String getDisplay() {
        return display;
    }
}
