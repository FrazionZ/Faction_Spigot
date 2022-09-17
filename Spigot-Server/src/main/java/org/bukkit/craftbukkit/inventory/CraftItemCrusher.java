package org.bukkit.craftbukkit.inventory;

import fz.frazionz.block.ItemCrusher;
import fz.frazionz.inventory.ItemCrusherInventory;
import net.minecraft.server.frazionz.tileentity.TileEntityItemCrusher;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.block.CraftContainer;

public class CraftItemCrusher extends CraftContainer<TileEntityItemCrusher> implements ItemCrusher {

    public CraftItemCrusher(final Block block) {
        super(block, TileEntityItemCrusher.class);
    }

    public CraftItemCrusher(final Material material, final TileEntityItemCrusher te) {
        super(material, te);
    }

    @Override
    public ItemCrusherInventory getSnapshotInventory() {
        return new CraftInventoryItemCrusher(this.getSnapshot());
    }

    @Override
    public ItemCrusherInventory getInventory() {
        if (!this.isPlaced()) {
            return this.getSnapshotInventory();
        }

        return new CraftInventoryItemCrusher(this.getTileEntity());
    }

}
