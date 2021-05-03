package mb.mizinkobusters.kitpvp.listener;

import mb.mizinkobusters.kitpvp.Main;
import mb.mizinkobusters.kitpvp.utils.KitPvPUtil;
import mb.mizinkobusters.kitpvp.utils.PlayerUtil;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class PlayerRespawnListener implements Listener {

    private JavaPlugin plugin;

    public PlayerRespawnListener(Main plugin) {
        this.plugin = plugin;
    }

    private String prefix = "§f[§dKitPvP§f] ";
    private HashMap<UUID, Long> cooldown = new HashMap<>();

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (!KitPvPUtil.isInWorld(player)) {
            return;
        }
        if (!KitPvPUtil.hasKit(player)) {
            return;
        }

        if (!event.isSneaking()
                && player.hasMetadata("respawning")) {
            player.removeMetadata("respawning", plugin);
            player.sendMessage(prefix + "§eスニークを終了したためリスポーンをキャンセルしました");
        }

        if (event.isSneaking()
                && !player.hasMetadata("respawning")) {
            if (cooldown.getOrDefault(player.getUniqueId(), 0L) + 1000L > System.currentTimeMillis()) {
                return;
            }
            cooldown.put(player.getUniqueId(), System.currentTimeMillis());

            player.setMetadata("respawning", new FixedMetadataValue(plugin, this));
            player.sendMessage(prefix + "§aリスポーンを申請しました");
            player.sendMessage(prefix + "§e8秒間§7その場から動かないでください...");

            new BukkitRunnable() {
                int i = 0;

                @Override
                public void run() {
                    if (7 < i || !player.hasMetadata("respawning")) {
                        this.cancel();
                        return;
                    }
                    i++;
                    if (event.isSneaking() && player.hasMetadata("respawning")) {
                        int sec = 8 - i;
                        player.sendMessage(prefix + "§7リスポーンまであと... §c" + sec + "§7秒");
                    }

                    if (i == 8 && player.hasMetadata("respawning")) {
                        player.teleport(new Location(player.getWorld(), 0.5, 11.0, 0.5, 0, 0), PlayerTeleportEvent.TeleportCause.PLUGIN);

                        player.removeMetadata("respawning", plugin);
                        PlayerUtil.salvage(player);
                        player.sendMessage(prefix + "§aリスポーンに成功しました");
                    }
                }
            }.runTaskTimerAsynchronously(plugin, 20, 20);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!KitPvPUtil.isInWorld(player)) {
            return;
        }
        if (!KitPvPUtil.hasKit(player)) {
            return;
        }

        Block from = event.getFrom().getBlock();
        Block to = event.getTo().getBlock();

        if (from.getLocation().distance(to.getLocation()) < 1) {
            return;
        }

        if (!player.hasMetadata("respawning")) {
            return;
        }

        player.removeMetadata("respawning", plugin);
        player.sendMessage(prefix + "§eその場から動いたためリスポーンをキャンセルしました");
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (!KitPvPUtil.isInWorld(player)) {
            return;
        }
        if (!KitPvPUtil.hasKit(player)) {
            return;
        }

        if (!player.hasMetadata("respawning")) {
            return;
        }
        player.removeMetadata("respawning", plugin);
    }
}
