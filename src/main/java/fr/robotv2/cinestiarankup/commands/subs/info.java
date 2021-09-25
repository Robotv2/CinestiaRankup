package fr.robotv2.cinestiarankup.commands.subs;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static fr.robotv2.cinestiarankup.utility.*;

public class info {

    public void info(CommandSender sender) {
        if(!(sender instanceof Player)) return;

        Player player = (Player) sender;
        int level = getLevel(player);
        Double exp = getExp(player);
        player.sendMessage(color("&6&m------------------------------------"));
        player.sendMessage(color("&f&l» &7&l" + player.getName().toUpperCase()));
        player.sendMessage(color("&f&l» &6&lNIVEAU: &f" + level));
        player.sendMessage(color("&f&l» &6&lEXP: &f" + exp));
        player.sendMessage(color("&6&m------------------------------------"));
    }
}
