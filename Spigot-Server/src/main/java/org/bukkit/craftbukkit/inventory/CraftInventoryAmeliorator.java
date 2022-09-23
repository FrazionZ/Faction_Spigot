package org.bukkit.craftbukkit.inventory;

import org.bukkit.block.Ameliorator;
import org.bukkit.inventory.AmelioratorInventory;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.IInventory;

public class CraftInventoryAmeliorator extends CraftInventory implements AmelioratorInventory {
    public CraftInventoryAmeliorator(IInventory tileAmeliorator) {
    	super(tileAmeliorator);
	}

	public ItemStack getResult() {
        return getItem(9);
    }

    public ItemStack getFuel() {
        return getItem(0);
    }

    public ItemStack getSmelting() {
        return getItem(0);
    }

    public void setFuel(ItemStack stack) {
        setItem(0,stack);
    }

    public void setResult(ItemStack stack) {
        setItem(9,stack);
    }

    public void setSmelting(ItemStack stack) {
        setItem(0,stack);
    }

    @Override
    public Ameliorator getHolder() {
        return (Ameliorator) inventory.getOwner();
    }
}
