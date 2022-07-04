package org.bukkit.craftbukkit.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.craftbukkit.inventory.CraftInventoryBauxiteFurnace;
import org.bukkit.inventory.FurnaceInventory;

import net.minecraft.server.TileEntityBauxiteFurnace;

public class CraftBauxiteFurnace extends CraftContainer<TileEntityBauxiteFurnace> implements Furnace {

    public CraftBauxiteFurnace(final Block block) {
        super(block, TileEntityBauxiteFurnace.class);
    }

    public CraftBauxiteFurnace(final Material material, final TileEntityBauxiteFurnace te) {
        super(material, te);
    }

    @Override
    public FurnaceInventory getSnapshotInventory() {
        return new CraftInventoryBauxiteFurnace(this.getSnapshot());
    }

    @Override
    public FurnaceInventory getInventory() {
        if (!this.isPlaced()) {
            return this.getSnapshotInventory();
        }

        return new CraftInventoryBauxiteFurnace(this.getTileEntity());
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
    	TileEntityBauxiteFurnace furnace = this.getSnapshot();
        return furnace.hasCustomName() ? furnace.getName() : null;
    }

    @Override
    public void setCustomName(String name) {
        this.getSnapshot().setCustomName(name);
    }

    @Override
    public void applyTo(TileEntityBauxiteFurnace furnace) {
        super.applyTo(furnace);

        if (!this.getSnapshot().hasCustomName()) {
            furnace.setCustomName(null);
        }
    }
}
