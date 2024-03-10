package Commands;

import azisaba.sirnkanycasescanceler.SirnkAnyCasesCanceler;
import org.bukkit.entity.Player;

public class ReloadConfig extends SubCommand {
    @Override
    public String getName() {
        return "reloadConfig";
    }

    @Override
    public void execute(Player player,String [] args) {
        SirnkAnyCasesCanceler.getInstance().reload();
        player.sendMessage("コンフィグをリロードしました。");
    }
}