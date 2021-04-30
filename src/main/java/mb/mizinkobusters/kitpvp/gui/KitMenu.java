package mb.mizinkobusters.kitpvp.gui;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import mb.mizinkobusters.kitpvp.Main;
import mb.mizinkobusters.kitpvp.other.ArmorGiver;
import mb.mizinkobusters.kitpvp.utils.ItemUtil;
import mb.mizinkobusters.kitpvp.utils.KitPvPUtil;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class KitMenu implements Listener, InventoryProvider {

    private final Main plugin;

    public KitMenu(Main plugin) {
        this.plugin = plugin;
    }

    private String prefix = "§f[§dKitPvP§f] ";

    public void optimizeInventory(Player player) {
        player.getInventory().clear();
        for (ItemStack armor : player.getInventory().getArmorContents()) {
            if (armor != null) {
                armor.setType(null);
            }
        }
        player.closeInventory();

    }

    @Override
    public void init(Player player, InventoryContents invContents) {
        invContents.add(ClickableItem.of(
                ItemUtil.createItem(
                        new ItemStack(Material.BOW),
                        "§f§lArcher Kitを選択する",
                        Arrays.asList("§dNormal §7/ §b一般Kit",
                                "",
                                "§f頭: 革",
                                "§f胴: チェーン",
                                "§f腰: チェーン",
                                "§f脚: 革",
                                "§f武器: 弓[無限I]",
                                "§f補助: 矢×1",
                                "§f補助: 金のリンゴ×1",
                                "§f特殊: なし")),
                e -> {
                    optimizeInventory(player);
                    new ArmorGiver(player, Material.LEATHER_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.LEATHER_BOOTS).equip();

                    ItemStack bow = new ItemStack(Material.BOW);
                    bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
                    player.getInventory().addItem(bow);
                    player.getInventory().addItem(new ItemStack(Material.ARROW));
                    player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));

                    KitPvPUtil.setKit(player, "Archer");
                    player.sendMessage(prefix + "§aArcher Kitを選択しました");
                }));
        invContents.add(ClickableItem.of(
                ItemUtil.createItem(
                        new ItemStack(Material.GLASS),
                        "§f§lAstronaut Kitを選択する",
                        Arrays.asList("§dNormal §7/ §b一般Kit",
                                "",
                                "§f頭: ガラス",
                                "§f胴: 鉄",
                                "§f腰: 鉄",
                                "§f脚: 鉄",
                                "§f武器: 鉄の剣",
                                "§f補助: 金のリンゴ×2",
                                "§f特殊: 跳躍力上昇III",
                                "§f特殊: 移動速度低下II",
                                "§f特殊: 落下ダメージ無効")),
                e -> {
                    optimizeInventory(player);
                    new ArmorGiver(player, Material.GLASS, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS).equip();

                    player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                    player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));

                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, 2, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 1, false, false));

                    KitPvPUtil.setKit(player, "Astronaut");
                    player.sendMessage(prefix + "§aAstronaut Kitを選択しました");
                }));
        invContents.add(ClickableItem.of(
                ItemUtil.createItem(
                        new ItemStack(Material.POTION, 1, (short) 16393),
                        "§f§lBerserker Kitを選択する",
                        Arrays.asList("§5Absolute §7/ §c購入Kit",
                                "",
                                "§f頭: 鉄",
                                "§f胴: 鉄",
                                "§f腰: チェーン",
                                "§f脚: 鉄",
                                "§f武器: 鉄の剣",
                                "§f補助: なし",
                                "§f特殊: 弱体化I",
                                "§f特殊: キルをすると弱体化の効果が消え,",
                                "§f攻撃力上昇I, 衝撃吸収I, 移動速度上昇Iの効果が30秒間付与される",
                                "§c特殊: キル時に金のリンゴを獲得できない")),
                e -> {
                    optimizeInventory(player);
                    new ArmorGiver(player, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.IRON_BOOTS).equip();

                    player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                    player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));

                    player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 1000000, 2, false, false));

                    KitPvPUtil.setKit(player, "Berserker");
                    player.sendMessage(prefix + "§aBerserker Kitを選択しました");
                }));
        invContents.add(ClickableItem.of(
                ItemUtil.createItem(
                        new ItemStack(Material.ICE),
                        "§f§lBlizzard Kitを選択する",
                        Arrays.asList("§dTarget §7/ §c購入Kit",
                                "",
                                "§f頭: ダイヤ",
                                "§f胴: チェーン",
                                "§f腰: チェーン",
                                "§f脚: ダイヤ",
                                "§f武器: ダイヤの剣",
                                "§f補助: 金のリンゴ×1",
                                "§f特殊: 攻撃時に1/4の確率で相手に移動速度低下IIIの効果を3秒間付与する")),
                e -> {
                    optimizeInventory(player);
                    new ArmorGiver(player, Material.DIAMOND_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.DIAMOND_BOOTS).equip();

                    player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                    player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));

                    KitPvPUtil.setKit(player, "Blizzard");
                    player.sendMessage(prefix + "§aBlizzard Kitを選択しました");
                }));
        invContents.add(ClickableItem.of(
                ItemUtil.createItem(
                        new ItemStack(Material.LEATHER_LEGGINGS),
                        "§f§lBoxer Kitを選択する",
                        Arrays.asList("§dNormal §7/ §b一般Kit",
                                "",
                                "§f頭: なし",
                                "§f胴: なし",
                                "§f腰: 革",
                                "§f脚: なし",
                                "§f武器: ダイヤの剣[ダメージ増加V]",
                                "§f補助: 金のリンゴ×2",
                                "§f特殊: なし")),
                e -> {
                    optimizeInventory(player);
                    new ArmorGiver(player, Material.AIR, Material.AIR, Material.LEATHER_LEGGINGS, Material.AIR).equip();

                    ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
                    sword.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                    player.getInventory().addItem(sword);
                    player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));

                    KitPvPUtil.setKit(player, "Boxer");
                    player.sendMessage(prefix + "§aBoxer Kitを選択しました");
                }));
        invContents.add(ClickableItem.of(
                ItemUtil.createItem(
                        new ItemStack(Material.DIAMOND_LEGGINGS),
                        "§f§lComet Kitを選択する",
                        Arrays.asList("§dNormal §7/ §b一般Kit",
                                "",
                                "§f頭: 革",
                                "§f胴: 革",
                                "§f腰: 革",
                                "§f脚: ダイヤ",
                                "§f武器: ダイヤの剣",
                                "§f補助: 金のリンゴ×2",
                                "§f特殊: 移動速度上昇III")),
                e -> {
                    LeatherArmorMeta armormeta;

                    ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
                    helmet.addEnchantment(Enchantment.DURABILITY, 1);
                    armormeta = (LeatherArmorMeta) helmet.getItemMeta();
                    armormeta.setColor(Color.AQUA);
                    helmet.setItemMeta(armormeta);

                    ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
                    helmet.addEnchantment(Enchantment.DURABILITY, 1);
                    armormeta = (LeatherArmorMeta) helmet.getItemMeta();
                    armormeta.setColor(Color.AQUA);
                    chest.setItemMeta(armormeta);

                    ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
                    helmet.addEnchantment(Enchantment.DURABILITY, 1);
                    armormeta = (LeatherArmorMeta) helmet.getItemMeta();
                    armormeta.setColor(Color.AQUA);
                    leg.setItemMeta(armormeta);

                    ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
                    boots.addEnchantment(Enchantment.DURABILITY, 1);
                    player.getInventory().setHelmet(helmet);
                    player.getInventory().setChestplate(chest);
                    player.getInventory().setLeggings(leg);
                    player.getInventory().setBoots(boots);

                    player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
                    player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));

                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 2, false, false));

                    KitPvPUtil.setKit(player, "Comet");
                    player.sendMessage(prefix + "§aComet Kitを選択しました");
                }));
        invContents.add(ClickableItem.of(
                ItemUtil.createItem(
                        new ItemStack(Material.DIAMOND_CHESTPLATE),
                        "§f§lCounter Kitを選択する",
                        Arrays.asList("§aGuard §7/ §c購入Kit",
                                "",
                                "§f頭: 鉄",
                                "§f胴: ダイヤ",
                                "§f腰: チェーン",
                                "§f脚: 鉄",
                                "§f武器: 石の剣",
                                "§f補助: 金のリンゴ×1",
                                "§f特殊: 1/5の確率で被ダメージを半減し,",
                                "§f被ダメージを相手に反射する")),
                e -> {
                    optimizeInventory(player);
                    new ArmorGiver(player, Material.IRON_HELMET, Material.DIAMOND_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.IRON_BOOTS).equip();

                    player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
                    player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));

                    KitPvPUtil.setKit(player, "Counter");
                    player.sendMessage(prefix + "§aCounter Kitを選択しました");
                }));
        invContents.add(ClickableItem.of(
                ItemUtil.createItem(
                        new ItemStack(Material.FISHING_ROD),
                        "§f§lFighter Kitを選択する",
                        Arrays.asList("§dNormal §7/ §b一般Kit",
                                "",
                                "§f頭: 鉄",
                                "§f胴: 鉄",
                                "§f腰: チェーン",
                                "§f脚: 鉄",
                                "§f武器: 鉄の剣",
                                "§f補助: 釣り竿×1",
                                "§f補助: 金のリンゴ×1")),
                e -> {
                    optimizeInventory(player);
                    new ArmorGiver(player, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.IRON_BOOTS).equip();

                    player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                    player.getInventory().addItem(new ItemStack(Material.FISHING_ROD));
                    player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));

                    KitPvPUtil.setKit(player, "Fighter");
                    player.sendMessage(prefix + "§aFighter Kitを選択しました");
                }));
        invContents.add(ClickableItem.of(
                ItemUtil.createItem(
                        new ItemStack(Material.RAW_FISH),
                        "§f§lFisherman Kitを選択する",
                        Arrays.asList("§dTechnical §7/ §c購入Kit",
                                "",
                                "§f頭: 革",
                                "§f胴: 鉄",
                                "§f腰: チェーン",
                                "§f脚: 革",
                                "§f武器: 鉄の剣",
                                "§f補助: 釣り竿×1",
                                "§f補助: 金のリンゴ×1",
                                "§f特殊: 釣り竿で相手を引っ掛けると少し引き寄せられる")),
                e -> {
                    optimizeInventory(player);
                    new ArmorGiver(player, Material.LEATHER_HELMET, Material.IRON_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.LEATHER_BOOTS).equip();

                    player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                    player.getInventory().addItem(new ItemStack(Material.FISHING_ROD));
                    player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));

                    KitPvPUtil.setKit(player, "Fisherman");
                    player.sendMessage(prefix + "§aFisherman Kitを選択しました");
                }));
        invContents.add(ClickableItem.of(
                ItemUtil.createItem(
                        new ItemStack(Material.BLAZE_POWDER),
                        "§f§lFlame Kitを選択する",
                        Arrays.asList("§dNormal §7/ §b一般Kit",
                                "",
                                "§f頭: 鉄",
                                "§f胴: 鉄",
                                "§f腰: チェーン",
                                "§f脚: 鉄",
                                "§f武器: 鉄の剣[火属性I]",
                                "§f武器: 弓[フレイムI]",
                                "§f補助: 矢×10",
                                "§f補助: 金のリンゴ×1",
                                "§f特殊: 火炎耐性III")),
                e -> {
                    optimizeInventory(player);
                    new ArmorGiver(player, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.IRON_BOOTS).equip();

                    ItemStack sword = new ItemStack(Material.IRON_SWORD);
                    sword.addEnchantment(Enchantment.FIRE_ASPECT, 1);
                    ItemStack bow = new ItemStack(Material.BOW);
                    bow.addEnchantment(Enchantment.ARROW_FIRE, 1);
                    player.getInventory().addItem(sword);
                    player.getInventory().addItem(bow);
                    player.getInventory().addItem(new ItemStack(Material.ARROW, 10));
                    player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));

                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1000000, 2, false, false));

                    KitPvPUtil.setKit(player, "Flame");
                    player.sendMessage(prefix + "§aFlame Kitを選択しました");
                }));
        invContents.add(ClickableItem.of(
                ItemUtil.createItem(
                        new ItemStack(Material.GOLDEN_APPLE),
                        "§f§lHealthBoost Kitを選択する",
                        Arrays.asList("§5Hard §7/ §c購入Kit",
                                "",
                                "§f頭: 鉄",
                                "§f胴: 鉄",
                                "§f腰: ダイヤ",
                                "§f脚: 鉄",
                                "§f武器: 鉄の剣",
                                "§f補助: なし",
                                "§f特殊: 初期HPが18になり,",
                                "§fキルをするとHPが1増える(最大26)",
                                "§c特殊: キル時に金のリンゴを獲得できない")),
                e -> {
                    optimizeInventory(player);
                    new ArmorGiver(player, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.IRON_BOOTS).equip();

                    player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));

                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(18.0);

                    KitPvPUtil.setKit(player, "HealthBoost");
                    player.sendMessage(prefix + "§aHealthBoost Kitを選択しました");
                }));
        invContents.add(ClickableItem.of(
                ItemUtil.createItem(
                        new ItemStack(Material.POTION, 1, (short) 16389),
                        "§f§lHealthBoost Kitを選択する",
                        Arrays.asList("§dManiac §7/ §b一般Kit",
                                "",
                                "§f頭: チェーン",
                                "§f胴: チェーン",
                                "§f腰: チェーン",
                                "§f脚: チェーン",
                                "§f武器: 鉄の剣",
                                "§f補助: 治癒のスプラッシュポーション×2",
                                "§f補助: 負傷のスプラッシュポーション×2",
                                "§f補助: 毒のスプラッシュポーション×2",
                                "§f特殊: キル時に治癒のスプラッシュポーションを獲得する",
                                "§c特殊: キル時に金のリンゴを獲得できない")),
                e -> {
                    optimizeInventory(player);
                    new ArmorGiver(player, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS).equip();

                    player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                    player.getInventory().addItem(new ItemStack(Material.POTION, 2, (short) 16389));
                    player.getInventory().addItem(new ItemStack(Material.POTION, 2, (short) 16396));
                    player.getInventory().addItem(new ItemStack(Material.POTION, 2, (short) 16388));

                    KitPvPUtil.setKit(player, "PotionHandler");
                    player.sendMessage(prefix + "§aPotionHandler Kitを選択しました");
                }));
        invContents.add(ClickableItem.of(
                ItemUtil.createItem(
                        new ItemStack(Material.RABBIT_FOOT),
                        "§f§lRabbit Kitを選択する",
                        Arrays.asList("§dNormal §7/ §c購入Kit",
                                "",
                                "§f頭: 鉄",
                                "§f胴: チェーン",
                                "§f腰: 鉄",
                                "§f脚: 革",
                                "§f武器: 鉄の剣",
                                "§f補助: 金のリンゴ×1",
                                "§f特殊: 跳躍力上昇II",
                                "§f特殊: 移動速度上昇II")),
                e -> {
                    optimizeInventory(player);
                    new ArmorGiver(player, Material.IRON_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.IRON_LEGGINGS, Material.LEATHER_BOOTS).equip();

                    player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                    player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));

                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, 1, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1, false, false));

                    KitPvPUtil.setKit(player, "Rabbit");
                    player.sendMessage(prefix + "§aRabbit Kitを選択しました");
                }));
        invContents.add(ClickableItem.of(
                ItemUtil.createItem(
                        new ItemStack(Material.POTION, 1, (short) 8193),
                        "§f§lRevive Kitを選択する",
                        Arrays.asList("§5Catastrophy §7/ §c購入Kit", //Catastrophy のスペルミスは意図的なもの
                                "",
                                "§f頭: 鉄",
                                "§f胴: 鉄",
                                "§f腰: ダイヤ",
                                "§f脚: ダイヤ",
                                "§f武器: 鉄の剣",
                                "§f補助: なし",
                                "§f特殊: キルをすると再生能力IIが60秒間付与される",
                                "§c特殊: キル時に金のリンゴを獲得できない",
                                "§c特殊: キル時にHPが全快しない")),
                e -> {
                    optimizeInventory(player);
                    new ArmorGiver(player, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS).equip();

                    player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));

                    KitPvPUtil.setKit(player, "Revive");
                    player.sendMessage(prefix + "§aRevive Kitを選択しました");
                }));
        invContents.add(ClickableItem.of(
                ItemUtil.createItem(
                        new ItemStack(Material.LONG_GRASS),
                        "§f§lRevive Kitを選択する",
                        Arrays.asList("§dTechnical §7/ §c購入Kit",
                                "",
                                "§f頭: チェーン",
                                "§f胴: チェーン",
                                "§f腰: チェーン",
                                "§f脚: チェーン",
                                "§f武器: 弓[射撃ダメージ増加I, 無限I]",
                                "§f補助: 矢×1",
                                "§f補助: 金のリンゴ×1",
                                "§f特殊: 矢を当てた相手との距離に応じて与ダメージが変動する",
                                "§f特殊: 10m未満: 与ダメージ-2",
                                "§f特殊: 10m以上15m未満: 与ダメージ+0",
                                "§f特殊: 15m以上30m未満: 与ダメージ+1",
                                "§f特殊: 30m以上: 与ダメージ+2")),
                e -> {
                    optimizeInventory(player);
                    new ArmorGiver(player, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS).equip();

                    ItemStack bow = new ItemStack(Material.BOW);
                    bow.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
                    bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
                    player.getInventory().addItem(bow);
                    player.getInventory().addItem(new ItemStack(Material.ARROW));
                    player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));

                    KitPvPUtil.setKit(player, "Sniper");
                    player.sendMessage(prefix + "§aSniper Kitを選択しました");
                }));
        invContents.add(ClickableItem.of(
                ItemUtil.createItem(
                        new ItemStack(Material.IRON_SWORD),
                        "§f§lStandard Kitを選択する",
                        Arrays.asList("§dNormal §7/ §b一般Kit",
                                "",
                                "§f頭: 鉄",
                                "§f胴: ダイヤ",
                                "§f腰: 鉄",
                                "§f脚: 鉄",
                                "§f武器: 鉄の剣",
                                "§f補助: 金のリンゴ×1",
                                "§f特殊: 金のリンゴを食べると30秒間移動速度上昇Iが付与される")),
                e -> {
                    optimizeInventory(player);
                    new ArmorGiver(player, Material.IRON_HELMET, Material.DIAMOND_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS).equip();

                    player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                    player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));

                    KitPvPUtil.setKit(player, "Standard");
                    player.sendMessage(prefix + "§aStandard Kitを選択しました");
                }));
        invContents.add(ClickableItem.of(
                ItemUtil.createItem(
                        new ItemStack(Material.ANVIL),
                        "§f§lTank Kitを選択する",
                        Arrays.asList("§aGuard §7/ §b一般Kit",
                                "",
                                "§f頭: ダイヤ",
                                "§f胴: ダイヤ",
                                "§f腰: ダイヤ",
                                "§f脚: ダイヤ",
                                "§f武器: 木の剣[ダメージ増加I]",
                                "§f補助: 金のリンゴ×2",
                                "§f特殊: 移動速度低下I")),
                e -> {
                    optimizeInventory(player);
                    new ArmorGiver(player, Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS).equip();

                    ItemStack sword = new ItemStack(Material.WOOD_SWORD);
                    sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                    player.getInventory().addItem(sword);
                    player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));

                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 0, false, false));

                    KitPvPUtil.setKit(player, "Tank");
                    player.sendMessage(prefix + "§aTank Kitを選択しました");
                }));
        invContents.add(ClickableItem.of(
                ItemUtil.createItem(
                        new ItemStack(Material.DOUBLE_PLANT),
                        "§f§lThunder Kitを選択する",
                        Arrays.asList("§dTarget §7/ §b一般Kit",
                                "",
                                "§f頭: 鉄",
                                "§f胴: 金",
                                "§f腰: 鉄",
                                "§f脚: 金",
                                "§f武器: 金の剣[ダメージ増加II]",
                                "§f補助: 金のリンゴ×2",
                                "§f特殊: 1/7の確率で与ダメージの1.2倍のダメージを与える雷を相手の頭上に落とす")),
                e -> {
                    optimizeInventory(player);
                    new ArmorGiver(player, Material.IRON_HELMET, Material.GOLD_CHESTPLATE, Material.IRON_LEGGINGS, Material.GOLD_BOOTS).equip();

                    ItemStack sword = new ItemStack(Material.GOLD_SWORD);
                    sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
                    player.getInventory().addItem(sword);
                    player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));

                    KitPvPUtil.setKit(player, "Thunder");
                    player.sendMessage(prefix + "§aThunder Kitを選択しました");
                }));
    }

    @Override
    public void update(Player player, InventoryContents inventoryContents) {
        // nothing
    }


    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (!event.hasItem()) {
            return;
        }

        ItemStack item = event.getItem();
        if (!item.hasItemMeta()) {
            return;
        }
        if (!item.getItemMeta().hasDisplayName()) {
            return;
        }

        Player player = event.getPlayer();
        if (item.getItemMeta().getDisplayName().equals("§6§lKitを選択する")) {
            new GUIManager(plugin).openKitSelector(player);
        }
    }
}
