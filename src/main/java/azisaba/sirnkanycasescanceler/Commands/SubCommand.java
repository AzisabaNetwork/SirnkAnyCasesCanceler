package azisaba.sirnkanycasescanceler.Commands;

import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public abstract class SubCommand {
    public abstract String getName();

    public abstract void execute(Player player, String [] args);

    public List<String> suggest(Player player, String [] args) {
        return Collections.emptyList();
    }
}
