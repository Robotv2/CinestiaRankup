package fr.robotv2.cinestiarankup.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static fr.robotv2.cinestiarankup.Main.LOGGER;
import static fr.robotv2.cinestiarankup.Utility.color;

public class RankupDB {

    private static File database;
    private static FileConfiguration databaseconfig;

    public static void setupDB() {
        database = new File(Bukkit.getServer().getPluginManager().getPlugin("CinestiaRankup").getDataFolder(), "rankup.yml");
        if(!database.exists()) {
            try {
                if(!database.getParentFile().exists())
                    database.getParentFile().mkdir();
                database.createNewFile();
            } catch(IOException e) {
                LOGGER.warning(color("&cErreur lors de la génération du fichier rankup.yml"));
            }
        }
        databaseconfig = YamlConfiguration.loadConfiguration(database);
    }

    public static FileConfiguration getDB() {
        return databaseconfig;
    }

    public static void saveDB() {
        try {
            databaseconfig.save(database);
        } catch (IOException e) {
            LOGGER.warning(color("&cErreur lors de la génération du fichier rankup.yml"));
        }
    }

    public static void reloadDB() {
        databaseconfig = YamlConfiguration.loadConfiguration(database);
    }
}
