package fr.robotv2.cinestiarankup;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static fr.robotv2.cinestiarankup.Utility.*;

public class Placeholder extends PlaceholderExpansion  {

    @Override
    public @NotNull String getAuthor() {
        return "Robotv2";
    }

    @Override
    public @NotNull String getIdentifier() {
        return "rankup";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    public String onPlaceholderRequest(Player player, String placeholder) {
        switch (placeholder.toLowerCase()) {
            case "display":
                return getRankupLevel(getLevel(player)).getDisplay();
            case "exp":
                return String.valueOf(getExp(player));
        }
        return placeholder;
    }
}
