package org.bukkit.craftbukkit.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.craftbukkit.inventory.CraftInventoryOnyxFurnace;
import org.bukkit.inventory.FurnaceInventory;

import net.minecraft.server.frazionz.tileentity.TileEntityOnyxFurnace;

public class CraftOnyxFurnace extends CraftContainer<TileEntityOnyxFurnace> implements Furnace {

    public CraftOnyxFurnace(final Block block) {
        super(block, TileEntityOnyxFurnace.class);
    }

    public CraftOnyxFurnace(final Material material, final TileEntityOnyxFurnace te) {
        super(material, te);
    }

    @Override
    public FurnaceInventory getSnapshotInventory() {
        return new CraftInventoryOnyxFurnace(this.getSnapshot());
    }

    @Override
    public FurnaceInventory getInventory() {
        if (!this.isPlaced()) {
            return this.getSnapshotInventory();
        }

        return new CraftInventoryOnyxFurnace(this.getTileEntity());
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
    public String getCustomName() {
    	TileEntityOnyxFurnace furnace = this.getSnapshot();
        return furnace.hasCustomName() ? furnace.getName() : null;
    }

    @Override
    public void setCustomName(String name) {
        this.getSnapshot().setCustomName(name);
    }

    @Override
    public void applyTo(TileEntityOnyxFurnace furnace) {
        super.applyTo(furnace);

        if (!this.getSnapshot().hasCustomName()) {
            furnace.setCustomName(null);
        }
    }
}
