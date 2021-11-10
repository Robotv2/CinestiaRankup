package fr.robotv2.cinestiarankup.commands.subs;

import fr.robotv2.cinestiarankup.Utility;
import fr.robotv2.cinestiarankup.config.RankupDB;
import org.bukkit.command.CommandSender;

import static fr.robotv2.cinestiarankup.Utility.colorize;

public class Reload {

    public void reload(CommandSender sender, String[] args) {
        RankupDB.reloadDB();
        Utility.clear();
        sender.sendMessage(colorize("&aLe fichier rankup.yml a bien été rechargé."));
    }
}
