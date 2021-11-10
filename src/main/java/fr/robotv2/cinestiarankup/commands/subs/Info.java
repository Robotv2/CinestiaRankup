package fr.robotv2.cinestiarankup.commands.subs;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static fr.robotv2.cinestiarankup.Utility.*;

public class Info {

    public void info(CommandSender sender) {
        if(!(sender instanceof Player)) return;

        Player player = (Player) sender;
        int level = getLevel(player);
        Double exp = getExp(player);
        player.sendMessage(colorize("&6&m------------------------------------"));
        player.sendMessage(colorize("&f&l» &7&l" + player.getName().toUpperCase()));
        player.sendMessage(colorize("&f&l» &6&lNIVEAU: &f" + level));
        player.sendMessage(colorize("&f&l» &6&lEXP: &f" + exp));
        player.sendMessage(colorize("&6&m------------------------------------"));
    }
}
