package azisaba.sirnkanycasescanceler.Cancelers;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
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
            Material m = event.getPlayer().getInventory().getItemInMainHand().getType();
            if (Objects.requireNonNull(meta).hasDisplayName() && s.contains("Storage Box")) {
                return;
            }
            if (meta.hasDisplayName() && s.contains("Build-Tool")) {
                return;
            }
            if (m == Material.DIAMOND_HOE || m == Material.IRON_HOE || m == Material.GOLDEN_HOE || m == Material.STONE_HOE || m == Material.WOODEN_HOE){
                return;
            }
            if (m == Material.DIAMOND_SHOVEL || m == Material.IRON_SHOVEL || m == Material.GOLDEN_SHOVEL || m == Material.STONE_SHOVEL || m == Material.WOODEN_SHOVEL){
                return;
            }
            if (m == Material.DIAMOND_AXE || m == Material.IRON_AXE || m == Material.GOLDEN_AXE || m == Material.STONE_AXE || m == Material.WOODEN_AXE){
                return;
            }
            if (m == Material.DIAMOND_PICKAXE || m == Material.IRON_PICKAXE || m == Material.GOLDEN_PICKAXE || m == Material.STONE_PICKAXE || m == Material.WOODEN_PICKAXE){
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
