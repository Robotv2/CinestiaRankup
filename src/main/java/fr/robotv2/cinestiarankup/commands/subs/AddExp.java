package fr.robotv2.cinestiarankup.commands.subs;

import fr.robotv2.cinestiarankup.Utility;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static fr.robotv2.cinestiarankup.Utility.colorize;

public class AddExp {

    public void addExp(CommandSender sender, String[] args) {
        if(!(args.length >= 3)) {
            sender.sendMessage(colorize("&cERREUR: /rankup addexp <joueur> <exp>"));
            return;
        }

        Player target = Bukkit.getPlayer(args[1]);
        if(target == null) {
            sender.sendMessage("&cLe joueur n'est pas connecté.");
            return;
        }

        double exp = Double.parseDouble(args[2]);
        Double current = Utility.getExp(target);
        Utility.setExp(target, current + exp);

        sender.sendMessage(colorize("&fL'exp de &e" + target.getName() + " &fest désormais &e" + current + exp));
    }
}
