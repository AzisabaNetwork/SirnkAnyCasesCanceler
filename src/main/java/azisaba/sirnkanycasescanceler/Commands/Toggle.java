package azisaba.sirnkanycasescanceler.Commands;

import azisaba.sirnkanycasescanceler.SirnkAnyCasesCanceler;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Toggle extends SubCommand {
    @Override
    public String getName() {
        return "toggle";
    }

    @Override
    public void execute(Player player, String [] args) {
        String type = SirnkAnyCasesCanceler.getInstance().getConfig().getString("PreventModification");
        FileConfiguration config = SirnkAnyCasesCanceler.getInstance().getConfig();
        if(type == "true") {
            config.set("PreventModification", "false");
            player.sendMessage(ChatColor.DARK_AQUA + "falseに設定しました。");
        }else{
            config.set("PreventModification", "true");
            player.sendMessage(ChatColor.DARK_AQUA + "trueに設定しました。");
        }
    }
}
