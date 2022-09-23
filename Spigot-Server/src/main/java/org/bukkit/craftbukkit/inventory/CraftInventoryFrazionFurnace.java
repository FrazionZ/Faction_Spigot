package org.bukkit.craftbukkit.inventory;

import org.bukkit.block.Furnace;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.frazionz.tileentity.TileEntityFrazionFurnace;

public class CraftInventoryFrazionFurnace extends CraftInventory implements FurnaceInventory {
    public CraftInventoryFrazionFurnace(TileEntityFrazionFurnace furnace) {
        super(furnace);
    }

    
    public ItemStack getResult() {
        return getItem(3);
    }

    public ItemStack getFuel() {
        return getItem(2);
    }

    public ItemStack getSmelting() {
        return getItem(0);
    }

    public void setFuel(ItemStack stack) {
        setItem(0,stack);
    }

    public void setResult(ItemStack stack) {
        setItem(3,stack);
    }

    public void setSmelting(ItemStack stack) {
        setItem(0,stack);
    }

    @Override
    public Furnace getHolder() {
        return (Furnace) inventory.getOwner();
    }
}
