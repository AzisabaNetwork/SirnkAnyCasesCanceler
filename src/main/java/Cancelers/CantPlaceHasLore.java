package Cancelers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

//Loreがついているアイテムの誤設置防止
public class CantPlaceHasLore implements Listener {
    @EventHandler
    public void onBlockPlaceWithLoredBlock(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = event.getItemInHand();
        if (itemInHand.hasItemMeta()) {
            ItemMeta meta = itemInHand.getItemMeta();
            String s = ChatColor.stripColor(Objects.requireNonNull(meta).getDisplayName());
            if (Objects.requireNonNull(meta).hasDisplayName() && s.contains("Storage Box")) {
                return;
            }
            if (meta.hasDisplayName() && s.contains("Build-Tool")) {
                return;
            }
            if (meta.hasLore()) {
                if (!player.isSneaking()) {
                    event.setCancelled(true);
                    player.sendMessage("説明文が付いているアイテムはスニークしていないと設置できません。");
                }
            }
        }
    }
}
