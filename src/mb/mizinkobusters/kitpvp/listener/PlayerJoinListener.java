package mb.mizinkobusters.kitpvp.listener;

import mb.mizinkobusters.kitpvp.utils.KitPvPUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent  event) {
        Player player = event.getPlayer();
        if(KitPvPUtil.hasKit(player)) {
            KitPvPUtil.resetKit(player);
        }
    }
}
