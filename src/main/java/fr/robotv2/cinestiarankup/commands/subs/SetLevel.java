package fr.robotv2.cinestiarankup.commands.subs;

import fr.robotv2.cinestiarankup.Utility;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import static fr.robotv2.cinestiarankup.Utility.colorize;

public class SetLevel {

    public void setlevel(CommandSender sender, String[] args) {
        if(!(args.length >= 3)) {
            sender.sendMessage(colorize("&cERREUR: /rankup setlevel <joueur> <level>"));
            return;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
        int level = Integer.parseInt(args[2]);
        Utility.setLevel(target.getUniqueId(), level);

        sender.sendMessage(colorize("&fLe niveau de &e" + target.getName() + " &fest désormais &e" + level));
    }

}
