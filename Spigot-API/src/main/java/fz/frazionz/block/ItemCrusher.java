package fz.frazionz.block;

import fz.frazionz.inventory.ItemCrusherInventory;
import org.bukkit.block.Container;

public interface ItemCrusher extends Container {

    @Override
    public ItemCrusherInventory getInventory();

    @Override
    public ItemCrusherInventory getSnapshotInventory();

}
