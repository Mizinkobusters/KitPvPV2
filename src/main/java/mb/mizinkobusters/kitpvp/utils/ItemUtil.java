package mb.mizinkobusters.kitpvp.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class ItemUtil {

    public static ItemStack createItem(ItemStack item, String itemName, List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(itemName);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createPotion(ItemStack potion, String itemName, List<String> lore, PotionEffectType type) {
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        meta.setDisplayName(itemName);
        meta.setLore(lore);
        meta.setMainEffect(type);
        potion.setItemMeta(meta);
        return potion;
    }
}
