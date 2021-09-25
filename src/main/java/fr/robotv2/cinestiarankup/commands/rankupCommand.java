package fr.robotv2.cinestiarankup.commands;

import fr.robotv2.cinestiarankup.commands.subs.info;
import fr.robotv2.cinestiarankup.commands.subs.setExp;
import fr.robotv2.cinestiarankup.commands.subs.setLevel;
import fr.robotv2.cinestiarankup.config.rankupDB;
import fr.robotv2.cinestiarankup.main;
import fr.robotv2.cinestiarankup.ui.stock.menuGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static fr.robotv2.cinestiarankup.utility.color;
import static fr.robotv2.cinestiarankup.utility.getExp;

public class rankupCommand implements CommandExecutor {

    public setLevel setLevel;
    public setExp setExp;
    public info info;
    public fr.robotv2.cinestiarankup.commands.subs.reload reload;

    public rankupCommand() {
        setLevel = new setLevel();
        setExp = new setExp();
        info = new info();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("cinestia.command.rankup")) {
            sender.sendMessage(color("&cVous n'avez pas la permission d'exécuter la commande."));
            return false;
        }

        if(args.length == 0 && sender instanceof Player) {
            Player player = (Player) sender;
            if(getExp(player) == null) {
                player.sendMessage(color("&cVos données n'ont pas pu être chargées. Merci de contacter un administrateur"));
                return false;
            }
            main.getManager().open(player, menuGUI.class);
            return true;
        }

        if(!sender.hasPermission("cinestia.admin")) return false;
        switch(args[0].toLowerCase()) {
            case "setlevel":
                setLevel.setlevel(sender, args);
                break;
            case "setexp":
                setExp.setExp(sender, args);
                break;
            case "addexp":
                break;
            case "info":
                info.info(sender);
                break;
            case "reload":
                rankupDB.reloadDB();
                sender.sendMessage(color("&aLe fichier rankup.yml a bien été rechargé."));
                break;
        }
        return false;
    }
}
