package azisaba.sirnkanycasescanceler.Cancelers;

import azisaba.sirnkanycasescanceler.SirnkAnyCasesCanceler;
import com.github.mori01231.lifecore.util.ItemUtil;
import io.lumine.xikage.mythicmobs.MythicMobs;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

//取引禁止系+改造アイテムの出品防止

public class IllegalAuctionCanceler implements Listener {

    @EventHandler
    public void onCantTrade(PlayerCommandPreprocessEvent e) {
        String command = e.getMessage().toLowerCase();
        if (isThatCommandAuction(command)) {
            Player player = e.getPlayer();
            ItemStack item = player.getInventory().getItemInMainHand();
            ItemMeta meta = item.getItemMeta();
            if (meta == null)
                return;
            String s = ChatColor.stripColor(String.valueOf(meta.getLore()));
            if (s.contains("取引禁止")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.RED + "取引禁止アイテムのため、オークションに出品できません。");
            }
        }
    }

    @EventHandler
    public void onModification(PlayerCommandPreprocessEvent e) {
        String command = e.getMessage().toLowerCase();
        if (isThatCommandAuction(command)) {
            if (!SirnkAnyCasesCanceler.getInstance().getConfig().getBoolean("PreventModification"))
                return;
            Player player = e.getPlayer();
            ItemStack stack = player.getInventory().getItemInMainHand();
            String id = ItemUtil.getMythicType(stack);
            if (id == null)
                return;
            List<String> ignores = SirnkAnyCasesCanceler.getInstance().getConfig().getStringList("ignores");
            if (ignores.toString().contains(id))
                return;
            List<String> ignoresMS = SirnkAnyCasesCanceler.getInstance().getConfig().getStringList("ignoreMultiStyles");
            String multi = id.substring(0, id.length() - 1);
            if (ignoresMS.contains(multi))
                return;
            ItemStack mythicItem = MythicMobs.inst().getItemManager().getItemStack(id);
            if (mythicItem == null)
                return;
            String display1 = mythicItem.getItemMeta().getDisplayName();
            String enchants1 = mythicItem.getEnchantments().toString();
            String display2 = stack.getItemMeta().getDisplayName();
            String enchants2 = stack.getEnchantments().toString();
            if (stack.getType() == Material.LEATHER_HELMET || stack.getType() == Material.LEATHER_CHESTPLATE || stack.getType() == Material.LEATHER_LEGGINGS || stack.getType() == Material.LEATHER_BOOTS) {
                Color color1 = getLeatherArmorColor(mythicItem);
                Color color2 = getLeatherArmorColor(stack);
                if (!color1.equals(color2)) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(ChatColor.RED + "改造アイテムのため、オークションに出品できません。");
                    return;
                }
            }
            if (!display1.equals(display2) || !enchants1.equals(enchants2)) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.RED + "改造アイテムのため、オークションに出品できません。");
            }
        }
    }

    public Color getLeatherArmorColor(ItemStack itemStack) {
        if (itemStack.getType() == Material.LEATHER_BOOTS || itemStack
                .getType() == Material.LEATHER_LEGGINGS || itemStack
                .getType() == Material.LEATHER_CHESTPLATE || itemStack
                .getType() == Material.LEATHER_HELMET) {
            ItemMeta itemMeta = itemStack.getItemMeta();
            if (itemMeta instanceof LeatherArmorMeta)
                return ((LeatherArmorMeta) itemMeta).getColor();
        }
        return null;
    }

    public boolean isThatCommandAuction(String command) {
        return (command.startsWith("/ah sell") || command
                .startsWith("/crazyauctions:ah sell") || command
                .startsWith("/ca sell") || command
                .startsWith("/crazyauctions:ca sell") || command
                .startsWith("/crazyauction sell") || command
                .startsWith("/crazyauctions:crazyauction sell") || command
                .startsWith("/crazyauctions sell") || command
                .startsWith("/crazyauctions:crazyauctions sell") || command
                .startsWith("/hdv sell") || command
                .startsWith("/crazyauctions:hdv sell"));
    }
}
