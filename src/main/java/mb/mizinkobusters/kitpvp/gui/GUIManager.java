package mb.mizinkobusters.kitpvp.gui;

import fr.minuskube.inv.InventoryManager;
import fr.minuskube.inv.SmartInventory;
import mb.mizinkobusters.kitpvp.Main;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class GUIManager {

    private final JavaPlugin plugin;
    private final InventoryManager manager;

    public GUIManager(Main plugin) {
        this.plugin = plugin;
        this.manager = new InventoryManager(plugin);
        this.manager.init();
    }

    public void openKitSelector(Player player) {
        SmartInventory.builder()
                .manager(manager)
                .provider(new KitMenu((Main) plugin))
                .size(6, 9)
                .title("§3§lKitを選択してください")
                .build().open(player);
    }
}
