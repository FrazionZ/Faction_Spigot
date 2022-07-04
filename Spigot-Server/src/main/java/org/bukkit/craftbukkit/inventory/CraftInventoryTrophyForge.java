package org.bukkit.craftbukkit.inventory;

import org.bukkit.block.TrophyForge;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.TrophyForgeInventory;

import net.minecraft.server.IInventory;

public class CraftInventoryTrophyForge extends CraftInventory implements TrophyForgeInventory {
    public CraftInventoryTrophyForge(IInventory tileTrophyForge) {
    	super(tileTrophyForge);
	}

	public ItemStack getResult() {
        return getItem(5);
    }
	
    public void setResult(ItemStack stack) {
        setItem(5,stack);
    }

    @Override
    public TrophyForge getHolder() {
        return (TrophyForge) inventory.getOwner();
    }
}
