package org.bukkit.block;

import org.bukkit.Nameable;
import org.bukkit.inventory.AmelioratorInventory;

/**
 * Represents a captured state of a furnace.
 */
public interface Ameliorator extends Container, Nameable {

    /**
     * Get burn time.
     *
     * @return Burn time
     */
    public short getBurnTime();

    /**
     * Set burn time.
     *
     * @param burnTime Burn time
     */
    public void setBurnTime(short burnTime);

    /**
     * Get cook time.
     *
     * @return Cook time
     */
    public short getCookTime();

    /**
     * Set cook time.
     *
     * @param cookTime Cook time
     */
    public void setCookTime(short cookTime);

    @Override
    public AmelioratorInventory getInventory();

    @Override
    public AmelioratorInventory getSnapshotInventory();
}
