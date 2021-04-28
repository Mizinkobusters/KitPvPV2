package mb.mizinkobusters.kitpvp.other;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class FishingCanceller implements Listener {

    @EventHandler
    public void onFishing(PlayerFishEvent event) {
        if(event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
            event.setCancelled(true);
        }
    }
}
