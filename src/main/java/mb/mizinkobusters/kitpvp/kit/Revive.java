package mb.mizinkobusters.kitpvp.kit;

import mb.mizinkobusters.kitpvp.utils.KitPvPUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Revive implements Listener {

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
        if (!KitPvPUtil.getKit(killer).equals("Revive")) {
            return;
        }
        killer.sendMessage("§cこのKitではキル時に金のリンゴを獲得できません");
        killer.sendMessage("§cこのKitではキル時にHPが全快しません");
        killer.removePotionEffect(PotionEffectType.REGENERATION);
        killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1200, 1));
        killer.sendMessage("§7Skill Trigger: §6Revive");
    }
}
