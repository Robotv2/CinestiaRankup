package fr.robotv2.cinestiarankup.ui;

import fr.robotv2.cinestiarankup.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class UImanager implements Listener {

    private final Main main;
    public UImanager(Main main) {
        this.main = main;
    }

    public static HashMap<Player, Integer> selectedMenus = new HashMap<>();

    public static class holdersMENU implements InventoryHolder {
        @Override
        public @NotNull Inventory getInventory() {
            return null;
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack current = e.getCurrentItem();

        if(e.getCurrentItem() == null) return;

        main.getMenus().values().stream()
                .filter(menu -> menu.getName(player).equals(e.getView().getTitle()))
                .forEach(menu -> {
                    menu.onClick(player, e.getInventory(), current, e.getRawSlot(), e.getClick());
                    e.setCancelled(true);
                });
    }

    public void addMenu(GUI gui){
        main.getMenus().put(gui.getClass(), gui);
    }

    public void open(Player player, Class<? extends GUI> gClass){

        if(!main.getMenus().containsKey(gClass)) return;

        GUI menu = main.getMenus().get(gClass);
        Inventory inv = Bukkit.createInventory(null, menu.getSize(), menu.getName(player));
        menu.contents(player, inv);

        Bukkit.getScheduler().runTaskLater(main, () -> {
            player.openInventory(inv);
        }, 1);
    }
}
