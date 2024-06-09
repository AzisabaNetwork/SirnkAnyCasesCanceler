package azisaba.sirnkanycasescanceler.Cancelers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

//Loreがついているアイテムの誤設置防止
public class CantUseHasSection implements Listener {
    @EventHandler
    public void onInteractWithSectionedItemInMainHand(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        aaaaaa(e, item);
    }

    @EventHandler
    public void onInteractWithSectionedItemInOffHand(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInOffHand();
        aaaaaa(e, item);
    }

    private void aaaaaa(PlayerInteractEntityEvent e, ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;
        String ms = meta.getDisplayName();

        Material type = item.getType();
        if (ms.contains("§") && type == Material.NAME_TAG) {
            e.setCancelled(true);
        }
    }
}
