package azisaba.sirnkanycasescanceler.Commands;

import azisaba.sirnkanycasescanceler.SirnkAnyCasesCanceler;
import io.lumine.xikage.mythicmobs.MythicMobs;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;

public class AddIgnoreMultiStyle extends SubCommand {
    @Override
    public String getName() {
        return "addIgnoreMultiStyle";
    }

    @Override
    public void execute( Player player, String [] args) {
        if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "idを入力してください。");
                return;
            }
        String s = args[0];
        FileConfiguration config = SirnkAnyCasesCanceler.getInstance().getConfig();
        List<String> list = config.getStringList("ignoreMultiStyles");
        if (list.contains(s)) {
            player.sendMessage(ChatColor.RED + "既に登録されています。");
            return;
        }
        list.add(s);
        config.set("ignoreMultiStyles", list);
        SirnkAnyCasesCanceler.getInstance().save();
        ItemStack item = MythicMobs.inst().getItemManager().getItemStack(s + "1");
        String name = Objects.requireNonNull(item.getItemMeta()).getDisplayName();
        player.sendMessage(ChatColor.DARK_AQUA + name + ChatColor.DARK_AQUA + "をIgnoresListに追加しました。");
    }
}
