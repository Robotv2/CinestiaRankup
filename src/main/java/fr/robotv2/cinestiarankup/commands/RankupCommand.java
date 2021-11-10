package fr.robotv2.cinestiarankup.commands;

import fr.robotv2.cinestiarankup.Main;
import fr.robotv2.cinestiarankup.commands.subs.*;
import fr.robotv2.cinestiarankup.ui.stock.MenuGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static fr.robotv2.cinestiarankup.Utility.colorize;
import static fr.robotv2.cinestiarankup.Utility.getExp;

public class RankupCommand implements CommandExecutor {

    public SetLevel setLevel;
    public SetExp setExp;
    public Info info;
    public Reload reload;
    public AddExp addExp;

    public RankupCommand() {
        setLevel = new SetLevel();
        setExp = new SetExp();
        info = new Info();
        reload = new Reload();
        addExp = new AddExp();
    }

    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(!sender.hasPermission("cinestia.command.rankup")) {
            sender.sendMessage(colorize("&cVous n'avez pas la permission d'exécuter la commande."));
            return false;
        }

        if(args.length == 0 && sender instanceof Player) {
            Player player = (Player) sender;
            if(getExp(player) == null) {
                player.sendMessage(colorize("&cVos données n'ont pas pu être chargées. Merci de contacter un administrateur"));
                return false;
            }
            Main.getManager().open(player, MenuGUI.class);
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
                addExp.addExp(sender, args);
                break;
            case "info":
                info.info(sender);
                break;
            case "reload":
                reload.reload(sender, args);
                break;
        }
        return false;
    }
}
