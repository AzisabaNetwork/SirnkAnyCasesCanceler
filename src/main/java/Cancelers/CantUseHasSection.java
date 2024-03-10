package Cancelers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

//Loreがついているアイテムの誤設置防止
public class CantUseHasSection implements Listener {
    @EventHandler
    public void onInteractWithSectionedItem(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;
        String s = meta.getDisplayName();

        String type = item.getType().name();
        if (s.contains("§") && type.equals("NAME_TAG")) {
            e.setCancelled(true);
        }
    }
}
