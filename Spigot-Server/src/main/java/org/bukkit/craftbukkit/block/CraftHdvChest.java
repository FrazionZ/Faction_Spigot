package org.bukkit.craftbukkit.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.inventory.Inventory;

import net.minecraft.server.frazionz.tileentity.TileEntityHdvChest;

public class CraftHdvChest extends CraftLootable<TileEntityHdvChest> implements Chest {

    public CraftHdvChest(final Block block) {
        super(block, TileEntityHdvChest.class);
    }

    public CraftHdvChest(final Material material, final TileEntityHdvChest te) {
        super(material, te);
    }

    @Override
    public Inventory getSnapshotInventory() {
        return new CraftInventory(this.getSnapshot());
    }

    @Override
    public Inventory getBlockInventory() {
        if (!this.isPlaced()) {
            return this.getSnapshotInventory();
        }

        return new CraftInventory(this.getTileEntity());
    }

    @Override
    public Inventory getInventory() {
        CraftInventory inventory = (CraftInventory) this.getBlockInventory();
        if (!isPlaced()) {
            return inventory;
        }

        // The logic here is basically identical to the logic in BlockChest.interact
        int x = this.getX();
        int y = this.getY();
        int z = this.getZ();
        CraftWorld world = (CraftWorld) this.getWorld();

        int id;
        if (world.getBlockTypeIdAt(x, y, z) == Material.HDV_CHEST.getId()) {
            id = Material.HDV_CHEST.getId();
        } else {
            throw new IllegalStateException("CraftChest is not a chest but is instead " + world.getBlockAt(x, y, z));
        }
        return inventory;
    }
}
