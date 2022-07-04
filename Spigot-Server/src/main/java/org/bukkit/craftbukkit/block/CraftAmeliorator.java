package org.bukkit.craftbukkit.block;

import org.bukkit.Material;
import org.bukkit.block.Ameliorator;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.inventory.CraftInventoryAmeliorator;
import org.bukkit.inventory.AmelioratorInventory;

import net.minecraft.server.TileEntityAmeliorator;

public class CraftAmeliorator extends CraftContainer<TileEntityAmeliorator> implements Ameliorator {

    public CraftAmeliorator(final Block block) {
        super(block, TileEntityAmeliorator.class);
    }

    public CraftAmeliorator(final Material material, final TileEntityAmeliorator te) {
        super(material, te);
    }

    @Override
    public AmelioratorInventory getSnapshotInventory() {
        return new CraftInventoryAmeliorator(this.getSnapshot());
    }

    @Override
    public AmelioratorInventory getInventory() {
        if (!this.isPlaced()) {
            return this.getSnapshotInventory();
        }

        return new CraftInventoryAmeliorator(this.getTileEntity());
    }

    @Override
    public short getBurnTime() {
        return (short) this.getSnapshot().getProperty(0);
    }

    @Override
    public void setBurnTime(short burnTime) {
        this.getSnapshot().setProperty(0, burnTime);
    }

    @Override
    public short getCookTime() {
        return (short) this.getSnapshot().getProperty(3);
    }

    @Override
    public void setCookTime(short cookTime) {
        this.getSnapshot().setProperty(3, cookTime);
    }

    @Override
    public void applyTo(TileEntityAmeliorator furnace) {
        super.applyTo(furnace);
    }
	public String getCustomName() {
		return null;
	}
	public void setCustomName(String name) {	
	}
}
