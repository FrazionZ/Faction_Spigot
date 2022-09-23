package org.bukkit.craftbukkit.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.craftbukkit.inventory.CraftInventoryYelliteFurnace;
import org.bukkit.inventory.FurnaceInventory;

import net.minecraft.server.frazionz.tileentity.TileEntityYelliteFurnace;

public class CraftYelliteFurnace extends CraftContainer<TileEntityYelliteFurnace> implements Furnace {

    public CraftYelliteFurnace(final Block block) {
        super(block, TileEntityYelliteFurnace.class);
    }

    public CraftYelliteFurnace(final Material material, final TileEntityYelliteFurnace te) {
        super(material, te);
    }

    @Override
    public FurnaceInventory getSnapshotInventory() {
        return new CraftInventoryYelliteFurnace(this.getSnapshot());
    }

    @Override
    public FurnaceInventory getInventory() {
        if (!this.isPlaced()) {
            return this.getSnapshotInventory();
        }

        return new CraftInventoryYelliteFurnace(this.getTileEntity());
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
    	TileEntityYelliteFurnace furnace = this.getSnapshot();
        return furnace.hasCustomName() ? furnace.getName() : null;
    }

    @Override
    public void setCustomName(String name) {
        this.getSnapshot().setCustomName(name);
    }

    @Override
    public void applyTo(TileEntityYelliteFurnace furnace) {
        super.applyTo(furnace);

        if (!this.getSnapshot().hasCustomName()) {
            furnace.setCustomName(null);
        }
    }
}
