package Commands;

import azisaba.sirnkanycasescanceler.SirnkAnyCasesCanceler;
import com.github.mori01231.lifecore.util.ItemUtil;
import io.lumine.xikage.mythicmobs.MythicMobs;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;

public class AddIgnore extends SubCommand {
    @Override
    public String getName() {
        return "addIgnore";
    }

    @Override
    public void execute( Player player, String [] args) {
        if (args.length == 0) {
            ItemStack stack = player.getInventory().getItemInMainHand();
            String id = ItemUtil.getMythicType(stack);
            if (id == null) {
                player.sendMessage(ChatColor.RED + "mmidを入力するか、対象アイテムを手に持ってください。");
                return;
            }
            FileConfiguration config = SirnkAnyCasesCanceler.getInstance().getConfig();
            List<String> list = config.getStringList("ignores");
            if (list.contains(id)) {
                player.sendMessage(ChatColor.RED + "既に登録されています。");
                return;
            }
            list.add(id);
            config.set("ignores", list);
            SirnkAnyCasesCanceler.getInstance().save();
            ItemStack item = MythicMobs.inst().getItemManager().getItemStack(id);
            String name = Objects.requireNonNull(item.getItemMeta()).getDisplayName();
            player.sendMessage(ChatColor.DARK_AQUA + name + ChatColor.DARK_AQUA + "をIgnoresListに追加しました。");
            return;
        }
        String s = args[0];
        ItemStack mythicItem = MythicMobs.inst().getItemManager().getItemStack(s);
        if (mythicItem == null) {
            player.sendMessage(ChatColor.RED + "無効なmmidです。");
            return;
        }
        FileConfiguration config = SirnkAnyCasesCanceler.getInstance().getConfig();
        List<String> list = config.getStringList("ignores");
        if (list.contains(s)) {
            player.sendMessage(ChatColor.RED + "既に登録されています。");
            return;
        }
        list.add(s);
        config.set("ignores", list);
        SirnkAnyCasesCanceler.getInstance().save();
        ItemStack item = MythicMobs.inst().getItemManager().getItemStack(s);
        String name = Objects.requireNonNull(item.getItemMeta()).getDisplayName();
        player.sendMessage(ChatColor.DARK_AQUA + name + ChatColor.DARK_AQUA + "をIgnoresListに追加しました。");
    }
}
