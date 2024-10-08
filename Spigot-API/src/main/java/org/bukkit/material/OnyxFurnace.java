package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

/**
 * Represents a furnace.
 */
public class OnyxFurnace extends FurnaceAndDispenser {

    public OnyxFurnace() {
        super(Material.ONYX_FURNACE);
    }

    /**
     * Instantiate a furnace facing in a particular direction.
     *
     * @param direction the direction the furnace's "opening" is facing
     */
    public OnyxFurnace(BlockFace direction) {
        this();
        setFacingDirection(direction);
    }

    /**
     * @param type the raw type id
     * @deprecated Magic value
     */
    @Deprecated
    public OnyxFurnace(final int type) {
        super(type);
    }

    public OnyxFurnace(final Material type) {
        super(type);
    }

    /**
     * @param type the raw type id
     * @param data the raw data value
     * @deprecated Magic value
     */
    @Deprecated
    public OnyxFurnace(final int type, final byte data) {
        super(type, data);
    }

    /**
     * @param type the type
     * @param data the raw data value
     * @deprecated Magic value
     */
    @Deprecated
    public OnyxFurnace(final Material type, final byte data) {
        super(type, data);
    }

    @Override
    public OnyxFurnace clone() {
        return (OnyxFurnace) super.clone();
    }
}
