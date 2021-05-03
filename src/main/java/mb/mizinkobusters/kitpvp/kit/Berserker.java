package mb.mizinkobusters.kitpvp.kit;

import mb.mizinkobusters.kitpvp.utils.KitPvPUtil;
import mb.mizinkobusters.kitpvp.utils.PlayerUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Berserker implements Listener {

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
        if (!KitPvPUtil.getKit(killer).equals("Berserker")) {
            return;
        }

        if (killer.hasPotionEffect(PotionEffectType.WEAKNESS)) {
            killer.removePotionEffect(PotionEffectType.WEAKNESS);
        }
        killer.sendMessage("§cこのKitではキル時に金のリンゴを獲得できません");
        PlayerUtil.heal(killer);
        killer.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        killer.removePotionEffect(PotionEffectType.ABSORPTION);
        killer.removePotionEffect(PotionEffectType.SPEED);
        killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 0, false, false));
        killer.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 600, 0, false, false));
        killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 0, false, false));
        killer.sendMessage("§7Skill Trigger: §4Berserker");
    }
}
