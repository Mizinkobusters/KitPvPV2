package mb.mizinkobusters.kitpvp.listener;

import mb.mizinkobusters.kitpvp.utils.KitPvPUtil;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ContentsClickListener implements Listener {

    private final String prefix = "§f[§dKitPvP§f] ";

    @EventHandler(priority = EventPriority.LOWEST)
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (player.getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }
        if (!KitPvPUtil.isInWorld(player)) {
            return;
        }
        ItemStack item = event.getCurrentItem();
        if (item == null) {
            return;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasDisplayName()) {
            return;
        }

        if (!KitPvPUtil.hasKit(player)) {
            event.setCancelled(true);
        }

        if (event.getSlotType().equals(InventoryType.SlotType.ARMOR)
                || event.getSlotType().equals(InventoryType.SlotType.RESULT)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onClickItem(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item == null) {
            return;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasDisplayName()) {
            return;
        }
        if (meta.getDisplayName().equals("§c§lマッチを観戦する")) {
            player.teleport(new Location(player.getWorld(), 0.5, 11.0, 0.5, 0, 0));
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage(prefix + "§a観戦モードになりました");
            player.sendMessage(prefix + "§7/specで観戦モードを解除できます");
        }
    }
}
