package azisaba.sirnkanycasescanceler.Cancelers;

import azisaba.sirnkanycasescanceler.SirnkAnyCasesCanceler;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class BlockInteractInventory implements Listener {
    @EventHandler
    public void onInteractBlock(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(SirnkAnyCasesCanceler.getInstance().getConfig().getBoolean("preventInteractInventory", false)) {
                if (isInventoryBlock(Objects.requireNonNull(e.getClickedBlock()).getType()) && !e.getPlayer().hasPermission("sacc.bypass.interact")) {
                    e.setCancelled(true);
                }
            }
        }
    }
    public boolean isInventoryBlock(Material material) {
        switch (material) {
            case ANVIL:
            case CHIPPED_ANVIL:
            case DAMAGED_ANVIL:
            case LOOM:
            case BEACON:
            case CRAFTING_TABLE:
            case ENCHANTING_TABLE:
            case GRINDSTONE:
            case BREWING_STAND:
                return true;
        }
        return false;
    }
}
