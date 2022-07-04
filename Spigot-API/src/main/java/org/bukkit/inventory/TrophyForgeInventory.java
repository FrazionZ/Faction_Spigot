package org.bukkit.inventory;

import org.bukkit.block.TrophyForge;

/**
 * Interface to the inventory of a Furnace.
 */
public interface TrophyForgeInventory extends Inventory {

    /**
     * Get the current item in the result slot.
     *
     * @return The item
     */
    ItemStack getResult();

    /**
     * Set the current item in the result slot.
     *
     * @param stack The item
     */
    void setResult(ItemStack stack);


    TrophyForge getHolder();
}
