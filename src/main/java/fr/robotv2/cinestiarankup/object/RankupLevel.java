package fr.robotv2.cinestiarankup.object;

import fr.robotv2.cinestiaapi.ItemAPI;
import fr.robotv2.cinestiarankup.config.RankupDB;
import fr.robotv2.cinestiarankup.ui.GUI;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

import static fr.robotv2.cinestiarankup.Utility.color;
import static fr.robotv2.cinestiarankup.Utility.getRankupLevel;

public class RankupLevel {

    private String levelStr;
    private final String display;

    private final Double cost;
    private final Double expCost;
    private final HashMap<ItemStack, Integer> blockRequirements;

    private final ItemStack item;

    private final int level;
    private final RankupLevel nextRankUpLevel;

    public RankupLevel(int level) {
        this.levelStr = "level-" + level;
        this.level = setupLevel(level);
        this.nextRankUpLevel = setupNextLevel();
        this.display = color(RankupDB.getDB().getString("rankup." + levelStr + ".display"));

        this.cost = RankupDB.getDB().getDouble("rankup." + levelStr + ".cost");
        this.expCost = RankupDB.getDB().getDouble("rankup." + levelStr + ".exp-cost");
        this.blockRequirements = this.setupBlockRequirement();

        ItemAPI.itemBuilder builder;
        String material = RankupDB.getDB().getString("rankup." + levelStr + ".item.material");
        String name = RankupDB.getDB().getString("rankup." + levelStr + ".item.name");
        List<String> lore = RankupDB.getDB().getStringList("rankup." + levelStr + ".item.lore");

        if(material.startsWith("head-"))
            builder = ItemAPI.toBuilder(ItemAPI.createSkull(material.replace("head-", "")));
        else
            builder = new ItemAPI.itemBuilder().setType(Material.valueOf(material));
        builder.setName(name);
        builder.setLore(lore);
        this.item = builder.build();
    }

    private int setupLevel(int level) {
        if(RankupDB.getDB().get("rankup." + levelStr + ".display") == null) {
            this.levelStr = "level--1";
            return -1;
        }
        return level;
    }

    private RankupLevel setupNextLevel() {
        if(level == -1) return null;
        if(RankupDB.getDB().get("rankup." + levelStr + ".display") == null) return new RankupLevel(-1);
        return getRankupLevel(level + 1);
    }

    private HashMap<ItemStack, Integer> setupBlockRequirement() {
        List<String> input = RankupDB.getDB().getStringList("rankup." + levelStr + ".block-requirements");
        HashMap<ItemStack, Integer> result = new HashMap<>();

        for(String materialAndAmount : input) {
            String[] args = materialAndAmount.split(";");

            Material material = Material.valueOf(args[0].toUpperCase());
            int amount = Integer.parseInt(args[1]);
            result.put(new ItemStack(material), amount);
        }
        return result;
    }

    public int getLevel() {
        return level;
    }

    public RankupLevel getNextLevel() {
        return nextRankUpLevel;
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

    public HashMap<ItemStack, Integer> getBlockRequirements() {
        return blockRequirements;
    }
}
