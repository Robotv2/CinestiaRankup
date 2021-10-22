package fr.robotv2.cinestiarankup.commands.subs;

import fr.robotv2.cinestiarankup.Utility;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import static fr.robotv2.cinestiarankup.Utility.color;

public class SetLevel {

    public void setlevel(CommandSender sender, String[] args) {
        if(!(args.length >= 3)) {
            sender.sendMessage(color("&cERREUR: /rankup setlevel <joueur> <level>"));
            return;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
        int level = Integer.parseInt(args[2]);
        Utility.setLevel(target.getUniqueId(), level);

        sender.sendMessage(color("&fLe niveau de &e" + target.getName() + " &fest désormais &e" + level));
    }

}
