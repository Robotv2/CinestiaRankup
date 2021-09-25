package fr.robotv2.cinestiarankup.commands.subs;

import fr.robotv2.cinestiarankup.utility;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import static fr.robotv2.cinestiarankup.utility.color;

public class setExp {

    public void setExp(CommandSender sender, String[] args) {
        if(!(args.length >= 3)) {
            sender.sendMessage(color("&cERREUR: /rankup setexp <joueur> <exp>"));
            return;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
        Double exp = Double.parseDouble(args[2]);
        utility.setExp(target.getUniqueId(), exp);

        sender.sendMessage(color("&fL'exp de &e" + target.getName() + " &fest désormais &e" + exp));
    }
}
