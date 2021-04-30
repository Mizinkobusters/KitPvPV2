package mb.mizinkobusters.kitpvp.kit;

import mb.mizinkobusters.kitpvp.utils.KitPvPUtil;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Standard implements Listener {

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

        if (!KitPvPUtil.getKit(killer).equals("Standard")) {
            return;
        }
        killer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));
        killer.setHealth(killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
    }

    @EventHandler
    public void onEatGapple(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        if (!KitPvPUtil.isInWorld(player)) {
            return;
        }
        if (!KitPvPUtil.getKit(player).equals("Standard")) {
            return;
        }

        ItemStack item = event.getItem();
        if (!item.getType().equals(Material.GOLDEN_APPLE)) {
            return;
        }

        if (player.hasPotionEffect(PotionEffectType.SPEED)) {
            player.removePotionEffect(PotionEffectType.SPEED);
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 0));
        player.sendMessage("§7Skill Trigger: §6Standard");
    }
}
