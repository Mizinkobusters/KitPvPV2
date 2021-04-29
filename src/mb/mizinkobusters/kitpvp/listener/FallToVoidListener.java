package mb.mizinkobusters.kitpvp.listener;

import mb.mizinkobusters.kitpvp.utils.KitPvPUtil;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class FallToVoidListener implements Listener {

    @EventHandler
    public void onFallToVoid(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            return;
        }
        Player player = (Player) entity;
        if (!KitPvPUtil.isInWorld(player)) {
            return;
        }
        if (!KitPvPUtil.hasKit(player)) {
            return;
        }
        if (!event.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
            return;
        }
        event.setCancelled(true);
        if (player.getLastDamageCause() == null
                || player.getLastDamageCause().getEntity() == null) {
            return;
        }
        if (!player.getLastDamageCause().getEntity().getType().equals(EntityType.PLAYER)) {
            player.damage(1024, player);
        } else {
            player.damage(1024, player.getLastDamageCause().getEntity());
        }
    }
}
