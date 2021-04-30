package mb.mizinkobusters.kitpvp.command;

import mb.mizinkobusters.kitpvp.utils.KitPvPUtil;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spec implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("spec")) {
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players can execute this command.");
            return true;
        }
        Player player = (Player) sender;
        if (!KitPvPUtil.isInWorld(player)) {
            return true;
        }
        if (!KitPvPUtil.hasKit(player)) {
            return true;
        }
        if (!player.getGameMode().equals(GameMode.SPECTATOR)) {
            return true;
        }
        player.setGameMode(GameMode.ADVENTURE);
        player.teleport(new Location((player.getWorld()), 0.5, 11.0, 0.5, 0, 0));
        player.sendMessage("§f[§dKitPvP§f] §a観戦モードを解除しました");
        return true;
    }
}
