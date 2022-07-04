package org.bukkit.block;

import org.bukkit.Nameable;
import org.bukkit.inventory.TrophyForgeInventory;

/**
 * Represents a captured state of a furnace.
 */
public interface TrophyForge extends Container, Nameable {

    /**
     * Get burn time.
     *
     * @return Burn time
     */
    public short getForgeTime();

    /**
     * Set burn time.
     *
     * @param burnTime Burn time
     */
    public void setForgeTime(short burnTime);

    @Override
    public TrophyForgeInventory getInventory();

    @Override
    public TrophyForgeInventory getSnapshotInventory();
}
