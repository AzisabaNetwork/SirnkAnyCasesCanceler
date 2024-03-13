package azisaba.sirnkanycasescanceler.Commands;

import azisaba.sirnkanycasescanceler.SirnkAnyCasesCanceler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LookList extends SubCommand {
    @Override
    public String getName() {
        return "list";
    }

    @Override
    public void execute(Player player,String [] args) {
        String ignores = SirnkAnyCasesCanceler.getInstance().getConfig().getStringList("ignores").toString();
        String ignores1 = ignores.replace("["," ");
        String ignores2 = ignores1.replace("]"," ");
        String ignoresMS = SirnkAnyCasesCanceler.getInstance().getConfig().getStringList("ignoreMultiStyles").toString();
        String ignoresMS1 = ignoresMS.replace("["," ");
        String ignoresMS2 = ignoresMS1.replace("]"," ");
        player.sendMessage(ChatColor.DARK_AQUA + "----------Ignores List----------");
        player.sendMessage(ignores2);
        player.sendMessage(ChatColor.DARK_AQUA + "------MultiStyleIgnores List------");
        player.sendMessage(ignoresMS2);
        player.sendMessage(ChatColor.DARK_AQUA + "------------------------------");
    }
}
