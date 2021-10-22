package fr.robotv2.cinestiarankup;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.robotv2.cinestiarankup.config.RankupDB;
import fr.robotv2.cinestiarankup.object.RankupLevel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static fr.robotv2.cinestiarankup.Main.ECO;

public class Utility {

    public static HashMap<Integer, RankupLevel> rankupLevels = new HashMap<>();

    public static void clear() {
        rankupLevels.clear();
    }

    public static HashMap<UUID, Integer> level = new HashMap<>();
    public static HashMap<UUID, Double> exp = new HashMap<>();

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String decolor(String message) {
        return ChatColor.stripColor(message);
    }

    public static RankupLevel getRankupLevel(int level) {
        RankupLevel rankupLevel = rankupLevels.get(level);
        if(rankupLevel == null)
            rankupLevel = new RankupLevel(level);
        return rankupLevel;
    }

    public static int getLevel(Player player) {
        return getLevel(player.getUniqueId());
    }

    public static int getLevel(UUID playerUUID) {
        return level.get(playerUUID);
    }

    public static void setLevel(Player player, int level) {
        setLevel(player.getUniqueId(), level);
    }

    public static void setLevel(UUID playerUUID, int level) {
        Utility.level.put(playerUUID, level);
        sendLevel(Bukkit.getPlayer(playerUUID));
    }

    public static Double getExp(Player player) {
        return getExp(player.getUniqueId());
    }

    public static Double getExp(UUID playerUUID) {
        return exp.get(playerUUID);
    }

    public static void setExp(Player player, Double exp) {
        setExp(player.getUniqueId(), exp);
    }

    public static void setExp(UUID playerUUID, Double exp) {
        Utility.exp.put(playerUUID, exp);
        sendExp(Bukkit.getPlayer(playerUUID));
    }

    public static boolean canRankUp(Player player) {
        int level = getLevel(player);
        double exp = getExp(player);
        RankupLevel current = getRankupLevel(level);

        RankupLevel next = current.getNextLevel();
        HashMap<ItemStack, Integer> blocks = next.getBlockRequirements();

        if(next.getLevel() == -1) {
            player.sendMessage(color("&cVous avez atteint le niveau maximum !"));
            return false;
        }

        for(Map.Entry<ItemStack, Integer> item : blocks.entrySet()) {
            if(!player.getInventory().containsAtLeast(item.getKey(), item.getValue())) {
                player.sendMessage(color("&cObjet manquant: " + item.getKey().getType() + "x" + item.getValue()));
                return false;
            }
        }

        for(Map.Entry<ItemStack, Integer> item : blocks.entrySet()) {
            player.getInventory().removeItemAnySlot();
        }

        boolean asMoney = ECO.has(player, next.getCost());
        boolean asExp = exp >=  next.getExpCost();

        return(asMoney && asExp);
    }

    public static void rankUp(Player player) {
        int level = getLevel(player);
        RankupLevel current = getRankupLevel(level);
        RankupLevel next = current.getNextLevel();

        if(next.getLevel() == -1) {
            return;
        }

        double cost = next.getCost();
        ECO.withdrawPlayer(player, cost);

        setLevel(player, next.getLevel());

        player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 0.7F, 0.7F);
        player.sendTitle(color("&6Félicitations !"), color("&7Vous venez de passer rang &f" + next.getDisplay()), 10, 50, 10);

        executeCommand(player);
        sendToBungee(player);
    }

    public static void executeCommand(Player player) {
        int level = getLevel(player);
        RankupLevel current = getRankupLevel(level);

        String levelStr = "level-" + current.getLevel();
        CommandSender sender = Bukkit.getConsoleSender();

        for(String cmd : RankupDB.getDB().getStringList("rankup." + levelStr + ".commands")) {
            if(cmd.startsWith("[CONSOLE]")) {
                cmd = cmd
                        .replace("[CONSOLE] ", "")
                        .replace("%player%", player.getName());
                Bukkit.dispatchCommand(sender, cmd);
                continue;
            }
            if(cmd.startsWith("[PLAYER]")) {
                cmd = cmd
                        .replace("[PLAYER] ", "")
                        .replace("%player%", player.getName());
                Bukkit.dispatchCommand(player, cmd);
                continue;
            }
            if(cmd.startsWith("[MESSAGE]")) {
                cmd = cmd
                        .replace("[MESSAGE] ", "")
                        .replace("%player%", player.getName());
                player.sendMessage(color(cmd));
            }
        }
    }

    public static void sendToBungee(Player player) {
        sendLevel(player);
        sendExp(player);
    }

    private static void sendLevel(Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();

        out.writeUTF("set-level");
        out.writeInt(getLevel(player));

        player.sendPluginMessage(Main.INSTANCE, Main.INSTANCE.channel, out.toByteArray());
    }

    private static void sendExp(Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();

        out.writeUTF("set-exp");
        out.writeDouble(getExp(player));

        player.sendPluginMessage(Main.INSTANCE, Main.INSTANCE.channel, out.toByteArray());
    }
}
