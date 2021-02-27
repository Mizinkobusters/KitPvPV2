package mb.mizinkobusters.kitpvp.kit;

import mb.mizinkobusters.kitpvp.utils.KitPvPUtil;
import mb.mizinkobusters.kitpvp.utils.PlayerUtil;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Astronaut implements Listener {

    @EventHandler
    public void onKill(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if (player.getKiller() == null) {
            return;
        }

        Player killer = player.getKiller();
        if (!KitPvPUtil.isInWorld(killer)) {
            return;
        }
        if (!KitPvPUtil.getKit(killer).equals("Astronaut")) {
            return;
        }
        PlayerUtil.heal(player);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!event.getEntity().getType().equals(EntityType.PLAYER)) {
            return;
        }

        Player player = (Player) event.getEntity();
        if (!KitPvPUtil.getKit(player).equals("Astronaut")) {
            return;
        }

        if (!event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
            return;
        }
        event.setCancelled(true);
    }
    // TODO ダブルジャンプの実装
}
