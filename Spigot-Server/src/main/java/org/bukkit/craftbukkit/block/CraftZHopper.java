package org.bukkit.craftbukkit.block;

import net.minecraft.server.frazionz.tileentity.TileEntityZHopper;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Hopper;
import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.inventory.Inventory;

public class CraftZHopper extends CraftLootable<TileEntityZHopper> implements Hopper {

    public CraftZHopper(final Block block) {
        super(block, TileEntityZHopper.class);
    }

    public CraftZHopper(final Material material, final TileEntityZHopper te) {
        super(material, te);
    }

    @Override
    public Inventory getSnapshotInventory() {
        return new CraftInventory(this.getSnapshot());
    }

    @Override
    public Inventory getInventory() {
        if (!this.isPlaced()) {
            return this.getSnapshotInventory();
        }

        return new CraftInventory(this.getTileEntity());
    }
}
