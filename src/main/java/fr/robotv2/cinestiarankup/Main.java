package fr.robotv2.cinestiarankup;

import fr.robotv2.cinestiarankup.bungee.PluginMessage;
import fr.robotv2.cinestiarankup.commands.RankupCommand;
import fr.robotv2.cinestiarankup.config.RankupDB;
import fr.robotv2.cinestiarankup.ui.GUI;
import fr.robotv2.cinestiarankup.ui.UImanager;
import fr.robotv2.cinestiarankup.ui.stock.BlockGUI;
import fr.robotv2.cinestiarankup.ui.stock.MenuGUI;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    public static Main INSTANCE;
    public static Economy ECO;
    public static Logger LOGGER;

    public final String channel = "rankup:channel";

    private static UImanager manager;
    private final Map<Class<? extends GUI>, GUI> menus = new HashMap<>();

    @Override
    public void onEnable() {
        registerChannels();
        setupGUI();
        setupConfigs();
        setupEconomy();
        setupCommands();

        (new Placeholder()).register();

        LOGGER = this.getLogger();
        INSTANCE = this;
    }

    @Override
    public void onDisable() {
        LOGGER = null;
        INSTANCE = null;
    }

    public void setupGUI() {
        manager = new UImanager(this);
        getServer().getPluginManager().registerEvents(manager, this);
        manager.addMenu(new MenuGUI());
        manager.addMenu(new BlockGUI());
    }

    public void setupConfigs() {
        RankupDB.setupDB();
    }

    public void setupEconomy() {
        RegisteredServiceProvider<Economy> economy = getServer().getServicesManager().getRegistration(Economy.class);
        if(economy != null)
            ECO = economy.getProvider();
    }

    public void setupCommands() {
        getCommand("rankup").setExecutor(new RankupCommand());
    }

    public void registerChannels() {
        getServer().getMessenger().registerIncomingPluginChannel(this, channel, new PluginMessage());
        getServer().getMessenger().registerOutgoingPluginChannel(this, channel);
    }


    public Map<Class<? extends GUI>, GUI> getMenus() {
        return menus;
    }

    public static UImanager getManager() {
        return manager;
    }
}
